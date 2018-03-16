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
import com.lvdao.dao.IDealLogDao;
import com.lvdao.entity.DealLogEntity;
import com.lvdao.service.IDealLogService;

@Service("dealLogService")
public class DealLogServiceImpl implements IDealLogService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DealLogServiceImpl.class);
	
	@Autowired
	private IDealLogDao dealLogDao;
	
	@Override
	public int insert(DealLogEntity entity) {
		if(null == entity) {
			LOGGER.info("RoleServiceImpl insert entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering DealLogEntity insert service...");
		int result = dealLogDao.insert(entity);
		LOGGER.info("Exiting DealLogEntity insert service...");
		return result;
	}
	
	@Override
	public int update(DealLogEntity entity) {
		if(null == entity) {
			LOGGER.info("RoleServiceImpl update entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering DealLogEntity update service...");
		int result = dealLogDao.update(entity);
		LOGGER.info("Exiting DealLogEntity update service...");
		return result;
	}
	
	@Override
	public int delete(DealLogEntity entity) {
		if(null == entity) {
			LOGGER.info("RoleServiceImpl delete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering DealLogEntity delete service...");
		int result = dealLogDao.delete(entity);
		LOGGER.info("Exiting DealLogEntity delete service...");
		return result;
	}
	
	@Override
	public List<DealLogEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("IDealLogService queryList map is null.");
			return null;
		}
		LOGGER.info("Entering IDealLogService queryList service...");
		List<DealLogEntity> list = dealLogDao.queryList(map);
		LOGGER.info("Exiting IDealLogService queryList service...");
		return list;
	}
	
	@Override
	public List<DealLogEntity> queryAll() {
		return dealLogDao.queryAll();
	}

	@Override
	public PageList<DealLogEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page || null == map) {
			LOGGER.info("DealLogService queryPage page or map is null.");
			return null;
		}
		LOGGER.info("Entering DealLogService queryPage service...");
		PageList<DealLogEntity> list = dealLogDao.queryPage(page, map);
		LOGGER.info("Exiting DealLogService queryPage service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return 0;
	}
}
