package com.lvdao.service.impl;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.lvdao.common.BasicResponse;
import com.lvdao.common.CommonConst;
import com.lvdao.common.MessageConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.common.util.AliyunSMSUtil;
import com.lvdao.common.util.BaiduMapUtils;
import com.lvdao.common.util.ChatIMUtil;
import com.lvdao.common.util.StringUtil;
import com.lvdao.dao.IAccountDao;
import com.lvdao.dao.IUserDao;
import com.lvdao.entity.AccountEntity;
import com.lvdao.entity.DealLogEntity;
import com.lvdao.entity.UserAccountEntity;
import com.lvdao.entity.UserEntity;
import com.lvdao.entity.UserPicEntity;
import com.lvdao.entity.UserRoleEntity;
import com.lvdao.entity.VehicleEntity;
import com.lvdao.service.IDealLogService;
import com.lvdao.service.IDictService;
import com.lvdao.service.IUserAccountService;
import com.lvdao.service.IUserOrderService;
import com.lvdao.service.IUserPicService;
import com.lvdao.service.IUserRoleService;
import com.lvdao.service.IUserService;
import com.lvdao.service.IUserWithdrawService;
import com.lvdao.service.IVehicleService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	//默认头像URL
	private static final String DEFAULT_HERD_IMG = "http://motian-oss.oss-cn-shenzhen.aliyuncs.com/alioss_52049212-4741-460c-9cd9-29cc86d62e12.png";
	
	@SuppressWarnings("unused")
	private static final String DEFAULT_HERD_IMG_NAME = "alioss_52049212-4741-460c-9cd9-29cc86d62e12.png";
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserPicService userPicService;

	@Autowired
	private IUserRoleService userRoleService;
	
	@Autowired
	private IVehicleService vehicleService;// 车辆操作的service
	
	@Autowired
	private IUserAccountService userAccountService;// 用户账单操作
	
	@Autowired
	private IDealLogService DealLogService;// 交易日志操作
	
	@Autowired
	private IUserWithdrawService userWithdrawService;//提现操作
	
	@Autowired
	private IDictService  dictService;//字典表操作
	
	@Autowired
	private IUserOrderService userOrderService;
	
	@Autowired
	private IAccountDao accountDao;
	

	@Override
	public int insert(UserEntity entity) {
		if(null == entity) {
			LOGGER.info("UserServiceImpl insert entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		
		LOGGER.info("Entering UserServiceImpl insert...");
		int result = userDao.insert(entity);
		LOGGER.info("Exiting UserServiceImpl insert...");
		return result;
		
	}
	
	@Override
	public int update(UserEntity entity) {
		if(null == entity) {
			LOGGER.info("UserServiceImpl update entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		
		if(StringUtils.isBlank(entity.getUserName())) {
			LOGGER.info("UserServiceImpl update userName is null.");
			return CommonConst.DIGIT_ZERO;
		}
		
		LOGGER.info("Entering UserServiceImpl update...");
		int result = userDao.update(entity);
		LOGGER.info("Exiting UserServiceImpl update...");
		return result;
	}
	
	@Override
	public int delete(UserEntity entity) {
		if(null == entity) {
			LOGGER.info("UserServiceImpl delete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		
		if(StringUtils.isBlank(entity.getUserName())) {
			LOGGER.info("UserServiceImpl delete userName is null.");
			return CommonConst.DIGIT_ZERO;
		}
		
		LOGGER.info("Entering UserServiceImpl delete...");
		int result = userDao.delete(entity);
		LOGGER.info("Exiting UserServiceImpl delete...");
		return result;
	}

	@Override
	public List<UserEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("UserServiceImpl queryList Service Map is null.");
			return null;
		}
		LOGGER.info("Entering UserServiceImpl queryList service...");
		List<UserEntity> list = userDao.queryList(map);
		LOGGER.info("Exiting UserServiceImpl queryList service...");
		return list;
	}
	
	@Override
	public List<UserEntity> queryAll() {
		LOGGER.info("Entering UserServiceImpl queryAll service...");
		List<UserEntity> list = userDao.queryAll();
		LOGGER.info("Exiting UserServiceImpl queryAll service...");
		return list;
	}
	
	@Override
	public List<UserEntity> queryAllUserIDUserName() {
		LOGGER.info("Entering UserServiceImpl queryAllUserIDUserName service...");
		List<UserEntity> list = userDao.queryAllUserIDUserName();
		LOGGER.info("Exiting UserServiceImpl queryAllUserIDUserName dao...");
		return list;
	}

	@Override
	public UserEntity validateUser(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("validateUser Map is null.");
			return null;
		}
		LOGGER.info("Entering UserServiceImpl validateUser service...");
		UserEntity entity = userDao.validateUser(map);
		LOGGER.info("Exiting UserServiceImpl validateUser service...");
		return entity;
	}

	@Override
	public int deleteUser(UserEntity entity) {
		if(null == entity || StringUtils.isBlank(entity.getUserName())) {
			LOGGER.info("UserServiceImpl deleteUser Service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserServiceImpl deleteUser Service...");
		int result = userDao.deleteUser(entity);
		LOGGER.info("Exiting UserServiceImpl deleteUser Service...");
		return result;
	}

	@Override
	public int countUser(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("UserServiceImpl countUser Service Map is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserServiceImpl countUser service...");
		int countUser = userDao.countUser(map);
		LOGGER.info("Exiting UserServiceImpl countUser service...");
		return countUser;
	}
	
	@Override
	public List<UserEntity> queryUnactiveUser(String userName) {
		
		if(null == userName || userName.trim().equals(CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS)) {
			LOGGER.info("UserServiceImpl queryUnactiveUser service userName is null.");
			return null;
		}
		
		LOGGER.info("Entering UserServiceImpl queryUnactiveUser service...");
		List<UserEntity> list = userDao.queryUnactiveUser(userName);
		LOGGER.info("Exiting UserServiceImpl queryUnactiveUser service...");
		return list;
	}

	@Override
	public List<UserEntity> queryListByName(String exportName) {
		if(StringUtils.isBlank(exportName)){
			return userDao.queryAll();
		}
		return userDao.queryListByName(exportName);
	}
	
	@Override
	public PageList<UserEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page || null == map) {
			LOGGER.info("UserSmsServiceImpl queryList page or map is null.");
			return null;
		}
		LOGGER.info("Entering UserSmsServiceImpl queryPage...");
		PageList<UserEntity> list = userDao.queryPage(page, map);
		LOGGER.info("Exiting UserSmsServiceImpl queryPage...");
		return list;
	}

	@Override
	public int getMaxId() {
		return userDao.getMaxId();
	}
	
	@Override
	public Map<String, Object> login(String userName, String password,String userRole) {
		LOGGER.info("Entering UserServiceImpl login...  userName = :{}, password = :{}", userName, password);
		Map<String,Object> result = new HashMap<String,Object>();
		//判断参数是否为空
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "手机号码或密码不能为空");
			LOGGER.info("Exiting UserServiceImpl login...  result = :{}", result);
			return result;
		}
		//登录增加角色类型
//		if(StringUtils.isEmpty(userRole)){
//			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
//			result.put(CommonConst.RESPONSE_MESSAGE, "用户角色不能为空");
//			LOGGER.info("Exiting UserServiceImpl login...  result = :{}", result);
//			return result;
//		}
		//查询是否存在
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		List<UserEntity> list = queryList(map);
		//数据库中也没有数据
		if(list == null || list.size() == CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "该手机号未注册");
			LOGGER.info("Exiting UserServiceImpl login...  result = :{}", result);
			return result;
		}
		//判断密码是否正确
		UserEntity user = list.get(CommonConst.DIGIT_ZERO);
		if(!password.equals(user.getUserPassword())) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, MessageConst.WARN_USER_PASSWORD_IS_NOT_MATCH);
			LOGGER.info("Exiting UserServiceImpl login...  result = :{}", result);
			return result;
		}
		//检查角色是否存在
		Map<String,Object> mapRole = new HashMap<String,Object>();
		mapRole.put("userName", userName);
		if(!StringUtils.isEmpty(userRole)){
		    mapRole.put("roleId", userRole);
		}
		List<UserRoleEntity> queryList = userRoleService.queryList(mapRole);
		if(queryList == null || queryList.size() == CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "该用户角色不存在，请重新注册");
			LOGGER.info("Exiting UserServiceImpl login...  result = :{}", result);
			return result;
		}
		
		//获取用户头像
		Map<String,Object> mapPic = new HashMap<String,Object>();
		mapPic.put("userName", userName);
		mapPic.put("picUse", CommonConst.DIGIT_ONE);
		List<UserPicEntity> userPicList = userPicService.queryList(mapPic);
		if(userPicList!=null && userPicList.size()>CommonConst.DIGIT_ZERO){
			String picUrl = userPicList.get(0).getPicUrl();
			user.setPicUrl(picUrl);
		}
		else {
			user.setPicUrl(DEFAULT_HERD_IMG);
		}
		
		//生成token
		//String token = CommonConst.TOKEN_PREFIX + com.meida.common.util.StringUtils.produceUUID();
		//保存token  -->不保存到mongo  未确定是否保存到redis
		result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		result.put(CommonConst.RESPONSE_MESSAGE, MessageConst.REMINDER_LOGIN_SUCCESS);
		//将角色信息补齐到用户表
		user.setRoleId(queryList.get(0).getRoleId());
		user.setRoleName(queryList.get(0).getRoleName());
		//将用户ID的信息获取的对像返回app端
		if(StringUtils.isEmpty(user.getUserRealName())){
			user.setUserRealName("");//昵称->真实姓名
		}
		if(StringUtils.isEmpty(user.getUserMobile())){
			user.setUserMobile("");//手机号
		}
		result.put(CommonConst.RESPONSE_DATA,user);
		//result.put("token", token);
		LOGGER.info("Exiting UserServiceImpl login...  result = :{}", result);
		return result;
	}
	
	@Override
	public Map<String, Object> loginByCode(String userName,	String userRole) {
		LOGGER.info("Entering UserServiceImpl login...  userName = :{}", userName);
		Map<String,Object> result = new HashMap<String,Object>();
		//判断参数是否为空
		if(StringUtils.isEmpty(userName)){
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "手机号码不能为空");
			LOGGER.info("Exiting UserServiceImpl loginByCode...  result = :{}", result);
			return result;
		}
		//登录增加角色类型
//		if(StringUtils.isEmpty(userRole)){
//			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
//			result.put(CommonConst.RESPONSE_MESSAGE, "用户角色不能为空");
//			LOGGER.info("Exiting UserServiceImpl login...  result = :{}", result);
//			return result;
//		}
		//查询是否存在
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		List<UserEntity> list = queryList(map);
		//数据库中也没有数据
		if(list == null || list.size() == CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "该手机号未注册");
			LOGGER.info("Exiting UserServiceImpl login...  result = :{}", result);
			return result;
		}
		UserEntity user = list.get(CommonConst.DIGIT_ZERO);
		//检查角色是否存在
			Map<String,Object> mapRole = new HashMap<String,Object>();
			mapRole.put("userName", userName);
			if(!StringUtils.isEmpty(userRole)){
			   mapRole.put("roleId", userRole);
			}
			List<UserRoleEntity> queryList = userRoleService.queryList(mapRole);
			if(queryList == null || queryList.size() == CommonConst.DIGIT_ZERO) {
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				result.put(CommonConst.RESPONSE_MESSAGE, "该用户角色不存在，请重新注册");
				LOGGER.info("Exiting UserServiceImpl login...  result = :{}", result);
				return result;
			}
			//获取用户头像
			Map<String,Object> mapPic = new HashMap<String,Object>();
			mapPic.put("userName", userName);
			mapPic.put("picUse", CommonConst.DIGIT_ONE);
			List<UserPicEntity> userPicList = userPicService.queryList(mapPic);
			if(userPicList!=null && userPicList.size()>CommonConst.DIGIT_ZERO){
				String picUrl = userPicList.get(0).getPicUrl();
				user.setPicUrl(picUrl);
			}
		//生成token
		//String token = CommonConst.TOKEN_PREFIX + com.meida.common.util.StringUtils.produceUUID();
		//保存token  -->不保存到mongo  未确定是否保存到redis
		result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		result.put(CommonConst.RESPONSE_MESSAGE, MessageConst.REMINDER_LOGIN_SUCCESS);
		//将角色信息补齐到用户表
		user.setRoleId(queryList.get(0).getRoleId());
		user.setRoleName(queryList.get(0).getRoleName());
		if(StringUtils.isEmpty(user.getUserRealName())){
			user.setUserRealName("");//昵称->真实姓名
		}
		if(StringUtils.isEmpty(user.getUserMobile())){
			user.setUserMobile("");//手机号
		}
		//将用户ID的信息获取的对像返回app端
		result.put(CommonConst.RESPONSE_DATA,user);
		//result.put("token", token);
		LOGGER.info("Exiting UserServiceImpl login...  result = :{}", result);
		return result;
	}

	@SuppressWarnings("null")
	@Override
	public Map<String, Object> register(String mobile,String userRole,String password) {
		LOGGER.info("Entering UserServiceImpl register...  mobile = :{}, userRole = :{}", mobile, userRole);
		Map<String,Object> result = new HashMap<String,Object>();
		//判断参数是否为空
		if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(userRole)) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "手机或角色不能为空");
			LOGGER.info("Exiting UserServiceImpl register...  result = :{}", result);
			return result;
		}
		//判断密码是否为空
		if(StringUtils.isEmpty(password)) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "密码不能为空");
			LOGGER.info("Exiting UserServiceImpl register...  result = :{}", result);
			return result;
		}
		
		//用户是否已经注册
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", mobile);
		List<UserEntity> list = queryList(map);
		if(list!=null && list.size()>CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "该手机号已经被注册");
			LOGGER.info("Exiting UserServiceImpl register...  result = :{}", result);
			return result;
		}
		
		UserEntity user = new UserEntity();
		user.setId(StringUtil.produceUUID());
		int userId = getMaxId() + 1;//userId最大值+1
		user.setUserId(Integer.toString(userId));
		user.setUserName(mobile);
		user.setUserMobile(mobile);
		//EASEMOB_NAME 环信名称
		user.setUserMobileValidation(true);//手机号是否验证 0未验证1已验证
		user.setUserPassword(password);//密码未加密
		user.setActive(true);
		user.setCreateUserId(userId+"");
		user.setCreateTime(new Date());
		user.setVersion(1);
		
		//环信注册
		try {
			 boolean registerIM = ChatIMUtil.registerIM(mobile, CommonConst.USER_PWD, "lvdao");
			 LOGGER.info("环信是否注册成功："+registerIM);
			 user.setEasemobName(mobile);
		} catch (URISyntaxException e) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "环信注册失败");
			LOGGER.info("Exiting UserServiceImpl register...  result = :{}, exception = :{}", result, e.getMessage());
			return result;
		}
		
		//t_user 表的数据插入
		int insert = insert(user);
		if(insert > CommonConst.DIGIT_ZERO) {
			//t_user_role-->插入角色表
			UserRoleEntity userRoleEntity = new UserRoleEntity();
			userRoleEntity.setId(StringUtil.produceUUID());
			userRoleEntity.setUserId(Integer.toString(userId));
			userRoleEntity.setUserName(mobile);
			userRoleEntity.setRoleId(userRole);
			if(userRole.equals("1")){
				userRoleEntity.setRoleName("司机");
			}else if(userRole.equals("2")){
				userRoleEntity.setRoleName("乘客");
			}
			
			userRoleEntity.setActive(true);
			userRoleEntity.setCreateUserId(userId+"");
			userRoleEntity.setCreateUserName(mobile);
			userRoleEntity.setCreateTime(new Date());
			userRoleEntity.setVersion(1);
			userRoleService.insert(userRoleEntity);
			//用户账单表插入
			//查找表账户字典 id=0389db2da1514fbd85cf030d934qe9fe
			map.clear();
			map.put("id","0389db2da1514fbd85cf030d934qe9fe");
			List<AccountEntity> accountEntitys = accountDao.queryList(map);
			if (accountEntitys ==null && accountEntitys.size()==0) {
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				result.put(CommonConst.RESPONSE_MESSAGE, "用户账户创建异常，请联系管理员");
			}
			UserAccountEntity userAccountEntity = new UserAccountEntity();
			userAccountEntity.setId(StringUtil.produceUUID());
			userAccountEntity.setUserId(user+"");
			userAccountEntity.setUserName(mobile);
			userAccountEntity.setAccountId(accountEntitys.get(0).getAccountId());
			userAccountEntity.setAccountName(accountEntitys.get(0).getAccountName());
			userAccountEntity.setAccountAmount("0.000000000");
			userAccountEntity.setActive(true);
			userAccountEntity.setCreateTime(new Date());
			userAccountEntity.setCreateUserId(userId+"");
			userAccountEntity.setCreateUserName(mobile);
			userAccountService.insert(userAccountEntity);
			//百度鹰眼
			try {
				BaiduMapUtils.registerBaiduMapYingYanUser(mobile);
			} catch (URISyntaxException e) {
				LOGGER.error("注册百度鹰眼轨迹用户失败");
			}
			
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			result.put(CommonConst.RESPONSE_MESSAGE, MessageConst.REMINDER_REGISTER_SUCCESS);
			LOGGER.info("Exiting UserServiceImpl register...  result = :{}", result);
			return result;
			
		}	
			
			
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, MessageConst.REMINDER_REGISTER_FAIL);
			LOGGER.info("Exiting UserServiceImpl register...  result = :{}", result);
		    return result;
	}
	
	@Override
	public Map<String,Object> sendSmsCode(String mobile, String content ,boolean mobileAvailable) {
		Map<String,Object> result = new HashMap<String,Object>();
		LOGGER.info("Entering UserServiceImpl sendSmsCode...  mobile = :{}, content = :{}", mobile, content);
		if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(content)){
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "手机账号或短信内容不能为空");
			LOGGER.info("Exiting UserServiceImpl sendSmsCode...  result = :{}", result);
			return result;
		}
		
		//登录的时候需要提前验证手机号是否存在
		if(mobileAvailable) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userName", mobile);
			List<UserEntity> list = queryList(map);
			if(list != null && list.size() > CommonConst.DIGIT_ZERO) {
//				int sendSmsCodeRet = StringUtil.sendSmsCode(mobile, content);
				SendSmsResponse sendSms = new SendSmsResponse();
				try {
					sendSms = AliyunSMSUtil.sendSms(mobile, content);
				} catch (ClientException e) {
					result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
					result.put(CommonConst.RESPONSE_MESSAGE, "发送短信失败");
					return result;
				}
				if("OK".equals(sendSms.getCode())){
					result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
					result.put(CommonConst.RESPONSE_MESSAGE, "发送短信成功");
					LOGGER.info("Exiting UserServiceImpl register...  result = :{}", result);
					return result;
				}
			}else{
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				result.put(CommonConst.RESPONSE_MESSAGE, "手机账号未注册");
				LOGGER.info("Exiting UserServiceImpl sendSmsCode...  result = :{}", result);
				return result;
			}
		}
		
		//注册的时候不需要验证手机号
		if(!mobileAvailable){
			SendSmsResponse sendSms = new SendSmsResponse();
			try {
				sendSms = AliyunSMSUtil.sendSms(mobile, content);
			} catch (ClientException e) {
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				result.put(CommonConst.RESPONSE_MESSAGE, "发送短信失败");
				return result;
			}
			if("OK".equals(sendSms.getCode())){
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
				result.put(CommonConst.RESPONSE_MESSAGE, "发送短信成功");
				LOGGER.info("Exiting UserServiceImpl register...  result = :{}", result);
				return result;
			}
		}
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "发送短信失败");
			LOGGER.info("Exiting UserServiceImpl register...  result = :{}", result);
		    return result;
	}


	@Override
	public List<UserEntity> queryNearPeople(Map<String, Object> map, Page page) {
//		return userDao.queryNearPeople(map, page);
		return  null;
	}

	@Override
	public int saveUserToken(String userName, String token) {
		return 0;
	}

	@Override
	public String getUserNameByToken(String token) {
		return null;
	}

//	@Override
//	public Map<String, Object> login(String userName, String password) {
//		return null;
//	}

//	@Override
//	public Map<String, Object> register(String mobile, String password,
//			String userParentId) {
//		return null;
//	}

//	@Override
//	public int sendSmsCode(String mobile, String content,
//			boolean mobileAvailable) {
//		return 0;
//	}

	@Override
	public String queryUserNameByUserId(String userId) {
		return null;
	}

	@Override
	public UserEntity getUserEntityByUserId(String userId) {
		return userDao.getUserEntityByUserId(userId);
	}

//	@Override
//	public BasicResponse getUserInfoByUserId(String userId) {
//		return null;
//	}

	@Override
	public BasicResponse updatePassword(String userId, String password) {
		return null;
	}

	@Override
	public Map<String, Object> thirdLogin(Map<String, Object> parameters) {
		return null;
	}

	@Override
	public BasicResponse updateUserInfoByUserName(UserEntity userEntity) {
		return null;
	}

	@Override
	public Map<String, Object> exitToLogin(String token) {
		return null;
	}

//	@Override
//	public BasicResponse uploadHeadPortrait(InputStream inputStream,
//			String picName, String picType, String userId) {
//		return null;
//	}

	@Override
	public BasicResponse updateHeadPortrait(String userId,
			InputStream inputStream, String picName, String picType) {
		return null;
	}

	@Override
	public BasicResponse getOtherUserInfo(String myUserId, String otherUserId) {
		return null;
	}

	@Override
	public Map<String, String> getUserGenderMap() {
		return null;
	}

	@Override
	public Map<String, Object> registerUserByParentId(Map<String, Object> param) {
		return null;
	}

	@Override
	public Map<String, String> saveUserRelation(String parentMobile,
			String mobile) {
		return null;
	}

	@Override
	public boolean bonus() {
		return false;
	}

	@Override
	public PageList<UserEntity> queryUserByUserIdNumNotNull(Page page) {
		return null;
	}

	@Override
	public int updateUserByUserId(UserEntity entity) {
		return 0;
	}

	@Override
	public int updateUserByUserName(UserEntity entity) {
		return 0;
	}

//	@Override
//	public UserEntity queryByUserName(String userName) {
//		return null;
//	}

	@Override
	public Map<String, Object> queryRoleCount(Map<String, Object> param) {
		return null;
	}

	@Override
	public PageList<UserEntity> queryInfoByParent(Map<String, Object> map,
			Page page) {
		return null;
	}

//	@Override
//	public BasicResponse updateUserInfoByUserName(UserEntity userEntity) {
//		BasicResponse response = new BasicResponse();
//		int ret = userDao.updateByUserName(userEntity);
//		if(ret == 0) {
//			response.setMessage("保存失败，请稍后尝试！");
//			response.setStatus(CommonConst.FAIL);
//			return response;
//		}
//		//更新成功，同时更新mongo
//		MongoEntity mongo = new MongoEntity();
//		mongo.setCollectionName(CommonConst.REDIS_MONGO_KEY_USER);
//		mongo.setQuery((new BasicDBObject("userName", userEntity.getUserName()).append("active", true)));
//		mongo.setUpdate((new BasicDBObject("userRealName", userEntity.getUserRealName()).
//				append("userGender",userEntity.getUserGender()).
//				append("userBirthday",userEntity.getUserBirthday()).
//				append("userDesc", userEntity.getUserDesc())));
//		this.update(mongo);
//		response.setMessage("保存成功！");
//		response.setStatus(CommonConst.SUCCESS);
//		return response;
//	}
	
//	@Override
//	public Map<String, Object> exitToLogin(String token) {
//		LOGGER.info("Entering UserServiceImpl exitToLogin Service...token:{}", token);
//		Map<String, Object> result = new HashMap<String, Object>();
//		if(StringUtils.isBlank(token)) {
//			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
//			result.put(CommonConst.RESPONSE_MESSAGE, CommonConst.RESPONSE_MSG_NO_PARAM);
//			return result;
//		}
//		
//		MongoEntity mongo = new MongoEntity();
//		mongo.setCollectionName(CommonConst.MONGO_KEY_USER_TOKEN);
//		mongo.setQuery(new BasicDBObject("token", token).append("active", true));
//		List<UserEntity> list = coreCacheService.queryList(mongo);
//		if(list == null || list.size() == CommonConst.DIGIT_ZERO) {
//			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
//			result.put(CommonConst.RESPONSE_MESSAGE, "token已失效");
//			return result;
//		}
//		
//		mongo.setUpdate(new BasicDBObject("active", false));
//		ServiceResult<UserEntity> serviceResult = coreCacheService.update(mongo);
//		
//		result.put(CommonConst.RESPONSE_STATUS, serviceResult.getSuccess());
//		result.put(CommonConst.RESPONSE_MESSAGE, serviceResult.getMessage());
//		LOGGER.info("Exiting UserServiceImpl exitToLogin Service...");
//		return result;
//	}  
	
//	/**
//	 * 上传头像
//	 * byzhaoming 0922
//	 * @return
//	 * 
//	 */
//	@Override
//	public BasicResponse uploadHeadPortrait(InputStream inputStream,String picName,String picType,String userId){
//		
//		BasicResponse response = new BasicResponse();
//		PictureEntity pictureEntity = new PictureEntity();
//		String uuid = com.meida.common.util.StringUtils.produceUUID();
//		if(StringUtils.isBlank(picType)) {
//			picType = CommonConst.PUNCTUATION_DOT + CommonConst.PIC_FORMAT_JPG;
//		}
//		String imageName = com.meida.common.util.StringUtils.produceUUID() + CommonConst.PUNCTUATION_DOT + picType;
//		
//		//将图片保存到ftp服务器
//		String filePath = DateUtils.format(new Date(), DateUtils.DATE_FORMAT_YEAR_MONTH_DAY);
//		boolean ftpFlag = FtpUtil.uploadFile(filePath, imageName, inputStream);
//		if(!ftpFlag) {
//			response.setMessage("上传图片失败，请稍后尝试");
//			response.setStatus(CommonConst.FAIL);
//			return response;
//		}
//		
//		String imageUrl = FtpUtil.FTP_BASEURL + filePath + CommonConst.PUNCTUATION_SPCIAL_SIGN + imageName;
//		pictureEntity.setPicId(uuid);
//		//图片服务器名称  ftp  mongo
//		pictureEntity.setPicName(imageName);
//		pictureEntity.setPicUrl(imageUrl);
//		pictureEntity.setPicSize("");
//		pictureEntity.setPicType(picType);
//		//图片真实名称
//		pictureEntity.setPicRealName(picName);
//		pictureEntity.setId(uuid);
//		pictureEntity.setActive(true);
//		pictureEntity.setCreateUserId("");
//		pictureEntity.setCreateUserName("");
//		pictureEntity.setCreateTime(new Date());
//		pictureEntity.setVersion(CommonConst.DIGIT_ONE);
//		
//		boolean insertPicture = true;
//				//pictureService.storeImageInMongo(inputStream, pictureEntity);
//		if(!insertPicture){
//			response.setMessage("上传图片失败，请稍后尝试");
//			response.setStatus(CommonConst.FAIL);
//			return response;
//		}
//		
//		UserPicEntity userPicEntity = new UserPicEntity();
//		userPicEntity.setUserId(userId);
//		userPicEntity.setUserName("");
//		userPicEntity.setPicId(uuid);
//		userPicEntity.setPicUrl(imageUrl);
//		userPicEntity.setId(com.meida.common.util.StringUtils.produceUUID());
//		//picType 1头像 2照片
//		userPicEntity.setPicUse(CommonConst.DIGIT_ONE);
//		userPicEntity.setActive(true);
//		userPicEntity.setCreateUserId("");
//		userPicEntity.setCreateUserName("");
//		userPicEntity.setCreateTime(new Date());
//		userPicEntity.setVersion(CommonConst.DIGIT_ONE);
//		int insertUserPic = userPicService.insert(userPicEntity);
//		if(insertUserPic == CommonConst.DIGIT_ZERO){
//			response.setMessage("插入用户图片表失败，请稍后尝试");
//			response.setStatus(CommonConst.FAIL);
//			return response;
//		}
//		
//		response.setMessage("用户上传头像成功");
//		response.setStatus(CommonConst.SUCCESS);
//		return response;
//		
//	}
	
	@Override
	public UserEntity queryByUserName(String userName) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userName", userName);
		List<UserEntity> queryList = queryList(queryMap);
		return queryList != null ? queryList.get(CommonConst.DIGIT_ZERO) : null;
	}
	
	@Override
	public Map<String,Object> updateUserPasswordByUserId(String userId, String password,String passwordSure){
		LOGGER.info("Entering UserServiceImpl updateUserPasswordByUserId...  mobile = :{}, password = :{}", userId, password);
		Map<String,Object> result = new HashMap<String,Object>();
		//判断参数是否为空
		if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(password)) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "手机号或密码不能为空");
			LOGGER.info("Exiting UserServiceImpl updateUserPasswordByUserId...  result = :{}", result);
			return result;
		}
		//判断两次密码是否相等
		if(!StringUtils.isEmpty(passwordSure)){
			if(!passwordSure.equals(password)){
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				result.put(CommonConst.RESPONSE_MESSAGE, "两次密码不一致");
				LOGGER.info("Exiting UserServiceImpl updateUserPasswordByUserId...  result = :{}", result);
				return result;
			}
		}
		//去更新密码
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(userId);
		userEntity.setUserPassword(password);
		userEntity.setUpdateUserId(userId);
		userEntity.setUpdateTime(new Date());
		try {
			int updateRet = this.update(userEntity);
			if(updateRet>CommonConst.DIGIT_ZERO){
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
				result.put(CommonConst.RESPONSE_MESSAGE, "修改密码成功");
				LOGGER.info("Exiting UserServiceImpl updateUserPasswordByUserId...  result = :{}", result);
				return result;
			}
		} catch (Exception e) {
			throw new RuntimeException("修改密码异常");
		}
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				result.put(CommonConst.RESPONSE_MESSAGE, "修改密码失败");
				LOGGER.info("Exiting UserServiceImpl updateUserPasswordByUserId...  result = :{}", result);
				return result;
	}
	
	@Override
	@Transactional
	public Map<String,Object> updateUserByUserId(Map<String,Object> parameters){
		LOGGER.info("Entering UserServiceImpl updateUserByUserId... parameters.size： ", parameters.size());
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			int updateRet = updateUser(parameters);
			if(updateRet==CommonConst.DIGIT_ZERO){
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				result.put(CommonConst.RESPONSE_MESSAGE, "更新用户表失败");
				LOGGER.info("Exiting UserServiceImpl updateUserByUserId...  result = :{}", result);
				return result;
			}
			int addVehicle = addVehicle(parameters);
			if(addVehicle==CommonConst.DIGIT_ZERO){
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				result.put(CommonConst.RESPONSE_MESSAGE, "提交用户车辆信息失败");
				LOGGER.info("Exiting UserServiceImpl updateUserByUserId...  result = :{}", result);
				return result;
			}
			int insertUserPicList = insertUserPicList(parameters);
			if(insertUserPicList==CommonConst.DIGIT_ZERO){
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				result.put(CommonConst.RESPONSE_MESSAGE, "批量插入图片失败");
				LOGGER.info("Exiting UserServiceImpl updateUserByUserId...  result = :{}", result);
				return result;
			}
			int updateUserRole = updateUserRole(parameters);
			if(updateUserRole==CommonConst.DIGIT_ZERO){
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				result.put(CommonConst.RESPONSE_MESSAGE, "默认成为司机失败");
				LOGGER.info("Exiting UserServiceImpl updateUserByUserId...  result = :{}", result);
				return result;
			}
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			result.put(CommonConst.RESPONSE_MESSAGE, "提交资料成功");
			LOGGER.info("Exiting UserServiceImpl updateUserByUserId...  result = :{}", result);
			return result;
		} catch (Exception e) {
			//throw new RuntimeException("提交资料异常");
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "提交资料异常");
			LOGGER.info("Exiting UserServiceImpl updateUserByUserId...  result = :{}", result);
			return result;
		}
	}
	
	
	public int updateUser(Map<String,Object> parameters){
		try {
			//更新用户信息
			String userId = parameters.get("userId").toString();
			UserEntity userEntity = new UserEntity();
			userEntity.setUserName(parameters.get("userName").toString());
			userEntity.setActive(true);//默认=1
			userEntity.setUserId(userId);
			userEntity.setUserRealName(parameters.get("userRealName").toString());;
			userEntity.setUserMobile(parameters.get("mobile").toString());
			userEntity.setUserGender(parameters.get("userGender").toString());
			if(parameters.get("userProvince")!=null){
				userEntity.setUserProvince(parameters.get("userProvince").toString());
			}
			if(parameters.get("userCity")!=null){
				userEntity.setUserCity(parameters.get("userCity").toString());
			}
			userEntity.setUpdateUserId(parameters.get("userId").toString());
			userEntity.setUpdateUserName(parameters.get("userRealName").toString());
			userEntity.setUpdateTime(new Date());
			int updateRet = this.update(userEntity);
			return updateRet;
		} catch (Exception e) {
			//throw new RuntimeException("更新用户信息失败");
		}
		return 0;
	}
	
	public int addVehicle(Map<String,Object> parameters){
		try {
			String userId = parameters.get("userId").toString();
			//添加车辆信息
	    	VehicleEntity vehicleEntity = new VehicleEntity();
	    	vehicleEntity.setId(StringUtil.produceUUID());
	    	int maxId = vehicleService.getMaxId();
	    	int vetVehicleId = maxId +1;
	    	vehicleEntity.setVehicleId(vetVehicleId+"");
	    	if(parameters.get("vehicleBrand")!=null){//选填
	    		vehicleEntity.setVehicleBrand(parameters.get("vehicleBrand").toString());
	    	}
	    	if(parameters.get("vehicleNo")!=null){//选填
	    	    vehicleEntity.setVehicleNo(parameters.get("vehicleNo").toString());
	    	}
	    	vehicleEntity.setVehicleDriverId(userId);
	    	vehicleEntity.setVehicleDriverMobileNo(parameters.get("mobile").toString());//车主联系电话
	    	vehicleEntity.setVehicleDriverName(parameters.get("userRealName").toString());
	    	vehicleEntity.setActive(true);
	    	vehicleEntity.setCreateUserId(userId);
	    	vehicleEntity.setCreateUserName(parameters.get("userRealName").toString());
	    	vehicleEntity.setVersion(1);
	    	vehicleEntity.setCreateTime(new Date());
	    	int insertRet = vehicleService.insert(vehicleEntity);
	    	return insertRet;
		} catch (Exception e) {
			//throw new RuntimeException("提交车辆信息异常");
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public int insertUserPicList(Map<String,Object> parameters){
		try {
			String userId = parameters.get("userId").toString();
			//批量添加用户图片信息
			List<UserPicEntity> userPicEntityList = new ArrayList<UserPicEntity>();
			List<String> picUrlList =  (List<String>) parameters.get("picUrlList");
			int type = 4;//类型 4身份证正面，5身份证反面,6驾照正面，7驾照反面
			for (String picUrl : picUrlList) {
				UserPicEntity userPicEntity = new UserPicEntity();
				userPicEntity.setId(StringUtil.produceUUID());
				userPicEntity.setUserId(userId);
				userPicEntity.setUserName(parameters.get("mobile").toString());
				int userPicMaxId = userPicService.getMaxId();
				int userPicMaxIdNext = userPicMaxId + CommonConst.DIGIT_ONE;
				userPicEntity.setPicId(userPicMaxIdNext+"");
				userPicEntity.setPicUrl(picUrl);
				//如果是头像
				if(picUrl.equals(CommonConst.LVDAO_OSSPATH+parameters.get("headPortrait").toString())){
					userPicEntity.setPicUse(1);
				}else{
					int picuse = type++;
					userPicEntity.setPicUse(picuse);
					//System.out.println(type++);
				}
				userPicEntity.setActive(true);
				userPicEntity.setCreateUserId(userId);
				userPicEntity.setCreateUserName(parameters.get("mobile").toString());
				userPicEntity.setCreateTime(new Date());
				userPicEntity.setVersion(1);
				userPicEntityList.add(userPicEntity);
			}
			
			int insertList = userPicService.insertList(userPicEntityList);
			return insertList;
		} catch (Exception e) {
			//throw new RuntimeException("批量插入用户图片失败");
		}
		return 0;
	}
	
	
	public  int updateUserRole(Map<String,Object> parameters){
		try {
			//默认不需要更改
			int updateRoleRet = 1 ;
			String userId = parameters.get("userId").toString();
			Map<String,Object> maptemp = new HashMap<String,Object>();
			maptemp.put("userId", userId);
			List<UserRoleEntity> queryList = userRoleService.queryList(maptemp);
			if(queryList!=null && queryList.size()>CommonConst.DIGIT_ZERO){
				UserRoleEntity userRoleEntity = queryList.get(0);
				if(userRoleEntity.getRoleId().equals("2")){//如果是乘客则默认改成司机
					//乘客角色-->默认变成司机
					UserRoleEntity roleEntity = new UserRoleEntity();
					roleEntity.setUserId(userId);
					roleEntity.setRoleId(CommonConst.DIGIT_ONE+"");
					roleEntity.setRoleName("司机");
					roleEntity.setUpdateUserId(userId);
					roleEntity.setUpdateTime(new Date());
					roleEntity.setActive(true);
					updateRoleRet = userRoleService.update(roleEntity);
				} 
			}
			return updateRoleRet;
		} catch (Exception e) {
			//throw new RuntimeException("默认将乘客升级成司机失败");
		}
		return 0;
	}

	@Override
	public Map<String, Object> queryUserByBalance(String userId) {	
		LOGGER.info("Entering UserServiceImpl queryUserByBalance...  userId = :{}", userId);
		
		Map<String,Object> result = new HashMap<String,Object>();
		if (StringUtils.isEmpty(userId)) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "userId不能为空");
			LOGGER.info("Exiting UserServiceImpl queryUserByBalance...  result = :{}", result);
			return result;
			
		}
		Map<String,Object> mapBalance = new HashMap<String,Object>();
		mapBalance.put("userId", userId);
		List<UserAccountEntity> userAccountEntities = userAccountService.queryList(mapBalance);
		if(userAccountEntities == null || userAccountEntities.size() == CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "该用户账单记录为空");
			LOGGER.info("Exiting UserServiceImpl queryUserByBalance...  result = :{}", result);
			return result;
		}
		UserAccountEntity userAccountEntity = new UserAccountEntity();
		userAccountEntity = userAccountEntities.get(CommonConst.DIGIT_ZERO);
		UserEntity userEntity = userDao.queryList(mapBalance).get(CommonConst.DIGIT_ZERO);
		mapBalance.put("picUse", CommonConst.DIGIT_ONE);
		List<UserPicEntity> userPicList = userPicService.queryList(mapBalance);
		if(userPicList!=null && userPicList.size()>CommonConst.DIGIT_ZERO){
			String picUrl = userPicList.get(0).getPicUrl();
			if (!picUrl.isEmpty()) {
				userAccountEntity.setHeadUrl(picUrl);
			}
			
		}
		else
		{userAccountEntity.setHeadUrl(DEFAULT_HERD_IMG);}
		if (userEntity.getUserRealName().isEmpty()) {
			userAccountEntity.setUserRealName(userEntity.getUserName());
		}
		else {
			userAccountEntity.setUserRealName(userEntity.getUserRealName());
		}
		userAccountEntity.setUserType(userEntity.getUserType()+"");
		
		
		result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		result.put(CommonConst.RESPONSE_MESSAGE, "查询成功");
		result.put(CommonConst.RESPONSE_DATA, userAccountEntity);
		LOGGER.info("Exiting UserServiceImpl queryUserByBalance...  result = :{}", result);
		return result;
		
	}

	@Override
	public Map<Object, Object> queryDealLogMessagePage(Page page,String userId) {
		LOGGER.info("Entering UserServiceImpl queryDealLogMessagePage...  userId = :{}", userId);
		Map<Object,Object> result = new HashMap<Object,Object>();
		if (page == null || StringUtils.isEmpty(userId)) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "page或userId为空");
			LOGGER.info("UserServiceImpl queryDealLogMessagePage page or userId is null.");
			return result;
			}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		PageList<DealLogEntity> dealLogEntities = DealLogService.queryPage(page, map);
		result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		result.put(CommonConst.RESPONSE_MESSAGE, "查询成功");
		result.put(CommonConst.RESPONSE_DATA, dealLogEntities);
		return result;
	}


	@Override
	public Integer vipCount(Map<String, Object> map) {
		
		return userDao.vipCount(map);
	}

}

