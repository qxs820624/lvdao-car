package com.lvdao.dao;

import com.lvdao.entity.RoleEntity;

public interface IRoleDao extends IBaseDao<RoleEntity> {
	 int updateById(RoleEntity entity);
}
