package com.lvdao.service;

import java.util.List;
import java.util.Map;

import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.entity.BonusReturnEntity;

public interface IBonusReturnService{
	int insert(BonusReturnEntity entity);
	int update(BonusReturnEntity entity);
	List<BonusReturnEntity> queryList(Map<String, Object> map);
	PageList<BonusReturnEntity> queryPage(Page page, Map<String, Object> map);
	List<BonusReturnEntity> queryAll();
}