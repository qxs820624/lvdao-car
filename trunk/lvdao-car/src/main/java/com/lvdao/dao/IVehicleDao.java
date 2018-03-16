package com.lvdao.dao;

import java.util.List;
import java.util.Map;

import com.lvdao.common.pagination.Page;
import com.lvdao.entity.VehicleEntity;

public interface IVehicleDao extends IBaseDao<VehicleEntity>{

	/**
	 * 查询附近的车辆
	 * 
	 * @param map
	 * @param page 
	 * @return
	 */
	List<VehicleEntity> findNearDriverUser(Map<String, Object> map, Page page);

}