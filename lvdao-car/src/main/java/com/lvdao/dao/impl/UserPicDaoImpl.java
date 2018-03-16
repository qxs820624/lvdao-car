package com.lvdao.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.dao.IUserPicDao;
import com.lvdao.entity.UserPicEntity;

@Repository("userPicDao")
public class UserPicDaoImpl extends BaseDao<UserPicEntity> implements IUserPicDao {

	private static Logger LOGGER = LoggerFactory.getLogger(UserPicDaoImpl.class);
	private static final String NAMESPACE = "UserPicEntity" + CommonConst.PUNCTUATION_DOT;
	
	/**
	 * 计算图片数量
	 * 
	 * @author zhaomingyj
	 * @since 2016-09-10 10:51
	 */
	@Override
	public int count(Map<String, Object> map) {
		LOGGER.info("Entering UserPicDaoImpl count service...");
		int result = this.getSqlSession().selectOne(NAMESPACE + "updateByUserName", map);
		LOGGER.info("Entering UserPicDaoImpl count service...");
		return result;
	}
	
	/**
	 * 根据用户手机号编写用户信息接口byzhaoming
	 * @param entity
	 * @return
	 */
	@Override
	public int updateUserPicByUserId(UserPicEntity entity){
		LOGGER.info("Entering {0} updateByUserName dao...");
		int result = this.getSqlSession().update(NAMESPACE + "updateUserPicByUserId", entity);
		LOGGER.info("Exiting {0} updateUserPicByUserId dao...");
	    return result;
	}

	@Override
	public int insertList(List<UserPicEntity> entityList) {
		LOGGER.info("Entering insertList dao...");
		int result = this.getSqlSession().insert(NAMESPACE + "insertList", entityList);
		LOGGER.info("Exiting insertList dao...");
	    return result;
	}

	@Override
	public int batchDelete(List<UserPicEntity> entityList) {
		LOGGER.info("Entering batchDelete dao...");
		int result = this.getSqlSession().delete(NAMESPACE + "batchDelete", entityList);
		LOGGER.info("Exiting batchDelete dao...");
	    return result;
	}

	/* (non-Javadoc)
	 * @see com.ssb.user.dao.IUserPicDao#getMaxGroupId()
	 */
	@Override
	public int getMaxGroupId() {
		int result = this.getSqlSession().selectOne(NAMESPACE + "getMaxGroupId");
		return result;
	}
	
}
