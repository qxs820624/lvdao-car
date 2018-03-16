package com.lvdao.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvdao.common.CommonConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.dao.ISettlementDao;
import com.lvdao.entity.SettlementEntity;
import com.lvdao.service.ISettlementService;

@Service("settlementService")
public class SettlementServiceImpl implements ISettlementService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SettlementServiceImpl.class);
	
	@Autowired
	private ISettlementDao settlementDao;
	
	@Override
	public int insert(SettlementEntity entity) {
		if(null == entity) {
			LOGGER.info("Settlement insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering Settlement insert service...");
		int result = settlementDao.insert(entity);
		LOGGER.info("Exiting Settlement insert service...");
		return result;
	}
	
	@Override
	public int update(SettlementEntity entity) {
		if(null == entity) {
			LOGGER.info("Settlement update service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering Settlement update service...");
		int result = settlementDao.update(entity);
		LOGGER.info("Exiting Settlement update service...");
		return result;
	}
	
	@Override
	public int delete(SettlementEntity entity) {
		if(null == entity) {
			LOGGER.info("Settlement delete service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering Settlement delete service...");
		int result = settlementDao.delete(entity);
		LOGGER.info("Exiting Settlement delete service...");
		return result;
	}
	
	@Override
	public List<SettlementEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("Settlement queryList service map is null.");
			return null;
		}
		LOGGER.info("Entering Settlement queryList service...");
		List<SettlementEntity> list = settlementDao.queryList(map);
		LOGGER.info("Exiting Settlement queryList service...");
		return list;
	}
	
	@Override
	public PageList<SettlementEntity> queryPage(Page page,
			Map<String, Object> map) {
		if(null == page || null == map ) {
			LOGGER.info("Settlement queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering Settlement queryPage service...");
		PageList<SettlementEntity> list = settlementDao.queryPage(page, map);
		LOGGER.info("Exiting Settlement queryPage service...");
		return list;
	}
	

	@Override
	public List<SettlementEntity> queryAll() {
		LOGGER.info("Entering Settlement queryAll service...");
		List<SettlementEntity> list = settlementDao.queryAll();
		LOGGER.info("Exiting Settlement queryAll service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return settlementDao.getMaxId();
	}
	
}
