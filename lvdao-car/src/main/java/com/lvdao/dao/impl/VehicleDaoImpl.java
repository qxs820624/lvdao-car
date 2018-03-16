package com.lvdao.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.dao.IVehicleDao;
import com.lvdao.entity.VehicleEntity;

@Repository("vehicleDao")
public class VehicleDaoImpl extends BaseDao<VehicleEntity> implements IVehicleDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleDaoImpl.class);
	private static final String NAMESPACE = "VehicleEntity" + CommonConst.PUNCTUATION_DOT;

	@Override
	public List<VehicleEntity> findNearDriverUser(Map<String, Object> map, Page page) {
		LOGGER.info("Entering VehicleDaoImpl findNearDriverUser ...");
		//校验参数
		if(map == null || page == null || map.isEmpty()) {
			return null;
		}
		map.put("startIndex", Integer.valueOf(page.getStartIndex()));
		map.put("pageSize", Integer.valueOf(page.getPageSize()));
		List<VehicleEntity> list = this.getSqlSession().selectList(NAMESPACE + "findNearDriverUser", map);
		LOGGER.info("Exiting VehicleDaoImpl findNearDriverUser ...");
		return list;
	}

}
