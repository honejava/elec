package cn.haut.elec.service.impl;




import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.dao.IElecCommonMsgDao;
import cn.haut.elec.domain.ElecCommonMsg;
import cn.haut.elec.service.IElecCommonMsgService;

@Service(IElecCommonMsgService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecCommonMsgServiceImpl implements IElecCommonMsgService {

	@Resource(name=IElecCommonMsgDao.SERVICE_NAME)
	private IElecCommonMsgDao eElecCommonMsgDao;
	/**  
	* @Name: save
	* @Description: 保存监控的信息  这里使用的是将数据全部保存到一条记录上  也就是更新操作
	* @Author: 甘亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-09（创建日期）
	* @Parameters: ElecCommonMsg elecCommonMsg
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void save(ElecCommonMsg elecCommonMsg) {
		//首先获取到原来存在的数据
		ElecCommonMsg commonMsg=findCommonMsg();
		//如果原来存储的数据为空  那么就添加记录  如果不为空那么就更新记录
		if(commonMsg!=null){
		commonMsg.setDevRun(elecCommonMsg.getDevRun());
		commonMsg.setStationRun(elecCommonMsg.getStationRun());
		commonMsg.setCreateDate(new Date());
		}else{
			elecCommonMsg.setCreateDate(new Date());
			eElecCommonMsgDao.save(elecCommonMsg);
		}
	}
	/**  
	* @Name: findCommonMsg
	* @Description: 查询监控的信息
	* @Author: 甘亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-09（创建日期）
	* @Parameters: 无
	* @Return: ElecCommonMsg
	*/
	public ElecCommonMsg findCommonMsg() {
		ElecCommonMsg commonMsg=null;
		List<ElecCommonMsg> list =eElecCommonMsgDao.findCollectionByConditionNoPage("", null, null);
		
		if(list!=null&&list.size()>0){
			commonMsg=list.get(0);
		}
		return commonMsg;
	}

}





