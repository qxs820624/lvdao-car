package com.lvdao.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.dao.IPictureDao;
import com.lvdao.entity.PictureEntity;

@Repository("pictureDao")
public class PictureDaoImpl extends BaseDao<PictureEntity> implements IPictureDao {
	private static Logger LOGGER = LoggerFactory.getLogger(PictureDaoImpl.class);
	private static final String NAMESPACE = "PictureEntity" + CommonConst.PUNCTUATION_DOT;

	/* (non-Javadoc)
	 * @see com.ssb.media.dao.IPictureDao#batchDelete(java.util.List)
	 */
	@Override
	public int batchDelete(List<PictureEntity> list) {
		LOGGER.info("Entering batchDelete dao...");
		int result = this.getSqlSession().delete(NAMESPACE + "batchDelete", list);
		LOGGER.info("Exiting batchDelete dao...");
	    return result;
	}

}
