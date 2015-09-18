package cn.haut.elec.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.haut.elec.utils.PageInfo;

public interface ICommonDao<T> {
	public void save(T entity);

	public void update(T entity);

	public T findObjectByID(Serializable id);

	public void deleteObjectByIDs(Serializable... ids);

	public void deleteObjectByCollection(List<T> list);

	public List<T> findCollectionByConditionNoPage(String condition,
			Object[] params, Map<String, String> orderby);

	public List<T> findCollectionByConditionNoPageWithCach(String condition,
			Object[] params, Map<String, String> orderby);

	public List<T> findCollectionByConditionWithPage(String condition,
			Object[] params, Map<String, String> orderby, PageInfo pageInfo);

}
