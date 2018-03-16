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
import com.lvdao.dao.IPictureDao;
import com.lvdao.entity.PictureEntity;
import com.lvdao.service.IPictureService;

/**
 * 图片存储服务实现类
 * 
 * @author hx
 * @since 2016-07-27 10:24
 */
@Service("pictureService")
public class PictureServiceImpl implements IPictureService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PictureServiceImpl.class);
	
	/**
	 * 图片Dao
	 */
	@Autowired
	private IPictureDao pictureDao;
	
	@Override
	public int insert(PictureEntity entity) {
		if(null == entity) {
			LOGGER.info("PictureServiceImpl insert entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering PictureServiceImpl insert...");
		int result = pictureDao.insert(entity);
		LOGGER.info("Exiting PictureServiceImpl insert...");
		return result;
	}
	
	@Override
	public int update(PictureEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getPicName())) {
			LOGGER.info("PictureServiceImpl update entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering PictureServiceImpl update...");
		int result = pictureDao.update(entity);
		LOGGER.info("Exiting PictureServiceImpl update...");
		return result;
	}
	
	@Override
	public int delete(PictureEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getPicId())) {
			LOGGER.info("PictureServiceImpl delete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering PictureServiceImpl delete...");
		int result = pictureDao.delete(entity);
		LOGGER.info("Exiting PictureServiceImpl delete...");
		return result;
	}

	@Override
	public List<PictureEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("PictureServiceImpl queryList entity is null.");
			return null;
		}
		LOGGER.info("Entering PictureServiceImpl queryList...");
		List<PictureEntity> list = pictureDao.queryList(map);
		LOGGER.info("Exiting PictureServiceImpl queryList...");
		return list;
	}

	@Override
	public PageList<PictureEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page || null == map || map.isEmpty()) {
			LOGGER.info("PictureServiceImpl queryPage page or map is null.");
			return null;
		}
		LOGGER.info("Entering PictureServiceImpl queryPage...");
		PageList<PictureEntity> list = pictureDao.queryPage(page, map);
		LOGGER.info("Exiting PictureServiceImpl queryPage...");
		return list;
	}
	
	@Override
	public List<PictureEntity> queryAll() {
		LOGGER.info("Entering PictureServiceImpl queryAll...");
		List<PictureEntity> list = pictureDao.queryAll();
		LOGGER.info("Exiting PictureServiceImpl queryAll...");
		return list;
	}


	@Override
	public int getMaxId() {
		return pictureDao.getMaxId();
	}

	@Override
	public int batchDelete(List<PictureEntity> list) {
		if(null == list || list.size() == CommonConst.DIGIT_ZERO) {
			LOGGER.info("PictureServiceImpl batchDelete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering PictureServiceImpl batchDelete...");
		int result = pictureDao.batchDelete(list);
		LOGGER.info("Exiting PictureServiceImpl batchDelete...");
		return result;
	}

}
