package cn.haut.elec.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.haut.elec.domain.ElecUser;
import cn.haut.elec.service.IElecUserService;

public class LogonFilter implements Filter {

	List<String> list = new ArrayList<String>();

	public void init(FilterConfig arg0) throws ServletException {
		// 添加那些jsp 和方法不需要 过滤器处理
		list.add("/index.jsp");
		list.add("/image.jsp");
		list.add("/system/elecMenuAction_menuHome.do");

		list.add("/error.jsp");
		list.add("/system/elecMenuAction_logout.do");

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// 类型转换
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// 获取转发servlet的路径
		String path = request.getServletPath();
		if (list.contains(path)) {// 如果访问的路径包含在list中 那么就放行
			this.ckeckRemember(path, request);// 检测记住我的功能
			chain.doFilter(request, response);// 放行
			return;
		}
		// 完成粗颗粒度的权限控制
		ElecUser logonUser = (ElecUser) request.getSession().getAttribute(
				"logonUser");
		if (logonUser != null) {
			/**
			 * 还有一种情况 如果当前登录用户发生了改变 比如异地登陆 修改了登陆信息 这是后应该提示用户重新登录 【可以使用主键来查询
			 * 如果查询不到 那么说明 登陆用户 已经被删除】
			 */
			boolean flag = this.checkLogonUserById(logonUser, request);
			if (flag) {// 返回为真说明 可以正常登录了
				// 如果用户登陆了 那么就放行
				chain.doFilter(request, response);// 放行
				return;
			}
		}
		// 如果为空 那么说明用户没有登录
		request.getSession().setAttribute("errorMsg", "你还没有登陆，请登录");

		// 重定向到登陆界面
		response.sendRedirect("../error.jsp");
	}

	public void destroy() {

	}  

	private void ckeckRemember(String path, HttpServletRequest request) {
		if (path.equals("/index.jsp")) {// 如果是首页 那么就有一个记住我的功能的实现
			String logonName = "";
			String logonPwd = "";
			String checked = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					String name = cookie.getName();
					if (name.equals("logonName")) {
						logonName = cookie.getValue();
						// 姓名存在中文乱码问题
						try {
							logonName = URLDecoder.decode(logonName, "utf-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						checked = "checked";
					}
					if (name.equals("logonPwd")) {
						logonPwd = cookie.getValue();
					}
				}
				request.setAttribute("name", logonName);
				request.setAttribute("password", logonPwd);
				request.setAttribute("checked", checked);
			}
		}

	}

	private boolean checkLogonUserById(ElecUser elecUser,
			HttpServletRequest request) {
		// 获取到elecUserService 在spring容器中
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		IElecUserService userService = (IElecUserService) applicationContext
				.getBean(IElecUserService.SERVICE_NAME);
		ElecUser user = userService.findUserByUserId(elecUser.getUserID());
		if (user == null) {// 如果这个用户不存在了 那么直接返回 false
			request.setAttribute("errorMsg", "该用户已经被删除，情重新登录");
			return false;
		}
		if (!this.checkEquals(elecUser, user)) {// 如果当前登录用户的个人信息发生了改变 那么提醒重新登录
			request.setAttribute("errorMsg", "该用户的个人信息已经发生了改变，情重新登录");
			return false;
		}
		// 异地登陆了 该怎么处理呢？？？？？？？？？？？？？？？？？？
		// 处理方法：在用户表中设置一个变量 每登陆一次 这个变量都增加1 在过滤器中查询这个变量
		// 如果不和登陆的时候获取到的变量一致 那么就提示用户被挤出
		//

		return true;
	}

	private boolean checkEquals(ElecUser elecUser, ElecUser user) {
		// 检测登陆用户修改的信息 如果修改的是密码,用户名那么提醒重新登录
		if (!elecUser.getUserName().equals(user.getUserName())) {
			return false;
		}
		if (!elecUser.getLogonPwd().equals(user.getLogonPwd())) {
			return false;
		}
		return true;
	}
}
