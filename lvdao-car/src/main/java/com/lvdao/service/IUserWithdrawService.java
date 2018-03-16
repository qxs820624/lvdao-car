package com.lvdao.service;

import java.util.List;

import com.lvdao.entity.UserWithdrawEntity;

public interface IUserWithdrawService extends IBaseService<UserWithdrawEntity>{

	/** 
     * 查询
     * 
     * @author zhanghj
     * @return
     */  
    public List<UserWithdrawEntity> queryBatchList(List<String> ids);
}
