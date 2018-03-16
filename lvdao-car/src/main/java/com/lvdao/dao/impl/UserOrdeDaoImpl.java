package com.lvdao.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.dao.IUserOrderDao;
import com.lvdao.entity.UserOrderEntity;

@Repository("userOrdeDao")
public class UserOrdeDaoImpl extends BaseDao<UserOrderEntity> implements IUserOrderDao {
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private static final String NAMESPACE = "UserOrderEntity" + CommonConst.PUNCTUATION_DOT;

	@Override
	public List<UserOrderEntity> queryUserOredeId(Map<String, Object> map) {
		LOGGER.info("Entering UserOrdeDaoImplv queryUserOredeId Dao...");
		List<UserOrderEntity> list = this.getSqlSession().selectList(NAMESPACE + "queryUserOredeId", map);
		LOGGER.info("Exiting UserOrdeDaoImpl queryUserOredeId Dao...");
		return list;
	}
	
	/**
	 * 查找用户最新订单
	 * 
	 */

	@Override
	public List<UserOrderEntity> queryUserIdOredeTime(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList(NAMESPACE+"queryUserIdOredeTime",map);
	}

	@Override
	public Integer yesterdayVipCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Integer vipCount = this.getSqlSession().selectOne(NAMESPACE+"yesterdayVipCount", map);
		return vipCount;
	}
	
}
