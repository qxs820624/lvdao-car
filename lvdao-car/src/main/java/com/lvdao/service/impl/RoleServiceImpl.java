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
import com.lvdao.dao.IRoleDao;
import com.lvdao.entity.RoleEntity;
import com.lvdao.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public int insert(RoleEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getRoleId()) || StringUtils.isBlank(entity.getRoleName())) {
			LOGGER.info("RoleServiceImpl insert entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering RoleServiceImpl insert...");
		int result = roleDao.insert(entity);
		LOGGER.info("Exiting RoleServiceImpl insert...");
		return result;
	}

	@Override
	public int update(RoleEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getRoleId()) || StringUtils.isBlank(entity.getRoleName())) {
			LOGGER.info("RoleServiceImpl update entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering RoleServiceImpl update...");
		int result = roleDao.update(entity);
		LOGGER.info("Exiting RoleServiceImpl update...");
		return result;
	}

	@Override
	public int delete(RoleEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getRoleId()) || StringUtils.isBlank(entity.getRoleName())) {
			LOGGER.info("RoleServiceImpl delete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering RoleServiceImpl delete...");
		int result = roleDao.delete(entity);
		LOGGER.info("Exiting RoleServiceImpl delete...");
		return result;
	}
	

	@Override
	public List<RoleEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("RoleServiceImpl queryList map is null.");
			return null;
		}
		LOGGER.info("Entering RoleServiceImpl queryList...");
		List<RoleEntity> list = roleDao.queryList(map);
		LOGGER.info("Exiting RoleServiceImpl queryList...");
		return list;
	}
	
	@Override
	public PageList<RoleEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page || null == map) {
			LOGGER.info("RoleServiceImpl queryPage map is null.");
			return null;
		}
		LOGGER.info("Entering RoleServiceImpl queryPage...");
		PageList<RoleEntity> list = roleDao.queryPage(page, map);
		LOGGER.info("Exiting RoleServiceImpl queryPage...");
		return list;
	}
	
	@Override
	public List<RoleEntity> queryAll() {
		LOGGER.info("Entering RoleServiceImpl queryAll...");
		List<RoleEntity> list = roleDao.queryAll();
		LOGGER.info("Exiting RoleServiceImpl queryAll...");
		return list;
	}

	@Override
	public int getMaxId() {
		return roleDao.getMaxId();
	}
	
}
