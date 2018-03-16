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
import com.lvdao.dao.IUserRoleDao;
import com.lvdao.entity.UserRoleEntity;
import com.lvdao.service.IUserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements IUserRoleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleServiceImpl.class);
	
	@Autowired
	private IUserRoleDao userRoleDao;
	
	@Override
	public int insert(UserRoleEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getRoleId()) || StringUtils.isBlank(entity.getRoleName())) {
			LOGGER.info("UserRoleService insert entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserRoleService insert...");
		int result = userRoleDao.insert(entity);
		LOGGER.info("Exiting UserRoleService insert...");
		return result;
	}
	
	@Override
	public int update(UserRoleEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getRoleId()) || StringUtils.isBlank(entity.getRoleName())) {
			LOGGER.info("UserRoleService update entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserRoleService update...");
		int result = userRoleDao.update(entity);
		LOGGER.info("Exiting UserRoleService update...");
		return result;
	}

	@Override
	public int delete(UserRoleEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserName())) {
			LOGGER.info("UserRoleService delete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserRoleService delete...");
		int result = userRoleDao.delete(entity);
		LOGGER.info("Exiting UserRoleService delete...");
		return result;
	} 
	
	@Override
	public List<UserRoleEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("UserRoleService queryList map is null.");
			return null;
		}
		LOGGER.info("Entering UserRoleService queryList service...");
		List<UserRoleEntity> list = userRoleDao.queryList(map);
		LOGGER.info("Exiting UserRoleService queryList service...");
		return list;
	}
	

	@Override
	public PageList<UserRoleEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page || null == map ) {
			LOGGER.info("UserRoleService queryPage page or map is null.");
			return null;
		}
		LOGGER.info("Entering UserRoleService queryPage service...");
		PageList<UserRoleEntity> list = userRoleDao.queryPage(page, map);
		LOGGER.info("Exiting UserRoleService queryPage service...");
		return list;
	}
	
	@Override
	public List<UserRoleEntity> queryAll() {
		LOGGER.info("Entering UserRoleService queryPage service...");
		List<UserRoleEntity> list = userRoleDao.queryAll();
		LOGGER.info("Exiting UserRoleService queryPage service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return userRoleDao.getMaxId();
	}

}
