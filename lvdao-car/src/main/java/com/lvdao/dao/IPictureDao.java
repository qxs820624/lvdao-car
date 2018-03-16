package com.lvdao.dao;

import java.util.List;

import com.lvdao.entity.PictureEntity;

public interface IPictureDao extends IBaseDao<PictureEntity> {

	/**
	 * @param list
	 * @return
	 */
	int batchDelete(List<PictureEntity> list);
    
}