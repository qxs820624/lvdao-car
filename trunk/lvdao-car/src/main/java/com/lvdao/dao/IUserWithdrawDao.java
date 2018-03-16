package com.lvdao.dao;

import java.util.List;

import com.lvdao.entity.UserWithdrawEntity;

public interface IUserWithdrawDao extends IBaseDao<UserWithdrawEntity> {
	
	/** 
     * 查询
     * 
     * @author zhanghj
     * @return
     */  
    public List<UserWithdrawEntity> queryBatchList(List<String> ids);
}
