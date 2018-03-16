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
import com.lvdao.dao.IUserOrderDao;
import com.lvdao.entity.UserOrderEntity;
import com.lvdao.service.IUserOrderService;

@Service("userOrderService")
public class UserOrderServiceImpl implements IUserOrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserOrderServiceImpl.class);
	
	@Autowired
	private IUserOrderDao userOrderDao;
	
	@Override
	public int insert(UserOrderEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getOrderId())) {
			LOGGER.info("UserOrderServiceImpl insert entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserOrderServiceImpl insert...");
		int result = userOrderDao.insert(entity);
		LOGGER.info("Exiting UserOrderServiceImpl insert...");
		return result;
	}
	
	@Override
	public int update(UserOrderEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getOrderId())) {
			LOGGER.info("UserOrderServiceImpl update entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserOrderServiceImpl update...");
		int result = userOrderDao.update(entity);
		LOGGER.info("Exiting UserOrderServiceImpl update...");
		return result;
	}
	
	@Override
	public List<UserOrderEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("UserOrderServiceImpl queryList map is null.");
			return null;
		}
		LOGGER.info("Entering UserOrderServiceImpl queryList...");
		List<UserOrderEntity> list = userOrderDao.queryList(map);
		LOGGER.info("Exiting UserOrderServiceImpl queryList...");
		return list;
	}

	@Override
	public List<UserOrderEntity> queryAll() {
		LOGGER.info("Entering UserOrderServiceImpl queryAll...");
		List<UserOrderEntity> list = userOrderDao.queryAll();
		LOGGER.info("Exiting UserOrderServiceImpl queryAll...");
		return list;
	}

	@Override
	public int delete(UserOrderEntity entity) {
		return 0;
	}

	@Override
	public PageList<UserOrderEntity> queryPage(Page page,Map<String, Object> map) {
		if(null == page || null == map) {
			LOGGER.info("UserOrderServiceImpl queryList page or map is null.");
			return null;
		}
		LOGGER.info("Entering UserOrderServiceImpl queryPage...");
		PageList<UserOrderEntity> list = userOrderDao.queryPage(page, map);
		LOGGER.info("Exiting UserOrderServiceImpl queryPage...");
		return list;
	}

	@Override
	public int getMaxId() {
		return userOrderDao.getMaxId();
	}

	@Override
	public List<UserOrderEntity> queryUserIdOredeTime(Map<String, Object> map) {
		return userOrderDao.queryUserIdOredeTime(map);
	}

	@Override
	public int yesterdayVipCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userOrderDao.yesterdayVipCount(map);
	}

	
}
