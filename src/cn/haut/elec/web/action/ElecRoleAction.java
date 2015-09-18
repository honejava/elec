package cn.haut.elec.web.action;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecPopedom;
import cn.haut.elec.domain.ElecRole;
import cn.haut.elec.domain.ElecUser;
import cn.haut.elec.service.IElecRoleService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.web.form.User_Role_Form;

@SuppressWarnings("serial")
@Controller("elecRoleAction")
@Scope(value = "prototype")
public class ElecRoleAction extends BaseAction<User_Role_Form> {

	private User_Role_Form user_Role_Form = this.getModel();

	@Resource(name = IElecRoleService.SERVICE_NAME)
	private IElecRoleService elecRoleService;

	/**
	 * @Name: home
	 * @Description: 跳转到角色管理的主页 【在这里获取到角色所有的类型】
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-14（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/system/roleIndex.jsp
	 */
	@AnnotationLimit(mid = "ao", pid = "am")
	public String home() {
		List<ElecRole> list = elecRoleService.findAllRole();
		request.setAttribute("list", list);
		return "home";
	}

	/**
	 * @Name: edit
	 * @Description: 使用ajax异步
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-14（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/system/roleEdit.jsp
	 */
	@AnnotationLimit(mid = "gb", pid = "ga")
	public String edit() {
		// 获取到所有的用户信息 并且将当前角色 的那些用户勾选上
		List<ElecUser> userlist = elecRoleService.findAllUser();
		request.setAttribute("userlist", userlist);
		// 根据roleID来获取到当前角色下的用户
		String roleID = user_Role_Form.getRoleID();
		// 在用户表中设置一个flag变量 如果 当前用户包含这个角色 就将flag设置为true
		for (ElecUser elecUser : userlist) {
			Set<ElecRole> set = elecUser.getElecRoles();
			if (set != null && set.size() > 0) {
				for (ElecRole elecRole : set) {
					if (elecRole.getRoleID().equals(roleID)) {
						elecUser.setFlag(true);
						break;
					}
				}
			}
		}
		// 获取到所有的权限 从数据库中查询
		List<ElecPopedom> all = elecRoleService.findAllPopedom();
		// 获取到当前用户角色下的权限用户的所有的权限
		List<ElecPopedom> list = elecRoleService.findPopedomByRoleId(roleID);
		if (list != null && list.size() > 0) {
			for (ElecPopedom elecPopedom : all) {
				for (ElecPopedom popedom : list) {
					if (elecPopedom.getMid().equals(popedom.getMid())
							&& (elecPopedom.getPid().equals(popedom.getPid()))) {
						elecPopedom.setFlag(true);
						break;
					}
				}
			}
		}
		request.getSession().setAttribute("popedomlists", all);
		return "edit";
	}

	/**
	 * @Name: saveRole
	 * @Description: 保存用户和角色属性之间的关联 【一个用户对应一个角色】【每个角色拥有的权限不一样】
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-14（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/system/roleEdit.jsp
	 */
	@AnnotationLimit(mid = "gc", pid = "ga")
	public String save() {
		elecRoleService.save(user_Role_Form);
		// 在这里除了要维护用户表和角色表的关系 还要 维护权限表和角色权限表的关系

		return "save";
	}

}
