package cn.haut.elec.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecText;
import cn.haut.elec.service.ElecTextService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.web.action.BaseAction;

@Controller(value = "elecTextAction")
public class ElecTextAction extends BaseAction<ElecText> {

	/**
	 * 测试项目环境的搭建
	 */
	private static final long serialVersionUID = 1L;

	// 注入service
	@Resource(name = ElecTextService.SERVICE_NAME)
	private ElecTextService service;

	/**
	 * @Name: index
	 * @Description: 进入到项目测试的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-20（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/text/textAdd.jsp
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String index() {
		return "index";
	}

	/**
	 * @Name: save
	 * @Description: 进入到保存测试text
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-20（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/system/text.jsp
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String save() {
		ElecText text = this.getModel();
		service.save(text);
		return "save";
	}

}
