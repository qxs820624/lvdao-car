package com.lvdao.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.dao.IPassengerOrderDao;
import com.lvdao.entity.PassengerOrderEntity;

@Repository("passengerOrderDao")
public class PassengerOrderDaoImpl extends BaseDao<PassengerOrderEntity> implements IPassengerOrderDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleDaoImpl.class);
	private static final String NAMESPACE = "PassengerOrderEntity" + CommonConst.PUNCTUATION_DOT;

	@Override
	public List<PassengerOrderEntity> findNearPassengerUser(Map<String, Object> map, Page page) {
		LOGGER.info("Entering PassengerOrderDaoImpl findNearDriverUser ...");
		//校验参数
		if(map == null || page == null || map.isEmpty()) {
			return null;
		}
		map.put("startIndex", Integer.valueOf(page.getStartIndex()));
		map.put("pageSize", Integer.valueOf(page.getPageSize()));
		List<PassengerOrderEntity> list = this.getSqlSession().selectList(NAMESPACE + "findNearPassengerUser", map);
		LOGGER.info("Exiting PassengerOrderDaoImpl findNearPassengerUser ...");
		return list;
	}
	
	
	@Override
	public List<PassengerOrderEntity> queryListByOrderType(Map<String, Object> map) {
		LOGGER.info("Entering PassengerOrderDaoImpl queryListByOrderType ...");
		//校验参数
		if(map == null || map.isEmpty()) {
			return null;
		}
		List<PassengerOrderEntity> list = this.getSqlSession().selectList(NAMESPACE + "queryListByOrderType", map);
		LOGGER.info("Exiting PassengerOrderDaoImpl queryListByOrderType ...");
		return list;
	}

}
