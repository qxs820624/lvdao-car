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
import com.lvdao.dao.IUserMailDao;
import com.lvdao.entity.UserMailEntity;
import com.lvdao.service.IUserMailService;

@Service("userMailService")
public class UserMailServiceImpl implements IUserMailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserMailServiceImpl.class);
	
	@Autowired
	private IUserMailDao userMailDao;
	
	@Override
	public int insert(UserMailEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserName()) || StringUtils.isBlank(entity.getMailId())) {
			LOGGER.info("UserMailServiceImpl insert entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserMailServiceImpl insert...");
		int result = userMailDao.insert(entity);
		LOGGER.info("Exiting UserMailServiceImpl insert...");
		return result;
	}

	@Override
	public int update(UserMailEntity entity) {
		if(null == entity) {
			LOGGER.info("UserMailServiceImpl update entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserMailServiceImpl update...");
		int result = userMailDao.update(entity);
		LOGGER.info("Exiting UserMailServiceImpl update...");
		return result;
	}

	@Override
	public int delete(UserMailEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserName()) || StringUtils.isBlank(entity.getMailId())) {
			LOGGER.info("UserMailServiceImpl delete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserMailServiceImpl delete...");
		int result = userMailDao.delete(entity);
		LOGGER.info("Exiting UserMailServiceImpl delete...");
		return result;
	}
	
	@Override
	public List<UserMailEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("UserMailServiceImpl queryList map is null.");
			return null;
		}
		LOGGER.info("Entering UserMailServiceImpl queryList...");
		List<UserMailEntity> list = userMailDao.queryList(map);
		LOGGER.info("Exiting UserMailServiceImpl queryList...");
		return list;
	}
	
	@Override
	public PageList<UserMailEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page || null == map) {
			LOGGER.info("UserMailServiceImpl queryList page or map is null.");
			return null;
		}
		LOGGER.info("Entering UserMailServiceImpl queryPage...");
		PageList<UserMailEntity> list = userMailDao.queryPage(page, map);
		LOGGER.info("Exiting UserMailServiceImpl queryPage...");
		return list;
	}
	
	@Override
	public List<UserMailEntity> queryAll() {
		LOGGER.info("Entering UserMailServiceImpl queryAll...");
		List<UserMailEntity> list = userMailDao.queryAll();
		LOGGER.info("Exiting UserMailServiceImpl queryAll...");
		return list;
	}

	@Override
	public int getMaxId() {
		return userMailDao.getMaxId();
	}
	
}
