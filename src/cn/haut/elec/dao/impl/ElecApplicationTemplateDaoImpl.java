package cn.haut.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecApplicationTemplateDao;
import cn.haut.elec.domain.ElecApplicationTemplate;

@Repository(IElecApplicationTemplateDao.SERVICE_NAME)
public class ElecApplicationTemplateDaoImpl extends
		CommonDaoImpl<ElecApplicationTemplate> implements
		IElecApplicationTemplateDao {

}
