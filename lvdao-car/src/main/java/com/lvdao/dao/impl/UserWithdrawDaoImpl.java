package com.lvdao.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.dao.IUserWithdrawDao;
import com.lvdao.entity.UserWithdrawEntity;

@Repository
public class UserWithdrawDaoImpl extends BaseDao<UserWithdrawEntity> implements IUserWithdrawDao {

	private static Logger LOGGER = LoggerFactory.getLogger(UserWithdrawDaoImpl.class);
	private static final String NAMESPACE = "UserWithdrawEntity" + CommonConst.PUNCTUATION_DOT;
	
	@Override
	public List<UserWithdrawEntity> queryBatchList(List<String> ids) {
		LOGGER.info("Entering {0} queryBatchList dao...");
		if(ids == null) {
			return null;
		}
		List<UserWithdrawEntity> userWithdrawList = this.getSqlSession().selectList(NAMESPACE + "queryBatchList", ids);
		if(userWithdrawList == null || userWithdrawList.isEmpty()) {
			return null;
		}
		LOGGER.info("Exiting {0} queryBatchList dao...");
		return userWithdrawList;
	}
}
