package com.lvdao.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.lvdao.common.BasicResponse;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.entity.UserEntity;

public interface IUserService extends IBaseService<UserEntity> {
	
    List<UserEntity> queryAllUserIDUserName();
    UserEntity validateUser(Map<String, Object> map);
    int deleteUser(UserEntity entity);
    int countUser(Map<String, Object> map);
	List<UserEntity> queryListByName(String exportName);
	List<UserEntity> queryUnactiveUser(String userName);
	int saveUserToken(String userName, String token);
	String getUserNameByToken(String token);
	
	/**
	 * App端登錄
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	Map<String, Object> login(String userName, String password,String userRole);
	
	/**
	 * App版註冊
	 * 
	 * @param mobile
	 * @param password
	 * @return
	 */
	Map<String, Object> register(String mobile, String userRole,String password);
	
	/**
	 * 发送短信验证码  同时校验手机号
	 * @param mobile 手机号
	 * @param content 短信内容
	 * @param mobileVilable 是否需要验证手机存在  （用于忘记密码之类的）true表示需要验证手机是否已注册  false表示不需要
	 * @return  0失败 1成功  2手机号已注册
	 */
	Map<String,Object> sendSmsCode(String mobile, String content, boolean mobileAvailable);
	
	/**
	 * 根据用户Id获取用户姓名 
	 * @author zhaoming
	 * @since 2016-09-09 15:47
	 */
	String queryUserNameByUserId(String userId);
	
	/**
	 * 根据用户Id获取对应得对象
	 * byzhaoming
	 * @param userId
	 * @return
	 */
	UserEntity getUserEntityByUserId(String userId);
	
	/**
	 * 个人资料展示信息
	 * by zhaoming
	 * @param userId
	 * @return
	 */
//	BasicResponse getUserInfoByUserId(String userId);
	
	/**
	 * 重置密码
	 * @param userId
	 * @param password
	 * @return
	 */
	BasicResponse updatePassword(String userId, String password);
	
	/**
	 * App端第三方登錄
	 * 
	 * @param openId
	 * @param imageUrl
	 * @return
	 */
	Map<String, Object> thirdLogin(Map<String, Object> parameters);
	
	/**
	 * 根据用户手机号码修改用户信息
	 * @param userEntity
	 * @return
	 */
	BasicResponse updateUserInfoByUserName(UserEntity userEntity);
	
	/**
	 * 附近的人
	 * @param map 经纬度的map
	 * @param page 分页信息
	 * @return
	 */
	List<UserEntity> queryNearPeople(Map<String, Object> map, Page page);
	
	/**
	 * 退出登录(删除token)
	 * @param token
	 * @return
	 */
	Map<String, Object> exitToLogin(String token);
	
	/**
	 * 上传头像 byzhaoming
	 * @param inputStream
	 * @param picName
	 * @param picType
	 * @param userId
	 * @return
	 */
//	BasicResponse uploadHeadPortrait(InputStream inputStream, String picName, String picType, String userId);
	
	/**
	 * 修改头像
	 * by zhaoming
	 * @param userId
	 * @param inputStream
	 * @return
	 */
	BasicResponse updateHeadPortrait(String userId, InputStream inputStream, String picName, String picType);
	
	/**
	 * 查看别人信息
	 * @param myUserId
	 * @param otherUserId
	 * @return
	 */
	BasicResponse getOtherUserInfo(String myUserId, String otherUserId);
	
	/**
	 * 获取userId gender 的map
	 * @author hexiang
	 * @return
	 */
	Map<String, String> getUserGenderMap();
	
	/**
	 * 推荐人注册
	 * @param param包括
	 * 		  userMobile 用户手机 必填
	 * 		  userPassword 用户密码  必填
	 * 		  userparentId 用户推荐人Id 必填
	 * @return
	 */
	Map<String, Object> registerUserByParentId(Map<String, Object> param);
	
	/**
	 * 保存用户关系
	 * @author yzp
	 * @since 2016-10-25
	 * @return
	 */
	Map<String, String> saveUserRelation(String parentMobile, String mobile);

	/**
	 * 每天分红
	 * @author yzp
	 * @since 2016-10-25
	 * @return
	 */
	boolean bonus();
	
	/**
	 * 查询所有身份证号码存在且身份证验证未通过的有效用户
	 * @param page 
	 * @return
	 */
	PageList<UserEntity> queryUserByUserIdNumNotNull(Page page);
	
	int updateUserByUserId(UserEntity entity);
	
	int updateUserByUserName(UserEntity entity);
	
	UserEntity queryByUserName(String userName);
	
	/**
	 * 统计旗下各角色的用户
	 * @param param
	 * @return
	 */
	Map<String, Object> queryRoleCount(Map<String, Object> param);
	
	/**
	 * 根据推荐人查询用户信息
	 * 
	 * @author zhangjianrong
	 * @since 2016-12-14
	 * @param map
	 * @param page
	 * @return
	 */
	PageList<UserEntity> queryInfoByParent(Map<String, Object> map, Page page);
	
	/**
	 * 根据验证码登录
	 * @param userName
	 * @return
	 */
	Map<String, Object> loginByCode(String userName,String userRole);
	
	/**
	 * 根据用户ID修改密码
	 * @param userId
	 * @param password
	 * @return
	 */
	Map<String, Object> updateUserPasswordByUserId(String userId,
			String password,String passwordSure);
	
	/**
	 * 个人资料提交
	 * @param parameters
	 * @return
	 */
	Map<String, Object> updateUserByUserId(Map<String, Object> parameters);
	/**
	 * 根据用户ID查找用户账单
	 * @author gxx
	 * @param userId
	 * @return
	 */
	Map<String, Object> queryUserByBalance(String userId);
	/**
	 * 根据用户ID查询账户交易记录
	 * @author gxx
	 * @param userId
	 * @param page
	 * @return
	 */
	Map<Object, Object> queryDealLogMessagePage(Page page,String userId);
	Integer vipCount(Map<String, Object> map);
}
