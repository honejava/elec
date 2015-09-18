package cn.haut.elec.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecDataChartDao;
import cn.haut.elec.domain.ElecDataChart;

@Repository(IElecDataChartDao.SERVICE_NAME)
public class ElecDataChartDaoImpl extends CommonDaoImpl<ElecDataChart>
		implements IElecDataChartDao {

	public void saveList(List<ElecDataChart> list) {
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ElecDataChart> findElecDataChartByCondition(String condition,
			final Object[] params) {
		/** 指定查询条件，获取资料图纸的信息，sql语句进行查询 */

		List<ElecDataChart> dataChartList = new ArrayList<ElecDataChart>();

		final String sql = "SELECT a.dataChartID as dataChartID,b.ddlName as jctname,c.ddlName as picname,a.dataChartName as filename,a.comment as comment "
				+ " FROM Elec_DataChart a "
				+ " JOIN  Elec_SystemDDL b  ON b.DdlCode=a.jctID  AND b.keyword='所属单位'"
				+ " JOIN  Elec_SystemDDL c  ON c.ddlCode=a.BelongTo  AND c.keyword='图纸类别' "
				+ " WHERE 1=1" + condition;
		List<Object[]> list = this.getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("deprecation")
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// 使用hibernate支持的sql语句的标量查询，将查询字段的结果转换成对应的值，并放置在Object数组中
						Query query = session
								.createSQLQuery(sql)
								.addScalar("dataChartID", Hibernate.INTEGER)
								// 参数一：查询字段的别名；参数二：指定封装类型
								.addScalar("jctname", Hibernate.STRING)
								.addScalar("picname", Hibernate.STRING)
								.addScalar("filename", Hibernate.STRING)
								.addScalar("comment", Hibernate.STRING);
						if (params != null && params.length > 0) {
							for (int i = 0; i < params.length; i++) {
								query.setParameter(i, params[i]);
							}
						}

						return query.list();
					}

				});
		// 转换成对应的PO对象
		if (list != null && list.size() > 0) {
			for (Object[] o : list) {
				ElecDataChart data = new ElecDataChart();
				data.setDataChartID(Integer.parseInt(o[0].toString()));
				data.setJctID(o[1].toString());
				data.setBelongTo(o[2].toString());
				data.setDataChartName(o[3].toString());
				data.setComment(o[4].toString());
				dataChartList.add(data);
			}
		}
		return dataChartList;
	}
}
