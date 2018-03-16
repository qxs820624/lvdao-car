package com.lvdao.service;

import java.util.List;

import com.lvdao.entity.PictureEntity;

public interface IPictureService extends IBaseService<PictureEntity> {
	
	int batchDelete(List<PictureEntity> list);
	
}