package com.lvdao.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvdao.common.CommonConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.dao.IPictureGroupDao;
import com.lvdao.entity.PictureGroupEntity;
import com.lvdao.service.IPictureGroupService;

/**
 * 相册服务实现类
 * 
 * @author hx
 * @since 2016-11-03 18:27
 */
@Service("pictureGroupService")
public class PictureGroupServiceImpl implements IPictureGroupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PictureGroupServiceImpl.class);
	
	/**
	 * 图片相册Dao
	 */
	@Autowired
	private IPictureGroupDao pictureGroupDao;
	
	@Override
	public int insert(PictureGroupEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getPicGroupId())) {
			LOGGER.info("PictureGroupServiceImpl insert entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering PictureGroupServiceImpl insert...");
		int result = pictureGroupDao.insert(entity);
		LOGGER.info("Exiting PictureGroupServiceImpl insert...");
		return result;
	}
	
	@Override
	public int update(PictureGroupEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getPicGroupId())) {
			LOGGER.info("PictureGroupServiceImpl update entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering PictureGroupServiceImpl update...");
		int result = pictureGroupDao.update(entity);
		LOGGER.info("Exiting PictureGroupServiceImpl update...");
		return result;
	}
	
	@Override
	public int delete(PictureGroupEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getPicGroupId())) {
			LOGGER.info("PictureGroupServiceImpl delete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering PictureGroupServiceImpl delete...");
		int result = pictureGroupDao.delete(entity);
		LOGGER.info("Exiting PictureGroupServiceImpl delete...");
		return result;
	}

	@Override
	public List<PictureGroupEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("PictureGroupServiceImpl queryList entity is null.");
			return null;
		}
		LOGGER.info("Entering PictureServiceImpl queryList...");
		List<PictureGroupEntity> list = pictureGroupDao.queryList(map);
		LOGGER.info("Exiting PictureGroupServiceImpl queryList...");
		return list;
	}

	@Override
	public PageList<PictureGroupEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page || null == map || map.isEmpty()) {
			LOGGER.info("PictureGroupServiceImpl queryPage page or map is null.");
			return null;
		}
		LOGGER.info("Entering PictureGroupServiceImpl queryPage...");
		PageList<PictureGroupEntity> list = pictureGroupDao.queryPage(page, map);
		LOGGER.info("Exiting PictureGroupServiceImpl queryPage...");
		return list;
	}
	
	@Override
	public List<PictureGroupEntity> queryAll() {
		LOGGER.info("Entering PictureGroupServiceImpl queryAll...");
		List<PictureGroupEntity> list = pictureGroupDao.queryAll();
		LOGGER.info("Exiting PictureGroupServiceImpl queryAll...");
		return list;
	}

	@Override
	public int getMaxId() {
		return pictureGroupDao.getMaxId();
	}


}
