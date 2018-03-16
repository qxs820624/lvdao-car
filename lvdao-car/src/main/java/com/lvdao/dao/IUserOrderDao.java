package com.lvdao.dao;

import java.util.List;
import java.util.Map;

import com.lvdao.entity.UserOrderEntity;

public interface IUserOrderDao extends IBaseDao<UserOrderEntity>{
	/**
	 * 根据订单号查找订单
	 * @param map
	 * @return
	 */
	List<UserOrderEntity> queryUserOredeId(Map<String,Object> map);
	
	/**
	 * 根据userId查询该用户最新订单
	 */
	List<UserOrderEntity> queryUserIdOredeTime(Map<String, Object> map);
	/**
	 * <!-- 查询昨天所用成为普通vip的人数  -->
	 */
	Integer yesterdayVipCount(Map<String, Object> map);

	
}
