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
import com.lvdao.dao.IUserAccountDao;
import com.lvdao.entity.UserAccountEntity;
import com.lvdao.service.IUserAccountService;
@Service("userAccountService")
public class UserAccountServiceImpl implements IUserAccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);
	
	@Autowired
	private IUserAccountDao userAccountDao;
	
	@Override
	public int insert(UserAccountEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserName()) || StringUtils.isBlank(entity.getAccountId())) {
			LOGGER.info("UserAccountServiceImpl insert entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserAccountServiceImpl insert...");
		int result = userAccountDao.insert(entity);
		LOGGER.info("Exiting UserAccountServiceImpl insert...");
		return result;
	}

	@Override
	public int update(UserAccountEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserName()) || StringUtils.isBlank(entity.getAccountId())) {
			LOGGER.info("UserAccountServiceImpl update entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserAccountServiceImpl update...");
		int result = userAccountDao.update(entity);
		LOGGER.info("Exiting UserAccountServiceImpl update...");
		return result;
	}

	@Override
	public int delete(UserAccountEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserName()) || StringUtils.isBlank(entity.getAccountId())) {
			LOGGER.info("UserAccountServiceImpl delete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserAccountServiceImpl delete...");
		int result = userAccountDao.delete(entity);
		LOGGER.info("Exiting UserAccountServiceImpl delete...");
		return result;
	}
	
	@Override
	public List<UserAccountEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("UserAccountServiceImpl queryList map is null.");
			return null;
		}
		LOGGER.info("Entering UserAccountServiceImpl queryList...");
		List<UserAccountEntity> list = userAccountDao.queryList(map);
		LOGGER.info("Exiting UserAccountServiceImpl queryList...");
		return list;
	}

	@Override
	public PageList<UserAccountEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page || null == map ) {
			LOGGER.info("UserAccountServiceImpl queryList page or map is null.");
			return null;
		}
		LOGGER.info("Entering UserAccountServiceImpl queryPage...");
		PageList<UserAccountEntity> list = userAccountDao.queryPage(page, map);
		LOGGER.info("Exiting UserAccountServiceImpl queryPage...");
		return list;
	}
	
	@Override
	public List<UserAccountEntity> queryAll() {
		LOGGER.info("Entering UserAccountServiceImpl queryAll...");
		List<UserAccountEntity> list = userAccountDao.queryAll();
		LOGGER.info("Exiting UserAccountServiceImpl queryAll...");
		return list;
	}
	
	@Override
	public int getMaxId() {
		return userAccountDao.getMaxId();
	}

}
