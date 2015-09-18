package cn.haut.elec.lucene.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.NumericUtils;
import org.apache.lucene.util.Version;

import cn.haut.elec.domain.ElecDataChart;

public class LuceneUtils {

	/** 向索引库中新增数据 */
	public void saveDataChart(ElecDataChart dataChart) {
		Document document = DataChartDocument.DataChartToDocument(dataChart);
		try {
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
					Version.LUCENE_36, Configuration.getAnalyzer());
			IndexWriter indexWriter = new IndexWriter(
					Configuration.getDirectory(), indexWriterConfig);
			indexWriter.addDocument(document);
			indexWriter.close();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/** 索引库中删除数据 */
	public void deleteDataChartByID(Integer dataChartID) {
		// 指定词条的最小单位，相当于id=1
		String id = NumericUtils.intToPrefixCoded(dataChartID);
		Term term = new Term("dataChartID", id);
		try {
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
					Version.LUCENE_36, Configuration.getAnalyzer());
			IndexWriter indexWriter = new IndexWriter(
					Configuration.getDirectory(), indexWriterConfig);
			indexWriter.deleteDocuments(term);
			indexWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** 使用搜索条件，从索引库中搜索出对应的结果 */
	@SuppressWarnings({ "deprecation" })
	public List<ElecDataChart> searchDataChartByCondition(String queryString,
			String jctID, String belongTo) {
		List<ElecDataChart> list = new ArrayList<ElecDataChart>();
		try {
			IndexSearcher indexSearcher = new IndexSearcher(
					IndexReader.open(Configuration.getDirectory()));
			// 指定查询条件在文件名称和文件描述、所属单位、图纸类别的字段上进行搜索
			QueryParser queryParser = new MultiFieldQueryParser(
					Version.LUCENE_36, new String[] { "dataChartName",
							"comment" }, Configuration.getAnalyzer());
			/** 使用lucene的多条件查询，即boolean查询，即必须满足3个条件 */
			BooleanQuery booleanQuery = new BooleanQuery();
			// 【按文件名称和描述搜素】搜素的条件
			if (StringUtils.isNotBlank(queryString)) {
				Query query1 = queryParser.parse(queryString);
				booleanQuery.add(query1, Occur.MUST);
			}
			// 【所属单位】搜素的条件
			if (StringUtils.isNotBlank(jctID) && !jctID.equals("0")) {
				Query query2 = new TermQuery(new Term("jctID", jctID));
				booleanQuery.add(query2, Occur.MUST);
			}
			// 【图纸类别】搜素的条件
			if (StringUtils.isNotBlank(belongTo) && !belongTo.equals("0")) {
				Query query3 = new TermQuery(new Term("belongTo", belongTo));
				booleanQuery.add(query3, Occur.MUST);
			}
			// 返回前100条数据
			TopDocs topDocs = indexSearcher.search(booleanQuery, 100);
			// 返回结果集
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			/** 设置高亮效果 begin */
			Formatter formatter = new SimpleHTMLFormatter("<font color='red'>",
					"</font>");
			Scorer scorer = new QueryScorer(booleanQuery);
			Highlighter highlighter = new Highlighter(formatter, scorer);
			// 摘要大小（设置大点，最好比文件名大，因为文件名最好不要截取）
			int fragmentSize = 50;
			Fragmenter fragmenter = new SimpleFragmenter(fragmentSize);
			highlighter.setTextFragmenter(fragmenter);
			/** 设置高亮效果 end */
			if (scoreDocs != null && scoreDocs.length > 0) {
				for (int i = 0; i < scoreDocs.length; i++) {
					ScoreDoc scoreDoc = scoreDocs[i];
					// 使用内部惟一编号，获取对应的数据，编号从0开始
					Document document = indexSearcher.doc(scoreDoc.doc);
					/** 获取高亮效果begin */
					/** 返回文件名的高亮效果 */
					String fileNameText = highlighter.getBestFragment(
							Configuration.getAnalyzer(), "dataChartName",
							document.get("dataChartName"));
					// 没有高亮的效果
					if (fileNameText == null) {
						fileNameText = document.get("dataChartName");
						if (fileNameText != null
								&& fileNameText.length() > fragmentSize) {
							fileNameText = fileNameText.substring(0,
									fragmentSize);
						}
					}
					document.getField("dataChartName").setValue(fileNameText);
					/** 返回文件描述的高亮效果 */
					String commentText = highlighter.getBestFragment(
							Configuration.getAnalyzer(), "comment",
							document.get("comment"));
					// 没有高亮的效果
					if (commentText == null) {
						commentText = document.get("comment");
						if (commentText != null
								&& commentText.length() > fragmentSize) {
							commentText = commentText
									.substring(0, fragmentSize);
						}
					}
					document.getField("comment").setValue(commentText);
					/** 获取高亮效果end */
					// 将Document转换成ElecFileUpload
					ElecDataChart dataChart = DataChartDocument
							.documentToDataChart(document);
					list.add(dataChart);
					indexSearcher.close();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}

		return list;
	}
}
