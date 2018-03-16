package com.lvdao.dao;

import java.util.List;
import java.util.Map;

import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.entity.UserEntity;

public interface IUserDao extends IBaseDao<UserEntity> {

    List<UserEntity> queryAllUserIDUserName();
    UserEntity validateUser(Map<String, Object> map);
    List<UserEntity> queryUnactiveUser(String userName);
    int deleteUser(UserEntity entity);
    int updateById(UserEntity entity);
    int countUser(Map<String, Object> map);
    List<UserEntity> queryMemberNameList(Map<String, Object> map);
	List<UserEntity> queryListByName(String exportName);
	int updateByUserName(UserEntity entity);
//	List<UserEntity> queryNearPeople(Map<String, Object> map, Page page);
	//查询用户身份证号码存在且身份证审核未通过的用户信息
	PageList<UserEntity> queryUserByUserIdNumNotNull(Page page);
	//根据用户ID修改用户信息
	int updateUserByUserId(UserEntity entity);	
	int updateUserByUserName(UserEntity entity);	
	Map<String,Object> queryRoleCount(Map<String, Object> map);
	//根据推荐人信息查询
	PageList<UserEntity> queryInfoByParent(Map<String, Object> map, Page page);
	Integer vipCount(Map<String, Object> map);
	UserEntity getUserEntityByUserId(String userId);
}
