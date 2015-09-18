package cn.haut.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecMenuDao;
import cn.haut.elec.web.form.MenuForm;

@Repository(IElecMenuDao.SERVICE_NAME)
public class ElecMenuDaoImpl extends CommonDaoImpl<MenuForm> implements
		IElecMenuDao {

}
