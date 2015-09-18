package cn.haut.elec.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.domain.ElecText;

@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public interface ElecTextService {

	public String SERVICE_NAME = "cn.itcast.dao.service.impl.ElecTextServiceImpl";

	public void save(ElecText text);
}
