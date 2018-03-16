package com.lvdao.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lvdao.common.CommonConst;
import com.lvdao.common.MessageConst;
import com.lvdao.common.enums.AccountEnum;
import com.lvdao.common.enums.LogTypeEnum;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.common.util.DataUtils;
import com.lvdao.common.util.SecurityUtils;
import com.lvdao.common.util.StringUtil;
import com.lvdao.entity.DealLogEntity;
import com.lvdao.entity.UserAccountEntity;
import com.lvdao.entity.UserEntity;
import com.lvdao.entity.UserMailEntity;
import com.lvdao.entity.UserPicEntity;
import com.lvdao.entity.UserRoleEntity;
import com.lvdao.entity.UserSmsEntity;
import com.lvdao.entity.UserViedoEntity;
import com.lvdao.entity.UserVoiceEntity;
import com.lvdao.service.IDealLogService;
import com.lvdao.service.IRolePermissionService;
import com.lvdao.service.IUserAccountService;
import com.lvdao.service.IUserMailService;
import com.lvdao.service.IUserPicService;
import com.lvdao.service.IUserRoleService;
import com.lvdao.service.IUserService;
import com.lvdao.service.IUserSmsService;
import com.lvdao.service.IUserVideoService;
import com.lvdao.service.IUserVoiceService;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	//佣金积分
	private static final String COMISSION_INTEGRAL_ACCOUNT_ID = "1";
	//兑换积分
	private static final String EXCHANGE_INTEGRAL_ACCOUNT_ID = "2";
	//分红点
	private static final String BONUS_POINTS_ACCOUNT_ID = "3";
	//现金积分
	private static final String CASH_ACCOUNT_ID = "4";
	
	@Autowired
	private IUserMailService userMailService;
	
	@Autowired
	private IUserService userService;
	
    @Autowired
    private IUserAccountService userAccountService;
    
    @Autowired
    private IUserRoleService userRoleService;
    
    @Autowired
    private IDealLogService dealLogService;
    
    @Autowired
    private IUserVideoService userVideoService;
    
    @Autowired
    private IUserSmsService userSmsService;
    
    @Autowired
    private IUserVoiceService userVoiceService;
    
    @Autowired
    private IUserPicService userPicService;
    
    @Autowired
    @Qualifier("rolePermissionService")
    private IRolePermissionService rolePermissionService;
    
//    @Autowired
//    @Qualifier("mongoService")
//    private IMongoService mongoService;
    
    public final static List<String> permissions = new ArrayList<String>();
    
    static{
    	permissions.add("DataCenter0000");
    	permissions.add("CarManageCenter0000");
    	permissions.add("ApproveCenter0000");
    	permissions.add("OrderCenter0000");
    	permissions.add("SettleAccountCenter0000");
    	permissions.add("System000");
    	permissions.add("BodyApprove0001");
    	permissions.add("CarManage0001");
    	permissions.add("DictDataManage0000");
    	permissions.add("OrderManage0001");
    	permissions.add("SettleAccountManage0001");
    	permissions.add("TakeCashManage0001");
    	
    	permissions.add("BodyApprove0001");
    	permissions.add("CarManage0001");
    	permissions.add("CarQuery");
    	permissions.add("DictDataManage0000");
    	permissions.add("OrderExport");
    	permissions.add("OrderManage0001");
    	permissions.add("OrderQueryButton");
    	permissions.add("SettleAccountManage0001");
    	permissions.add("SettleAccountSet");
    	permissions.add("TakeCashBatch");
    	permissions.add("TakeCashExport");
    	permissions.add("TakeCashManage0001");
    	permissions.add("TakeCashQuery");
    }
    @RequestMapping(value="/index")
	public ModelAndView forwardUserIndex(HttpServletRequest request) throws ServletRequestBindingException {
    	ModelAndView mav = new ModelAndView("user/user_index");
		Map<String, Object> map = new HashMap<String, Object>();
		//临时添加session
//		map.put("userName", "13712055951");
//		List<UserEntity> queryList = userService.queryList(map);
//		if(queryList != null && queryList.size() > CommonConst.DIGIT_ZERO){
//			request.getSession().setAttribute(CommonConst.SESSION_USER, queryList.get(0));
//		}
		
		UserEntity user =  (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		if(user != null){
			map.put("userId", user.getUserId());
			//现金积分
			map.put("accountId", AccountEnum.RMB.getId());
			List<UserAccountEntity> comissionList = userAccountService.queryList(map);
			if(comissionList != null && comissionList .size() > CommonConst.DIGIT_ZERO){
				mav.addObject("comissionCount", comissionList.get(CommonConst.DIGIT_ZERO).getAccountAmount());
			}
			
			//购物积分
			map.put("accountId", AccountEnum.INTEGRAL.getId());
			List<UserAccountEntity> exchangeList = userAccountService.queryList(map);
			if(exchangeList != null && exchangeList .size() > CommonConst.DIGIT_ZERO){
				mav.addObject("exchangeCount", exchangeList.get(CommonConst.DIGIT_ZERO).getAccountAmount());
			}
			
			//分红点
			map.put("accountId", AccountEnum.RMB_COUPON.getId());
			List<UserAccountEntity> bonusList = userAccountService.queryList(map);
			if(bonusList != null && bonusList .size() > CommonConst.DIGIT_ZERO){
				mav.addObject("bonusCount", bonusList.get(CommonConst.DIGIT_ZERO).getAccountAmount());
			}
			
			//分红点
			map.put("accountId", AccountEnum.STOCK.getId());
			List<UserAccountEntity> cashList = userAccountService.queryList(map);
			
			if(cashList != null && cashList .size() > CommonConst.DIGIT_ZERO) {
				mav.addObject("cashCount", cashList.get(CommonConst.DIGIT_ZERO).getAccountAmount());
			}
			
			//推荐人数
			String userName = user.getUserName();
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userParentName", user.getUserName());
			int countUser = userService.countUser(paramMap);
		
			String wxid = user.getWxId();
			if(!org.springframework.util.StringUtils.isEmpty(wxid) && !wxid.equals(userName)) {
				paramMap.put("userParentName", user.getWxId());
				countUser = countUser + userService.countUser(paramMap);
			}
			mav.addObject("countUser", countUser);
			
//			List<SystemNoticeEntity> newList = new ArrayList<SystemNoticeEntity>();
//			if(list.size()>4){ //取最新四条
//				for (int i = 0; i < 4; i++) {
//					newList.add(list.get(i));
//				}
//				mav.addObject("noticeList", newList);
//			}else{
//				mav.addObject("noticeList", list);
//			}
			
		}
		return mav;
	}
    
	@RequestMapping(value="/changePassword", method=RequestMethod.GET)
	public ModelAndView forwardChangePassword(HttpServletRequest request) throws ServletRequestBindingException {
		Map<String, String> map = new HashMap<String, String>();
		
		return new ModelAndView("user/user_change_password", map);
	}
	
	@RequestMapping(value="/userRegister", method=RequestMethod.GET)
	public ModelAndView forwardUserRegister(HttpServletRequest request) throws ServletRequestBindingException {
		Map<String, String> map = new HashMap<String, String>();
		
		return new ModelAndView("user/user_register", map);
	}
	
	@RequestMapping(value = "/userPicList", method = RequestMethod.GET)
	public ModelAndView forwardUserPicView (HttpServletRequest request) {
		LOGGER.info("Enter into UserController forwardUserPicView..");
		ModelAndView mav = new ModelAndView("user/user_pic");
		LOGGER.info("Exit UserController forwardUserPicView..");
		return mav;
	}
	
	@RequestMapping(value = "/userSmsList", method = RequestMethod.GET)
	public ModelAndView forwardUserSmsList (HttpServletRequest request) {
		LOGGER.info("Enter into UserController forwardUserSmsList..");
		ModelAndView mav = new ModelAndView("user/user_sms");
		LOGGER.info("Exit UserController forwardUserSmsList..");
		return mav;
	}
	
	@RequestMapping(value = "/userVideoList", method = RequestMethod.GET)
	public ModelAndView forwardUserVideoList (HttpServletRequest request) {
		LOGGER.info("Enter into UserController forwardUserVideoList..");
		ModelAndView mav = new ModelAndView("user/user_video");
		LOGGER.info("Exit UserController forwardUserVideoList..");
		return mav;
	}
	
	@RequestMapping(value = "/userVoiceList", method = RequestMethod.GET)
	public ModelAndView forwardUserVoiceList (HttpServletRequest request) {
		LOGGER.info("Enter into UserController forwardUserVoiceList..");
		ModelAndView mav = new ModelAndView("user/user_voice");
		LOGGER.info("Exit UserController forwardUserVoiceList..");
		return mav;
	}
	
	@RequestMapping(value="/userActive", method=RequestMethod.GET)
	public ModelAndView forwardUserActive(HttpServletRequest request) throws ServletRequestBindingException {
		LOGGER.info("Entering userActive Controller...");
		String userName = request.getParameter("userName");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(null == userName || userName.trim().equals(CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS)) {
			LOGGER.info("userActive userName is null.");
			map.put("userList", null);
		} else {
			List<UserEntity> userList = userService.queryUnactiveUser(userName);
			
			if(null == userList || userList.isEmpty()) {
				map.put("userList", null);
			} else {
				for(int i = 0, len = userList.size(); i < len; i++) {
					userList.get(i).setUserMobile(userList.get(i).getUserMobile());
//					userList.get(i).setUserMobile(SecurityUtils.decryptText(userList.get(i).getUserMobile()));
				}
				map.put("userList", userList);
			}
		}
		LOGGER.info("Exiting userActive Controller...");
		return new ModelAndView("user/user_active", map);
	}
	
	@RequestMapping(value="/userGraph", method=RequestMethod.GET)
	public ModelAndView forwardUserGraph(HttpServletRequest request) throws ServletRequestBindingException {
		Map<String, String> map = new HashMap<String, String>();
		
		return new ModelAndView("user/user_graph", map);
	}
	
	@RequestMapping(value="/userInfo", method=RequestMethod.GET)
	public ModelAndView forwardUserInfo(HttpServletRequest request) throws ServletRequestBindingException {
		ModelAndView mav = new ModelAndView("user/user_info");
		UserEntity user =  (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		
		if(user != null) {
			
			List<UserEntity> list = null;
//			try {
////				Query query = new Query().addCriteria(new Criteria("userName").is(user.getUserName())); 
////				DBObject query = new BasicDBObject();
////				query.put("userName", user.getUserName());
////				list = userService.queryList(query, CommonConst.REDIS_MONGO_KEY_USER);
//				
//				MongoEntity mongo = new MongoEntity();
//				mongo.setCollectionName(CommonConst.REDIS_MONGO_KEY_USER);
//				mongo.setQuery(new BasicDBObject("userName", user.getUserName()).append("active", true));
//				list = userService.queryList(mongo);
//			} catch (Exception e) {
//				LOGGER.error("userService.queryPage in mongo exception");
//			}
			
			//mongo中无数据 将在数据库中查询
			if(list == null || list.size() == CommonConst.DIGIT_ZERO) {
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("userName", user.getUserName());
				list = userService.queryList(paramMap);
			}
			
			if(list != null && list.size() > CommonConst.DIGIT_ZERO) {
				mav.addObject("userInfo", list.get(CommonConst.DIGIT_ZERO));
			}
		}
		return mav;
	}
	
	
	@RequestMapping(value="/updateBank", method=RequestMethod.GET)
	public ModelAndView updateBank(HttpServletRequest request, Map<String, Object> dataMap) throws ServletRequestBindingException {
		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		if(user == null) {
			return new ModelAndView("redirect:/user/userLogin.do");
		}
		
		List<UserEntity> list = null;
//		try {
////			Query query = new Query().addCriteria(new Criteria("userName").is(user.getUserName()));
////			DBObject query = new BasicDBObject();
////			query.put("userName", user.getUserName());
////			list = userService.queryList(query, CommonConst.REDIS_MONGO_KEY_USER);
//			
//			MongoEntity mongo = new MongoEntity();
//			mongo.setCollectionName(CommonConst.REDIS_MONGO_KEY_USER);
//			mongo.setQuery(new BasicDBObject("userName", user.getUserName()).append("active", true));
//			list = userService.queryList(mongo);
//		} catch (Exception e) {
//			LOGGER.error("userService.queryPage in mongo exception");
//		}
		
		//mongo中无数据 将在数据库中查询
		if(list == null || list.size() == CommonConst.DIGIT_ZERO) {
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userName", user.getUserName());
			list = userService.queryList(paramMap);
		}
		
		//如果数据库中依然没有数据 跳转登录页面
		if(list == null || list.size() == CommonConst.DIGIT_ZERO) {
			return new ModelAndView("redirect:/user/userLogin.do");
		}
		dataMap.put("userEntity", list.get(0));
		return new ModelAndView("user/user_addaccount");
		
	}
	
	@RequestMapping(value="/userAccount", method=RequestMethod.GET)
	public ModelAndView userAccount(HttpServletRequest request, Map<String, Object> dataMap) throws ServletRequestBindingException {
		
		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		if(user == null) {
			return new ModelAndView("redirect:/user/userLogin.do");
		}
		
		List<UserEntity> list = null;
//		try {
////			Query query = new Query().addCriteria(new Criteria("userName").is(user.getUserName()));
////			DBObject query = new BasicDBObject();
////			query.put("userName", user.getUserName());
////			list = userService.queryList(query, CommonConst.REDIS_MONGO_KEY_USER);
//			
//			MongoEntity mongo = new MongoEntity();
//			mongo.setCollectionName(CommonConst.REDIS_MONGO_KEY_USER);
//			mongo.setQuery(new BasicDBObject("userName", user.getUserName()).append("active", true));
//			list = userService.queryList(mongo);
//		} catch (Exception e) {
//			LOGGER.error("userService.queryPage in mongo exception");
//		}
		
		//mongo中无数据 将在数据库中查询
		if(list == null || list.size() == CommonConst.DIGIT_ZERO) {
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userName", user.getUserName());
			list = userService.queryList(paramMap);
		}
		
		if(list == null || list.size() == CommonConst.DIGIT_ZERO) {
			return new ModelAndView("redirect:/user/userLogin.do");
		}
		
		UserEntity ue = list.get(0);
		if(StringUtils.isEmpty(ue.getUserBankName())) {
			//update by hexiang freemarker模板报错
			UserEntity entity = new UserEntity();
			dataMap.put("userEntity", entity);
			return new ModelAndView("user/user_addaccount");
		}else {
			dataMap.put("userEntity", ue);
			return new ModelAndView("user/user_account");
		}
		
	}
	
	@RequestMapping(value="/checkUserName", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkUserName(HttpServletRequest request) {
		
		try {
			LOGGER.info("Entering checkUserName...");
			String userName = request.getParameter("userName");
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			if(null == userName || userName.trim().equals(CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS)) {
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_NAME_IS_NULL);
				return map;
			}
		
			if(userName.trim().length() < CommonConst.DIGIT_SIX || userName.trim().length() > CommonConst.DIGIT_SIXTEEN) {
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_NAME_LENGTH_IS_INVALID);
				return map;
			}

			map.put("userName", userName);
			List<UserEntity> list = userService.queryList(map);
			
			if(null == list || list.isEmpty()) {
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_IS_NOT_EXIST);
			} else {
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
				map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.WARN_USER_IS_EXIST);
			}
			
			LOGGER.info("Exiting checkUserName...");
			return map;
		} catch (Exception e) {
			LOGGER.error("Check checkUserName error.");
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> register(HttpServletRequest request) {
		/*		
		if(StringUtils.isBlank(userName)) {
			LOGGER.info("Register username is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_NAME_IS_NULL);
			return map;
		}
		
		String userDesc = request.getParameter("userDesc");
		if(StringUtils.isEmpty(userDesc)) {
			LOGGER.info("Register userDesc is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_DESC_IS_NULL);
			return map;
		}*/
		
		/*String userParentName = request.getParameter("userParentName");
		if(StringUtils.isBlank(userParentName)) {
			LOGGER.info("Register userParentName is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_PARENT_NAME_IS_NULL);
			return map;
		}*/
		
		/*String userEmail = request.getParameter("userEmail");
		if(StringUtils.isBlank(userEmail)) {
			LOGGER.info("Register userEmail is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_MAIL_IS_NULL);
			return map;
		}*/
		Map<String, Object> map = new HashMap<String, Object>();
		String userMobile = request.getParameter("userMobile");
		if(StringUtils.isBlank(userMobile)) {
			LOGGER.info("Register userMobile is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_MOBILE_IS_NULL);
			return map;
		}
		
		map.put("userMobile", userMobile);
		List<UserEntity> list = userService.queryList(map);
		if(list != null && list.size() > 0){
			LOGGER.info("Register user is always.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_MOBILE_IS_ALWAYS);
			return map;
		}
		
		String userPassword = request.getParameter("userPassword");
		if(StringUtils.isBlank(userPassword)) {
			LOGGER.info("Register userPassword is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_PASSWORD_IS_NULL);
			return map;
		}
		
		/*String userSecondPassword = request.getParameter("userSecondPassword");
		if(StringUtils.isBlank(userSecondPassword)) {
			LOGGER.info("Register userSecondPassword is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_SECOND_PASSWORD_IS_NULL);
			return map;
		}*/
		
//		String userThirdPassword = request.getParameter("userThirdPassword");
//		if(StringUtils.isEmpty(userThirdPassword)) {
//			LOGGER.info("Register userThirdPassword is null.");
//			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
//			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_THIRD_PASSWORD_IS_NULL);
//			return map;
//		}
		
		UserEntity entity = new UserEntity();
		
		String userParentName = request.getParameter("userParentName");
		if(!StringUtils.isBlank(userParentName)){
			List<UserEntity> userlist = new ArrayList<UserEntity>();
//			try {
////				Query query = new Query().addCriteria(new Criteria("userName").is(userParentName));
////				DBObject query = new BasicDBObject();
////				query.put("userName", userParentName);
////				userService.queryList(query, CommonConst.REDIS_MONGO_KEY_USER);
//				
//				MongoEntity mongo = new MongoEntity();
//				mongo.setCollectionName(CommonConst.REDIS_MONGO_KEY_USER);
//				mongo.setQuery(new BasicDBObject("userName", userParentName).append("active", true));
//				list = userService.queryList(mongo);
//			} catch (Exception e) {
//				LOGGER.error("userService.queryList in mongo exception");
//			}
			
			if(null == userlist || userlist.isEmpty()){
				map.put("userName", userParentName);
				userlist = userService.queryList(map);
			}
			
			if(null == userlist || userlist.isEmpty()) {
				LOGGER.info("Register userParent is null.");
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_PARENT_IS_NULL);
				return map;
			} else {
				UserEntity parentUser = userlist.get(0);
				entity.setUserParentId(parentUser.getUserId());
				entity.setUserParentName(parentUser.getUserName());
				/*entity.setGenerationNo(String.valueOf(Integer.valueOf(entity.getGenerationNo()) + CommonConst.DIGIT_ONE));
				Map<String, Object> layerMap = new HashMap<String, Object>();
				layerMap.put("userId", entity.getUserParentId());
				List<UserEntity> layerList = userService.queryList(layerMap);
				String layerNo = String.valueOf(Integer.valueOf(layerList.get(CommonConst.DIGIT_ZERO).getLayerNo()) + CommonConst.DIGIT_ONE);
				entity.setLayerNo(layerNo);*/
			}
		}
		
		String code = request.getParameter("code");
		if(StringUtils.isBlank(code)) {
			LOGGER.info("Register code is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_VALIDATE_CODE_IS_NULL);
			return map;
		}
		
		Object oCode = request.getSession().getAttribute(CommonConst.SEND_CODE);
		if(oCode == null){
			LOGGER.info("Register session code is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_VALIDATE_CODE_IS_F);
			return map;
		}
		
		if(!oCode.toString().equals(code)){
			LOGGER.info("Register  code is error.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_VALIDATE_CODE_IS_F);
			return map;
		}
		
		entity.setId(StringUtil.produceUUID());
		entity.setUserId(userMobile);
		entity.setUserName(userMobile);
		//entity.setUserDesc(userDesc);
		//entity.setUserEmail(userEmail);
		//entity.setUserEmailBindingStatus(false);
		entity.setUserMobile(userMobile);
//		entity.setUserPassword(SecurityUtils.encryptText(userPassword));
		entity.setUserPassword(userPassword);
		//entity.setUserSecondPassword(SecurityUtils.encryptText(userSecondPassword));
		entity.setUserMobile(userMobile);
		entity.setCreateTime(new Date());
		entity.setActive(true);
//		int insertResult = userService.insert(entity, CommonConst.REDIS_MONGO_KEY_USER);
		int insertResult = userService.insert(entity);
		if(insertResult > CommonConst.DIGIT_ZERO) {
			UserRoleEntity ure = new UserRoleEntity();
			ure.setActive(true);
			ure.setId(StringUtil.produceUUID());
			ure.setCreateTime(new Date());
			ure.setRoleId("1");
			ure.setRoleName("普通用户");
			ure.setUserId(userMobile);
			ure.setUserName(userMobile);
			ure.setVersion(1);
//			userRoleService.insert(ure, CommonConst.REDIS_MONGO_KEY_USER_ROLE);
			userRoleService.insert(ure);
			
			DealLogEntity log = new DealLogEntity();
			String ipAddr = DataUtils.getIpAddr(request);
			log.setActive(true);
			log.setId(StringUtil.produceUUID());
			log.setCreateTime(new Date());
			log.setLogType(LogTypeEnum.LOG_TYPE_REGISTER_INFO.getId());
			log.setLogDesc(LogTypeEnum.LOG_TYPE_REGISTER_INFO.getValue());
			log.setUserId(userMobile);
			log.setUserName(userMobile);
			log.setLogIp(ipAddr);
			dealLogService.insert(log);
			
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.REMINDER_REGISTER_SUCCESS);
			map.put("userName", userMobile);
			request.getSession().setAttribute(CommonConst.SESSION_USER, entity);
		} else {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.REMINDER_REGISTER_FAIL);
		}
		
		return map;
	}
	
	/**
	 * 用户登录跳转
	 * 
	 * @author zhxihu2008
	 * @since 2016-07-04 11:31
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> userLogin(HttpServletRequest request) {
		String loginName = request.getParameter("loginName");
		Map<String,Object> map = new HashMap<String, Object>();
		if(null == loginName || loginName.trim().equals(CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS)) {
			LOGGER.info("user Login login name is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_NAME_IS_NULL);
		    return map;
		    
		}
		
		loginName = loginName.trim().toLowerCase();
		
		String password = request.getParameter("password");
		
		if(null == password || password.trim().equals(CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS)) {
			LOGGER.info("user Login passsword is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_PASSWORD_IS_NULL);
			return map;
		}
		
		BasicDBObject obj =new BasicDBObject();
		if(loginName.contains(CommonConst.PUNCTUATION_EMAIL_SIGN)) {
			map.put("userEmail", loginName);
			obj.put("userEmail", loginName);
		} else {
			map.put("userName", loginName);
			obj.put("userName",loginName);
		}
		
		UserEntity entity =  userService.validateUser(map);
		
		if(null == entity){
			map.clear();
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_IS_NOT_EXIST);
			return map;
		}
		
		String dbPassword = entity.getUserPassword();
		if(StringUtils.isBlank(dbPassword)) {
			LOGGER.info("validateUser entity from db is null.");
			map.clear();
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_IS_NOT_EXIST);
			return map;
		}
		
		//SecurityUtils.encryptText(password)
		if(password.equals(dbPassword)) {
			LOGGER.info("validateUser success.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		} else {
			LOGGER.info("validateUser failed.");
			map.clear();
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_PASSWORD_IS_NOT_MATCH);
			return map;
		}
		
		//登录用户为乘客 直接退出
		map.clear();
		map.put("userName", entity.getUserName());
		List<UserRoleEntity> queryUserRoleList = userRoleService.queryList(map);
		if(null == queryUserRoleList || queryUserRoleList.size() == CommonConst.DIGIT_ZERO) {
			map.clear();
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_ROLE_IS_NULL);
			return map;
		}
		
		UserRoleEntity userRoleEntity = queryUserRoleList.get(CommonConst.DIGIT_ZERO);
		if(null == userRoleEntity || null == userRoleEntity.getRoleId()) {
			map.clear();
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_ROLE_IS_NULL);
			return map;
		}
		
		String roleId = userRoleEntity.getRoleId();
		if(null == roleId || StringUtils.isBlank(roleId) || roleId.equals(CommonConst.STRING_TWO)) {
			LOGGER.info("fare login failed.");
			map.clear();
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_FARE_NOT_LOGIN);
			return map;
		} 
		
		request.getSession().setAttribute(CommonConst.SESSION_USER, entity);
		
		Map<String,Object> parmMap = new HashMap<String, Object>();
		parmMap.put("userName", entity.getUserName());
		List<UserRoleEntity> userRoleList = userRoleService.queryList(parmMap);
		//request.getSession().setAttribute(CommonConst.SESSION_ROLE, userRoleList.get(0));
		
		request.getSession().setAttribute(CommonConst.SESSION_USER, entity);
		List<String> userPermissions = CommonConst.USER_PERMISSION_CACHE_MAP
				.get(entity.getUserName());
		if (userPermissions == null || userPermissions.isEmpty()) {
			rolePermissionService.saveAllUserPermissionInSession();
			userPermissions = CommonConst.USER_PERMISSION_CACHE_MAP
					.get(entity.getUserName());

			if (userPermissions == null || userPermissions.isEmpty()) {
				userPermissions = this.permissions;
			}
		}
		request.getSession().setAttribute(CommonConst.PERMISSION, userPermissions);
//			LogEntity log = new LogEntity();
//			String ipAddr = IPUtils.getIpAddr(request);
//			log.setId(com.mmp.util.StringUtils.produceUUID());
//			log.setActive(true);
//			log.setCreateTime(new Date());
//			log.setLogIP(ipAddr);
//			log.setLogDesc(LogTypeEnum.LOG_TYPE_LOGIN_INFO.getValue());
//			log.setLogType(LogTypeEnum.LOG_TYPE_LOGIN_INFO.getId());
//			log.setUserId(entity.getUserId());
//			log.setUserName(entity.getUserName());
//			logService.insert(log,CommonConst.MONGO_KEY_LOG);
//		}
		
		map.clear();
		map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		return map;
	}
	
	@RequestMapping(value="/activeUser", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> activeUser(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(null == userName || userName.trim().equals(CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS)) {
			LOGGER.info("activeUser Controller userName is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_NAME_IS_NULL);
			return map;
		}
		
		LOGGER.info("Entering activeUser Controller...");
		UserEntity entity = new UserEntity();
		entity.setUserName(userName);
		entity.setActive(true);
		int activeResult = userService.update(entity);
		
		if(activeResult > CommonConst.DIGIT_ZERO) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.REMINDER_ACTIVE_SUCCESS);
		} else {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.REMINDER_ACTIVE_FAIL);
		}
		LOGGER.info("Exiting activeUser Controller...");
		return map;
	}
	
	/**
	 * 物理删除
	 * @param request
	 * @return
	 */
//	@RequestMapping(value="/deleteUser", method=RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> deleteUser(HttpServletRequest request) {
//		String userName = request.getParameter("userName");
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		if(null == userName || userName.trim().equals(CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS)) {
//			LOGGER.info("deleteUser Controller userName is null.");
//			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
//			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_NAME_IS_NULL);
//			return map;
//		}
//		
//		LOGGER.info("Entering deleteUser Controller...");
//		UserEntity entity = new UserEntity();
//		entity.setUserName(userName);
//		int deleteResult = userService.deleteUser(entity);
//		
//		if(deleteResult > CommonConst.DIGIT_ZERO) {
//			DBObject query = new BasicDBObject();
//			query.put("userName", userName);
//			userService.remove(query, CommonConst.REDIS_MONGO_KEY_USER);
//			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
//			map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.REMINDER_USER_DELETE_SUCCESS);
//		} else {
//			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
//			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.REMINDER_USER_DELETE_FAIL);
//		}
//		LOGGER.info("Exiting deleteUser Controller...");
//		return map;
//	}
	
	@RequestMapping(value="/userLogin", method=RequestMethod.GET)
	public ModelAndView forwardUserLogin(HttpServletRequest request) throws ServletRequestBindingException {
		Map<String, String> map = new HashMap<String, String>();
		return new ModelAndView("user/user_login", map);
	}
	
	@RequestMapping(value="/toChangePassword", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changePassword(HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String passwordType = request.getParameter("passwordType");
		
		UserEntity sessionUser =  (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		
		if(StringUtils.isBlank(passwordType)) {
			LOGGER.info("Get passwordType is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_PASSWORD_TYPE_IS_NULL);
			return map;
		}
		
		int passwordtype_int = Integer.parseInt(passwordType);
		
		if(passwordtype_int == CommonConst.DIGIT_ZERO){
			LOGGER.info("Not select password type.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_PASSWORD_TYPE_IS_NULL);
			return map;
		}
		
		String password = request.getParameter("password");
		if(StringUtils.isBlank(password)) {
			LOGGER.info("ChangePassword primary password is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_PASSWORD_IS_NULL);
			return map;
		}
		
		password = SecurityUtils.encryptText(password);
		if(sessionUser!=null){
			map.put("userName", sessionUser.getUserName());
		}
		if(passwordtype_int == 1){
			map.put("userPassword", password);
		}else if(passwordtype_int == 2){
			map.put("userSecondPassword", password);
		}else{
			LOGGER.info("Get passwordType is not exist.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_PASSWORD_TYPE_IS_NULL);
			return map;
		}
		UserEntity user = userService.validateUser(map);
		if(null == user) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.ERROR_USER_PRIMARY_PASSWORD);
			return map;
		}
		
		String id = user.getId();
		String newpassword = request.getParameter("newpassword");
		newpassword = SecurityUtils.encryptText(newpassword);
		if(StringUtils.isBlank(newpassword)) {
			LOGGER.info("ChangePassword newpassword is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_NEW_PASSWORD_IS_NULL);
			return map;
		}
		
		UserEntity entity = new UserEntity();
//		Update update = new Update();
		//DBObject update = new BasicDBObject();
		BasicDBObject update = new BasicDBObject();
		entity.setId(id);
		entity.setUserName(sessionUser.getUserName());
		entity.setActive(true);
		if(passwordtype_int == 1){
			entity.setUserPassword(newpassword);
			update.put("userPassword", newpassword);
		}else if(passwordtype_int == 2){
			update.put("userSecondPassword", newpassword);
		}else{
			LOGGER.info("Get passwordType is not exist.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_PASSWORD_TYPE_IS_NULL);
			return map;
		}
		
		int updateResult = userService.update(entity);
		if(updateResult > CommonConst.DIGIT_ZERO) {
			
			//userService.update(new BasicDBObject("userName",entity.getUserName()), update, CommonConst.REDIS_MONGO_KEY_USER);
			
			
//			MongoEntity mongo = new MongoEntity();
//			mongo.setCollectionName(CommonConst.REDIS_MONGO_KEY_USER);
//			mongo.setUpdate(new BasicDBObject("userName", entity.getUserName()));
//			mongo.setUpdate(update);
//			userService.update(mongo);
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.REMINDER_CHANGE_PASSWORD_SUCCESS);
		} else {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.REMINDER_CHANGE_PASSWORD_FAIL);
		}
		
		return map;
	}
	
//	@SuppressWarnings("unused")
//	private String produceUserId(String userName) {
//		if(null == userName || userName.trim().equals(CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS)) {
//			LOGGER.info("produceUserId userName is null.");
//			return null;
//		}
//
//		if(CommonConst.USER_CACHE_MAP.isEmpty()) {
//			CacheManager.getInstance().loadUserCache();
//		}
//		
//		List<String> list = new ArrayList<String>();
//		list.add(userName);
//		
//		while (true) {
//
//			if(null == list || list.isEmpty()) {
//				break;
//			}
//			
//			for(int i = 0, len = list.size(); i < len; i++) {
//				String tempUserName = list.get(i);
//				String firstUserId = tempUserName + CommonConst.PUNCTUATION_NUMBER_SIGN + CommonConst.DIGIT_ZERO;
//				String secondUserId = tempUserName + CommonConst.PUNCTUATION_NUMBER_SIGN + CommonConst.DIGIT_ONE;
//				String thirdUserId = tempUserName + CommonConst.PUNCTUATION_NUMBER_SIGN + CommonConst.DIGIT_TWO;
//				
//				if(!CommonConst.USER_CACHE_MAP.containsKey(firstUserId)) {
//					return firstUserId;
//				}
//				
//				if(!CommonConst.USER_CACHE_MAP.containsKey(secondUserId)) {
//					return secondUserId;
//				}
//
//				if(!CommonConst.USER_CACHE_MAP.containsKey(thirdUserId)) {
//					return thirdUserId;
//				}
//				
//				list.add((String) CommonConst.USER_CACHE_MAP.get(firstUserId));
//				list.add((String) CommonConst.USER_CACHE_MAP.get(secondUserId));
//				list.add((String) CommonConst.USER_CACHE_MAP.get(thirdUserId));
//			}
//		}
//		return null;
//	}
	
	/**
	 * 获取当前用户下的会员页面
	 * 进入途径 1.首页菜单会员管理-会员查询  通过session传当前用户
	 *       2.系统管理 - 会员管理  里查询操作，直接传递要查看的会员name
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value="/userSearch", method=RequestMethod.GET)
	public ModelAndView findUserList(HttpServletRequest request) throws ServletRequestBindingException {
		ModelAndView mav = new ModelAndView("user/user_list");
		String userName = request.getParameter("userName");
		String wxId = "";
		//获取userName参数  如没有  获取session中的userName
		if(StringUtils.isEmpty(userName)){
			UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
			if(user != null){
				userName = user.getUserName();
				wxId = user.getWxId();
			}
		}
		
		List<UserEntity> list = new ArrayList<UserEntity>();
		if(!StringUtils.isEmpty(userName)){
			//查询mongo
//			Query query = new Query();
//			query.addCriteria(new Criteria("userParentName").is(userName));
//			DBObject query = new BasicDBObject();
//			query.put("userParentName", userName);
//			list = userService.queryList(query, CommonConst.REDIS_MONGO_KEY_USER);
			
//			MongoEntity mongo = new MongoEntity();
//			mongo.setCollectionName(CommonConst.REDIS_MONGO_KEY_USER);
//			mongo.setQuery(new BasicDBObject("userParentName", userName).append("active", true));
//			list = userService.queryList(mongo);
			
			//mongo数据库无数据  查询mysql
			if(list == null || list.size() == CommonConst.DIGIT_ZERO){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userParentName", userName);
				list = userService.queryList(map);
			}
		}
		
		List<UserEntity> listByWxId = new ArrayList<UserEntity>();
		if(!StringUtils.isEmpty(wxId) && !wxId.equals(userName)){
			//以微信号查询mongo
//			Query queryByWxId = new Query();
//			queryByWxId.addCriteria(new Criteria("userParentName").is(wxId));
//			DBObject queryByWxId = new BasicDBObject();
//			queryByWxId.put("userParentName", wxId);
//			listByWxId = userService.queryList(queryByWxId, CommonConst.REDIS_MONGO_KEY_USER);
		}
		
		if(listByWxId == null || listByWxId.size() <= 0){
			mav.addObject("userList", list);
			return mav;
		}
		
		for(UserEntity u:listByWxId){
			list.add(u);
		}
		
		mav.addObject("userList", list);
		return mav;
	}
	
	/**
	 * 编辑用户
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value="/editUser", method=RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request) throws ServletRequestBindingException {
		ModelAndView mav = new ModelAndView("user/user_edit");
		String userName = request.getParameter("userName");
		UserEntity entity = new UserEntity();
		if(!StringUtils.isEmpty(userName)){
			List<UserEntity> list = new ArrayList<UserEntity>();
			
//			try {
////				DBObject query = new BasicDBObject();
////				query.put("userName", userName);
////				list = userService.queryList(query, CommonConst.REDIS_MONGO_KEY_USER);
//				MongoEntity mongo = new MongoEntity();
//				mongo.setCollectionName(CommonConst.REDIS_MONGO_KEY_USER);
//				mongo.setQuery(new BasicDBObject("userName", userName).append("active", true));
//				list = userService.queryList(mongo);
//			} catch (Exception e) {
//				LOGGER.error("editUser userService.queryList in mongo exception");
//			}
			
			if(list == null || list.isEmpty()){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("userName", userName);
				list = userService.queryList(map);
			}
			if(list != null && list.size() > CommonConst.DIGIT_ZERO){
				entity = list.get(CommonConst.DIGIT_ZERO);
			}
		}
		mav.addObject("userEntity", entity);
		return mav;
	}
	
	@RequestMapping(value="/editSubmit", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editSubmit(HttpServletRequest request) {
		
		String userName = request.getParameter("userName");
		String userDegreeId = request.getParameter("userDegreeId");
		String userDesc = request.getParameter("userDesc");
		String userEmail = request.getParameter("userEmail");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		UserEntity entity = new UserEntity();
		
		entity.setId(StringUtil.produceUUID());
		entity.setUserName(userName);
		entity.setUserDegreeId(userDegreeId);
//		entity.setUserDegreeName(UserDegreeEnum.getDegreeNameByLevelId(userDegreeId));
		entity.setUserDesc(userDesc);
		entity.setUserEmail(userEmail);
		entity.setUpdateTime(new Date());
		
		int updateResult = userService.update(entity);
		
		if(updateResult > CommonConst.DIGIT_ZERO) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.REMINDER_EDIT_SUCCESS);
			map.put("userName", userName);
		} else {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.REMINDER_EDIT_FAIL);
		}
		
		return map;
	}
	
	/**
	 * 逻辑删除
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delUser", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delUser(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(null == userName || userName.trim().equals(CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS)) {
			LOGGER.info("deleteUser Controller userName is null.");
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_NAME_IS_NULL);
			return map;
		}
		
		LOGGER.info("Entering deleteUser Controller...");
		UserEntity entity = new UserEntity();
		entity.setUserName(userName);
		entity.setActive(true);
		int deleteResult = userService.delete(entity);
		
		if(deleteResult > CommonConst.DIGIT_ZERO) {
			DBObject query = new BasicDBObject();
			query.put("userName", userName);
//			userService.remove(query, CommonConst.REDIS_MONGO_KEY_USER);
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.REMINDER_USER_DELETE_SUCCESS);
		} else {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.REMINDER_USER_DELETE_FAIL);
		}
		LOGGER.info("Exiting deleteUser Controller...");
		return map;
	}

	
	@RequestMapping(value="/editInfo", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editInfo(HttpServletRequest request) {
		
		String userName = request.getParameter("userName");
		String userMobile = request.getParameter("userMobile");
		String IDCardNo = request.getParameter("IDCardNo");
		String userEmail = request.getParameter("userEmail");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		UserEntity entity = new UserEntity();
		
		entity.setUserName(userName);
		entity.setActive(true);
		entity.setUserMobile(userMobile);
		entity.setUserIdNum(IDCardNo);
		entity.setUserEmail(userEmail);
		entity.setUpdateTime(new Date());
		
		int updateResult = userService.update(entity);
		
//		Update update = new Update();
//		update.set("userMobile", userMobile);
//		update.set("userIdNum", IDCardNo);
//		update.set("userEmail", userEmail);
//		update.set("updateTime", new Date());
		Map<String,Object> mongoMap = new HashMap<String,Object>();
		mongoMap.put("userMobile", userMobile);
		mongoMap.put("userIdNum", IDCardNo);
		mongoMap.put("userEmail", userEmail);
		mongoMap.put("updateTime", new Date());
		if(updateResult > CommonConst.DIGIT_ZERO) {
//			DBObject query = new BasicDBObject();
//			query.put("userName", userName);
//			DBObject update = new BasicDBObject(mongoMap);
//			
//			userService.update(query, update, CommonConst.REDIS_MONGO_KEY_USER);
			
//			BasicDBObject update = new BasicDBObject(mongoMap);
//			MongoEntity mongo = new MongoEntity();
//			mongo.setCollectionName(CommonConst.REDIS_MONGO_KEY_USER);
//			mongo.setUpdate(new BasicDBObject("userName", userName));
//			mongo.setUpdate(update);
//			userService.update(mongo);
			
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.REMINDER_EDIT_SUCCESS);
			map.put("userName", userName);
		} else {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.REMINDER_EDIT_FAIL);
		}
		
		return map;
	}
	
	/**
	 * 发短信
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sendCode", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> sendCode(HttpServletRequest request) {
		
		Map<String,String> map = new HashMap<String,String>();
		String mobile = request.getParameter("mobile");
		if(org.springframework.util.StringUtils.isEmpty(mobile)){
			map.put("result", "F");
			return map;
		}
		 
		String code = StringUtil.randomNumber(6);
		request.getSession().setAttribute(CommonConst.SEND_CODE, code);
		String message = "【探米店商】 你本次注册的验证码" + code+"，在15分钟内有效，千万不要告诉别人哟！" ;
		int status = StringUtil.send(mobile, message);
		
		if(status == 1){
			map.put("result", "S");
		}else{
			map.put("result", "F");
		}
		map.put("result", "S");
		
		return map;
	}
	
	/**
	 * 编辑用户
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request) throws ServletRequestBindingException {
//		appUserService.httpQueryInternational();
		//appUserService.httpAppUser(new Date());
		//appUserService.httpAppSeller(new Date());
		//appUserService.queryAppSellerToMMP(new Date());
		//appUserService.queryAppUserToMMP(new Date());
		//appUserService.award();
		//appUserService.jdbcCHIHUO();
		return null;
		
		
	}
	
	@RequestMapping(value="/userDetailInfo", method=RequestMethod.GET)
	public ModelAndView forwardUserDetailInfo(HttpServletRequest request) throws ServletRequestBindingException {
		ModelAndView mav = new ModelAndView("user/user_detailinfo");
		String userName = request.getParameter("name");
		
		List<UserEntity> userList = new ArrayList<UserEntity>();
		Map<String,Object> map = new HashMap<String, Object>();
//		DBObject query = new BasicDBObject();
//		query.put("userName", userName);
//		userList = userService.queryList(query, CommonConst.REDIS_MONGO_KEY_USER);
		
//		MongoEntity mongo = new MongoEntity();
//		mongo.setCollectionName(CommonConst.REDIS_MONGO_KEY_USER);
//		mongo.setQuery(new BasicDBObject("userName", userName).append("active", true));
//		userList = userService.queryList(mongo);
		
		if(userList == null || userList.size() == CommonConst.DIGIT_ZERO){
			map.put("userName", userName);
			userList = userService.queryList(map);
		}
		
		if(userList != null && userList.size() > CommonConst.DIGIT_ZERO){
			UserEntity user = userList.get(CommonConst.DIGIT_ZERO);
			mav.addObject("userInfo", userList.get(CommonConst.DIGIT_ZERO));
			
			map.clear();
			map.put("userId", user.getUserId());
			//佣金积分
			map.put("accountId", COMISSION_INTEGRAL_ACCOUNT_ID);
			List<UserAccountEntity> comissionList = userAccountService.queryList(map);
			if(comissionList != null && comissionList .size() > CommonConst.DIGIT_ZERO){
				mav.addObject("comissionCount", comissionList.get(CommonConst.DIGIT_ZERO).getAccountAmount());
			}
			
			//兑换积分
			map.put("accountId", EXCHANGE_INTEGRAL_ACCOUNT_ID);
			List<UserAccountEntity> exchangeList = userAccountService.queryList(map);
			if(exchangeList != null && exchangeList .size() > CommonConst.DIGIT_ZERO){
				mav.addObject("exchangeCount", exchangeList.get(CommonConst.DIGIT_ZERO).getAccountAmount());
			}
			
			//分红点
			map.put("accountId", BONUS_POINTS_ACCOUNT_ID);
			List<UserAccountEntity> bonusList = userAccountService.queryList(map);
			if(bonusList != null && bonusList .size() > CommonConst.DIGIT_ZERO){
				mav.addObject("bonusCount", bonusList.get(CommonConst.DIGIT_ZERO).getAccountAmount());
			}
		}else{
			mav.addObject("userInfo", new UserEntity());
		}
		return mav;
	}
	
	
	@RequestMapping(value="/checkMobile", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkMobile(HttpServletRequest request) {
		
		try {
			LOGGER.info("Entering checkMobile...");
			String userName = request.getParameter("userName");
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			

			map.put("userMobile", userName);
			List<UserEntity> list = userService.queryList(map);
			
			if(null == list || list.isEmpty()) {
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_IS_NOT_EXIST);
			} else {
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
				map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.WARN_USER_IS_EXIST);
			}
			
			LOGGER.info("Exiting checkUserName...");
			return map;
		} catch (Exception e) {
			LOGGER.error("Check checkUserName error.");
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/userList", method=RequestMethod.GET)
	public ModelAndView forwardProductList(HttpServletRequest request) throws ServletRequestBindingException {
		ModelAndView mav = new ModelAndView("user/user_list");
		
		List<UserEntity> list = new ArrayList<UserEntity>();
//		try {
//			//permissionService.queryList(new BasicDBObject(), CommonConst.MONGO_KEY_PERMISSION);
//			
//			MongoEntity mongo = new MongoEntity();
//			mongo.setCollectionName(CommonConst.REDIS_MONGO_KEY_USER);
//			list = userService.queryList(mongo);
//		} catch (Exception e) {
//			LOGGER.error("permissionService.queryList in mongo exception");
//		}
		
		if(null == list || list.size() == CommonConst.DIGIT_ZERO) {
			list = userService.queryAll();
		}
		mav.addObject("userList", list);
		
		return mav;
	}
	
//	@RequestMapping(value="/userFavouriteSearch", method=RequestMethod.GET)
//	public ModelAndView findUserFavouriteList(HttpServletRequest request) throws ServletRequestBindingException {
//		ModelAndView mav = new ModelAndView("user/user_favourite");
//		String userName = request.getParameter("userName");
//		//获取userName参数  如没有  获取session中的userName
//		if(StringUtils.isEmpty(userName)){
//			UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
//			if(user != null){
//				userName = user.getUserName();
//			}
//		}
//		List<UserFavouriteEntity> list = new ArrayList<UserFavouriteEntity>();
//		if(!StringUtils.isEmpty(userName)){
//			MongoEntity mongo = new MongoEntity();
//			mongo.setCollectionName(CommonConst.MONGO_KEY_USER_FAVOURITE);
//			mongo.setQuery(new BasicDBObject("userName", userName).append("active", true));
//			list = userFavouriteService.queryList(mongo);
//			//mongo数据库无数据  查询mysql
//			if(list == null || list.size() == CommonConst.DIGIT_ZERO){
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("userName", userName);
//				list = userFavouriteService.queryList(map);
//			}
//		}
//		mav.addObject("userFavouriteList", list);
//		return mav;
//	}
	
	
//	@RequestMapping(value="/userMessageSearch", method=RequestMethod.GET)
//	public ModelAndView findUserMessageList(HttpServletRequest request) throws ServletRequestBindingException {
//		ModelAndView mav = new ModelAndView("user/user_message");
//		String userName = request.getParameter("userName");
//		//获取userName参数  如没有  获取session中的userName
//		if(StringUtils.isEmpty(userName)){
//			UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
//			if(user != null){
//				userName = user.getUserName();
//			}
//		}
//		List<UserReceiveMessageEntity> list = new ArrayList<UserReceiveMessageEntity>();
//		if(!StringUtils.isEmpty(userName)){
//			MongoEntity mongo = new MongoEntity();
//			mongo.setCollectionName(CommonConst.MONGO_KEY_USER_MESSAGE);
//			mongo.setQuery(new BasicDBObject("userName", userName).append("active", true));
//			list = userReceiveMessageService.queryList(mongo);
//			//mongo数据库无数据  查询mysql
//			if(list == null || list.isEmpty()){
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("userName", userName);
//				list = userReceiveMessageService.queryList(map);
//			}
//		}
//		mav.addObject("userMessageList", list);
//		return mav;
//	}
//	
	
	@RequestMapping(value="/userMailSearch", method=RequestMethod.GET)
	public ModelAndView findUserMailList(HttpServletRequest request) throws ServletRequestBindingException {
		ModelAndView mav = new ModelAndView("user/user_mail");
		String userName = request.getParameter("userName");
		//获取userName参数  如没有  获取session中的userName
		if(StringUtils.isEmpty(userName)){
			UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
			if(user != null){
				userName = user.getUserName();
			}
		}
		List<UserMailEntity> list = new ArrayList<UserMailEntity>();
		if(!StringUtils.isEmpty(userName)){
//			MongoEntity mongo = new MongoEntity();
//			mongo.setCollectionName(CommonConst.MONGO_KEY_USER_MAIL);
//			mongo.setQuery(new BasicDBObject("userName", userName).append("active", true));
//			list = userMailService.queryList(mongo);
			//mongo数据库无数据  查询mysql
			if(list == null || list.isEmpty()){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userName", userName);
				list = userMailService.queryList(map);
			}
		}
		mav.addObject("userMailList", list);
		return mav;
	}
	
//	@RequestMapping(value="/getReceiveMessageList", method=RequestMethod.POST)
//	@ResponseBody
//	public String getReceiveMessageList(HttpServletRequest request, @RequestParam String aoData){
//
//		JSONArray jsonarray = JSONArray.parseArray(aoData);
//		String sEcho = null;
//		int iDisplayStart = 0; // 起始索引
//		int iDisplayLength = 0; // 每页显示的行数
//		String sSearch = "";
//		
//		for (int i = 0; i < jsonarray.size(); i++) {
//			JSONObject obj = (JSONObject) jsonarray.get(i);
//			if (obj.get("name").equals("sEcho"))
//				sEcho = obj.get("value").toString();
//
//			if (obj.get("name").equals("iDisplayStart"))
//				iDisplayStart = obj.getIntValue("value");
//
//			if (obj.get("name").equals("iDisplayLength"))
//				iDisplayLength = obj.getIntValue("value");
//			
//			if (obj.get("name").equals("sSearch"))
//				sSearch = obj.get("value").toString();
//		}
//		
//		//List<UserReceiveMessageEntity> receiveMessageList = new ArrayList<UserReceiveMessageEntity>();
//		int allCount = 0;
//		Page page = new Page();
//		page.setPageNo(iDisplayStart/iDisplayLength+1);
//		page.setPageSize(iDisplayLength);
//		BasicDBObject query = new BasicDBObject();
//		BasicDBList basicDBList = new BasicDBList();
//		if(!StringUtils.isEmpty(sSearch)){
//			basicDBList.add(new BasicDBObject("userName",sSearch));
//			basicDBList.add(new BasicDBObject("userParentName",sSearch));
//			query.put("$or", basicDBList);
//		}
//		if(null == receiveMessageList || receiveMessageList.isEmpty()) {
//			Map<String,Object> mapParam = new HashMap<String, Object>();
//			PageList<UserReceiveMessageEntity> pageList = userReceiveMessageService.queryPage(page, mapParam);
//			if(pageList != null){
//				receiveMessageList = pageList.getList();
//				allCount = (int) pageList.getPage().getTotalCount();
//			}
//		}
//		JSONObject getObj = new JSONObject();
//		getObj.put("sEcho", sEcho);
//		getObj.put("iTotalRecords", allCount);//实际的行数
//		getObj.put("iTotalDisplayRecords", allCount);//显示的行数,这个要和上面写的一样
//		
//		getObj.put("aaData", receiveMessageList);//要以JSON格式返回
//		return getObj.toString();
//	}
	
	@RequestMapping(value="/getUserVideoList", method=RequestMethod.POST)
	@ResponseBody
	public String getUserVideoList(HttpServletRequest request, @RequestParam String aoData){

		JSONArray jsonarray = JSONArray.parseArray(aoData);
		String sEcho = null;
		int iDisplayStart = 0; // 起始索引
		int iDisplayLength = 0; // 每页显示的行数
		String sSearch = "";
		
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject obj = (JSONObject) jsonarray.get(i);
			if (obj.get("name").equals("sEcho"))
				sEcho = obj.get("value").toString();

			if (obj.get("name").equals("iDisplayStart"))
				iDisplayStart = obj.getIntValue("value");

			if (obj.get("name").equals("iDisplayLength"))
				iDisplayLength = obj.getIntValue("value");
			
			if (obj.get("name").equals("sSearch"))
				sSearch = obj.get("value").toString();
		}
		
		List<UserViedoEntity> videoList = new ArrayList<UserViedoEntity>();
		int allCount = 0;
		Page page = new Page();
		page.setPageNo((iDisplayStart-1)/iDisplayLength+1);
		page.setPageSize(iDisplayLength);
		BasicDBObject query = new BasicDBObject();
		BasicDBList basicDBList = new BasicDBList();
		if(!StringUtils.isEmpty(sSearch)){
			basicDBList.add(new BasicDBObject("userName",sSearch));
			basicDBList.add(new BasicDBObject("userParentName",sSearch));
			query.put("$or", basicDBList);
		}
		if(null == videoList || videoList.isEmpty()) {
			Map<String,Object> mapParam = new HashMap<String, Object>();
			PageList<UserViedoEntity> pageList = userVideoService.queryPage(page, mapParam);
			if(pageList != null){
				videoList = pageList.getList();
				allCount = (int) pageList.getPage().getTotalCount();
			}
		}
		JSONObject getObj = new JSONObject();
		getObj.put("sEcho", sEcho);
		getObj.put("iTotalRecords", allCount);//实际的行数
		getObj.put("iTotalDisplayRecords", allCount);//显示的行数,这个要和上面写的一样
		
		getObj.put("aaData", videoList);//要以JSON格式返回
		return getObj.toString();
	}
	
	@RequestMapping(value="/getUserVoiceList", method=RequestMethod.POST)
	@ResponseBody
	public String getUserVoiceList(HttpServletRequest request, @RequestParam String aoData){

		JSONArray jsonarray = JSONArray.parseArray(aoData);
		String sEcho = null;
		int iDisplayStart = 0; // 起始索引
		int iDisplayLength = 0; // 每页显示的行数
		String sSearch = "";
		
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject obj = (JSONObject) jsonarray.get(i);
			if (obj.get("name").equals("sEcho"))
				sEcho = obj.get("value").toString();

			if (obj.get("name").equals("iDisplayStart"))
				iDisplayStart = obj.getIntValue("value");

			if (obj.get("name").equals("iDisplayLength"))
				iDisplayLength = obj.getIntValue("value");
			
			if (obj.get("name").equals("sSearch"))
				sSearch = obj.get("value").toString();
		}
		
		List<UserVoiceEntity> voiceList = new ArrayList<UserVoiceEntity>();
		int allCount = 0;
		Page page = new Page();
		page.setPageNo((iDisplayStart-1)/iDisplayLength+1);
		page.setPageSize(iDisplayLength);
		BasicDBObject query = new BasicDBObject();
		BasicDBList basicDBList = new BasicDBList();
		if(!StringUtils.isEmpty(sSearch)){
			basicDBList.add(new BasicDBObject("userName",sSearch));
			basicDBList.add(new BasicDBObject("userParentName",sSearch));
			query.put("$or", basicDBList);
		}
		if(null == voiceList || voiceList.isEmpty()) {
			Map<String,Object> mapParam = new HashMap<String, Object>();
			PageList<UserVoiceEntity> pageList = userVoiceService.queryPage(page, mapParam);
			if(pageList != null){
				voiceList = pageList.getList();
				allCount = (int) pageList.getPage().getTotalCount();
			}
		}
		JSONObject getObj = new JSONObject();
		getObj.put("sEcho", sEcho);
		getObj.put("iTotalRecords", allCount);//实际的行数
		getObj.put("iTotalDisplayRecords", allCount);//显示的行数,这个要和上面写的一样
		
		getObj.put("aaData", voiceList);//要以JSON格式返回
		return getObj.toString();
	}
	@RequestMapping(value="/getUserPicList", method=RequestMethod.POST)
	@ResponseBody
	public String getUserPicList(HttpServletRequest request, @RequestParam String aoData){

		JSONArray jsonarray = JSONArray.parseArray(aoData);
		String sEcho = null;
		int iDisplayStart = 0; // 起始索引
		int iDisplayLength = 0; // 每页显示的行数
		String sSearch = "";
		
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject obj = (JSONObject) jsonarray.get(i);
			if (obj.get("name").equals("sEcho"))
				sEcho = obj.get("value").toString();

			if (obj.get("name").equals("iDisplayStart"))
				iDisplayStart = obj.getIntValue("value");

			if (obj.get("name").equals("iDisplayLength"))
				iDisplayLength = obj.getIntValue("value");
			
			if (obj.get("name").equals("sSearch"))
				sSearch = obj.get("value").toString();
		}
		
		List<UserPicEntity> picList = new ArrayList<UserPicEntity>();
		int allCount = 0;
		Page page = new Page();
		page.setPageNo((iDisplayStart-1)/iDisplayLength+1);
		page.setPageSize(iDisplayLength);
		BasicDBObject query = new BasicDBObject();
		BasicDBList basicDBList = new BasicDBList();
		if(!StringUtils.isEmpty(sSearch)){
			basicDBList.add(new BasicDBObject("userName",sSearch));
			basicDBList.add(new BasicDBObject("userParentName",sSearch));
			query.put("$or", basicDBList);
		}
		if(null == picList || picList.isEmpty()) {
			Map<String,Object> mapParam = new HashMap<String, Object>();
			PageList<UserPicEntity> pageList = userPicService.queryPage(page, mapParam);
			if(pageList != null){
				picList = pageList.getList();
				allCount = (int) pageList.getPage().getTotalCount();
			}
		}
		JSONObject getObj = new JSONObject();
		getObj.put("sEcho", sEcho);
		getObj.put("iTotalRecords", allCount);//实际的行数
		getObj.put("iTotalDisplayRecords", allCount);//显示的行数,这个要和上面写的一样
		
		getObj.put("aaData", picList);//要以JSON格式返回
		return getObj.toString();
	}
	
	@RequestMapping(value="/getUserSmsList", method=RequestMethod.POST)
	@ResponseBody
	public String getUserSmsList(HttpServletRequest request, @RequestParam String aoData){

		JSONArray jsonarray = JSONArray.parseArray(aoData);
		String sEcho = null;
		int iDisplayStart = 0; // 起始索引
		int iDisplayLength = 0; // 每页显示的行数
		String sSearch = "";
		
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject obj = (JSONObject) jsonarray.get(i);
			if (obj.get("name").equals("sEcho"))
				sEcho = obj.get("value").toString();

			if (obj.get("name").equals("iDisplayStart"))
				iDisplayStart = obj.getIntValue("value");

			if (obj.get("name").equals("iDisplayLength"))
				iDisplayLength = obj.getIntValue("value");
			
			if (obj.get("name").equals("sSearch"))
				sSearch = obj.get("value").toString();
		}
		
		List<UserSmsEntity> smsList = new ArrayList<UserSmsEntity>();
		int allCount = 0;
		Page page = new Page();
		page.setPageNo((iDisplayStart-1)/iDisplayLength+1);
		page.setPageSize(iDisplayLength);
		BasicDBObject query = new BasicDBObject();
		BasicDBList basicDBList = new BasicDBList();
		if(!StringUtils.isEmpty(sSearch)){
			basicDBList.add(new BasicDBObject("userName",sSearch));
			basicDBList.add(new BasicDBObject("userParentName",sSearch));
			query.put("$or", basicDBList);
		}
		if(null == smsList || smsList.isEmpty()) {
			Map<String,Object> mapParam = new HashMap<String, Object>();
			PageList<UserSmsEntity> pageList = userSmsService.queryPage(page, mapParam);
			if(pageList != null){
				smsList = pageList.getList();
				allCount = (int) pageList.getPage().getTotalCount();
			}
		}
		JSONObject getObj = new JSONObject();
		getObj.put("sEcho", sEcho);
		getObj.put("iTotalRecords", allCount);//实际的行数
		getObj.put("iTotalDisplayRecords", allCount);//显示的行数,这个要和上面写的一样
		
		getObj.put("aaData", smsList);//要以JSON格式返回
		return getObj.toString();
	}
	
	
	/**
	 * 获取用户喜好爱好列表
	 * by zhaoming
	 * @param request
	 * @param aoData
	 * @return
	 */
//	@RequestMapping(value="/ajaxUserFavouriteList", method=RequestMethod.POST)
//	@ResponseBody
//	public String ajaxUserFavouriteList(HttpServletRequest request, @RequestParam String aoData){
//
//		JSONArray jsonarray = JSONArray.parseArray(aoData);
//		String sEcho = null;
//		int iDisplayStart = 0; // 起始索引
//		int iDisplayLength = 0; // 每页显示的行数
//		for (int i = 0; i < jsonarray.size(); i++) {
//			JSONObject obj = (JSONObject) jsonarray.get(i);
//			if (obj.get("name").equals("sEcho"))
//				sEcho = obj.get("value").toString();
//
//			if (obj.get("name").equals("iDisplayStart"))
//				iDisplayStart = obj.getIntValue("value");
//
//			if (obj.get("name").equals("iDisplayLength"))
//				iDisplayLength = obj.getIntValue("value");
//		}
//		
//		List<UserFavouriteEntity> list = new ArrayList<UserFavouriteEntity>();
//		int allCount = 0;
//		//先查询MongoDB 然后MySQL数据库
//		Page page = new Page();
//		page.setPageNo((iDisplayStart-1)/iDisplayLength+1);
//		page.setPageSize(iDisplayLength);
//		BasicDBObject query = new BasicDBObject();
//		MongoEntity entity = new MongoEntity();
//		entity.setCollectionName(CommonConst.MONGO_KEY_USER_FAVOURITE);
//		entity.setQuery(query);
//		PageList<UserFavouriteEntity> datePage = userFavouriteService.queryPage(entity, page);
//		if(datePage != null) {
//			list = datePage.getList();
//			allCount = (int) datePage.getPage().getTotalCount();
//		}
//		
//		if(null == list || list.isEmpty()) {
//			Map<String,Object> mapParam = new HashMap<String, Object>();
//			PageList<UserFavouriteEntity> pageList = userFavouriteService.queryPage(page, mapParam);
//			if(pageList != null){
//				list = pageList.getList();
//				allCount = (int) pageList.getPage().getTotalCount();
//			}
//		}
//		
//		JSONObject getObj = new JSONObject();
//		getObj.put("sEcho", sEcho);
//		getObj.put("iTotalRecords", allCount);//实际的行数
//		getObj.put("iTotalDisplayRecords", allCount);//显示的行数,这个要和上面写的一样
//		getObj.put("aaData", list);//要以JSON格式返回
//		return getObj.toString();
//	}
//	
}
