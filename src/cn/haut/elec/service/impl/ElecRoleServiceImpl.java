package cn.haut.elec.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.dao.IElecPopedomDao;
import cn.haut.elec.dao.IElecRoleDao;
import cn.haut.elec.dao.IElecRolePopedomDao;
import cn.haut.elec.dao.IElecUserDao;
import cn.haut.elec.domain.ElecPopedom;
import cn.haut.elec.domain.ElecRole;
import cn.haut.elec.domain.ElecRolePopedom;
import cn.haut.elec.domain.ElecUser;
import cn.haut.elec.service.IElecRoleService;
import cn.haut.elec.utils.StringToArrayUtil;
import cn.haut.elec.web.form.User_Role_Form;

/**
 * 相当于spring容器中定义： <bean id="cn.haut.elec.service.impl.ElecTextServiceImpl"
 * class="cn.haut.elec.service.impl.ElecTextServiceImpl"/>
 */
@Service(IElecRoleService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecRoleServiceImpl implements IElecRoleService {

	@Resource(name = IElecRoleDao.SERVICE_NAME)
	private IElecRoleDao elecRoleDao;

	@Resource(name = IElecUserDao.SERVICE_NAME)
	private IElecUserDao elecUserDao;

	@Resource(name = IElecPopedomDao.SERVICE_NAME)
	private IElecPopedomDao elecPopedomDao;

	@Resource(name = IElecRolePopedomDao.SERVICE_NAME)
	private IElecRolePopedomDao elecRolePopedomDao;

	/**
	 * @Name: findAllRole
	 * @Description: 找到所有的角色类型
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-14（创建日期）
	 * @Parameters: 无
	 * @Return: List<ElecRole> list;
	 */
	public List<ElecRole> findAllRole() {
		return elecRoleDao.findCollectionByConditionNoPage(null, null, null);
	}

	/**
	 * @Name: findAllUser
	 * @Description: 查询所有的用户信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-14（创建日期）
	 * @Parameters: ElecRole elecRole
	 * @Return: null
	 */
	public List<ElecUser> findAllUser() {
		List<ElecUser> list = elecUserDao
				.findCollectionByConditionNoPageWithCach(null, null, null);
		for (ElecUser elecUser : list) {
			Hibernate.initialize(elecUser.getElecRoles());
		}

		return list;
	}

	/**
	 * @Name: save
	 * @Description: 保存用户表和角色表的联系 也就是维护 表elec_user_role 同时也保存权限 和 角色 权限 用户
	 *               三者之间的关系
	 * 
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-14（创建日期）
	 * @Parameters: ElecRole elecRole
	 * @Return: null
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(User_Role_Form user_Role_Form) {
		// 删除要修改的角色原来的角色权限的关系
		this.deletePopedomByUserId(user_Role_Form.getRoleID());
		// 保存用户表和角色表的联系
		this.saveUserAndRole(user_Role_Form);
		// 同时也保存权限 和 角色 权限 用户 三者之间的关系
		this.saveUserRolePopedom(user_Role_Form);
	}

	private void deletePopedomByUserId(String roleID) {
		elecRolePopedomDao.deletePopedomByRoleId(roleID);
	}

	// Duplicate entry '1-ao-am' for key 1 因为设置的是联合主键 如果添加重复 那么会
	private void saveUserRolePopedom(User_Role_Form user_Role_Form) {
		String[] popedomMidArray = StringToArrayUtil.StringToArray(
				user_Role_Form.getSelectoper(), ", ");
		// 根据权限表的mid来查询权限的数据 来获取到pid的数据 然后将 mid pid roleID保存到数据库中
		for (int i = 0; i < popedomMidArray.length; i++) {
			ElecPopedom popedom = elecPopedomDao
					.finPopedomByMid(popedomMidArray[i]);
			String pid = popedom.getPid();
			ElecRolePopedom rolePopedom = new ElecRolePopedom();
			rolePopedom.setRoleID(user_Role_Form.getRoleID());
			rolePopedom.setMid(popedomMidArray[i]);
			rolePopedom.setPid(pid);
			// 在这里要判断是保存还是更新【查询数据库 如果数据库中 存在这条记录 那么就不新增加了 如果不存在 那个就添加】
			Boolean flag = elecRolePopedomDao.isExist(rolePopedom);
			if (!flag) {
				elecRolePopedomDao.save(rolePopedom);
			}

		}

	}

	private void saveUserAndRole(User_Role_Form user_Role_Form) {
		// 获取到RoleID userID是 获取到userID集合组成的一个字符串
		String userID = user_Role_Form.getUserID();
		String roleID = user_Role_Form.getRoleID();
		// 多对多关系 根据映射文件设置的reverse属性来判断哪方面维护双方之间的关系【role来维护双方之间的关系】
		ElecRole elecRole = elecRoleDao.findObjectByID(roleID);
		Set<ElecUser> set = new LinkedHashSet<ElecUser>();
		String[] userIdArray = StringToArrayUtil.StringToArray(userID, ", ");
		for (int i = 0; i < userIdArray.length; i++) {
			ElecUser user = elecUserDao.findObjectByID(userIdArray[i]);
			set.add(user);
		}
		elecRole.setElecUsers(set);
	}

	// 根据当前角色的id来查询当前角色下的权限
	public List<ElecPopedom> findPopedomByRoleId(String roleID) {
		String condition = " and o.roleID=? ";
		Object[] params = { roleID };
		List<ElecPopedom> popedomlist = new ArrayList<ElecPopedom>();
		List<ElecRolePopedom> rolePopedomlist = elecRolePopedomDao
				.findCollectionByConditionNoPage(condition, params, null);
		for (ElecRolePopedom elecRolePopedom : rolePopedomlist) {
			String mid = elecRolePopedom.getMid();
			String pid = elecRolePopedom.getPid();
			// 根据mid pid 来获取一个 权限的名称
			ElecPopedom elecPopedom = this.findPopedomByMidAndPid(mid, pid);
			popedomlist.add(elecPopedom);
		}
		return popedomlist;
	}

	public ElecPopedom findPopedomByMidAndPid(String mid, String pid) {
		String condition = " and MID=?  and pid=?";
		Object[] params = { mid, pid };
		List<ElecPopedom> list = elecPopedomDao
				.findCollectionByConditionNoPage(condition, params, null);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public List<ElecPopedom> findAllPopedom() {
		return elecPopedomDao.findCollectionByConditionNoPageWithCach(null,
				null, null);
	}

	public boolean findRolePopedomByID(String roleID, String mid, String pid) {
		// 组织查询条件
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		// 角色ID
		if (StringUtils.isNotBlank(roleID)) {
			condition += " and o.roleID = ?";
			paramsList.add(roleID);
		}
		// 子权限名称
		if (StringUtils.isNotBlank(mid)) {
			condition += " and o.mid = ?";
			paramsList.add(mid);
		}
		// 父权限名称
		if (StringUtils.isNotBlank(pid)) {
			condition += " and o.pid = ?";
			paramsList.add(pid);
		}
		Object[] params = paramsList.toArray();
		// 查询对应的角色权限信息
		List<ElecRolePopedom> list = elecRolePopedomDao
				.findCollectionByConditionNoPage(condition, params, null);
		boolean flag = false;
		if (list != null && list.size() > 0) {
			flag = true;
		}
		return flag;

	}

}
