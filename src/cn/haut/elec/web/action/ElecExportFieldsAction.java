package cn.haut.elec.web.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecExportFields;
import cn.haut.elec.service.IElecExportFieldsService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.utils.StringToListUtils;

@SuppressWarnings("serial")
@Controller("exportFieldsAction")
@Scope(value = "prototype")
public class ElecExportFieldsAction extends BaseAction<ElecExportFields> {

	private ElecExportFields exportFields = this.getModel();

	@Resource(name = IElecExportFieldsService.SERVICE_NAME)
	private IElecExportFieldsService elecExportFieldsService;

	/**
	 * @Name: setExportExcel
	 * @Description: 弹出一个显示导出设置的页面 并且动态的该表 导出设置的内容
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-09（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/system/exportExcel.jsp
	 */
	@AnnotationLimit(mid = "db", pid = "da")
	public String setExportExcel() {
		String belongTo = exportFields.getBelongTo();
		ElecExportFields exportFields = elecExportFieldsService
				.findExportFieldsByID(belongTo);
		/**
		 * 2：使用2个Map集合，分别存放导出字段的英文名和中文名，未导出字段的英文名和中文名 map集合的key：英文字段名称
		 * map集合的value：中文字段名称
		 */
		if (exportFields == null) {
			this.addFieldError("", "你查找的数据不存在");
			return "input";
		}
		Map<String, String> map = new LinkedHashMap<String, String>();// 导出设置
		Map<String, String> nomap = new LinkedHashMap<String, String>();// 未导出设置

		List<String> eList = StringToListUtils.stringToList(
				exportFields.getExpFieldName(), "#");
		List<String> zList = StringToListUtils.stringToList(
				exportFields.getExpNameList(), "#");
		List<String> noEList = StringToListUtils.stringToList(
				exportFields.getNoExpFieldName(), "#");
		List<String> noZList = StringToListUtils.stringToList(
				exportFields.getNoExpNameList(), "#");
		/**
		 * 未导出的中文字段和未导出的英文字段，数据要一致，而且长度要一一对应 导出的中文字段和导出的英文字段，数据要一致，而且长度要一一对应
		 */
		if (eList != null && eList.size() > 0) {
			for (int i = 0; i < eList.size(); i++) {
				map.put(eList.get(i), zList.get(i));
			}
		}
		if (noEList != null && noEList.size() > 0) {
			for (int i = 0; i < noEList.size(); i++) {
				nomap.put(noEList.get(i), noZList.get(i));
			}
		}
		// 3：将2个Map集合存放request对象中
		request.setAttribute("map", map);
		request.setAttribute("nomap", nomap);
		return "setExportExcel";
	}

	@AnnotationLimit(mid = "dc", pid = "da")
	public String saveSetExportExcel() {
		elecExportFieldsService.saveSetExportExcel(exportFields);
		return "close";
	}

}
