package com.lvdao.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.dao.IDriverOrderDao;
import com.lvdao.entity.DriverOrderEntity;

@Repository("driverOrderDao")
public class DriverOrderDaoImpl extends BaseDao<DriverOrderEntity> implements IDriverOrderDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverOrderDaoImpl.class);
	private static final String NAMESPACE = "DriverOrderEntity" + CommonConst.PUNCTUATION_DOT;

	@Override
	public List<DriverOrderEntity> queryListByOrderType(Map<String, Object> map) {
		LOGGER.info("Entering DriverOrderDaoImpl queryListByOrderType ...");
		//校验参数
		if(map == null || map.isEmpty()) {
			return null;
		}
		List<DriverOrderEntity> list = this.getSqlSession().selectList(NAMESPACE + "queryListByOrderType", map);
		LOGGER.info("Exiting DriverOrderDaoImpl queryListByOrderType ...");
		return list;
	}
	
}
