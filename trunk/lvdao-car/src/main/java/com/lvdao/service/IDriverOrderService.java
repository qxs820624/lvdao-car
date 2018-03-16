package com.lvdao.service;

import java.util.List;
import java.util.Map;

import com.lvdao.entity.DriverOrderEntity;

public interface IDriverOrderService extends IBaseService<DriverOrderEntity>{
	
	/**
	 * 增加bus司机的订单
	 * @param parameters
	 * @return
	 */
	Map<String,Object> addBusDriverOrder(Map<String, Object> parameters);

	/**
	 * 查询发布中心信息列表  司机  乘客 端
	 * @param userId
	 * @return
	 */
	Map<String, Object> publishOrderList(String userId);

	List<DriverOrderEntity> queryListByOrderType(Map<String, Object> map);

	/**
	 * 司机发布 匹配到的乘客信息
	 * @param userId
	 * @param orderType
	 * @param longtitude
	 * @param latitude
	 * @return
	 */
	Map<String, Object> matchPassengerOrderList(String userId,
			String orderType, String longtitude, String latitude);

	/**
	 * 我是司机的订单
	 * @param parameters
	 * @return
	 */
	Map<String, Object> getDriverOrderList(Map<String, Object> parameters);

	/**
	 * 司机订单详情
	 * @param parameters
	 * @return
	 */
	Map<String, Object> getDriverOrderDetail(Map<String, Object> parameters);
	
}