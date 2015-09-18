package cn.haut.elec.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.jbpm.api.task.Task;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecCommonMsg;
import cn.haut.elec.domain.ElecPopedom;
import cn.haut.elec.domain.ElecRole;
import cn.haut.elec.domain.ElecUser;
import cn.haut.elec.service.IElecMenuService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.utils.ValueStackUtils;
import cn.haut.elec.web.form.MenuForm;

@SuppressWarnings("serial")
@Controller("elecMenuAction")
@Scope(value = "prototype")
public class ElecMenuAction extends BaseAction<MenuForm> {

	private MenuForm menuForm = this.getModel();

	@Resource(name = IElecMenuService.SERVICE_NAME)
	private IElecMenuService elecmenuService;

	/**
	 * @Name: menuHome
	 * @Description: 跳转到主页面 在这里要检测登陆的用户和密码是否匹配 根据用户user ID来查询到roleID然后在获取到权限等
	 *               然后加载主页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-16（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/menu/home.jsp
	 */
	@AnnotationLimit(mid = "bb", pid = "ba")
	public String menuHome() {
		return "menuHome";
	}

	// 验证登录信息是否匹配数据库
	public void validateMenuHome() {
		// 第一步 验证验证码
		String CHECK_NUMBER_KEY = (String) request.getSession().getAttribute(
				"CHECK_NUMBER_KEY");
		// 取完值后清空session
		request.getSession().removeAttribute("CHECK_NUMBER_KEY");

		if (CHECK_NUMBER_KEY == null || menuForm.getCheckNumber() == null
				|| !CHECK_NUMBER_KEY.equals(menuForm.getCheckNumber())) {
			this.addFieldError("", "验证码输入错误");
			return;
		}
		// 第二部 验证用户名和密码是否匹配 查询数据库
		ElecUser logonUser = elecmenuService.logon(menuForm.getName(),
				menuForm.getPassword());
		if (logonUser == null) {
			this.addFieldError("", "用户名和密码不匹配");
			return;
		}
		// 将用户信息保存到session中
		request.getSession().setAttribute("logonUser", logonUser);
		// 第三不 判断是否选中【记住我】的选项 如果选中 下次可以直接登陆 也就是自动登陆
		Cookie userNameCookie = new Cookie("logonName",
				logonUser.getLogonName());
		Cookie passwordCookie = new Cookie("logonPwd", logonUser.getLogonPwd());
		userNameCookie.setPath(request.getContextPath() + "/");
		passwordCookie.setPath(request.getContextPath() + "/");
		if ("on".equals(menuForm.getRemeberMe())) {// on代表着选项被选中
			userNameCookie.setMaxAge(60 * 60 * 12);
			passwordCookie.setMaxAge(60 * 60 * 12);
		} else {
			userNameCookie.setMaxAge(0);
			passwordCookie.setMaxAge(0);
		}
		response.addCookie(userNameCookie);
		response.addCookie(passwordCookie);

		// 最后 将登录用户的权限信息 保存到session中
		List<ElecPopedom> popedomlist = elecmenuService
				.findPopedomByUser(logonUser);
		request.getSession().setAttribute("popedomlist", popedomlist);
		// 获取到所有的权限 保存到session中
		List<ElecPopedom> allpopedomlist = elecmenuService.findAllPopedom();
		request.getSession().setAttribute("allpopedomlist", allpopedomlist);
		// 获取到当前用户的所有的角色
		Set<ElecRole> roles = elecmenuService.findRolelistByUserId(logonUser
				.getUserID());
		request.getSession().setAttribute("globle_role", roles);
	}

	// 重定向主页面
	public String main() {
		// session过期问题的解决
		ElecUser logonUser = (ElecUser) request.getSession().getAttribute(
				"logonUser");
		System.out.println("logonUser" + logonUser);
		request.getSession().setAttribute("logonUser", logonUser);
		return "main";
	}

	/**
	 * @Name: title
	 * @Description: 加载title
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-09（创建日期）
	 * @Parameters: 无
	 * @Return: String：加载/WEB-INF/pages/menu/title.jsp页面
	 */
	@AnnotationLimit(mid = "bc", pid = "ba")
	public String title() {
		return "title";
	}

	/**
	 * @Name: left
	 * @Description: 加载菜单页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-09（创建日期）
	 * @Parameters: 无
	 * @Return: String：加载/WEB-INF/pages/menu/left.jsp
	 */
	@AnnotationLimit(mid = "bd", pid = "ba")
	public String left() {
		return "left";
	}

	/**
	 * @Name: change
	 * @Description: 修改页面的半屏或者全屏
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-09（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/menu/change.jsp
	 */
	@AnnotationLimit(mid = "bf", pid = "ba")
	public String change() {
		return "change";
	}

	/**
	 * @Name: loading
	 * @Description: 加载主页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-09（创建日期）
	 * @Parameters: 无
	 * @Return: String：加载/WEB-INF/pages/menu/loading.jsp
	 */

	/** 首页，主页面 */
	@AnnotationLimit(mid = "bg", pid = "ba")
	public String loading() {
		// 获取当前审核人能够查询的申请信息
		List<Task> list = elecmenuService.findMyTaskList();
		request.setAttribute("taskSize", list.size());
		return "loading";
	}

	/**
	 * @Name: logout
	 * @Description: 重新登录【要删除session的登陆信息】
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-09（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/menu/home.jsp
	 */
	@AnnotationLimit(mid = "bj", pid = "ba")
	public String logout() {
		request.getSession().invalidate();
		return "logout";
	}

	public void validateLogout() {
		request.getSession().setAttribute("index", "true");
	}

	/**
	 * @Name: alermStation
	 * @Description: 跳转到主页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-09（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/menu/home.jsp
	 */
	@AnnotationLimit(mid = "bh", pid = "ba")
	public String alermStation() {
		ElecCommonMsg commonMsg = elecmenuService.findCommonMsg();
		ValueStackUtils.setValueStatck(commonMsg);
		return "alermStation";
	}

	/**
	 * @Name: alermDevice
	 * @Description: 获取到 CommonMsg中的CommonMsg
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-09（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/menu/home.jsp
	 */
	@AnnotationLimit(mid = "bi", pid = "ba")
	public String alermDevice() {
		ElecCommonMsg commonMsg = elecmenuService.findCommonMsg();
		ValueStackUtils.setValueStatck(commonMsg);
		return "alermDevice";
	}

	/**
	 * @Name: showMenu
	 * @Description: 加载左侧的树心的功能结构
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-17（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/menu/home.jsp
	 */
	@SuppressWarnings("unchecked")
	@AnnotationLimit(mid = "be", pid = "ba")
	public String showMenu() {

		List<ElecPopedom> popedomlist = (List<ElecPopedom>) request
				.getSession().getAttribute("popedomlist");

		List<ElecPopedom> list = new ArrayList<ElecPopedom>();
		// 当使用权限管理的时候 有的权限可能也显示在左侧
		// 我希望用户拥有这个权限 但是 不显示出来 只显示 6 大功能模块
		// 可以咋这里修改 权限集合 而不是再在数据库里查询 根据 mid特性 第一位为a
		for (int i = 0; i < popedomlist.size(); i++) {
			ElecPopedom popedom = popedomlist.get(i);
			String mid = popedom.getMid();
			if (mid.startsWith("a")) {
				list.add(popedom);
			}
		}

		ValueStackUtils.setValueStatck(list);
		return "showMenu";
	}
}
