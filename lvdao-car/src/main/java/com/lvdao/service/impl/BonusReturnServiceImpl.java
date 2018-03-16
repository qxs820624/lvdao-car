package com.lvdao.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvdao.common.CommonConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.dao.IBonusReturnDao;
import com.lvdao.entity.BonusReturnEntity;
import com.lvdao.service.IBonusReturnService;

@Service("bonusReturnService")
public class BonusReturnServiceImpl implements IBonusReturnService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BonusReturnServiceImpl.class);
	
	@Autowired
	private IBonusReturnDao bonusReturnDao;
	
	@Override
	public int insert(BonusReturnEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getId())) {
			LOGGER.info("BonusReturn insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering BonusReturn insert service...");
		int result = bonusReturnDao.insert(entity);
		LOGGER.info("Exiting BonusReturn insert service...");
		return result;
	}
	
	@Override
	public int update(BonusReturnEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getId())) {
			LOGGER.info("BonusReturn update service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering BonusReturn update service...");
		int result = bonusReturnDao.update(entity);
		LOGGER.info("Exiting BonusReturn update service...");
		return result;
	}
		
	@Override
	public List<BonusReturnEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("BonusReturn queryList service map is null.");
			return null;
		}
		LOGGER.info("Entering BonusReturn queryList service...");
		List<BonusReturnEntity> list = bonusReturnDao.queryList(map);
		LOGGER.info("Exiting BonusReturn queryList service...");
		return list;
	}
	
	@Override
	public PageList<BonusReturnEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page || null == map) {
			LOGGER.info("BonusReturn queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering BonusReturn queryPage service...");
		PageList<BonusReturnEntity> list = bonusReturnDao.queryPage(page, map);
		LOGGER.info("Exiting BonusReturn queryPage service...");
		return list;
	}
	
	@Override
	public List<BonusReturnEntity> queryAll() {
		LOGGER.info("Entering BonusReturn queryAll service...");
		List<BonusReturnEntity> list = bonusReturnDao.queryAll();
		LOGGER.info("Exiting BonusReturn queryAll service...");
		return list;
	}
	
}
