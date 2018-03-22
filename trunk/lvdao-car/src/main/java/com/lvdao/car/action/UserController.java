package com.lvdao.car.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.lvdao.common.CommonConst;
import com.lvdao.common.MessageConst;
import com.lvdao.common.enums.AccountEnum;
import com.lvdao.common.util.StringUtil;
import com.lvdao.entity.DealLogEntity;
import com.lvdao.entity.UserAccountEntity;
import com.lvdao.entity.UserEntity;
import com.lvdao.entity.UserWithdrawEntity;
import com.lvdao.service.IAccountService;
import com.lvdao.service.IDealLogService;
import com.lvdao.service.IUserAccountService;
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
		if(userName == null || StringUtils.isBlank(userName)) {
			return mav;
		}
		
		try {
			String userNamecode = getUserName(userName);
			UserEntity user = getMobileUser(userNamecode);
			request.getSession().setAttribute(CommonConst.SESSION_USER,user);
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
	//编码
	//final String encodedText = base64.encodeToString(textByte);
	//解码
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
				mav.addObject("stockAccount", accountStockAmount);
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
				mav.addObject("accountbonuAmount", roundByScale(accountbonuAmount, 2));
			}

			// 乘车券账户
			map.clear();
			map.put("userId", user.getUserId());
			map.put("accountId", AccountEnum.RIDE_COUPON.getId());
			List<UserAccountEntity> rideCouponAccountList = userAccountService.queryList(map);

			if (rideCouponAccountList != null && rideCouponAccountList.size() > CommonConst.DIGIT_ZERO) {
				String accountRideCouponAmount = rideCouponAccountList.get(CommonConst.DIGIT_ZERO).getAccountAmount();
				mav.addObject("rideCouponAccount", roundByScale(accountRideCouponAmount, 2));
			}

			// 燃油包账户
			map.clear();
			map.put("userId", user.getUserId());
			map.put("accountId", AccountEnum.SHARE_REWARD.getId());
			List<UserAccountEntity> shareRewardAccountList = userAccountService.queryList(map);

			if (shareRewardAccountList != null && shareRewardAccountList.size() > CommonConst.DIGIT_ZERO) {
				String accountShareRewardAmount = shareRewardAccountList.get(CommonConst.DIGIT_ZERO).getAccountAmount();
				mav.addObject("shareRewardAccount", roundByScale(accountShareRewardAmount, 2));
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
		Map<String, Object> map = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("/reference");
		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		if (user != null) {
			// 我的推荐人

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.clear();
			paramMap.put("userId", user.getUserId());
			paramMap.put("userParentName", user.getUserName());
			List<UserEntity> userRecommendList = userService.queryList(paramMap);

			if (null != userRecommendList && userRecommendList.size() != CommonConst.DIGIT_ZERO) {
				UserEntity userEntity = userRecommendList.get(CommonConst.DIGIT_ZERO);
				String userParentName = userEntity.getUserParentName();
				mav.addObject("myRecommendUser", userParentName);
			}

			// 直接推荐人数
			paramMap.clear();
			paramMap.put("userParentName", user.getUserRealName());
			int countUser = userService.countUser(paramMap);
			mav.addObject("countUser", countUser);
			
			String createQRCode = createQRCode(response,request,"HTTP://192.168.0.1");
			mav.addObject("createQRCode", createQRCode);
			
		}
		return mav;
	}
	
/*	*//**
	 * 返还明细
	 * 
	 * @param request
	 * @return
	 *//*
	@RequestMapping(value = "/returnDetail", method = RequestMethod.GET)
	public ModelAndView returnDetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/returnDetail");
		return mav;
	}*/
	
/*	*//**
	 * 奖励明细
	 * 
	 * @param request
	 * @return
	 *//*
	@RequestMapping(value = "/rewardDetail", method = RequestMethod.GET)
	public ModelAndView rewardDetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/rewardDetail");
		return mav;
	}
	*/
	
	

/**
	 * 根据
	 * @author guotao
	 * @param request
	 * @param logType 账户日志类型
	 * @return
	 */
	@RequestMapping(value="/accountListDetail", method=RequestMethod.GET)
	public ModelAndView accountListBySource(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/returnDetail");

		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		if(user == null) {
			return new ModelAndView("redirect:/user/userLogin.do");
		}
		
		String logType = request.getParameter("logType");
		
		Map<String, Object> map = new HashMap<>();
		map.clear();
		map.put("logType", logType);
		map.put("userId", user.getUserId());
		List<DealLogEntity> logList = dealLogService.queryList(map);
		DealLogEntity dealLogEntity = new DealLogEntity();
		dealLogEntity.setLogAmount("0.3");
		dealLogEntity.setCreateTime(new Date());
		logList.add(dealLogEntity);
		if(null == logList || logList.size() == CommonConst.DIGIT_ZERO) {
			return mav;		
		}
		mav.addObject("accountList",logList);
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

		String userMobile = request.getParameter("userMobile");
		String realName = request.getParameter("realName");
		String desc = request.getParameter("desc");
		String amount = request.getParameter("amount");
		String accountType = request.getParameter("accountType");
		//
		if (null == userMobile || StringUtils.isBlank(userMobile)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			// map.put(CommonConst.RESPONSE_MESSAGE,
			// MessageConst.WARN_USER_ACCOUNT_NULL);
			return map;
		}

		if (null == userMobile || StringUtils.isBlank(userMobile)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			// map.put(CommonConst.RESPONSE_MESSAGE,
			// MessageConst.WARN_USER_ACCOUNT_NAME_NULL);
			return map;
		}

		if (null == amount || StringUtils.isBlank(amount)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			// map.put(CommonConst.RESPONSE_MESSAGE,
			// MessageConst.WARN_USER_ACCOUNT_MONEY_NULL);
			return map;
		}

		if (null == accountType || StringUtils.isBlank(accountType)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_MESSAGE, "账户类型不能为空");
			return map;
		}

		// fundTransBillNo 商户转账唯一订单号 payeeType
		// (1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
		// 2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。)
		// payerShowName 付款方姓名
		// boolean result = AlipayUtil.FundTransToAccount("fundTransBillNo",
		// "payeeType", userMobile, amount, "payerShowName", realName, desc);
		/*
		 * if(result) { map.put(CommonConst.RESPONSE_STATUS,
		 * CommonConst.RESPONSE_STATUS_SUCCESS);
		 * map.put(CommonConst.RESPONSE_MESSAGE,
		 * MessageConst.REMINDER_APPLY_SUCCESS); return map; } else {
		 * map.put(CommonConst.RESPONSE_STATUS,
		 * CommonConst.RESPONSE_STATUS_FAIL);
		 * map.put(CommonConst.RESPONSE_ERROR_MESSAGE,
		 * MessageConst.REMINDER_EDIT_FAIL); return map; }
		 */

		List<UserAccountEntity> userAccountList = new ArrayList<>();
		if (accountType.equals(CommonConst.STRING_TWO)) {
			map.clear();
			map.put("userId", user.getUserId());
			map.put("accountId", AccountEnum.BOUNS_RETURN.getId());
			userAccountList = userAccountService.queryList(map);
			if (null == userAccountList || userAccountList.size() == CommonConst.DIGIT_ZERO) {
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "没有查询到该账户余额记录");
				return map;
			}

		}
		if (accountType.equals(CommonConst.STRING_TWO)) {
			map.clear();
			map.put("userId", user.getUserId());
			map.put("accountId", AccountEnum.RECOMMEND_BONUS.getId());
			userAccountService.queryList(map);
			if (null == userAccountList || userAccountList.size() == CommonConst.DIGIT_ZERO) {
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "没有查询到该账户余额记录");
				return map;
			}
		}

		UserAccountEntity userAccountEntity = userAccountList.get(CommonConst.DIGIT_ZERO);
		String accountBalance = userAccountEntity.getAccountAmount();
		if (null == accountBalance || StringUtils.isBlank(accountBalance)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "查询到该账户余额为空");
			return map;
		}

		BigDecimal submitAmount = new BigDecimal(CommonConst.DIGIT_ZERO);
		BigDecimal accountBalanceBig = new BigDecimal(accountBalance);

		int res = accountBalanceBig.compareTo(new BigDecimal(amount));
		if (res != -1) {
			submitAmount = new BigDecimal(amount).subtract(new BigDecimal(accountBalance));
		} else {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "查询到该账户余额不足");
			return map;
		}

		BigDecimal procedureMoney = submitAmount.multiply(new BigDecimal("0.2"));
		BigDecimal withdrawMoney = submitAmount.subtract(procedureMoney);
		BigDecimal Balance = accountBalanceBig.subtract(new BigDecimal(amount));
		UserWithdrawEntity userWithdrawEntity = new UserWithdrawEntity();

		userWithdrawEntity.setId(StringUtil.produceUUID());
		userWithdrawEntity.setUserId(user.getUserId());
		userWithdrawEntity.setUserName(user.getUserName());
		userWithdrawEntity.setParentUserId(user.getUserParentId());
		userWithdrawEntity.setParentUserName(userWithdrawEntity.getParentUserName());
		userWithdrawEntity.setWithdrawAccountType(CommonConst.STRING_ONE); // 支付宝类型
		userWithdrawEntity.setWithdrawAccount(userMobile);// 账户
		userWithdrawEntity.setWithdrawMoney(withdrawMoney.toString());// 提现总额减去手续费
		userWithdrawEntity.setWithdrawProcedure(procedureMoney.toString());// 手续费
		userWithdrawEntity.setWithdrawTotal(amount);
		userWithdrawEntity.setAccountBalance(Balance.toString());// 账户余额
		userWithdrawEntity.setWithdrawAccountName("");// 银行
		userWithdrawEntity.setWithdrawBankFullName("");// 开户行全名
		userWithdrawEntity.setComment(desc);
		userWithdrawEntity.setOrderSn(StringUtil.produceUUID()); // 订单号
		userWithdrawEntity.setStatus(CommonConst.DIGIT_ONE);

		int result = userWithdrawService.insert(userWithdrawEntity);

		if (result >= 1) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			// map.put(CommonConst.RESPONSE_MESSAGE,
			// MessageConst.REMINDER_APPLY_SUCCESS);
			return map;
		} else {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			// map.put(CommonConst.RESPONSE_ERROR_MESSAGE,
			// MessageConst.REMINDER_WITHDRAW_APPLY_FAIL);
			return map;
		}
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
			return "";
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
	public String createQRCode(HttpServletResponse response,HttpServletRequest request,@RequestParam(required = true, defaultValue = "") String codeUrl) {
		
		try {
			if(null == codeUrl){
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
			BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl,BarcodeFormat.QR_CODE, 300, 300, hints);
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
	
}
