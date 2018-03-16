package com.lvdao.dao;

import java.util.List;
import java.util.Map;

import com.lvdao.common.pagination.Page;
import com.lvdao.entity.PassengerOrderEntity;

public interface IPassengerOrderDao extends IBaseDao<PassengerOrderEntity>{

	/**
	 * 附近的乘客
	 * 
	 * @param map
	 * @param page
	 * @return
	 */
	List<PassengerOrderEntity> findNearPassengerUser(Map<String, Object> map, Page page);

	/**
	 * 发布中心  货车  巴士  小汽车不需要在发布中心
	 * @param map
	 * @return
	 */
	List<PassengerOrderEntity> queryListByOrderType(Map<String, Object> map);

}