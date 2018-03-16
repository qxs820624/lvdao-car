package com.lvdao.dao;

import com.lvdao.entity.SignEntity;

public interface ISignDao extends IBaseDao<SignEntity>{

	int updateByRechargeOrderid(SignEntity entity);

}