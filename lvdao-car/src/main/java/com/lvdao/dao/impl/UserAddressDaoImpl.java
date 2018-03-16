package com.lvdao.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.dao.IUserAddressDao;
import com.lvdao.entity.UserAddressEntity;

@Repository("userAddressDao")
public class UserAddressDaoImpl extends BaseDao<UserAddressEntity> implements IUserAddressDao {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private static final String NAMESPACE = "UserAddressEntity" + CommonConst.PUNCTUATION_DOT;

	@Override
	public List<UserAddressEntity> queryFrequencyAddress(Map<String, Object> map) {
		LOGGER.info("Entering UserAddressDaoImplv queryFrequencyAddress Dao...");
		List<UserAddressEntity> list = this.getSqlSession().selectList(NAMESPACE + "queryFrequencyAddress", map);
		LOGGER.info("Exiting UserAddressDaoImpl queryFrequencyAddress Dao...");
		return list;
	}
	
}
