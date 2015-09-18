package cn.haut.elec.service;

import cn.haut.elec.domain.ElecCommonMsg;

public interface IElecCommonMsgService {
	
	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecCommonMsgServiceImpl";

	public void save(ElecCommonMsg elecCommonMsg);

	public ElecCommonMsg findCommonMsg();

}
