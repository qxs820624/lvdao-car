package com.lvdao.service;

import java.util.List;
import java.util.Map;

import com.lvdao.common.pagination.Page;
import com.lvdao.entity.PassengerOrderEntity;

public interface IPassengerOrderService extends IBaseService<PassengerOrderEntity>{
	
	/**
	 * 添加bus服务
	 * @param parameters
	 * @return
	 */
	Map<String,Object> busPassengerAddOrder(Map<String, Object> parameters);

	/**
	 * 查询附近的乘客
	 * 
	 * @param map
	 * @param page
	 * @return
	 */
	List<PassengerOrderEntity> findNearPassengerUser(Map<String, Object> map, Page page);

	/**
	 * 
	 * 发布中心 乘客  巴士 货车
	 * @param map
	 * @return
	 */
	List<PassengerOrderEntity> queryListByOrderType(Map<String, Object> map);

	
    /**
     * 发布中心 乘客发布服务 匹配到的司机列表
     * @param userId
     * @param orderType
     * @param longtitude
     * @param latitude
     * @return
     */
	Map<String, Object> matchDrivaerOrderList(String userId, String orderType,
			String longtitude, String latitude);
	
	public Map<String, Object> releaseFreightDemand(Map<String, Object> parameters);

	/**
	 * 我是乘客 订单 处理返回的结果 参数
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryPageListRet(Page page,
			Map<String, Object> map);

	/**
	 * 我是乘客订单详情
	 * @param parameters
	 * @return
	 */
	Map<String, Object> getPassengerOrderDetail(Map<String, Object> parameters);

}