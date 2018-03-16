package com.lvdao.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.dao.ISignDao;
import com.lvdao.entity.SignEntity;

@Repository("signDao")
public class ISignDaoImpl extends BaseDao<SignEntity> implements ISignDao{

	private static Logger LOGGER = LoggerFactory.getLogger(ISignDaoImpl.class);
	private static final String NAMESPACE = "SignEntity" + CommonConst.PUNCTUATION_DOT;
	
	
	@Override
	public int updateByRechargeOrderid(SignEntity entity) {
		LOGGER.info("Entering {0} updateByRechargeOrderid dao...");
		int result = this.getSqlSession().update(NAMESPACE + "updateByRechargeOrderid", entity);
		LOGGER.info("Exiting {0} updateByRechargeOrderid dao...");
	    return result;
	}
}
