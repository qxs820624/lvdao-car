package com.lvdao.dao;

import java.util.List;
import java.util.Map;

import com.lvdao.entity.DriverOrderEntity;

public interface IDriverOrderDao extends IBaseDao<DriverOrderEntity>{

	/**
	 * 司机发布中心  巴士  货车
	 * @param map
	 * @return
	 */
	List<DriverOrderEntity> queryListByOrderType(Map<String, Object> map);

}