package com.lvdao.dao.impl;

import org.springframework.stereotype.Repository;

import com.lvdao.dao.IPermissionDao;
import com.lvdao.entity.PermissionEntity;

@Repository("permissionDao")
public class PermissionDaoImpl extends BaseDao<PermissionEntity> implements IPermissionDao {
	
}
