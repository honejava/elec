package cn.haut.elec.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecProcessDefinitionDao;
import cn.haut.elec.domain.ElecApplication;

@Repository(IElecProcessDefinitionDao.SERVICE_NAME)
public class ElecProcessDefinitionDaoImpl extends
		CommonDaoImpl<ElecApplication> implements IElecProcessDefinitionDao {

	@SuppressWarnings("unchecked")
	public List<String> findDistinctApplicationByKey() {
		return this.getHibernateTemplate().find(
				"select distinct(title) from ElecApplication ");
	}

	@SuppressWarnings("unchecked")
	public List<ElecApplication> findMaxApplicationByTitle(String title) {
		return this
				.getHibernateTemplate()
				.find("select a from ElecApplication a where title=? order by applyTime desc",
						title);
	}

	public void deleteAllApplicationByKey(ElecApplication elecApplication) {
		this.getHibernateTemplate().delete(elecApplication);
	}

	@SuppressWarnings("unchecked")
	public List<ElecApplication> findApplicationByKey(int id) {
		return this
				.getHibernateTemplate()
				.find("from ElecApplication e where e.elecApplicationTemplate.id=? order by applyTime desc",
						id);
	}

}
