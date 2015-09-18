package cn.haut.elec.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.dao.IElecBugDao;
import cn.haut.elec.dao.IElecDataChartDao;
import cn.haut.elec.dao.IElecExportFieldsDao;
import cn.haut.elec.dao.IElecStationDao;
import cn.haut.elec.dao.IElecSystemDDLDao;
import cn.haut.elec.domain.ElecDataChart;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.lucene.utils.LuceneUtils;
import cn.haut.elec.service.IElecDataChartService;
import cn.haut.elec.utils.PageInfo;

@Service(IElecDataChartService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecDataChartServiceImpl implements IElecDataChartService {

	// 注入业务层的数据
	@Resource(name = IElecBugDao.SERVICE_NAME)
	private IElecBugDao bugDao;
	// 注入数据字典的数据
	@Resource(name = IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;

	// 注入导出elecExportFieldsDao
	@Resource(name = IElecExportFieldsDao.SERVICE_NAME)
	private IElecExportFieldsDao elecExportFieldsDao;

	// 注入站点的dao
	@Resource(name = IElecStationDao.SERVICE_NAME)
	private IElecStationDao stationDao;

	// 注入站点的dao
	@Resource(name = IElecDataChartDao.SERVICE_NAME)
	private IElecDataChartDao dataChartDao;

	@Override
	public List<ElecSystemDDL> findSystemDDLListByKeyword(String keyword) {
		if (keyword.isEmpty()) {
			return elecSystemDDLDao.findCollectionByConditionNoPageWithCach(
					null, null, null);
		}
		String condition = " and o.keyword=?";
		Object[] params = { keyword };

		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.ddlCode", "asc");

		return elecSystemDDLDao.findCollectionByConditionNoPageWithCach(
				condition, params, orderby);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(ElecDataChart dataChart) {
		List<ElecDataChart> list = new ArrayList<ElecDataChart>();
		try {
			for (int i = 0; i < dataChart.getFiles().length; i++) {
				// 保存 文件到服务器
				String path = ServletActionContext.getServletContext()
						.getRealPath("/dataChart/");
				File destFile = new File(path, dataChart.getFilesFileName()[i]);
				FileUtils.copyFile(dataChart.getFiles()[i], destFile);

				ElecDataChart chart = new ElecDataChart();
				chart.setBelongTo(dataChart.getBelongTo());
				chart.setJctID(dataChart.getJctID());
				chart.setComment(dataChart.getComment());
				chart.setDataChartName(dataChart.getFilesFileName()[i]);
				list.add(chart);
			}
			dataChartDao.saveList(list);
			for (ElecDataChart elecDataChart : list) {
				// 保存到索引库
				LuceneUtils luceneUtils = new LuceneUtils();
				luceneUtils.saveDataChart(elecDataChart);
			}
		} catch (IOException e) {
			throw new RuntimeException("文件上传失败", e);
		}
	}

	public List<ElecDataChart> findDataChartByCondition(ElecDataChart dataChart) {

		String condition = "";
		List<Object> paramList = new ArrayList<Object>();
		if (StringUtils.isNotBlank(dataChart.getJctID())
				&& !dataChart.getJctID().equals("0")) {
			condition += " and o.jctID=?";
			paramList.add(dataChart.getJctID());
		}
		if (StringUtils.isNotBlank(dataChart.getBelongTo())
				&& !dataChart.getBelongTo().equals("0")) {
			condition += " and o.belongTo=?";
			paramList.add(dataChart.getBelongTo());
		}
		if (StringUtils.isNotBlank(dataChart.getComment())) {
			condition += " and o.comment=?";
			paramList.add(dataChart.getComment());
		}
		Object[] params = paramList.toArray();

		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put(" o.dataChartID", "asc");
		PageInfo pageInfo = new PageInfo(ServletActionContext.getRequest());
		List<ElecDataChart> list = dataChartDao
				.findCollectionByConditionWithPage(condition, params, orderby,
						pageInfo);
		ServletActionContext.getRequest().setAttribute("page",
				pageInfo.getPageBean());
		this.convertSystemDDL(list);
		return list;
	}

	private void convertSystemDDL(List<ElecDataChart> list) {
		for (ElecDataChart elecDataChart : list) {
			// 所属单位
			elecDataChart.setJctID(StringUtils.isNotBlank(elecDataChart
					.getJctID()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("所属单位",
							elecDataChart.getJctID()) : "");
			// 图纸类别
			elecDataChart.setBelongTo(StringUtils.isNotBlank(elecDataChart
					.getBelongTo()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("图纸类别",
							elecDataChart.getBelongTo()) : "");
		}
	}

	public ElecDataChart findDataChartById(Integer dataChartID) {
		return dataChartDao.findObjectByID(dataChartID);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(ElecDataChart model) {
		// 删除上传的附件
		ElecDataChart data = dataChartDao
				.findObjectByID(model.getDataChartID());
		String path = ServletActionContext.getServletContext().getRealPath(
				"/dataChart/" + data.getDataChartName());
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		/** 删除索引库 */
		LuceneUtils luceneUtils = new LuceneUtils();
		luceneUtils.deleteDataChartByID(model.getDataChartID());

		dataChartDao.deleteObjectByIDs(model.getDataChartID());
	}

	// 使用lucene来检索
	public List<ElecDataChart> findDataChartList(ElecDataChart dataChart) {

		/*************************** 使用lucene全文检索 **********************************************************/
		// 封装结果集
		List<ElecDataChart> fileList = new ArrayList<ElecDataChart>();
		// 搜索条件
		String queryString = dataChart.getQueryString();
		LuceneUtils luceneUtils = new LuceneUtils();
		// 查询索引库，传递文件名和文件描述的搜索条件、所属单位、图纸类别
		List<ElecDataChart> list = luceneUtils.searchDataChartByCondition(
				queryString, dataChart.getJctID(), dataChart.getBelongTo());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ElecDataChart data = list.get(i);
				// 获取主键ID，主键ID检索性能不会出现问题
				Integer dataChartID = data.getDataChartID();
				String condition = " and a.dataChartID=?";
				List<Object> paramsList = new ArrayList<Object>();
				paramsList.add(dataChartID);
				Object[] params = paramsList.toArray();
				List<ElecDataChart> dataChartList = dataChartDao
						.findElecDataChartByCondition(condition, params);
				if (dataChartList != null && dataChartList.size() > 0) {
					ElecDataChart upload = dataChartList.get(0);
					// 设置文字高亮的值，高亮的字段包括文件名和文件描述
					upload.setDataChartName(data.getDataChartName());
					upload.setComment(data.getComment());
					fileList.add(upload);
				}
			}
		}
		return fileList;
	}
}
