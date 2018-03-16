package com.lvdao.dao;

import com.lvdao.entity.UserRoleEntity;

public interface IUserRoleDao extends IBaseDao<UserRoleEntity> {
	 int updateById(UserRoleEntity entity);
}
