package com.lvdao.dao;

import com.lvdao.entity.UserAccountEntity;

public interface IUserAccountDao extends IBaseDao<UserAccountEntity> {
    int updateById(UserAccountEntity entity);

    int updateAccountAmount(UserAccountEntity entity);
}