package com.lvdao.service;

import com.lvdao.entity.RechargeEntity;

public interface IRechargeService extends IBaseService<RechargeEntity>{

	int updateByRechargeOrderid(RechargeEntity entity);

}
