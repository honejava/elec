package cn.haut.elec.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.dao.IElecRolePopedomDao;
import cn.haut.elec.service.IElecRolePopedomService;

@Service(IElecRolePopedomService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecRolePopedomServiceImpl implements IElecRolePopedomService {

	@Resource(name = IElecRolePopedomDao.SERVICE_NAME)
	private IElecRolePopedomDao elecRolePopedomDao;

}
