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
import com.lvdao.dao.IUserVoiceDao;
import com.lvdao.entity.UserVoiceEntity;
import com.lvdao.service.IUserVoiceService;

@Service("userVoiceService")
public class UserVoiceServiceImpl implements IUserVoiceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserVoiceServiceImpl.class);
	
	@Autowired
	private IUserVoiceDao userVoiceDao;
	
	@Override
	public int insert(UserVoiceEntity entity) {
		if(null == entity) {
			LOGGER.info("UserVoiceEntity insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserVoiceEntity insert service...");
		int result = userVoiceDao.insert(entity);
		LOGGER.info("Exiting UserVoiceEntity insert service...");
		return result;
	}

	@Override
	public int update(UserVoiceEntity entity) {
		if(null == entity) {
			LOGGER.info("UserVoiceEntity update service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserVoiceEntity update service...");
		int result = userVoiceDao.update(entity);
		LOGGER.info("Exiting UserVoiceEntity update service...");
		return result;
	}
	
	@Override
	public int delete(UserVoiceEntity entity) {
		if(null == entity) {
			LOGGER.info("UserVoiceEntity delete service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserVoiceEntity delete service...");
		int result = userVoiceDao.delete(entity);
		LOGGER.info("Exiting UserVoiceEntity delete service...");
		return result;
	}
	
	@Override
	public List<UserVoiceEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("UserVoiceEntity queryList service map is null.");
			return null;
		}
		LOGGER.info("Entering UserVoiceEntity queryList service...");
		List<UserVoiceEntity> list = userVoiceDao.queryList(map);
		LOGGER.info("Exiting UserVoiceEntity queryList service...");
		return list;
	}
	
	@Override
	public PageList<UserVoiceEntity> queryPage(Page page,
			Map<String, Object> map) {
		if(null == page || null == map ) {
			LOGGER.info("UserVoiceEntity queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering UserVoiceEntity queryPage service...");
		PageList<UserVoiceEntity> list = userVoiceDao.queryPage(page, map);
		LOGGER.info("Exiting UserVoiceEntity queryPage service...");
		return list;
	}
	
	@Override
	public List<UserVoiceEntity> queryAll() {
		LOGGER.info("Entering UserVoiceEntity queryAll service...");
		List<UserVoiceEntity> list = userVoiceDao.queryAll();
		LOGGER.info("Exiting UserVoiceEntity queryAll service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return userVoiceDao.getMaxId();
	}
	
}
