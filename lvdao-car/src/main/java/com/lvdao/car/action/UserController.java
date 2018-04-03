package com.lvdao.car.action;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.lvdao.common.CommonConst;
import com.lvdao.common.MessageConst;
import com.lvdao.common.enums.AccountEnum;
import com.lvdao.common.enums.LogTypeEnum;
import com.lvdao.common.enums.WithdrawAccountTypeEnum;
import com.lvdao.common.util.AliyunSMSUtil;
import com.lvdao.common.util.BaiduMapUtils;
import com.lvdao.common.util.ChatIMUtil;
import com.lvdao.common.util.DateUtils;
import com.lvdao.common.util.StringUtil;
import com.lvdao.entity.AccountEntity;
import com.lvdao.entity.BonusReturnEntity;
import com.lvdao.entity.DealLogEntity;
import com.lvdao.entity.DictEntity;
import com.lvdao.entity.UserAccountEntity;
import com.lvdao.entity.UserEntity;
import com.lvdao.entity.UserRoleEntity;
import com.lvdao.entity.UserWithdrawEntity;
import com.lvdao.service.IAccountService;
import com.lvdao.service.IBonusReturnService;
import com.lvdao.service.IDealLogService;
import com.lvdao.service.IDictService;
import com.lvdao.service.IUserAccountService;
import com.lvdao.service.IUserRoleService;
import com.lvdao.service.IUserService;
import com.lvdao.service.IUserWithdrawService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IAccountService accountService;

	@Autowired
	private IDealLogService dealLogService;

	@Autowired
	private IUserAccountService userAccountService;

	//用户提现servier
	@Autowired
	private IUserWithdrawService userWithdrawService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IBonusReturnService bonusReturnService;

	@Autowired
	private IDictService dictService;
	
	@Autowired
	private IUserRoleService userRoleService;
	
	/**
	 * 注册页面
	 * 
	 * @author hexiang
	 * @since 2018-03-28 15:29
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register (HttpServletRequest request) {
		String userParentName = request.getParameter("userParentName");
		ModelAndView mav = new ModelAndView("/register");
		mav.addObject("userParentName", userParentName);
		return mav;
	}
	
	/**
	 * 获取验证码
	 * 
	 * @author hexiang
	 * @since 2018-03-28 15:47
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "sendCode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> forwardSendCode(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String mobile = request.getParameter("mobile");
		if (mobile == null || CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS.equals(mobile)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "手机号码为空");
			return map;
		}
		String code = StringUtil.randomNumber(CommonConst.DIGIT_SIX);
		request.getSession().setAttribute(CommonConst.SEND_CODE, code);
		request.getSession().setAttribute("userMobile", mobile);
		LOGGER.info("code==========" + code);
		LOGGER.info("userMobile=============" + mobile);
//		int status = StringUtils.sendSmsCode(mobile, message);
		SendSmsResponse sendSms = new SendSmsResponse();
		try {
			sendSms = AliyunSMSUtil.sendSms(mobile, code);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		if ("OK".equals(sendSms.getCode())) {
			map.put("result", "S");
		} else {
			map.put("result", "F");
		}
		return map;
	}
	
	/**
	 * 分享推荐注册
	 * 
	 * @author hexiang
	 * @since 2017/7/20
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userRegister(HttpServletRequest request) {
		//获取页面参数
		String userMobile = request.getParameter("mobile");
		String sendCode = request.getParameter("sendCode");
		String userParentName = request.getParameter("userParentName");
		String userParentId = CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS;
		
		Map<String, Object> map = new HashMap<String, Object>();
		int result = CommonConst.DIGIT_ZERO;
		//校验参数
		if (userMobile == null || CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS.equals(userMobile)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "手机号码为空");
			return map;
		}
	
		if (sendCode == null || CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS.equals(sendCode)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "验证码为空");
			return map;
		}
		String mobile = (String) request.getSession().getAttribute("userMobile");
		String code = (String) request.getSession().getAttribute("sendCode");
	
		// 手机号码与验证码一致则登录成功 否则失败
		if(!mobile.equals(userMobile) || !code.equals(sendCode)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "验证码不正确");
			return map;
		}
		
		// 验证手机号码是否已经存在
		map.put("userName", userMobile);
		List<UserEntity> userList = userService.queryList(map);
		if(userList != null && userList.size() > CommonConst.DIGIT_ZERO) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "该用户已注册");
			return map;
		}
		
		//查看推荐人是否存在
		if(!org.apache.commons.lang.StringUtils.isBlank(userParentName)) {
			map.put("userName", userParentName);
			List<UserEntity> userParentList = userService.queryList(map);
			if(userParentList == null || userParentList.size() == CommonConst.DIGIT_ZERO) {
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "该推荐人手机号不存在");
				return map;
			}
			userParentId = userParentList.get(CommonConst.DIGIT_ZERO).getUserId();
		}
		
		UserEntity user = new UserEntity();
		user.setId(StringUtil.produceUUID());
		int userId = userService.getMaxId() + 1;//userId最大值+1
		user.setUserId(Integer.toString(userId));
		user.setUserName(mobile);
		user.setUserMobile(mobile);
		//EASEMOB_NAME 环信名称
		user.setUserMobileValidation(true);//手机号是否验证 0未验证1已验证
		user.setUserPassword("123456");//密码未加密
		user.setActive(true);
		user.setCreateUserId(userId+"");
		user.setCreateTime(new Date());
		user.setVersion(1);
		
		//根据userParentId查询parentUser
		if(!StringUtils.isBlank(userParentName)) {
			UserEntity parentUser = userService.queryByUserName(userParentName);
			if(parentUser != null) {
				user.setUserParentId(parentUser.getUserId());
				user.setUserParentName(parentUser.getUserName());
			}
		}
		
		//环信注册
		try {
			 boolean registerIM = ChatIMUtil.registerIM(mobile, CommonConst.USER_PWD, "lvdao");
			 LOGGER.info("环信是否注册成功："+registerIM);
			 user.setEasemobName(mobile);
		} catch (URISyntaxException e) {
			LOGGER.info("Exiting UserServiceImpl register...  result = :{}, exception = :{}", result, e.getMessage());
		}
		
		//t_user 表的数据插入
		int insert = userService.insert(user);
		if(insert > CommonConst.DIGIT_ZERO) {
			//t_user_role-->插入角色表
			UserRoleEntity userRoleEntity = new UserRoleEntity();
			userRoleEntity.setId(StringUtil.produceUUID());
			userRoleEntity.setUserId(Integer.toString(userId));
			userRoleEntity.setUserName(mobile);
			userRoleEntity.setRoleId(CommonConst.STRING_TWO);
			userRoleEntity.setRoleName("乘客");
			
			userRoleEntity.setActive(true);
			userRoleEntity.setCreateUserId(userId + "");
			userRoleEntity.setCreateUserName(mobile);
			userRoleEntity.setCreateTime(new Date());
			userRoleEntity.setVersion(1);
			userRoleService.insert(userRoleEntity);
			//用户账单表插入
			List<AccountEntity> accountList = accountService.queryAll();
			for (AccountEntity accountEntity : accountList) {
				UserAccountEntity userAccount = new UserAccountEntity();
				userAccount.setAccountAmount("0.00");
				userAccount.setAccountId(accountEntity.getAccountId());
				userAccount.setAccountName(accountEntity.getAccountName());
				userAccount.setActive(true);
				userAccount.setCreateTime(new Date());
				userAccount.setId(com.lvdao.common.util.StringUtil.produceUUID());
				userAccount.setUserId(Integer.toString(userId));
				userAccount.setUserName(mobile);
				userAccount.setVersion(1l);
				userAccountService.insert(userAccount);
				
			}
			
			//百度鹰眼
			try {
				BaiduMapUtils.registerBaiduMapYingYanUser(mobile);
			} catch (URISyntaxException e) {
				LOGGER.error("注册百度鹰眼轨迹用户失败");
			}
			
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.REMINDER_REGISTER_SUCCESS);
			LOGGER.info("Exiting UserServiceImpl register...  result = :{}", result);
			return map;
		}	
			
		map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
		map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.REMINDER_REGISTER_FAIL);
		LOGGER.info("Exiting UserServiceImpl register...  result = :{}", result);
	    return map;
	}

	/**
	 * app跳转过来首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkUserName", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		String userName = request.getParameter("getUserName");
		ModelAndView mav = new ModelAndView("/index");
		if (userName == null || StringUtils.isBlank(userName)) {
			return mav;
		}

		try {
			String userNamecode = getUserName(userName);
			UserEntity user = getMobileUser(userNamecode);
			request.getSession().setAttribute(CommonConst.SESSION_USER, user);
		} catch (Exception e) {
			LOGGER.info("解码失败" + userName);
			e.printStackTrace();
		}

		return mav;
	}

	private String getUserName(String encodedText) throws Exception {
		final Base64 base64 = new Base64();
		final String text = "字串文字";
		final byte[] textByte = text.getBytes("UTF-8");
		// 编码
		// final String encodedText = base64.encodeToString(textByte);
		// 解码
		return new String(base64.decode(encodedText), "UTF-8");
	}

	/**
	 * 添加银行卡
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addBank", method = RequestMethod.GET)
	public ModelAndView addBank(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/addBank");
		return mav;
	}

	/**
	 * 现金提现
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cashWithdraw", method = RequestMethod.GET)
	public ModelAndView orderList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/addBank");
		String accountType = request.getParameter("accountType");
		
		if (StringUtils.isBlank(accountType)) {
			return mav;
		}
		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", user.getUserId());
		paramMap.put("accountId", accountType);
		List<UserAccountEntity> userAccountList = userAccountService.queryList(paramMap);
		if (userAccountList != null && userAccountList.size() > CommonConst.DIGIT_ZERO) {
			UserAccountEntity userAccountEntity = userAccountList.get(CommonConst.DIGIT_ZERO);
			//可提现的金额是整百
			if (!StringUtils.isBlank(userAccountEntity.getAccountAmount())) {
				int accountAmount = Integer.valueOf(userAccountEntity.getAccountAmount()) / CommonConst.DIGIT_HUNDRED * CommonConst.DIGIT_HUNDRED;
				userAccountEntity.setAccountAmount(Integer.toString(accountAmount));
			}
			mav.addObject("userAccount", userAccountEntity);
		}
		return mav;
	}

	/**
	 * 收入明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/incomeDetail", method = RequestMethod.GET)
	public ModelAndView incomeDetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/incomeDetail");
		return mav;
	}

	/**
	 * 选择投资项目
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/investment", method = RequestMethod.GET)
	public ModelAndView investment(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/investment");
		return mav;
	}

	/**
	 * 个人中心
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/personal", method = RequestMethod.GET)
	public ModelAndView personal(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/personal");
		Map<String, Object> map = new HashMap<String, Object>();
		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		if (user != null) {
			// 现金账户
			map.clear();
			map.put("userId", user.getUserId());
			map.put("accountId", AccountEnum.RMB.getId());
			List<UserAccountEntity> rmbAccountList = userAccountService.queryList(map);
			if (rmbAccountList != null && rmbAccountList.size() > CommonConst.DIGIT_ZERO) {
				String accountRmbAmount = rmbAccountList.get(CommonConst.DIGIT_ZERO).getAccountAmount();
				mav.addObject("rmbAccount", roundByScale(accountRmbAmount, 2));
			}

			// 股票积分账户
			map.clear();
			map.put("userId", user.getUserId());
			map.put("accountId", AccountEnum.STOCK.getId());
			List<UserAccountEntity> stockAccountList = userAccountService.queryList(map);

			if (stockAccountList != null && stockAccountList.size() > CommonConst.DIGIT_ZERO) {
				String accountStockAmount = stockAccountList.get(CommonConst.DIGIT_ZERO).getAccountAmount();
				mav.addObject("stockAccount", roundByScale(accountStockAmount, 2));
			}

			// 分享补贴账户
			map.clear();
			map.put("userId", user.getUserId());
			map.put("accountId", AccountEnum.RECOMMEND_BONUS.getId());
			List<UserAccountEntity> recommendBonusAccountList = userAccountService.queryList(map);

			if (recommendBonusAccountList != null && recommendBonusAccountList.size() > CommonConst.DIGIT_ZERO) {
				String accountRecomendBonusAmount = recommendBonusAccountList.get(CommonConst.DIGIT_ZERO)
						.getAccountAmount();
				mav.addObject("recommendBonusAccount", roundByScale(accountRecomendBonusAmount, 2));
			}

			// 燃油补贴账户
			map.clear();
			map.put("userId", user.getUserId());
			map.put("accountId", AccountEnum.BOUNS_RETURN.getId());
			List<UserAccountEntity> bonusReturnAccountList = userAccountService.queryList(map);

			if (bonusReturnAccountList != null && bonusReturnAccountList.size() > CommonConst.DIGIT_ZERO) {
				String accountbonuAmount = bonusReturnAccountList.get(CommonConst.DIGIT_ZERO).getAccountAmount();
				if (StringUtils.isBlank(accountbonuAmount)) {
					accountbonuAmount=CommonConst.STRING_ZERO;
				}
				mav.addObject("accountbonuAmount", roundByScale(accountbonuAmount, 2));
			}
			String sumBonusRaturn = this.sumBonusRaturn(user.getUserId());
			mav.addObject("sumBonusRaturn", sumBonusRaturn);

			// 乘车券账户
			map.clear();
			map.put("userId", user.getUserId());
			map.put("accountId", AccountEnum.RIDE_COUPON.getId());
			List<UserAccountEntity> rideCouponAccountList = userAccountService.queryList(map);
			if (rideCouponAccountList != null && rideCouponAccountList.size() > CommonConst.DIGIT_ZERO) {
				UserAccountEntity accountEntity = rideCouponAccountList.get(CommonConst.DIGIT_ZERO);
				String accountRideCouponAmount = rideCouponAccountList.get(CommonConst.DIGIT_ZERO).getAccountAmount();
				mav.addObject("rideCouponAccount", roundByScale(accountRideCouponAmount, 2));
				mav.addObject("expirationDate", expirationDate(accountEntity.getCreateTime()));
			}

			// 燃油包账户
			map.clear();
			map.put("userId", user.getUserId());
			map.put("accountId", AccountEnum.SHARE_REWARD.getId());
			List<UserAccountEntity> shareRewardAccountList = userAccountService.queryList(map);

			if (shareRewardAccountList != null && shareRewardAccountList.size() > CommonConst.DIGIT_ZERO) {
				UserAccountEntity accountEntity = shareRewardAccountList.get(CommonConst.DIGIT_ZERO);
				String accountShareRewardAmount = shareRewardAccountList.get(CommonConst.DIGIT_ZERO).getOwnAmount();
				mav.addObject("ownAmount", roundByScale(accountShareRewardAmount, 2));
				mav.addObject("packagePrice", roundByScale(accountEntity.getAccountAmount(), 2));

			}

			// 推荐人数
			List<UserEntity> recommendList = getUserRecommendList(user.getUserId());
			if (null == recommendList || recommendList.isEmpty()) {
				mav.addObject("sumRecommend", CommonConst.STRING_ZERO);
			} else {
				mav.addObject("sumRecommend", recommendList.size());
			}

			// 激活状态
			map.clear();
			map.put("userId", user.getUserId());
			List<UserEntity> userList = userService.queryList(map);

			if (userList != null && userList.size() > CommonConst.DIGIT_ZERO) {
				mav.addObject("userStatus", userList.get(CommonConst.DIGIT_ZERO).getUserStatus());
				mav.addObject("userName", userList.get(CommonConst.DIGIT_ZERO).getUserName());
				mav.addObject("userDegreen", userList.get(CommonConst.DIGIT_ZERO).getUserDegreeName());// 等级
			}

		}

		return mav;
	}

	/**
	 * 推荐人
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/reference", method = RequestMethod.GET)
	public ModelAndView reference(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/reference");
		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		if (user != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// 我的推荐人
			if (!StringUtils.isBlank(user.getUserParentId()) && !StringUtils.isBlank(user.getUserParentName())) {
				paramMap.clear();
				paramMap.put("userId", user.getUserId());
				paramMap.put("userName", user.getUserName());
				List<UserEntity> userRecommendList = userService.queryList(paramMap);
				
				if (null != userRecommendList && userRecommendList.size() != CommonConst.DIGIT_ZERO) {
					UserEntity userEntity = userRecommendList.get(CommonConst.DIGIT_ZERO);
					mav.addObject("myRecommendUser", userEntity);
				}
				
			}

			// 直接推荐人数
			paramMap.clear();
			paramMap.put("userParentName", user.getUserName());
			List<UserEntity> userList = userService.queryList(paramMap);
			mav.addObject("userList", userList);
			
//			String createQRCode = createQRCode(response,request,"http://car.motian123.cn/order/uploadVoucher.do?type=0&userParentName=" + user.getUserName());
			mav.addObject("createQRCode", "http://car.motian123.cn/user/register.do?userParentName=" + user.getUserName());
			
		}
		return mav;
	}

	/**
	 * 根据
	 * 
	 * @author guotao
	 * @param request
	 * @param logType
	 *            账户日志类型
	 * @return
	 */
	@RequestMapping(value = "/accountListDetail", method = RequestMethod.GET)
	public ModelAndView accountListBySource(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/returnDetail");

		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		if (user == null) {
			return new ModelAndView("redirect:/index/index.do");
		}

		String logType = request.getParameter("logType");
		String selectDate = request.getParameter("selectDate");
		if (StringUtils.isBlank(selectDate)) {
			selectDate = DateUtils.format(new Date(), "yyyy-MM");
		}
		Map<String, Object> map = new HashMap<>();
		map.clear();
		map.put("logType", logType);
		map.put("userId", user.getUserId());
		map.put("selectDate", selectDate);
		List<DealLogEntity> logList = dealLogService.queryList(map);
		//	若查询的是燃油包log则返还单独页面
		if (logType.equals(LogTypeEnum.LOG_TYPE_REWARD_PACKAGE.getId())) {
			ModelAndView otherMav = new ModelAndView("/packageChangeDetail");
			map.clear();
			map.put("userId", user.getUserId());
			map.put("accountId", AccountEnum.SHARE_REWARD.getId());
			List<UserAccountEntity> accountList = userAccountService.queryList(map);
			if (null!=accountList && !accountList.isEmpty()) {
				String ownAmount = accountList.get(CommonConst.DIGIT_ZERO).getOwnAmount();
				if (StringUtils.isBlank(ownAmount)) {
					ownAmount=CommonConst.STRING_ZERO;
				}
				otherMav.addObject("ownAmount", ownAmount);
			}
			otherMav.addObject("accountList", logList);
			otherMav.addObject("logType", logType);
			otherMav.addObject("selectDate", selectDate);
			return otherMav;
			
		}else {
			BigDecimal totalPrice = new BigDecimal(CommonConst.DIGIT_ZERO);
			for (DealLogEntity dealLogEntity : logList) {
				totalPrice = totalPrice.add(new BigDecimal(dealLogEntity.getLogAmount()));
			}
			mav.addObject("totalPrice", totalPrice.toString());
			mav.addObject("accountList", logList);
			mav.addObject("logType", logType);
		}
		mav.addObject("selectDate", selectDate);
		return mav;

	}
	
	/**
	 * 返回这个用户类型的当月补贴总金额
	 * 
	 * @param userId
	 * @param accountType
	 * @return
	 */
	private String getAmountMoney(String userId, String accountType) {
		return "";
	}

	/**
	 * 提现申请
	 * 
	 * @author guotao
	 * @since 2018-03-18 04:20
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/takeCashApply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> takeCashApply(HttpServletRequest request) {
		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		Map<String, Object> map = new HashMap<String, Object>();
		if (user == null) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_NAME_IS_NULL);
			return map;
		}

		String userMobile = request.getParameter("alipayAccount");// 支付宝账户
		String realName = request.getParameter("userRealName");// 真实姓名
		String amount = request.getParameter("withdrawAmount");// 提现金额
		String accountType = request.getParameter("accountId");
		String acceptAccountType = request.getParameter("accountType");//	提现至账户
		String comment = request.getParameter("desc");// 备注
		if (StringUtils.isBlank(userMobile)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "支付宝账户不能为空");
			return map;
		}
		if (StringUtils.isBlank(realName)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "姓名不能为空");
			return map;
		}
		
		if (StringUtils.isBlank(amount)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "金额不能为空！");
			return map;
		}
		
		if (StringUtils.isBlank(accountType)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "获取被提现账户失败！");
			return map;
		}
		
		// 提现到哪种类型账户(默认是支付宝)
		acceptAccountType = WithdrawAccountTypeEnum.WITHDRAW_ACCOUNT_TYPE_ALIPAY.getId();
		Map<String, Object> resultMap = this.saveWithdrawals(user.getUserId(), realName, userMobile,amount, accountType, acceptAccountType, comment);

		return resultMap;
	}
	
	/**
	 * @author guotao
	 * @since 2018-03-17 判断是否为小数
	 * @param num
	 * @return
	 */
	private boolean judgeIsDecimal(String num) {
		boolean isdecimal = false;
		if (num.contains(".")) {
			isdecimal = true;
		}
		return isdecimal;
	}

	/**
	 * @author guotao
	 * @since 2018-03-17
	 * @des 将String格式化为指定小数位的String，不足小数位用0补全 是0 0.0 0.00 ""返回""
	 *      不是小数直接返回Double.toString()
	 * @param v
	 *            需要格式化的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return
	 */
	public String roundByScale(String v, int scale) {
		if (StringUtils.isBlank(v) || v.equals("0") || v.equals("0.0") || v.equals("0.00")) {
			return "0.00";
		}
		if (!judgeIsDecimal(v)) {
			return v;
		}
		String[] s = v.split("\\.");
		if (s[1].equals("0") || s[1].equals("00")) {
			return StringUtils.substringBefore(v, ".");
		}
		Double d = Double.parseDouble(v);
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		if (scale == 0) {
			return new DecimalFormat("0").format(d);
		}
		String formatStr = "0.";
		for (int i = 0; i < scale; i++) {
			formatStr = formatStr + "0";
		}
		return new DecimalFormat(formatStr).format(d);
	}

	/**
	 * 生成二维码
	 * 
	 * @param response
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws IOException
	 * @throws WriterException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/createQRCode")
	public String createQRCode(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(required = true, defaultValue = "") String codeUrl) {

		try {
			if (null == codeUrl) {
				return null;
			}

			OutputStream os = response.getOutputStream();

			response.reset();
			// 设置相应类型,告诉浏览器输出的内容为图片
			response.setContentType("image/jpg");
			// 设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);

			Hashtable hints = new Hashtable();

			// 内容所使用编码
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, 300, 300, hints);
			// 生成二维码
			MatrixToImageWriter.writeToStream(bitMatrix, "jpg", os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 验证手机号反回userEntity
	 * 
	 * @param request
	 * @return
	 */
	public UserEntity getMobileUser(String userName) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("userMobile", userName);
		List<UserEntity> list = userService.queryList(map);

		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list.get(CommonConst.DIGIT_ZERO);
		}
	}

	/**
	 * 用户提交提现申请(暂默认提现到支付宝)
	 *
	 * @author fqb
	 * @since 2018年3月22日 10:30
	 * @param userId 用户ID
	 * @param amount 提现金额
	 * @param accountId 从当前类型账户提现(账户类型ID)
	 * @param userRealName 提现至账号的用户真实姓名
	 * @param acceptAccountType 提现方式(WithdrawAccountTypeEnum为准)
	 * @param userMobile 提现至账户(银行卡号或支付宝账号,暂默认支付宝账号)
	 * @param acceptAccountType 提现至账户类型
	 */
	private Map<String, Object> saveWithdrawals(String userId, String realName, String userMobile,String amount, String accountType, String acceptAccountType,String comment) {
		LOGGER.info("Entering UserWithdrawService saveWithdrawals...  parameters = :{}");

		Map<String, Object> result = new HashMap<String, Object>();
		// 提现金额
		BigDecimal withdrawalsAmount = new BigDecimal(amount);
		// 校验提现金额(只能是100的倍数)
//		int compare = new BigDecimal(withdrawalsAmount.intValue()).compareTo(withdrawalsAmount);
		int compare = Integer.valueOf(amount) % CommonConst.DIGIT_HUNDRED;
		// 非百的整数倍
		if (compare != CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "请输入一百的整数倍提现金额。");
			return result;
		}

		// 获取当前用户的账号余额
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("accountId", accountType);
		List<UserAccountEntity> userAccountEntities = userAccountService.queryList(map);
		if (userAccountEntities == null || userAccountEntities.size() == CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "该用户的账户不存在");
			return result;
		}
		UserAccountEntity userAccountEntity = userAccountEntities.get(CommonConst.DIGIT_ZERO);
		String userAmount = userAccountEntity.getAccountAmount();
		if (StringUtils.isBlank(userAmount)) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "获取账户余额失败！");
			return result;
		}

		// 账号余额
		BigDecimal userAccoutAmount = new BigDecimal(userAmount);
		// 字典表查询手续费
		map.clear();
		//	燃油补贴、分享账户提现和现金账户不同费用率
		if (accountType.equals(AccountEnum.BOUNS_RETURN.getId())||accountType.equals(AccountEnum.RECOMMEND_BONUS.getId())) {
			map.put("dictId", "bonus_commission_rate");
		}else {
			map.put("dictId", "withdrawal_commission_rate");
		}
		List<DictEntity> dictEntities = dictService.queryList(map);
		if (dictEntities == null || dictEntities.size() == CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "获取手续费失败，请稍后重试。");
			return result;
		}
		// 提现费用率
		BigDecimal reat = new BigDecimal(dictEntities.get(CommonConst.DIGIT_ZERO).getDictValue());

		// 手续费
		BigDecimal serviceCharge = reat.multiply(withdrawalsAmount);

		// 若账户总额小于提现金额加上手续费则返回失败
		int compareTo = userAccoutAmount.compareTo(serviceCharge.add(withdrawalsAmount));
		if (compareTo < 0) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "账户余额不足，您可尝试减小提现金额！");
			return result;
		}

		// 生成提现记录
		UserWithdrawEntity withdrawEntity = new UserWithdrawEntity();
		withdrawEntity.setId(StringUtil.produceUUID());
		withdrawEntity.setUserId(userId);
		withdrawEntity.setUserName(userAccountEntity.getUserName());
		String accountTypeName=null;
		for (AccountEnum account : AccountEnum.values()) {
			if (accountType.equals(account.getId())) {
				accountTypeName=account.getValue();
				break;
			}
		}
		withdrawEntity.setAccountTypeId(accountType);
		withdrawEntity.setAccountTypeName(accountTypeName);
		withdrawEntity.setWithdrawMoney(amount);// 提现金额(只能100的倍数)
		withdrawEntity.setWithdrawAccount(userMobile);// 提现账号(支付宝账号)
		withdrawEntity.setWithdrawAccountType(acceptAccountType);// 提现方式（枚举类为准）
		String toAccountName=null;
		//	暂时默认为支付宝，以待修改
		for (WithdrawAccountTypeEnum enums : WithdrawAccountTypeEnum.values()) {
			if (acceptAccountType.equals(enums.getId())) {
				toAccountName=enums.getValue();
						break;
			}
		}
		if (StringUtils.isBlank(toAccountName)) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "获取接收账户类型失败");
			return result;
		}
		withdrawEntity.setWithdrawBankFullName(toAccountName);// 银行全称(此处为支付宝)
		withdrawEntity.setWithdrawAccountName(realName);// 开户人名称（真实姓名）
		withdrawEntity.setWithdrawProcedure(serviceCharge.toString());// 手续费
		withdrawEntity.setWithdrawTotal(serviceCharge.add(withdrawalsAmount).toString());// 提现总额=提现金额+手续费
		withdrawEntity.setAccountBalance(userAccoutAmount.toString());// 账户余额
		withdrawEntity.setCreateUserId(userId);
		withdrawEntity.setOrderSn(StringUtil.getOrderSn());
		withdrawEntity.setCreateTime(new Date());
		withdrawEntity.setActive(true);
		withdrawEntity.setStatus(CommonConst.DIGIT_ONE);
		withdrawEntity.setVersion(1l);
		withdrawEntity.setComment(comment);
		// 插入数据
		int insert = userWithdrawService.insert(withdrawEntity);
		if (insert < 1) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "提交失败，请刷新页面后重试！");
			return result;
		}
		// 更新用户账户
		BigDecimal nowAccountAmou = userAccoutAmount.subtract(serviceCharge).subtract(withdrawalsAmount);
		userAccountEntity.setAccountAmount(nowAccountAmou.setScale(9, BigDecimal.ROUND_HALF_UP).toString());
		userAccountEntity.setUpdateUserId(userAccountEntity.getUserId());
		userAccountEntity.setUpdateUserName(userAccountEntity.getUserName());
		userAccountEntity.setUpdateTime(new Date());
		int updateResult = userAccountService.update(userAccountEntity);
		if (updateResult < 1) {
			userWithdrawService.delete(withdrawEntity);
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "提交失败，请刷新页面后重试！");
			return result;
		}
		result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
		result.put(CommonConst.RESPONSE_MESSAGE, "提交成功！");
		return result;

	}

	/**
	 * 获取用户推荐的所有人
	 */
	private List<UserEntity> getUserRecommendList(String userId) {
		if (StringUtils.isBlank(userId)) {
			return null;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("userParentId", userId);
		List<UserEntity> list = userService.queryList(map);
		return list;
	}

	/**
	 * 获取乘车券过期日期
	 */
	private String expirationDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date expirationDate = calendar.getTime();
		return format.format(expirationDate);
	}

	/**
	 * 获取投资者投资返还记录已返还总额
	 *
	 * @author fqb @since2018年3月26日 @param @return @throws
	 */
	private String sumBonusRaturn(String userId) {
		if (StringUtils.isBlank(userId)) {
			return CommonConst.STRING_ZERO;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		List<BonusReturnEntity> list = bonusReturnService.queryList(map);
		if (null == list || list.isEmpty()) {
			return CommonConst.STRING_ZERO;
		}
		BigDecimal amout = new BigDecimal("0.00");
		for (BonusReturnEntity bonusReturn : list) {
			String returnAmount = bonusReturn.getReturnAmount();
			if (StringUtils.isBlank(returnAmount)) {
				continue;
			}
			amout = amout.add(new BigDecimal(returnAmount));
		}

		return amout.setScale(2, BigDecimal.ROUND_DOWN).toString();// 截取小数点后两位
	}

	/**
	 * 修改密码页
	 * 
	 * @author hexiang
	 * @since 2018-04-03 14:20
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updatePasswordView", method = RequestMethod.GET)
	public ModelAndView updatePasswordView (HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/updatePassword");
		return mav;
	}
	
	/**
	 * 修改密码
	 * 
	 * @author hexiang
	 * @since 2018-04-03 14:50
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePassword(HttpServletRequest request) {
		//获取页面参数
		String userMobile = request.getParameter("mobile");
		String sendCode = request.getParameter("sendCode");
		String newPassword = request.getParameter("newPassword");
		
		Map<String, Object> map = new HashMap<String, Object>();
		int result = CommonConst.DIGIT_ZERO;
		//校验参数
		if (userMobile == null || CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS.equals(userMobile)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "手机号码为空");
			return map;
		}
	
		if (sendCode == null || CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS.equals(sendCode)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "验证码为空");
			return map;
		}
		String mobile = (String) request.getSession().getAttribute("userMobile");
		String code = (String) request.getSession().getAttribute("sendCode");
	
		// 手机号码与验证码一致则登录成功 否则失败
		if(!mobile.equals(userMobile) || !code.equals(sendCode)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "验证码不正确");
			return map;
		}
		
		// 验证手机号码是否已经存在
		map.put("userName", userMobile);
		List<UserEntity> userList = userService.queryList(map);
		if(userList == null || userList.size() == CommonConst.DIGIT_ZERO) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "该用户不存在");
			return map;
		}
		UserEntity userEntity = userList.get(CommonConst.DIGIT_ZERO);
		userEntity.setUserPassword(newPassword);
		int update = userService.update(userEntity);
		if (update == CommonConst.DIGIT_ZERO) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "修改密码失败");
			LOGGER.info("Exiting UserServiceImpl updatePassword...  result = :{}", result);
		    return map;
		}
		
		map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		map.put(CommonConst.RESPONSE_MESSAGE, "修改密码成功");
		LOGGER.info("Exiting UserServiceImpl updatePassword...  result = :{}", result);
	    return map;
	}
}
