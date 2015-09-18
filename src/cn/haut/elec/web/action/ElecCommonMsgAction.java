package cn.haut.elec.web.action;

import java.io.PrintWriter;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecCommonMsg;
import cn.haut.elec.service.IElecCommonMsgService;
import cn.haut.elec.utils.AnnotationLimit;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller("elecCommonMsgAction")
@Scope(value = "prototype")
public class ElecCommonMsgAction extends BaseAction<ElecCommonMsg> {

	private ElecCommonMsg elecCommonMsg = this.getModel();

	@Resource(name = IElecCommonMsgService.SERVICE_NAME)
	private IElecCommonMsgService iElecCommonMsgService;
	/**  
	* @Name: home
	* @Description: 进入到运行监控的页面  并且获取到所有的数据库的数据  在页面进行显示 
	* @Author: 甘亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-09（创建日期）
	* @Parameters: 无
	* @Return: String：跳转到/WEB-INF/pages/system/actingIndex.jsp
	*/
	@AnnotationLimit(mid = "ap", pid = "am")
	public String home() {
		ElecCommonMsg commonMsg=iElecCommonMsgService.findCommonMsg();
		//将获取到的数据压入栈顶
		ActionContext.getContext().getValueStack().push(commonMsg);
		return "home";
	}
	/**  
	* @Name: save
	* @Description: 保存监控的信息
	* @Author: 甘亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-09（创建日期）
	* @Parameters: 无
	* @Return: String：重定向到elecCommonMsgAction.do 
	*/
	/*public String save(){
		iElecCommonMsgService.save(elecCommonMsg);
		return "save";
	}*/
	/**  
	* @Name: save
	* @Description: 保存运行监控数据
	* @Author: 刘洋（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-09 （创建日期）
	* @Parameters: 无
	* @Return: String：重定向到system/elecCommonMsgAction_home.do
	*/
	@AnnotationLimit(mid = "cb", pid = "ca")
	public String save(){
		
		//模拟：循环遍历150条数据，观察百分比的变化情况
		for(int i=1;i<=150;i++){
			iElecCommonMsgService.save(elecCommonMsg);
			request.getSession().setAttribute("percent", (double)i/150*100);//存放计算的百分比
		}
		//线程结束时，清空当前session
		request.getSession().removeAttribute("percent");
		return "save";
	}
	/**  
	* @Name: progressBar
	* @Description: 使用带百分比的进度条【ajax技术实现】
	* @Author: 甘亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-09（创建日期）
	* @Parameters: 无
	* @Return: String：重定向到elecCommonMsgAction.do 
	*/
	@AnnotationLimit(mid = "cc", pid = "ca")
	public String progressBar() throws Exception{
		//从session中获取操作方法中计算的百分比
		Double percent = (Double) request.getSession().getAttribute("percent");
		String res = "";
		//此时说明操作的业务方法仍然继续在执行
		if(percent!=null){
			//计算的小数，四舍五入取整
			int percentInt = (int) Math.rint(percent); 
			res = "<percent>" + percentInt + "</percent>";
		}
		//此时说明操作的业务方法已经执行完毕，session中的值已经被清空
		else{
			//存放百分比
			res = "<percent>" + 100 + "</percent>";
		}
		//定义ajax的返回结果是XML的形式
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		//存放结果数据，例如：<response><percent>88</percent></response>
		out.println("<response>");
		out.println(res); 
		out.println("</response>");
		out.close();
		return null;
		//rpfv-18bn-qwq6
	}
	
}
