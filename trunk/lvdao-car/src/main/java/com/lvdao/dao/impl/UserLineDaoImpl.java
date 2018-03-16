package com.lvdao.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.dao.IUserLineDao;
import com.lvdao.entity.UserLineEntity;

@Repository("userLineDao")
public class UserLineDaoImpl extends BaseDao<UserLineEntity> implements IUserLineDao {
	
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private static final String NAMESPACE = "UserLineEntity" + CommonConst.PUNCTUATION_DOT;

	@Override
	public List<UserLineEntity> queryFrequencyLine(Map<String, Object> map) {
		LOGGER.info("Entering UserLineDaoImpl queryFrequencyLine Dao...");
		List<UserLineEntity> list = this.getSqlSession().selectList(NAMESPACE + "queryFrequencyLine", map);
		LOGGER.info("Exiting UserLineDaoImpl queryFrequencyLine Dao...");
		return list;
	}
	
}
