package com.lvdao.car.action;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.lvdao.common.CommonConst;
import com.lvdao.common.MessageConst;
import com.lvdao.common.enums.AccountEnum;
import com.lvdao.common.util.DateUtils;
import com.lvdao.common.util.StringUtil;
import com.lvdao.entity.BonusReturnEntity;
import com.lvdao.entity.DealLogEntity;
import com.lvdao.entity.DictEntity;
import com.lvdao.entity.UserAccountEntity;
import com.lvdao.entity.UserEntity;
import com.lvdao.entity.UserWithdrawEntity;
import com.lvdao.service.IAccountService;
import com.lvdao.service.IBonusReturnService;
import com.lvdao.service.IDealLogService;
import com.lvdao.service.IDictService;
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
    
    @Autowired
    private IBonusReturnService bonusReturnService;
    
    @Autowired
    private IDictService dictService;
    
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
			mav.addObject("createQRCode", "http://car.motian123.cn/order/uploadVoucher.do?type=0&userParentName=" + user.getUserName());
			
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
		BigDecimal totalPrice = new BigDecimal(CommonConst.DIGIT_ZERO);
		for (DealLogEntity dealLogEntity : logList) {
			totalPrice = totalPrice.add(new BigDecimal(dealLogEntity.getLogAmount()));
		}
		mav.addObject("totalPrice", totalPrice.toString());
		mav.addObject("accountList", logList);
		mav.addObject("logType", logType);
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
		userWithdrawEntity.setOrderSn(StringUtil.getOrderSn()); // 订单号
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
	
	/**
	 * 用户提交提现申请(暂默认提现到支付宝)
	 *
	 * @since 2018年3月22日 10:30
	 * @param userId
	 *            用户ID
	 * @param amount
	 *            提现金额
	 * @param accountId
	 *            从当前账户提现(账户类型ID)
	 * @param userRealName
	 *            提现至账号的用户真实姓名
	 * @param type
	 *            提现方式(WithdrawAccountTypeEnum为准)
	 * @param accountNum
	 *            提现至账户(银行卡号或支付宝账号)
	 * @param toAccountName
	 *            提现至账户名称
	 * @param comment
	 *            备注
	 */
	private Map<String, Object> saveWithdrawals(String userId, String amount, String accountId, String userRealName,
			String type, String accountNum, String toAccountName, String comment) {
		LOGGER.info("Entering UserWithdrawService saveWithdrawals...  parameters = :{}");

		Map<String, Object> result = new HashMap<String, Object>();
		// 提现金额
		BigDecimal withdrawalsAmount = new BigDecimal(amount);
		// 校验提现金额(只能是100的倍数)
		int compare = new BigDecimal(withdrawalsAmount.intValue()).compareTo(withdrawalsAmount);
		// 非百的整数倍
		if (compare != 0) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "请输入一百的整数倍提现金额。");
			return result;
		}

		// 获取当前用户的账号余额
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("accountId", accountId);
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
		map.put("dictId", "withdrawal_commission_rate");
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
		withdrawEntity.setWithdrawMoney(amount);// 提现金额(只能100的倍数)
		withdrawEntity.setWithdrawAccount(accountNum);// 提现账号
		withdrawEntity.setWithdrawAccountType(type);// 提现方式（枚举类为准）
		withdrawEntity.setWithdrawBankFullName(toAccountName);// 银行全称
		withdrawEntity.setWithdrawAccountName(userRealName);// 开户人名称（真实姓名）
		withdrawEntity.setWithdrawProcedure(serviceCharge.toString());// 手续费
		withdrawEntity.setWithdrawTotal(serviceCharge.add(withdrawalsAmount).toString());// 提现总额=提现金额+手续费
		withdrawEntity.setAccountBalance(userAccoutAmount.toString());// 账户余额
		withdrawEntity.setCreateUserId(userId);
		withdrawEntity.setOrderSn(StringUtil.getOrderSn());
		withdrawEntity.setCreateTime(new Date());
		withdrawEntity.setActive(true);
		withdrawEntity.setStatus(CommonConst.DIGIT_ONE);
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

	
}
