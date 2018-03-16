package com.lvdao.service;

import java.util.List;
import java.util.Map;

import com.lvdao.common.pagination.Page;
import com.lvdao.entity.VehicleEntity;

public interface IVehicleService extends IBaseService<VehicleEntity>{

	/**
	 * 
	 * @param map
	 * @param page 
	 */
	List<VehicleEntity> findNearDriverUser(Map<String, Object> map, Page page);
	
	/**
	 * 添加车辆信息
	 * @param parameters
	 * @return
	 */
	Map<String, Object> addVehicle(Map<String, Object> parameters);

	/**
	 * 查询用户的车辆信息
	 * @param parameters
	 * @return
	 */
	List<VehicleEntity> queryVehicleAndPhotoInfo(Map<String, Object> parameters);
	
	/**
	 * 删除车辆信息
	 * @param parameters
	 * @return
	 */
	Map<String, Object> deleteVehicle(Map<String, Object> parameters);
}