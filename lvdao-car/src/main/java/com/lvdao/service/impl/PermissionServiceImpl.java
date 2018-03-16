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
import com.lvdao.dao.IPermissionDao;
import com.lvdao.entity.PermissionEntity;
import com.lvdao.service.IPermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionServiceImpl.class);
	
	@Autowired
	private IPermissionDao permissionDao;
	
	@Override
	public int insert(PermissionEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getPermissionId()) || StringUtils.isBlank(entity.getPermissionName())) {
			LOGGER.info("Permission insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering Permission insert service...");
		int result = permissionDao.insert(entity);
		LOGGER.info("Exiting Permission insert service...");
		return result;
	}
	
	@Override
	public int update(PermissionEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getPermissionId()) || StringUtils.isBlank(entity.getPermissionName())) {
			LOGGER.info("Permission update service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering Permission update service...");
		int result = permissionDao.update(entity);
		LOGGER.info("Exiting Permission update service...");
		return result;
	}

	@Override
	public int delete(PermissionEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getPermissionId()) || StringUtils.isBlank(entity.getPermissionName())) {
			LOGGER.info("Permission delete service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering Permission delete service...");
		int result = permissionDao.delete(entity);
		LOGGER.info("Exiting Permission delete service...");
		return result;
	}
	
	@Override
	public List<PermissionEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("Permission queryList service map is null.");
			return null;
		}
		LOGGER.info("Entering Permission queryList service...");
		List<PermissionEntity> list = permissionDao.queryList(map);
		LOGGER.info("Exiting Permission queryList service...");
		return list;
	}

	@Override
	public PageList<PermissionEntity> queryPage(Page page,
			Map<String, Object> map) {
		if(null == page || null == map) {
			LOGGER.info("Permission queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering Permission queryPage service...");
		PageList<PermissionEntity> list = permissionDao.queryPage(page, map);
		LOGGER.info("Exiting Permission queryPage service...");
		return list;
	}
	
	@Override
	public List<PermissionEntity> queryAll() {
		LOGGER.info("Entering Permission queryAll service...");
		List<PermissionEntity> list = permissionDao.queryAll();
		LOGGER.info("Exiting Permission queryAll service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return permissionDao.getMaxId();
	}
	
}
