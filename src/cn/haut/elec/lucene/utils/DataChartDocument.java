package cn.haut.elec.lucene.utils;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.util.NumericUtils;

import cn.haut.elec.domain.ElecDataChart;

public class DataChartDocument {

	/** 将ElecDataChart对象转换成Document对象 */
	public static Document DataChartToDocument(ElecDataChart dataChart) {
		Document document = new Document();
		String dataChartID = NumericUtils.intToPrefixCoded(dataChart
				.getDataChartID());
		// 主键ID
		document.add(new Field("dataChartID", dataChartID, Store.YES,
				Index.NOT_ANALYZED));
		// 文件名
		document.add(new Field("dataChartName", dataChart.getDataChartName(),
				Store.YES, Index.ANALYZED));
		// 文件描述
		document.add(new Field("comment", dataChart.getComment(), Store.YES,
				Index.ANALYZED));
		// 所属单位
		document.add(new Field("jctID", dataChart.getJctID(), Store.YES,
				Index.NOT_ANALYZED));
		// 图纸类别
		document.add(new Field("belongTo", dataChart.getBelongTo(), Store.YES,
				Index.NOT_ANALYZED));
		return document;
	}

	/** 将Document对象转换成ElecFileUpload对象 */

	public static ElecDataChart documentToDataChart(Document document) {
		ElecDataChart dataChart = new ElecDataChart();
		Integer dataChartID = NumericUtils.prefixCodedToInt(document
				.get("dataChartID"));
		// 主键ID
		dataChart.setDataChartID(dataChartID);
		// 文件名
		dataChart.setDataChartName(document.get("dataChartName"));
		// 文件描述
		dataChart.setComment(document.get("comment"));
		// 所属单位
		dataChart.setJctID(document.get("jctID"));
		// 图纸类别
		dataChart.setBelongTo(document.get("belongTo"));

		return dataChart;
	}
}
