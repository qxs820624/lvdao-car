package com.lvdao.dao.impl;

import org.springframework.stereotype.Repository;

import com.lvdao.dao.IPictureGroupDao;
import com.lvdao.entity.PictureGroupEntity;

@Repository("pictureGroupDao")
public class PictureGroupDaoImpl extends BaseDao<PictureGroupEntity> implements IPictureGroupDao {

}
