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
import com.lvdao.dao.IRechargeDao;
import com.lvdao.entity.RechargeEntity;
import com.lvdao.service.IRechargeService;

@Service("rechargeService")
public class RechargeServiceImpl implements IRechargeService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RechargeServiceImpl.class);
	
    @Autowired
	private IRechargeDao rechargeDao;

	@Override
	public int insert(RechargeEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserId()) || StringUtils.isBlank(entity.getUserName())) {
			LOGGER.info("RechargeServiceImpl insert entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering RechargeServiceImpl insert...");
		int result = rechargeDao.insert(entity);
		LOGGER.info("Exiting RechargeServiceImpl insert...");
		return result;
	}

	@Override
	public int update(RechargeEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserId()) || StringUtils.isBlank(entity.getUserName())) {
			LOGGER.info("RechargeServiceImpl update entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering RechargeServiceImpl update...");
		int result = rechargeDao.update(entity);
		LOGGER.info("Exiting RechargeServiceImpl update...");
		return result;
	}

	@Override
	public int delete(RechargeEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserId()) || StringUtils.isBlank(entity.getUserName())) {
			LOGGER.info("RechargeServiceImpl delete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering RechargeServiceImpl delete...");
		int result = rechargeDao.delete(entity);
		LOGGER.info("Exiting RechargeServiceImpl delete...");
		return result;
	}
	

	@Override
	public List<RechargeEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("RechargeServiceImpl queryList map is null.");
			return null;
		}
		LOGGER.info("Entering RechargeServiceImpl queryList...");
		List<RechargeEntity> list = rechargeDao.queryList(map);
		LOGGER.info("Exiting RechargeServiceImpl queryList...");
		return list;
	}
	
	@Override
	public PageList<RechargeEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page || null == map) {
			LOGGER.info("RechargeServiceImpl queryPage map is null.");
			return null;
		}
		LOGGER.info("Entering RechargeServiceImpl queryPage...");
		PageList<RechargeEntity> list = rechargeDao.queryPage(page, map);
		LOGGER.info("Exiting RechargeServiceImpl queryPage...");
		return list;
	}
	
	@Override
	public List<RechargeEntity> queryAll() {
		LOGGER.info("Entering RechargeServiceImpl queryAll...");
		List<RechargeEntity> list = rechargeDao.queryAll();
		LOGGER.info("Exiting RechargeServiceImpl queryAll...");
		return list;
	}

	@Override
	public int getMaxId() {
		return rechargeDao.getMaxId();
	}

	@Override
	public int updateByRechargeOrderid(RechargeEntity entity) {
		 LOGGER.info("Entering RechargeServiceImpl updateByRechargeOrderid...");
		 int updateByRechargeOrderid = rechargeDao.updateByRechargeOrderid(entity);
		 LOGGER.info("Exiting RechargeServiceImpl updateByRechargeOrderid...");
		 return updateByRechargeOrderid;
	}

}
