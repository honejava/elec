package cn.haut.elec.dao;

import cn.haut.elec.domain.ElecText;

public interface ElecTextDao {

	public String SERVICE_NAME = "cn.itcast.dao.impl.ElecTextDaoImpl";

	public void save(ElecText text);
	

}
