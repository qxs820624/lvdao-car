
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
import com.lvdao.dao.IUserSmsDao;
import com.lvdao.entity.UserSmsEntity;
import com.lvdao.service.IUserSmsService;

@Service("userSmsService")
public class UserSmsServiceImpl implements IUserSmsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserSmsServiceImpl.class);
	
	@Autowired
	private IUserSmsDao userSmsDao;
	
	@Override
	public int insert(UserSmsEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserName()) || StringUtils.isBlank(entity.getSmsId())) {
			LOGGER.info("UserSmsServiceImpl insert entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserSmsServiceImpl insert...");
		int result = userSmsDao.insert(entity);
		LOGGER.info("Exiting UserSmsServiceImpl insert...");
		return result;
	}
	
	@Override
	public int update(UserSmsEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserName()) || StringUtils.isBlank(entity.getSmsId())) {
			LOGGER.info("UserSmsServiceImpl update entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserSmsServiceImpl update...");
		int result = userSmsDao.update(entity);
		LOGGER.info("Exiting UserSmsServiceImpl update...");
		return result;
	}

	@Override
	public int delete(UserSmsEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserName()) || StringUtils.isBlank(entity.getSmsId())) {
			LOGGER.info("UserSmsServiceImpl delete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserSmsServiceImpl delete...");
		int result = userSmsDao.delete(entity);
		LOGGER.info("Exiting UserSmsServiceImpl delete...");
		return result;
	}
	
	@Override
	public List<UserSmsEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("UserSmsServiceImpl queryList map is null.");
			return null;
		}
		LOGGER.info("Entering UserSmsServiceImpl queryList...");
		List<UserSmsEntity> list = userSmsDao.queryList(map);
		LOGGER.info("Exiting UserSmsServiceImpl queryList...");
		return list;
	}

	@Override
	public PageList<UserSmsEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page || null == map) {
			LOGGER.info("UserSmsServiceImpl queryList page or map is null.");
			return null;
		}
		LOGGER.info("Entering UserSmsServiceImpl queryPage...");
		PageList<UserSmsEntity> list = userSmsDao.queryPage(page, map);
		LOGGER.info("Exiting UserSmsServiceImpl queryPage...");
		return list;
	}
	
	@Override
	public List<UserSmsEntity> queryAll() {
		LOGGER.info("Entering UserSmsServiceImpl queryAll...");
		List<UserSmsEntity> list = userSmsDao.queryAll();
		LOGGER.info("Exiting UserSmsServiceImpl queryAll...");
		return list;
	}

	@Override
	public int getMaxId() {
		return userSmsDao.getMaxId();
	}
}
