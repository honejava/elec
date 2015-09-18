package cn.haut.elec.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.dao.IElecSystemDDLDao;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecSystemDDLService;

/**
 * 相当于spring容器中定义：
 * <bean id="cn.haut.elec.service.impl.ElecTextServiceImpl" class="cn.haut.elec.service.impl.ElecTextServiceImpl"/>
 */
@Service(IElecSystemDDLService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecSystemDDLServiceImpl implements IElecSystemDDLService {

	@Resource(name=IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;

	public List<ElecSystemDDL> findDistinctKeyWordList() {
		return elecSystemDDLDao.findDistinctKeyWordList();
	}

	public List<ElecSystemDDL> findSystemDDLByKeywordlist(String keyword) {
		if(keyword.isEmpty()){
			return elecSystemDDLDao.findCollectionByConditionNoPageWithCach(null, null, null);
		}
		String condition=" and o.keyword=?";
		Object[] params = {keyword};
		
		Map<String ,String> orderby=new LinkedHashMap<String, String>();
		orderby.put("o.ddlCode", "asc");
		
		return elecSystemDDLDao.findCollectionByConditionNoPageWithCach(condition, params, orderby);
	}

	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveSystemDDL(ElecSystemDDL elecSystemDDL) {
		//1：页面传递的值
		//数据类型
		String keyword = elecSystemDDL.getKeywordname();
		//判断的标识
		String typeflag = elecSystemDDL.getTypeflag();
		//数据项的值
		String [] itemname = elecSystemDDL.getItemname();
		//获取判断的标识typeflag的值
		//如果typeflag的值==new（表示新增一种数据类型）
		if(typeflag!=null && typeflag.equals("new")){
			//遍历itemname的数组对象，组织PO对象，执行保存
			this.saveSystem(itemname,keyword);
		}
		//如果typeflag的值==add（在已有类型的基础上进行编辑和修改）
		else{
			//* 使用keyword作为查询条件，查询当前keyword下的集合List<ElecSystem>
			List<ElecSystemDDL> list = this.findSystemDDLByKeywordlist(keyword);
		    //* 执行集合List的删除，删除之前的数据
			elecSystemDDLDao.deleteObjectByCollection(list);
			//遍历itemname的数组对象，组织PO对象，执行保存
			this.saveSystem(itemname, keyword);
		}
	}

	/**遍历itemname的数组对象，组织PO对象，执行保存*/
	private void saveSystem(String[] itemname, String keyword) {
		if(itemname!=null && itemname.length>0){
			for(int i=0;i<itemname.length;i++){
				ElecSystemDDL systemDDL = new ElecSystemDDL();
				systemDDL.setKeyword(keyword);
				systemDDL.setDdlCode(i+1);
				systemDDL.setDdlName(itemname[i]);
				elecSystemDDLDao.save(systemDDL);
			}
		}
	}

	public String findSystemDDLByKeywordAndDDLCode(String keyword, String ddlCode) {
		return elecSystemDDLDao.findSystemDDLByKeywordAndDDLCode(keyword,ddlCode);
	}

	public List<ElecSystemDDL> findSystemDDLListByKeyword(String keyword) {
		return elecSystemDDLDao.findCollectionByConditionNoPage(keyword, null, null);
	}

	
}







