package com.lvdao.service;

import com.lvdao.entity.SignEntity;

public interface ISignService extends IBaseService<SignEntity>{
	
	int updateByRechargeOrderid(SignEntity entity);

}
