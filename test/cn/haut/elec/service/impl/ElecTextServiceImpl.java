package cn.haut.elec.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.dao.ElecTextDao;
import cn.haut.elec.domain.ElecText;
import cn.haut.elec.service.ElecTextService;

@Service(value = ElecTextService.SERVICE_NAME)
public class ElecTextServiceImpl implements ElecTextService {

	// 注入dao
	@Autowired
	private ElecTextDao dao;

	/**
	 * @Name: save
	 * @Description: 进入到保存测试text
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-20（创建日期）
	 * @Parameters: 无
	 * @Return:void
	 */
	@Override
	@Transactional(readOnly = false)
	public void save(ElecText text) {
		dao.save(text);
	}

}
