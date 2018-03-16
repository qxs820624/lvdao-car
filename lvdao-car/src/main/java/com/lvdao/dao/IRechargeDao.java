package com.lvdao.dao;

import com.lvdao.entity.RechargeEntity;

public interface IRechargeDao extends IBaseDao<RechargeEntity>{

	int updateByRechargeOrderid(RechargeEntity entity);

}