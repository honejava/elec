package cn.haut.elec.web.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ExceptionInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() {

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		/**
		 * 拦截器实现异常处理 拦截器和过滤器一样 当执行了 invocation.invoke() 会执行 访问的action访问的方法
		 * 然后再返回到拦截器中 如果出现异常 那么就 在拦截器处理 然后转到特定的异常处理页面
		 * */
		try {
			/**
			 * 验证用户进入的方法的权限 验证用户是否有进入到这个方法的权限
			 */
			String result = "";
			// boolean flag = this.isCheckLimit();
			result = invocation.invoke();
			return result;
		} catch (Exception e) {
			String errorMsg = "出现错误信息，请查看日志！";
			if (e instanceof RuntimeException) {
				// 未知的运行时异常
				RuntimeException re = (RuntimeException) e;
				// re.printStackTrace();
				errorMsg = re.getMessage();
			}
			ServletActionContext.getRequest()
					.setAttribute("errorMsg", errorMsg);
			Log log = LogFactory.getLog(invocation.getAction().getClass());
			log.error(errorMsg, e);
			return "errorMsg";
		}
	}

	public void destroy() {

	}

}
