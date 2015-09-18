package cn.haut.elec.service;

import java.util.List;
import java.util.Set;

import org.jbpm.api.task.Task;

import cn.haut.elec.domain.ElecCommonMsg;
import cn.haut.elec.domain.ElecPopedom;
import cn.haut.elec.domain.ElecRole;
import cn.haut.elec.domain.ElecRolePopedom;
import cn.haut.elec.domain.ElecUser;


public interface IElecMenuService {
	
	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecMenuServiceImpl";

	public ElecUser logon(String name, String password);

	public ElecCommonMsg findCommonMsg();

	public ElecUser findUserById(String userID);

	public Set<ElecRole> findRolelistByUserId(String userID);

	public List<ElecRolePopedom> findRolePopedomlistByRoleID(String roleID);

	public ElecPopedom findPopedomByMidAndPid(String mid, String pid);

	public List<ElecPopedom> findPopedomByUser(ElecUser logonUser);

	public List<ElecPopedom> findAllPopedom();

	public List<Task> findMyTaskList();

	
	
	

	
	
	
	
	
}
