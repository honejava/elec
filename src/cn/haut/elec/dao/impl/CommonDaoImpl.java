package cn.haut.elec.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.haut.elec.dao.ICommonDao;
import cn.haut.elec.utils.PageInfo;
import cn.haut.elec.utils.TUtils;

public class CommonDaoImpl<T> extends HibernateDaoSupport implements
		ICommonDao<T> {

	@SuppressWarnings("rawtypes")
	Class entityClass = TUtils.getTGenericSuperClass(this.getClass());

	@Resource(name = "sessionFactory")
	public void setSessionFactoryDi(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	/** 保存的操作 */
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	/** 更新 */
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	/** 使用主键ID，查询对象 */
	@SuppressWarnings("unchecked")
	public T findObjectByID(Serializable id) {
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}

	/** 使用ID的数组删除 */
	public void deleteObjectByIDs(Serializable... ids) {
		if (ids != null && ids.length > 0) {
			for (Serializable id : ids) {
				Object entity = this.findObjectByID(id);
				this.getHibernateTemplate().delete(entity);
			}
		}
	}

	/** 使用对象组织集合，批量删除 */
	public void deleteObjectByCollection(List<T> list) {
		this.getHibernateTemplate().deleteAll(list);
	}

	/** 指定查询条件，查询结果集合（不分页） */
	/**
	 * SELECT * FROM elec_text o WHERE 1=1 #Dao层 AND o.textName LIKE '%张%' #业务层
	 * AND o.textRemark LIKE '%张%' #业务层 ORDER BY o.textDate ASC,o.textName DESC
	 * #业务层
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findCollectionByConditionNoPage(String condition,
			final Object[] params, Map<String, String> orderby) {
		String hql = "from " + entityClass.getSimpleName() + " o where 1=1 ";
		// 将Map集合组织成hql语句的排序条件
		String orderbyCondition = this.orderby(orderby);
		if (condition == null) {// 预防出现
								// eElecCommonMsgDao.findCollectionByConditionNoPage(null,
								// null, null);
			condition = "";
		}
		final String finalHql = hql + condition + orderbyCondition;

		List<T> list = this.getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(finalHql);
						if (params != null && params.length > 0) {
							for (int i = 0; i < params.length; i++) {
								query.setParameter(i, params[i]);
							}
						}
						return query.list();
					}

				});
		return list;
	}

	/** 将Map集合组织成hql语句的排序条件 */
	private String orderby(Map<String, String> orderby) {
		// 条件
		StringBuffer buffer = new StringBuffer("");
		if (orderby != null && orderby.size() > 0) {
			buffer.append(" order by ");
			for (Map.Entry<String, String> map : orderby.entrySet()) {
				buffer.append(map.getKey() + " " + map.getValue() + ",");
			}
			// 删除最后一个多出的逗号
			buffer.deleteCharAt(buffer.length() - 1);
		}
		return buffer.toString();
	}

	/** 指定查询条件，查询结果集合（不分页） */
	/**
	 * SELECT * FROM elec_text o WHERE 1=1 #Dao层 AND o.textName LIKE '%张%' #业务层
	 * AND o.textRemark LIKE '%张%' #业务层 ORDER BY o.textDate ASC,o.textName DESC
	 * #业务层
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findCollectionByConditionNoPageWithCach(String condition,
			final Object[] params, Map<String, String> orderby) {
		String hql = "from " + entityClass.getSimpleName() + " o where 1=1 ";
		// 将Map集合组织成hql语句的排序条件
		String orderbyCondition = this.orderby(orderby);
		if (condition == null) {// 预防出现
								// eElecCommonMsgDao.findCollectionByConditionNoPage(null,
								// null, null);
			condition = "";
		}
		final String finalHql = hql + condition + orderbyCondition;

		// 查询结果（方式一）
		// List<T> list = this.getHibernateTemplate().find(finalHql,params);
		// 方式二（使用hibernate获取SessionFactory，从而获取当前Session）
		// SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		// Session s = sf.getCurrentSession();
		// 方式三：使用execute的回调方法，获取Session
		List<T> list = this.getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(finalHql);
						if (params != null && params.length > 0) {
							for (int i = 0; i < params.length; i++) {
								query.setParameter(i, params[i]);
							}
						}
						query.setCacheable(true);
						return query.list();
					}

				});
		return list;
	}

	/** 指定查询条件，查询结果集合（分页） */
	/**
	 * SELECT * FROM elec_text o WHERE 1=1 #Dao层 AND o.textName LIKE '%张%' #业务层
	 * AND o.textRemark LIKE '%张%' #业务层 ORDER BY o.textDate ASC,o.textName DESC
	 * #业务层
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findCollectionByConditionWithPage(String condition,
			final Object[] params, Map<String, String> orderby,
			final PageInfo pageInfo) {
		String hql = "from " + entityClass.getSimpleName() + " o where 1=1 ";
		// 将Map集合组织成hql语句的排序条件
		String orderbyCondition = this.orderby(orderby);
		final String finalHql = hql + condition + orderbyCondition;

		List<T> list = this.getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(finalHql);
						if (params != null && params.length > 0) {
							for (int i = 0; i < params.length; i++) {
								query.setParameter(i, params[i]);
							}
						}
						/** 2014-2-27 添加分页，使用hibernate的分页， begin */
						pageInfo.setTotalResult(query.list().size());// 设置总记录数
						query.setFirstResult(pageInfo.getBeginResult());// 表示当前页从第几条开始检索，默认是0（0表示第1条）
						query.setMaxResults(pageInfo.getPageSize());// 表示当前页最多显示多少条记录
						/** 2014-2-27 添加分页 end */
						return query.list();
					}
				});
		return list;
	}

	/** 指定查询条件，查询结果集合（不分页，用于excel报表） */
	/**
	 * SELECT * FROM elec_text o WHERE 1=1 #Dao层 AND o.textName LIKE '%张%' #业务层
	 * AND o.textRemark LIKE '%张%' #业务层 ORDER BY o.textDate ASC,o.textName DESC
	 * #业务层
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findCollectionByConditionNoPageWithExcel(String condition,
			final Object[] params, Map<String, String> orderby,
			String selectCondition) {
		String hql = "select " + selectCondition + " from "
				+ entityClass.getSimpleName() + " o where 1=1 ";
		// 将Map集合组织成hql语句的排序条件
		String orderbyCondition = this.orderby(orderby);
		final String finalHql = hql + condition + orderbyCondition;
		// 查询结果（方式一）
		// List<T> list = this.getHibernateTemplate().find(finalHql,params);
		// 方式二（使用hibernate获取SessionFactory，从而获取当前Session）
		// SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		// Session s = sf.getCurrentSession();
		// 方式三：使用execute的回调方法，获取Session
		List list = this.getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(finalHql);
						if (params != null && params.length > 0) {
							for (int i = 0; i < params.length; i++) {
								query.setParameter(i, params[i]);
							}
						}
						return query.list();
					}

				});
		return list;
	}

}
