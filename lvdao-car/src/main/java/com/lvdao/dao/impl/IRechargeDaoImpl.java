package com.lvdao.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.dao.IRechargeDao;
import com.lvdao.entity.RechargeEntity;

@Repository("rechargeDao")
public class IRechargeDaoImpl extends BaseDao<RechargeEntity> implements IRechargeDao{

	private static Logger LOGGER = LoggerFactory.getLogger(IRechargeDaoImpl.class);
	private static final String NAMESPACE = "RechargeEntity" + CommonConst.PUNCTUATION_DOT;
	
	
	@Override
	public int updateByRechargeOrderid(RechargeEntity entity) {
		LOGGER.info("Entering {0} updateByRechargeOrderid dao...");
		int result = this.getSqlSession().update(NAMESPACE + "updateByRechargeOrderid", entity);
		LOGGER.info("Exiting {0} updateByRechargeOrderid dao...");
	    return result;
	}
}
