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
import com.lvdao.dao.IAccountDao;
import com.lvdao.entity.AccountEntity;
import com.lvdao.service.IAccountService;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private IAccountDao accountDao;
	
	@Override
	public int insert(AccountEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getAccountId()) || StringUtils.isBlank(entity.getAccountName())) {
			LOGGER.info("Account insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering Account insert service...");
		int result = accountDao.insert(entity);
		LOGGER.info("Exiting Account insert service...");
		return result;
	}
	
	@Override
	public int update(AccountEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getAccountId()) || StringUtils.isBlank(entity.getAccountName())) {
			LOGGER.info("Account update service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering Account update service...");
		int result = accountDao.update(entity);
		LOGGER.info("Exiting Account update service...");
		return result;
	}
	
	@Override
	public int delete(AccountEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getAccountId()) || StringUtils.isBlank(entity.getAccountName())) {
			LOGGER.info("Account delete service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering Account delete service...");
		int result = accountDao.delete(entity);
		LOGGER.info("Exiting Account delete service...");
		return result;
	}
	
	@Override
	public List<AccountEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("Account queryList service map is null.");
			return null;
		}
		LOGGER.info("Entering Account queryList service...");
		List<AccountEntity> list = accountDao.queryList(map);
		LOGGER.info("Exiting Account queryList service...");
		return list;
	}
	
	@Override
	public PageList<AccountEntity> queryPage(Page page,
			Map<String, Object> map) {
		if(null == page || null == map ) {
			LOGGER.info("Account queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering Account queryPage service...");
		PageList<AccountEntity> list = accountDao.queryPage(page, map);
		LOGGER.info("Exiting Account queryPage service...");
		return list;
	}
	

	@Override
	public List<AccountEntity> queryAll() {
		LOGGER.info("Entering Account queryAll service...");
		List<AccountEntity> list = accountDao.queryAll();
		LOGGER.info("Exiting Account queryAll service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return accountDao.getMaxId();
	}
	
}
