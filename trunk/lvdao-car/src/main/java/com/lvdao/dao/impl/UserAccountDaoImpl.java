package com.lvdao.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.dao.IUserAccountDao;
import com.lvdao.entity.UserAccountEntity;

@Repository("userAccountDao")
public class UserAccountDaoImpl extends BaseDao<UserAccountEntity> implements IUserAccountDao {

	private static Logger LOGGER = LoggerFactory.getLogger(UserAccountDaoImpl.class);
	private static final String NAMESPACE = "UserAccountEntity" + CommonConst.PUNCTUATION_DOT;
	
	@Override
	public int updateById(UserAccountEntity entity) {
		LOGGER.info("Entering {0} updateById dao...");
		int result = this.getSqlSession().update(NAMESPACE + "updateById", entity);
		LOGGER.info("Exiting {0} updateById dao...");
	    return result;
	}

	@Override
	public int updateAccountAmount(UserAccountEntity entity) {
		LOGGER.info("Entering {0} updateAccountAmount dao...");
		int result = this.getSqlSession().update(NAMESPACE + "updateAccountAmount", entity);
		LOGGER.info("Exiting {0} updateAccountAmount dao...");
	    return result;
	}

}
