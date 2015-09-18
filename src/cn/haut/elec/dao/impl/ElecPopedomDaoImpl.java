package cn.haut.elec.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecPopedomDao;
import cn.haut.elec.domain.ElecPopedom;

@Repository(IElecPopedomDao.SERVICE_NAME)
public class ElecPopedomDaoImpl extends CommonDaoImpl<ElecPopedom> implements
		IElecPopedomDao {

	@SuppressWarnings("unchecked")
	public ElecPopedom finPopedomByMid(String mid) {
		List<ElecPopedom> popedomlist = this.getHibernateTemplate().find(
				"from ElecPopedom where MID=?", mid);
		return popedomlist != null && popedomlist.size() > 0 ? popedomlist
				.get(0) : null;
	}

}
