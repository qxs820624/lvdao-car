package com.lvdao.car.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

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
		ModelAndView mav = new ModelAndView("/cashWithdraw");
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
		return mav;
	}
	
	/**
	 * 推荐人
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/reference", method = RequestMethod.GET)
	public ModelAndView reference(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/reference");
		return mav;
	}
	
	/**
	 * 返还明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/returnDetail", method = RequestMethod.GET)
	public ModelAndView returnDetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/returnDetail");
		return mav;
	}
	
	/**
	 * 奖励明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/rewardDetail", method = RequestMethod.GET)
	public ModelAndView rewardDetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/rewardDetail");
		return mav;
	}
	
	
	

/**
	 * 根据
	 * @author guotao
	 * @param request
	 * @param logType 账户日志类型
	 * @return
	 */
	@RequestMapping(value="/accountListDetail", method=RequestMethod.GET)
	public ModelAndView accountListBySource(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("personAccountDetail/account_list");

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
		
		if(null == logList || logList.size() == CommonConst.DIGIT_ZERO) {
			return mav;		
		}
		
		mav.addObject("accountList",logList);
		return mav;
	    
	}
	
	


/**
	 * 提现申请
	 * @author guotao
	 * @since 2018-03-18 04:20
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/takeCashApply", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> takeCashApply(HttpServletRequest request) {
		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		Map<String, Object> map = new HashMap<String, Object>();
		if(user == null) {
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
		if(null == userMobile || StringUtils.isBlank(userMobile)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			//map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.WARN_USER_ACCOUNT_NULL); 
			return map;
		}
		
		if(null == userMobile || StringUtils.isBlank(userMobile)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
		//	map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.WARN_USER_ACCOUNT_NAME_NULL);
			return map;
		}
		
		if(null == amount || StringUtils.isBlank(amount)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
		//	map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.WARN_USER_ACCOUNT_MONEY_NULL);
			return map;
		}
		
		if(null == accountType || StringUtils.isBlank(accountType)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_MESSAGE, "账户类型不能为空");
			return map;
		}
		
		//fundTransBillNo 商户转账唯一订单号   payeeType (1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。 2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。)
				//payerShowName 付款方姓名
				//boolean result = AlipayUtil.FundTransToAccount("fundTransBillNo", "payeeType", userMobile, amount, "payerShowName", realName, desc);
				/*if(result) {
					map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
					map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.REMINDER_APPLY_SUCCESS);
		            return map;
				} else {
					map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
					map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.REMINDER_EDIT_FAIL);
					return map;
				}*/
		
		List<UserAccountEntity> userAccountList = new ArrayList<>();
		if (accountType.equals(CommonConst.STRING_TWO)) {
            map.clear();
            map.put("userId", user.getUserId());
            map.put("accountId", AccountEnum.BOUNS_RETURN.getId());
            userAccountList = userAccountService.queryList(map);
            if(null == userAccountList || userAccountList.size() == CommonConst.DIGIT_ZERO) {
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
            if(null == userAccountList || userAccountList.size() == CommonConst.DIGIT_ZERO) {
            	map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "没有查询到该账户余额记录");
				return map;
            }
		}
		
		UserAccountEntity userAccountEntity = userAccountList.get(CommonConst.DIGIT_ZERO);
		String accountBalance = userAccountEntity.getAccountAmount();
		if(null == accountBalance || StringUtils.isBlank(accountBalance)) {
        	map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "查询到该账户余额为空");
			return map;
        }
		
		BigDecimal submitAmount = new BigDecimal(CommonConst.DIGIT_ZERO);
		BigDecimal accountBalanceBig = new BigDecimal(accountBalance);
		
		int res = accountBalanceBig.compareTo(new BigDecimal(amount));
		if(res != -1) {
			submitAmount = new BigDecimal(amount).subtract(new BigDecimal(accountBalance));
		}else {
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
		userWithdrawEntity.setWithdrawAccountType(CommonConst.STRING_ONE); //支付宝类型
		userWithdrawEntity.setWithdrawAccount(userMobile);//账户
		userWithdrawEntity.setWithdrawMoney(withdrawMoney.toString());//提现总额减去手续费
		userWithdrawEntity.setWithdrawProcedure(procedureMoney.toString());//手续费
		userWithdrawEntity.setWithdrawTotal(amount);
		userWithdrawEntity.setAccountBalance(Balance.toString());//账户余额
		userWithdrawEntity.setWithdrawAccountName("");// 银行
		userWithdrawEntity.setWithdrawBankFullName("");//开户行全名
		userWithdrawEntity.setComment(desc);
		userWithdrawEntity.setOrderSn(StringUtil.produceUUID()); //订单号
		userWithdrawEntity.setStatus(CommonConst.DIGIT_ONE);
		
		int result = userWithdrawService.insert(userWithdrawEntity);
		
		if(result >= 1) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		//	map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.REMINDER_APPLY_SUCCESS);
           return map;
		} else {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
		//	map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.REMINDER_WITHDRAW_APPLY_FAIL);
			return map;
		}	
	}

	
	
	
}
