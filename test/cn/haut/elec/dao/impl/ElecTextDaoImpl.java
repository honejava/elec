package cn.haut.elec.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.ElecTextDao;
import cn.haut.elec.domain.ElecText;

@Repository(value = ElecTextDao.SERVICE_NAME)
public class ElecTextDaoImpl extends HibernateDaoSupport implements ElecTextDao {

	// 注入sessionFactory
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * @Name: save
	 * @Description: 保存text
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-20（创建日期）
	 * @Parameters: ElecText text
	 * @Return: void
	 */
	@Override
	public void save(ElecText text) {
		this.getHibernateTemplate().save(text);
	}

}
