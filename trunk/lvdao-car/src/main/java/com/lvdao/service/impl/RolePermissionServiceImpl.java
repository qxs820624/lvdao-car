package com.lvdao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.lvdao.dao.IRolePermissionDao;
import com.lvdao.entity.RolePermissionEntity;
import com.lvdao.entity.UserRoleEntity;
import com.lvdao.service.IRolePermissionService;
import com.lvdao.service.IUserRoleService;

@Service("rolePermissionService")
public class RolePermissionServiceImpl implements IRolePermissionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RolePermissionServiceImpl.class);
	
	@Autowired
	private IRolePermissionDao rolePermissionDao;
	
	@Autowired
	private IUserRoleService userRoleService;
	
	@Override
	public int insert(RolePermissionEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getRoleId()) || StringUtils.isBlank(entity.getPermissionId())) {
			LOGGER.info("RolePermissionServiceImpl insert entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering RolePermissionServiceImpl insert...");
		int result = rolePermissionDao.insert(entity);
		LOGGER.info("Exiting RolePermissionServiceImpl insert...");
		return result;
	}

	@Override
	public int update(RolePermissionEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getRoleId()) || StringUtils.isBlank(entity.getPermissionId())) {
			LOGGER.info("RolePermissionServiceImpl update entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering RolePermissionServiceImpl update...");
		int result = rolePermissionDao.update(entity);
		LOGGER.info("Exiting RolePermissionServiceImpl update...");
		return result;
	}

	@Override
	public int delete(RolePermissionEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getRoleId())) {
			LOGGER.info("RolePermissionServiceImpl delete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering RolePermissionServiceImpl delete...");
		int result = rolePermissionDao.delete(entity);
		LOGGER.info("Exiting RolePermissionServiceImpl delete...");
		return result;
	}
	
	@Override
	public List<RolePermissionEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("RolePermissionServiceImpl queryList entity is null.");
			return null;
		}
		LOGGER.info("Entering RolePermissionServiceImpl queryList...");
		List<RolePermissionEntity> list = rolePermissionDao.queryList(map);
		LOGGER.info("Exiting RolePermissionServiceImpl queryList...");
		return list;
	}
	
	@Override
	public PageList<RolePermissionEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page ||null == map ) {
			LOGGER.info("RolePermissionServiceImpl queryList entity is null.");
			return null;
		}
		LOGGER.info("Entering RolePermissionServiceImpl queryList...");
		PageList<RolePermissionEntity> list = rolePermissionDao.queryPage(page, map);
		LOGGER.info("Exiting RolePermissionServiceImpl queryList...");
		return list;
	}
	
	@Override
	public List<RolePermissionEntity> queryAll() {
		LOGGER.info("Entering RolePermissionServiceImpl queryAll...");
		List<RolePermissionEntity> list = rolePermissionDao.queryAll();
		LOGGER.info("Exiting RolePermissionServiceImpl queryAll...");
		return list;
	}

	/**
	 * 判断该角色中是否有该权限
	 */
	@Override
	public boolean hasPermission(String permCode, List<String> roles) {
		LOGGER.info("Entering RolePermissionServiceImpl hasPermission..");
		Map<String,Object> map = new HashMap<String,Object>();
		int count = CommonConst.DIGIT_ZERO;
		map.put("permissionId", permCode);
		for(int i = 0; i < roles.size();i++ ){
			map.put("roleId", roles.get(i));
			count = rolePermissionDao.getCount(map);
			if(count > CommonConst.DIGIT_ZERO){
				return true;
			}
		}
		LOGGER.info("Exiting RolePermissionServiceImpl hasPermission..");
		return false;
	}

	@Override
	public List<String> queryPermissionListByRoleId(String roleId) {
		return rolePermissionDao.queryPermissionListByRoleId(roleId);
	}
	
	@Override
	public int getMaxId() {
		return rolePermissionDao.getMaxId();
	}
	
	@Override
	public boolean saveAllUserPermissionInSession() {
		List<UserRoleEntity> allUserRoleList = userRoleService.queryAll();
		List<RolePermissionEntity> allRolePermissionList = this.queryAll();
		List<String> permissions = null;
		for (UserRoleEntity userRoleEntity : allUserRoleList) {
			permissions = new ArrayList<String>();
			for (RolePermissionEntity rolePermissionEntity : allRolePermissionList) {
				if(userRoleEntity.getRoleId().equals(rolePermissionEntity.getRoleId())){
					permissions.add(rolePermissionEntity.getPermissionId());
				}
			}
			if(!permissions.isEmpty()) {
			CommonConst.USER_PERMISSION_CACHE_MAP.put(userRoleEntity.getUserName(), permissions);
			}
		}
		
		
		return true;
	}

	
}
