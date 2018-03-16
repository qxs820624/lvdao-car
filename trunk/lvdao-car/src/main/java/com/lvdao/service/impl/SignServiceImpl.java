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
import com.lvdao.dao.ISignDao;
import com.lvdao.entity.SignEntity;
import com.lvdao.service.ISignService;

@Service("signService")
public class SignServiceImpl implements ISignService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SignServiceImpl.class);
	
    @Autowired
	private ISignDao signDao;

	@Override
	public int insert(SignEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserId()) || StringUtils.isBlank(entity.getUserName())) {
			LOGGER.info("SignServiceImpl insert entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering SignServiceImpl insert...");
		int result = signDao.insert(entity);
		LOGGER.info("Exiting SignServiceImpl insert...");
		return result;
	}

	@Override
	public int update(SignEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserId()) || StringUtils.isBlank(entity.getUserName())) {
			LOGGER.info("SignServiceImpl update entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering SignServiceImpl update...");
		int result = signDao.update(entity);
		LOGGER.info("Exiting SignServiceImpl update...");
		return result;
	}

	@Override
	public int delete(SignEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserId()) || StringUtils.isBlank(entity.getUserName())) {
			LOGGER.info("SignServiceImpl delete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering SignServiceImpl delete...");
		int result = signDao.delete(entity);
		LOGGER.info("Exiting SignServiceImpl delete...");
		return result;
	}
	

	@Override
	public List<SignEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("SignServiceImpl queryList map is null.");
			return null;
		}
		LOGGER.info("Entering SignServiceImpl queryList...");
		List<SignEntity> list = signDao.queryList(map);
		LOGGER.info("Exiting SignServiceImpl queryList...");
		return list;
	}
	
	@Override
	public PageList<SignEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page || null == map) {
			LOGGER.info("SignServiceImpl queryPage map is null.");
			return null;
		}
		LOGGER.info("Entering SignServiceImpl queryPage...");
		PageList<SignEntity> list = signDao.queryPage(page, map);
		LOGGER.info("Exiting SignServiceImpl queryPage...");
		return list;
	}
	
	@Override
	public List<SignEntity> queryAll() {
		LOGGER.info("Entering SignServiceImpl queryAll...");
		List<SignEntity> list = signDao.queryAll();
		LOGGER.info("Exiting SignServiceImpl queryAll...");
		return list;
	}

	@Override
	public int getMaxId() {
		return signDao.getMaxId();
	}

	@Override
	public int updateByRechargeOrderid(SignEntity entity) {
		return signDao.updateByRechargeOrderid(entity);
	}

}
