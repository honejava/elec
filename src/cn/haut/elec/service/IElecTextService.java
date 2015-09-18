package cn.haut.elec.service;

import java.util.List;

import cn.haut.elec.domain.ElecText;

public interface IElecTextService {
	
	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecTextServiceImpl";
	
	public void saveElecText(ElecText elecText);

	public List<ElecText> findCollectionByConditionNoPage(ElecText elecText);
}
