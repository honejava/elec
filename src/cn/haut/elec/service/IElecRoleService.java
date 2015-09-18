package cn.haut.elec.service;

import java.util.List;

import cn.haut.elec.domain.ElecPopedom;
import cn.haut.elec.domain.ElecRole;
import cn.haut.elec.domain.ElecUser;
import cn.haut.elec.web.form.User_Role_Form;


public interface IElecRoleService {
	
	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecRoleServiceImpl";

	public List<ElecRole> findAllRole();

	public void save(User_Role_Form user_Role_Form);

	public List<ElecUser> findAllUser();

	public List<ElecPopedom> findPopedomByRoleId(String roleID);

	public List<ElecPopedom> findAllPopedom();

	public boolean findRolePopedomByID(String roleID, String mid, String pid);
	
	
	

	
}
