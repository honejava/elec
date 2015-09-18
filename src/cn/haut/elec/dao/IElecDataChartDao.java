package cn.haut.elec.dao;

import java.util.List;

import cn.haut.elec.domain.ElecDataChart;

public interface IElecDataChartDao extends ICommonDao<ElecDataChart> {
	public static final String SERVICE_NAME = "cn.haut.elec.dao.impl.ElecDataChartDaoImpl";

	public void saveList(List<ElecDataChart> list);

	public List<ElecDataChart> findElecDataChartByCondition(String condition,
			Object[] params);

}
