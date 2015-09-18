package cn.haut.elec.dao;

import java.util.List;

import cn.haut.elec.domain.ElecApplication;

public interface IElecProcessDefinitionDao extends ICommonDao<ElecApplication> {
	public static final String SERVICE_NAME = "cn.haut.elec.dao.impl.ElecProcessDefinitionDaoImpl";

	public List<String> findDistinctApplicationByKey();

	public List<ElecApplication> findMaxApplicationByTitle(String title);

	public void deleteAllApplicationByKey(ElecApplication elecApplication);

	public List<ElecApplication> findApplicationByKey(int id);

}
