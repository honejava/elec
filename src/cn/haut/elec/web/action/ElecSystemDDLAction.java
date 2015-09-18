package cn.haut.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecSystemDDLService;
import cn.haut.elec.utils.AnnotationLimit;


@SuppressWarnings("serial")
@Controller("elecSystemDDLAction")
@Scope(value = "prototype")

public class ElecSystemDDLAction extends BaseAction<ElecSystemDDL> {


	private ElecSystemDDL elecSystemDDL = this.getModel();

	@Resource(name = IElecSystemDDLService.SERVICE_NAME)
	private IElecSystemDDLService elecSystemDDLService;
	/**  
	* @Name: home
	* @Description: 查询到数据字典里面的 关键字的不重复的数据   
	* @Author: 甘亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-09（创建日期）
	* @Parameters: 无
	* @Return: String：跳转到/WEB-INF/pages/system/dictionaryIndex.jsp
	*/
	@AnnotationLimit(mid = "aq", pid = "am")
	public String home() {
		List<ElecSystemDDL> systemddllist=elecSystemDDLService.findDistinctKeyWordList();
		request.setAttribute("list", systemddllist);
		return "home";
	}
	/**  
	* @Name: edit
	* @Description: 使用ajax来异步实现  进入到编辑页面 在编辑页面要显示出有关键字的数据
	* @Author: 甘亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-09（创建日期）
	* @Parameters: 无
	* @Return: String：请求转发/WEB-INF/pages/system/dictionaryEdit.jsp  到   
	*/
	@AnnotationLimit(mid = "eb", pid = "ea")
	public String edit(){
		String keyword=elecSystemDDL.getKeyword();
		System.out.print("keyword"+elecSystemDDL.getKeyword());
		List<ElecSystemDDL> list=elecSystemDDLService.findSystemDDLByKeywordlist(keyword);
		request.setAttribute("list", list);
		//request.getRequestDispatcher("/WEB-INF/pages/system/dictionaryEdit.jsp").forward(request, response);
		return "edit";
	}
	/**  
	* @Name: save
	* @Description: 保存从页面获取到的数据  分为新增加的和添加的两种增加方式
	* @Author: 甘亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-10（创建日期）
	* @Parameters: 无
	* @Return: String：请求转发/WEB-INF/pages/system/dictionaryEdit.jsp  到   
	*/
	@AnnotationLimit(mid = "ec", pid = "ea")
	public String save(){
		elecSystemDDLService.saveSystemDDL(elecSystemDDL);
		return home();
	}
	
}
