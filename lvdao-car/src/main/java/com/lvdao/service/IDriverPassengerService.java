package com.lvdao.service;

import java.util.Map;

import com.lvdao.entity.DriverPassengerEntity;

public interface IDriverPassengerService extends IBaseService<DriverPassengerEntity> {
	
	/**
	 * t_passenger_order(表1)  t_driver_order(表2)  t_passenger_driver(表3)的ORDER_STATUS(订单状态(""表示未匹配,0表示待出发,1表示已完成,2表示已取消))
	 * 状态：
	 * 1.乘客发布需求 新增表1时该字段为空（不需要调接口）
	 * 2.司机发布需求 新增表2时该字段为空 （不需要调接口）
	 * 3.匹配后 表1 表2状态改为0（待出发），新增表3状态为0（待出发）
	 * 4.司机点击完成 三张表状态都改为已完成
	 * 5.乘客取消订单 表1，表3状态都改为已取消；表2状态改为"";
	 * 
	 * @param passengerOrderId 乘客订单
	 * @param driverOrderId 司机订单
	 * @param type 状态
	 * @return
	 */
	Map<String, Object> updateOrderStatus(String passengerOrderId, String driverOrderId, String type);

	/**
	 * 司机接单
	 * 
	 * @param parameters
	 * @return
	 */
	Map<String, Object> receiveOrder(Map<String, Object> parameters);

}
