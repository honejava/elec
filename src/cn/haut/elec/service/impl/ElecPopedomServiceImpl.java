package cn.haut.elec.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.dao.IElecPopedomDao;
import cn.haut.elec.domain.ElecPopedom;
import cn.haut.elec.service.IElecPopedomService;

@Service(IElecPopedomService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecPopedomServiceImpl implements IElecPopedomService {

	@Resource(name = IElecPopedomDao.SERVICE_NAME)
	private IElecPopedomDao elecPopedomDao;

	public ElecPopedom findPopedomByUrl(String url) {

		String condition = " and o.url=? ";
		Object[] params = { url };
		List<ElecPopedom> list = elecPopedomDao
				.findCollectionByConditionNoPage(condition, params, null);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

}
