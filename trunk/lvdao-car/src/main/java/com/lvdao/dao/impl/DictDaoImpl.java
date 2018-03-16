package com.lvdao.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.dao.IDictDao;
import com.lvdao.entity.DictEntity;

@Repository("dictDao")
public class DictDaoImpl extends BaseDao<DictEntity> implements IDictDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DictDaoImpl.class);
	private static final String NAMESPACE = "DictEntity" + CommonConst.PUNCTUATION_DOT;

	@Override
	public DictEntity queryDitcValueByDictId(String dictId) {
		LOGGER.info("Entering DictDaoImpl queryDitcValueByDictId ...");
		DictEntity dict = this.getSqlSession().selectOne(NAMESPACE + "queryDitcValueByDictId", dictId);
		LOGGER.info("Exiting DictDaoImpl queryDitcValueByDictId ...");
		return dict;
	}

	@Override
	public List<DictEntity> queryDictGroupList(Map<String, Object> map) {
		LOGGER.info("Entering DictDaoImpl queryDictGroupList ...");
		List<DictEntity> dictList = this.getSqlSession().selectList(NAMESPACE + "queryDictGroupList", map);
		LOGGER.info("Exiting DictDaoImpl queryDictGroupList ...");
		return dictList;
	}

}
