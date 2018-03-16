package com.lvdao.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.lvdao.common.CommonConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.dao.IUserDao;
import com.lvdao.entity.UserEntity;

@Repository("userDao")
public class UserDaoImpl extends BaseDao<UserEntity> implements IUserDao {

	private static Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	private static final String NAMESPACE = "UserEntity" + CommonConst.PUNCTUATION_DOT;
	
//	@Autowired
//	private MongoTemplate mongoTemplate;
	
	/**
	 * 查询所有的用户ID 用户名
	 * 
	 * @author zhxihu2008
	 * @since 2016-07-15 16:04
	 */
	@Override
	public List<UserEntity> queryAllUserIDUserName() {
		LOGGER.info("Entering queryAllUserIDUserName dao...");
		List<UserEntity> list = this.getSqlSession().selectList(NAMESPACE + "queryAllUserIDUserName");
		
		if(null == list || list.isEmpty()) {
			LOGGER.info("queryAllUserIDUserName list is null.");
			return null;
		}
		
		LOGGER.info("Exiting queryAllUserIDUserName dao...");
		return list;
	}

	@Override
	public UserEntity validateUser(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("validateUser Map is null.");
			return null;
		}
		LOGGER.info("Entering validateUser dao...");
		UserEntity entity = this.getSqlSession().selectOne(NAMESPACE + "validateUser", map);
		LOGGER.info("Exiting validateUser dao...");
		return entity;
	}

	@Override
	public List<UserEntity> queryUnactiveUser(String userName) {
		
		if(null == userName || userName.trim().equals(CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS)) {
			LOGGER.info("queryUnactiveUser dao userName is null.");
			return null;
		}
		
		LOGGER.info("Entering queryUnactiveUser...");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		map.put("userParentName", userName);
		List<UserEntity> list = this.getSqlSession().selectList(NAMESPACE + "queryUnactiveUser", map);
		LOGGER.info("Exiting queryUnactiveUser...");
		return list;
	}

	@Override
	public int deleteUser(UserEntity entity) {
		if(null == entity || StringUtils.isEmpty(entity.getUserName())) {
			LOGGER.info("deleteUser Dao entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering deleteUser Dao...");
		int result = this.getSqlSession().delete(NAMESPACE + "deleteUser", entity);
		LOGGER.info("Exiting deleteUser Dao...");
		return result;
	}

	@Override
	public int countUser(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("countUser Map is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering countUser dao...");
		int count = this.getSqlSession().selectOne(NAMESPACE + "count", map);
		LOGGER.info("Exiting countUser dao...");
		return count;
	}
	

	@Override
	public List<UserEntity> queryMemberNameList(Map<String, Object> map) {
		return this.getSqlSession().selectList(NAMESPACE + "queryMMPUserNameList", map);
	}

	@Override
	public List<UserEntity> queryListByName(String exportName) {
		LOGGER.info("Entering queryListByName dao...");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", exportName);
		map.put("userParentName", exportName);
		List<UserEntity> list = this.getSqlSession().selectList(NAMESPACE + "queryListByName", map);
		LOGGER.info("Exiting queryListByName dao...");
		return list;
	}

	@Override
	public int updateById(UserEntity entity) {
		LOGGER.info("Entering {0} updateById dao...");
		int result = this.getSqlSession().update(NAMESPACE + "updateById", entity);
		LOGGER.info("Exiting {0} updateById dao...");
	    return result;
	}
	
	/**
	 * 根据用户手机号编写用户信息接口byzhaoming
	 * @param entity
	 * @return
	 */
	@Override
	public int updateByUserName(UserEntity entity){
		LOGGER.info("Entering {0} updateByUserName dao...");
		int result = this.getSqlSession().update(NAMESPACE + "updateByUserName", entity);
		LOGGER.info("Exiting {0} updateByUserName dao...");
	    return result;
	}

//	@Override
//	public List<UserEntity> queryNearPeople(Map<String, Object> map, Page page) {
//		LOGGER.info("Entering queryNearPeople dao...");
//		//校验参数
//		if(map == null || page == null || map.isEmpty()) {
//			return null;
//		}
//		
//		//处理参数  获取经纬度范围
//		//最小纬度
//		Double minLat = (Double)map.get("minLat");
//		//最大纬度
//		Double maxLat = (Double)map.get("maxLat");
//		//最小经度
//		Double minLon = (Double)map.get("minLon");
//		//最大经度
//		Double maxLon = (Double)map.get("maxLon");
//		
//		//skip
//		int pageStartIndex = page.getStartIndex();
//		//limit
//		int pageSize = page.getPageSize();
//		
//		//先从mongo中查
//		Query query = new Query();
//		query.addCriteria(new Criteria().andOperator(Criteria.where("active").is(true),
//													 Criteria.where("userLongitude").gt(minLon),
//													 Criteria.where("userLongitude").lt(maxLon),
//													 Criteria.where("userLatitude").gt(minLat),
//													 Criteria.where("userLatitude").lt(maxLat)));
//		query.skip(pageStartIndex).limit(pageSize);
//		List<String> properties = new ArrayList<String>();
//		properties.add("userLongitude");
//		properties.add("userLatitude");
//		query.with(new Sort(Direction.DESC, properties));
//		List<UserEntity> list = mongoTemplate.find(query, UserEntity.class, CommonConst.REDIS_MONGO_KEY_USER);
//		
//		//若不存在查询mysql
//		if(list == null || list.size() == CommonConst.DIGIT_ZERO) {
//			map.put("startIndex", Integer.valueOf(page.getStartIndex()));
//			map.put("pageSize", Integer.valueOf(page.getPageSize()));
//			list = this.getSqlSession().selectList(NAMESPACE + "queryNearPeople", map);
//		}
//		LOGGER.info("Exiting queryNearPeople dao...");
//		return list;
//	}

	@Override
	public PageList<UserEntity> queryUserByUserIdNumNotNull(Page page) {
		int totalCount = ((Integer)this.getSqlSession().selectOne("queryUserByUserIdNumNotNullCount")).intValue();
		page.setTotalCount(totalCount);
		
		Map<String, Object>	map = new HashMap<String, Object>();
		map.put("startIndex", Integer.valueOf(page.getStartIndex()));
		map.put("pageSize", Integer.valueOf(page.getPageSize()));
		List<UserEntity> list = this.getSqlSession().selectList("queryUserByUserIdNumNotNullList", map);
		PageList<UserEntity> pagingList = new PageList<UserEntity>(list, page);
		return pagingList;
	}

	@Override
	public int updateUserByUserId(UserEntity entity) {
		LOGGER.info("Entering {0} updateUserByUserId dao...");
		int result = this.getSqlSession().update(NAMESPACE + "updateUserByUserId", entity);
		LOGGER.info("Exiting {0} updateUserByUserId dao...");
	    return result;
	}

	@Override
	public int updateUserByUserName(UserEntity entity) {
		LOGGER.info("Entering {0} updateUserByUserName dao...");
		int result = this.getSqlSession().update(NAMESPACE + "updateUserByUserName", entity);
		LOGGER.info("Exiting {0} updateUserByUserName dao...");
	    return result;
	}

	@Override
	public Map<String, Object> queryRoleCount(Map<String, Object> map) {
		LOGGER.info("Entering {0} queryRoleCount dao...");
		Map<String, Object> result = this.getSqlSession().selectOne(NAMESPACE + "queryRoleCount", map);
		LOGGER.info("Exiting {0} queryRoleCount dao...");
		return result;
	}

	@Override
	public PageList<UserEntity> queryInfoByParent(Map<String, Object> map, Page page) {
		LOGGER.info("Entering {0} queryInfoByParent dao...");
		
		int totalCount = ((Integer)this.getSqlSession().selectOne(NAMESPACE + "queryInfoByParentCount", map)).intValue();
		page.setTotalCount(totalCount);
		
		if(null == map) {
			map = new HashMap<String, Object>();
		}

		map.put("startIndex", Integer.valueOf(page.getStartIndex()));
		map.put("pageSize", Integer.valueOf(page.getPageSize()));

		List<UserEntity> list = this.getSqlSession().selectList(NAMESPACE + "queryInfoByParent", map);

		PageList<UserEntity> pagingList = new PageList<UserEntity>(list, page);
		
		LOGGER.info("Exiting {0} queryInfoByParent dao...");
		
		return pagingList;
	}

	@Override
	public Integer vipCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne(NAMESPACE+"vipCount", map);
	}
	
	@Override
	public UserEntity getUserEntityByUserId(String userId) {
		LOGGER.info("Entering getUserEntityByUserId dao...");
		UserEntity entity = this.getSqlSession().selectOne(NAMESPACE + "getUserEntityByUserId", userId);
		LOGGER.info("Exiting getUserEntityByUserId dao...");
		return entity;
	}

}
