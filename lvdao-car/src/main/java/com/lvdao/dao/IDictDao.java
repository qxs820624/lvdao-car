package com.lvdao.dao;

import java.util.List;
import java.util.Map;

import com.lvdao.entity.DictEntity;

public interface IDictDao extends IBaseDao<DictEntity> {

	DictEntity queryDitcValueByDictId(String dictId);

	List<DictEntity> queryDictGroupList(Map<String, Object> map);
    
}