package cn.haut.elec.service;

import cn.haut.elec.domain.ElecPopedom;

public interface IElecPopedomService {
	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecPopedomServiceImpl";

	public ElecPopedom findPopedomByUrl(String url);
	
	
}
