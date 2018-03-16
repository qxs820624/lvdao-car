package com.lvdao.service;

import java.util.List;
import java.util.Map;

import com.lvdao.entity.UserOrderEntity;

public interface IUserOrderService extends IBaseService<UserOrderEntity> {
	/**
	 * 根据userId查询该用户最新订单
	 */
	List<UserOrderEntity> queryUserIdOredeTime(Map<String, Object> map);
	/**
	 * <!-- 查询昨天所用成为普通vip的人数  -->
	 */
	int yesterdayVipCount(Map<String, Object> map);
	
	//boolean upGradeOperationAfterPay(String orderId, String transactionId, BigDecimal money);
    
}