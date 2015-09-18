package cn.haut.elec.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.dao.IElecCommonMsgDao;
import cn.haut.elec.dao.IElecMenuDao;
import cn.haut.elec.dao.IElecPopedomDao;
import cn.haut.elec.dao.IElecRoleDao;
import cn.haut.elec.dao.IElecRolePopedomDao;
import cn.haut.elec.dao.IElecUserDao;
import cn.haut.elec.domain.ElecCommonMsg;
import cn.haut.elec.domain.ElecPopedom;
import cn.haut.elec.domain.ElecRole;
import cn.haut.elec.domain.ElecRolePopedom;
import cn.haut.elec.domain.ElecUser;
import cn.haut.elec.service.IElecMenuService;
import cn.haut.elec.utils.MD5keyBean;

/**
 * 相当于spring容器中定义： <bean id="cn.haut.elec.service.impl.ElecTextServiceImpl"
 * class="cn.haut.elec.service.impl.ElecTextServiceImpl"/>
 */
@Service(IElecMenuService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecMenuServiceImpl implements IElecMenuService {

	@Resource(name = IElecMenuDao.SERVICE_NAME)
	private IElecMenuDao elecMenuDao;

	@Resource(name = IElecCommonMsgDao.SERVICE_NAME)
	private IElecCommonMsgDao elecCommonMsgDao;

	@Resource(name = IElecUserDao.SERVICE_NAME)
	private IElecUserDao elecUserDao;

	@Resource(name = IElecRoleDao.SERVICE_NAME)
	private IElecRoleDao elecRoleDao;

	@Resource(name = IElecRolePopedomDao.SERVICE_NAME)
	private IElecRolePopedomDao elecRolePopedomDao;

	@Resource(name = IElecPopedomDao.SERVICE_NAME)
	private IElecPopedomDao elecPopedomDao;
	// 注入流程引擎
	@Resource(name = "processEngine")
	private ProcessEngine processEngine;

	/**
	 * @Name: findCommonMsg
	 * @Description: 查询监控的信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-09（创建日期）
	 * @Parameters: 无
	 * @Return: ElecCommonMsg
	 */
	public ElecCommonMsg findCommonMsg() {
		ElecCommonMsg commonMsg = null;
		List<ElecCommonMsg> list = elecCommonMsgDao
				.findCollectionByConditionNoPage("", null, null);

		if (list != null && list.size() > 0) {
			commonMsg = list.get(0);
		}
		return commonMsg;
	}

	/**
	 * @Name: logon
	 * @Description: 查询监控的信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-15（创建日期）
	 * @Parameters: 无
	 * @Return: 验证是否登陆成功或者失败的状态码
	 */
	public ElecUser logon(String name, String password) {
		String condition = " and logonName=? and logonPwd=? ";
		// 要验证密码的话 必须想要 把 用户输入的密码进行MD5加密 然后在和数据库的密码比对
		// 在jsp页面回显了密码 如果是回显的密码那么不能 进行加密而是使用 会显的密码
		if (password.length() != 32) {// 用户不可能会输入32为密码【jsp输入字符控制】
			password = new MD5keyBean().getkeyBeanofStr(password);
		}
		Object[] params = { name, password };
		List<ElecUser> userlist = elecUserDao.findCollectionByConditionNoPage(
				condition, params, null);
		if (userlist != null && userlist.size() > 0) {// 查询用户存在的话 那么显示 登陆成功
			return userlist.get(0);
		}
		return null;
	}

	/**
	 * @Name: findUserById
	 * @Description: 根据用户的id来出啊讯用户信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-16（创建日期）
	 * @Parameters: 无
	 * @Return: 验证是否登陆成功或者失败的状态码
	 */
	public ElecUser findUserById(String userID) {
		return elecUserDao.findObjectByID(userID);
	}

	/**
	 * @Name: findRolelistByUserId
	 * @Description: 根据用户的id来出当前用户的全部角色
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-16（创建日期）
	 * @Parameters: 无
	 * @Return: 验证是否登陆成功或者失败的状态码
	 */
	public Set<ElecRole> findRolelistByUserId(String userID) {

		ElecUser user = elecUserDao.findRolelistByID(userID);
		return user.getElecRoles();
	}

	/**
	 * @Name: findRolePopedomlistByRoleID
	 * @Description: 查询监控的信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-16（创建日期）
	 * @Parameters: 无
	 * @Return: 权限的集合
	 */
	public List<ElecRolePopedom> findRolePopedomlistByRoleID(String roleID) {

		String condition = " and roleID=?";
		Object[] params = { roleID };

		return elecRolePopedomDao.findCollectionByConditionNoPage(condition,
				params, null);
	}

	/**
	 * @Name: findPopedomByMidAndPid
	 * @Description: 查询监控的信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-15（创建日期）
	 * @Parameters: 无
	 * @Return: 根据mid pid 来获得一个权限对象
	 */
	public ElecPopedom findPopedomByMidAndPid(String mid, String pid) {
		String condition = " and MID=?  and pid=?";
		Object[] params = { mid, pid };
		List<ElecPopedom> list = elecPopedomDao
				.findCollectionByConditionNoPage(condition, params, null);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @Name: findPopedomByUser
	 * @Description: 查询监控的信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-17（创建日期）
	 * @Parameters: 无
	 * @Return: 根据mid pid 来获得一个权限对象
	 */
	public List<ElecPopedom> findPopedomByUser(ElecUser logonUser) {
		// 加载这页面的数据 【左边的树状结构】
		// 首先创建一个 保存 权限的list【有序可重复的】
		List<ElecPopedom> popedomlist = new ArrayList<ElecPopedom>();
		// 第二步 根据用户的信息 查询到当前用户的所有的角色id【查询数据库】
		Set<ElecRole> roleIDset = this.findRolelistByUserId(logonUser
				.getUserID());
		if (roleIDset != null && roleIDset.size() > 0) {
			// 第三步 遍历roleIDset获取每一个roleID对应的一组 mid pid
			for (ElecRole elecRole : roleIDset) {
				String roleID = elecRole.getRoleID();//
				// 根据roleID来获取到 对应的一组 mid pid
				List<ElecRolePopedom> rolePopedomlist = this
						.findRolePopedomlistByRoleID(roleID);
				if (rolePopedomlist != null && rolePopedomlist.size() > 0) {
					for (ElecRolePopedom elecRolePopedom : rolePopedomlist) {
						String mid = elecRolePopedom.getMid();
						String pid = elecRolePopedom.getPid();
						// 根据mid pid 来获取一个 权限的名称
						ElecPopedom elecPopedom = this.findPopedomByMidAndPid(
								mid, pid);
						// 遍历元素的集合 如果不存在这条数据 就添加 如果存在 就不添加数据
						this.addNoRepeatPopedom(popedomlist, elecPopedom);
						// popedomlist.add(elecPopedom);
					}
				}
			}
		}
		// 在这里对elecpopedom进行处理 使得可以在主页面的【左边的菜单栏】
		// 去除重复的元素 【就算使用了 set集合 无序不可重复】 因为添加的是对象 对象不相等 所以 会出现大量的子一样的数据
		return popedomlist;
	}

	// 检测集合中是否已经存在这条数据 如果存在 就 不添加了
	private void addNoRepeatPopedom(List<ElecPopedom> popedomlist,
			ElecPopedom elecPopedom) {
		if (popedomlist != null && popedomlist.size() > 0) {
			for (ElecPopedom popedom : popedomlist) {
				if ((popedom.getMid().equals(elecPopedom.getMid()))
						&& (popedom.getPid().equals(elecPopedom.getPid()))) {
					return;
				}
			}
		}
		popedomlist.add(elecPopedom);
	}

	// 找到所有的权限
	public List<ElecPopedom> findAllPopedom() {
		return elecPopedomDao.findCollectionByConditionNoPage(null, null, null);
	}

	// 找到当前登录用户的人物数量
	public List<Task> findMyTaskList() {
		// 获取到当前登录用户的信息
		ElecUser logonUser = (ElecUser) ServletActionContext.getRequest()
				.getSession().getAttribute("logonUser");
		String logonName = logonUser.getLogonName();

		List<Task> list = processEngine.getTaskService().createTaskQuery()
				.assignee(logonName).list();
		return list;
	}

}
