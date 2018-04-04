package com.lvdao.car.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lvdao.car.vo.UserAccountMsgVO;
import com.lvdao.common.CommonConst;
import com.lvdao.common.enums.AccountEnum;
import com.lvdao.common.enums.LogTypeEnum;
import com.lvdao.common.util.DateUtils;
import com.lvdao.common.util.RelativeDateFormatUtil;
import com.lvdao.common.util.StringUtil;
import com.lvdao.entity.DealLogEntity;
import com.lvdao.entity.DictEntity;
import com.lvdao.entity.UserEntity;
import com.lvdao.service.IDealLogService;
import com.lvdao.service.IDictService;

@Controller
@RequestMapping("/info")
public class InfoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InfoController.class);
	
	@Autowired
	private IDealLogService dealLogService;
	
	@Autowired
	private IDictService dictService;
	
	/**
	 * 返还通知
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/infoMoney", method = RequestMethod.GET)
	public ModelAndView infoMoney(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/infoMoney");
		List<UserAccountMsgVO> userAccountMsgList = userAccountMsg(request);
		mav.addObject("userAccountMsgList", userAccountMsgList);
		return mav;
	}
	
	/**
	 * 消息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/infoPage", method = RequestMethod.GET)
	public ModelAndView infoPage(HttpServletRequest request) {
		UserEntity sessionUser = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		ModelAndView mav = new ModelAndView("/infoPage");
		
		// 查询系统消息
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("dictGroupId", "message_system");
		List<DictEntity> dictList = dictService.queryList(map);
		if (dictList == null || dictList.size() == CommonConst.DIGIT_ZERO) {
			mav.addObject("systemMessageSize", CommonConst.DIGIT_ZERO);
		} else {
			mav.addObject("systemMessageSize", dictList.size());
			mav.addObject("systemMessageVO", dictList.get(CommonConst.DIGIT_ZERO));
			if (null != dictList.get(CommonConst.DIGIT_ZERO).getCreateTime()) {
				mav.addObject("systemMessageTime", RelativeDateFormatUtil.format(dictList.get(CommonConst.DIGIT_ZERO).getCreateTime()));
			}
		}
		
		// 查询log的类型
		List<String> list=new ArrayList<>();
		list.add(LogTypeEnum.LOG_TYPE_BOUNS_RETURN.getId());//	燃油补贴
		list.add(LogTypeEnum.LOG_TYPE_OUT_CAR_RETURN.getId());// 上传补贴
		list.add(LogTypeEnum.LOG_TYPE_YECO_STOCK.getId());//	YECO股
		list.add(LogTypeEnum.LOG_TYPE_INGEO_STOCK.getId());//	英吉尔股
		list.add(LogTypeEnum.LOG_TYPE_HONG_KONG_STOCK.getId());//	港股
		map.clear();
		map.put("logTypes", StringUtil.assembleParam(list));
		map.put("userId", sessionUser.getUserId());
		
		//	只查今天
		SimpleDateFormat format=new SimpleDateFormat(DateUtils.DATE_FORMAT_DAY);
		map.put("startTime", format.format(new Date()));
		Date nextDay = DateUtils.getLastOfDay(new Date(), CommonConst.DIGIT_ONE);
		map.put("endTime", format.format(nextDay));
		List<DealLogEntity> logList = dealLogService.queryList(map);
		if (logList == null || logList.size() == CommonConst.DIGIT_ZERO) {
			//返还消息数量
			mav.addObject("returnMessageSize", CommonConst.DIGIT_ZERO);
		} else {
			DealLogEntity log = logList.get(CommonConst.DIGIT_ZERO);
			UserAccountMsgVO vo = new UserAccountMsgVO();
			String logType = log.getLogType();
			
			String logAmount=log.getLogAmount();
			if (StringUtils.isBlank(logAmount)) {
				logAmount=CommonConst.STRING_ZERO;
			}
			//	日志金额
			BigDecimal amoutn = new BigDecimal(logAmount).setScale(2,BigDecimal.ROUND_DOWN);
			
			//	燃油补贴日志
			if (logType.equals(LogTypeEnum.LOG_TYPE_BOUNS_RETURN.getId())) {
				vo.setAccountTypeId(AccountEnum.BOUNS_RETURN.getId());
				vo.setAccountTypeName(AccountEnum.BOUNS_RETURN.getValue());
				vo.setAmount("￥"+amoutn.toString()+"元");
				//	分享补贴日志(上车补贴)
			}else if(logType.equals(LogTypeEnum.LOG_TYPE_OUT_CAR_RETURN.getId())){
				vo.setAccountTypeId(AccountEnum.RECOMMEND_BONUS.getId());
				vo.setAccountTypeName(AccountEnum.RECOMMEND_BONUS.getValue());
				vo.setAmount("￥"+amoutn.toString()+"元");
				//	投资反股券积分
			}else if(logType.equals(LogTypeEnum.LOG_TYPE_YECO_STOCK.getId())){
				vo.setAccountTypeId(AccountEnum.YECO_STOCK.getId());
				vo.setAccountTypeName(AccountEnum.YECO_STOCK.getValue());
				vo.setAmount(amoutn.toString()+"份");
			}else if(logType.equals(LogTypeEnum.LOG_TYPE_INGEO_STOCK.getId())){
				vo.setAccountTypeId(AccountEnum.INGEO_STOCK.getId());
				vo.setAccountTypeName(AccountEnum.INGEO_STOCK.getValue());
				vo.setAmount(amoutn.toString()+"份");
			}else if(logType.equals(LogTypeEnum.LOG_TYPE_HONG_KONG_STOCK.getId())){
				vo.setAccountTypeId(AccountEnum.HONG_KONG_STOCK.getId());
				vo.setAccountTypeName(AccountEnum.HONG_KONG_STOCK.getValue());
				vo.setAmount(amoutn.toString()+"份");
			}
			vo.setCreateTime(log.getCreateTime());
			mav.addObject("returnMessageVO", vo);
			mav.addObject("returnMessageSize", logList.size());
			if (null != log.getCreateTime()) {
				mav.addObject("returnMessageTime", RelativeDateFormatUtil.format(log.getCreateTime()));
			}
		}
		
		return mav;
	}
	
	/**
	 * 系统通知
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/infoSystem", method = RequestMethod.GET)
	public ModelAndView infoSystem(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/infoSystem");
		// 查询系统消息
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("dictGroupId", "message_system");
		List<DictEntity> dictList = dictService.queryList(map);
		mav.addObject("dictList", dictList);
		return mav;
	}
	

	/**
	 * 用户对话框展示账户变动
	 * 
	 * @author fqb
	 * @param request
	 */
	public List<UserAccountMsgVO> userAccountMsg(HttpServletRequest request) {
		
		Object user = request.getSession().getAttribute(CommonConst.SESSION_USER);
		if (user == null) {
			return null;
		}
		UserEntity sessionUser=(UserEntity) user;
		
		//	查询log的类型
		List<String> list=new ArrayList<>();
		list.add(LogTypeEnum.LOG_TYPE_BOUNS_RETURN.getId());//	燃油补贴
		list.add(LogTypeEnum.LOG_TYPE_OUT_CAR_RETURN.getId());//	上传补贴
		list.add(LogTypeEnum.LOG_TYPE_YECO_STOCK.getId());//	YECO股
		list.add(LogTypeEnum.LOG_TYPE_INGEO_STOCK.getId());//	英吉尔股
		list.add(LogTypeEnum.LOG_TYPE_HONG_KONG_STOCK.getId());//	港股
		
		Map<String, Object> map=new HashMap<>();
		map.put("logTypes", StringUtil.assembleParam(list));
		map.put("userId", sessionUser.getUserId());
		
		//	只查今天
		SimpleDateFormat format=new SimpleDateFormat(DateUtils.DATE_FORMAT_DAY);
		map.put("startTime",format.format(new Date()) );
		Date nextDay = DateUtils.getLastOfDay(new Date(), CommonConst.DIGIT_ONE);
		map.put("endTime",format.format(nextDay));
		List<DealLogEntity> logList = dealLogService.queryList(map);
		
		//	返回数据封装
		List<UserAccountMsgVO> msgList = new ArrayList<>();
		
		for (DealLogEntity log : logList) {
			UserAccountMsgVO vo=new UserAccountMsgVO();
			String logType = log.getLogType();
			
			String logAmount=log.getLogAmount();
			if (StringUtils.isBlank(logAmount)) {
				logAmount=CommonConst.STRING_ZERO;
			}
			//	日志金额
			BigDecimal amoutn = new BigDecimal(logAmount).setScale(2,BigDecimal.ROUND_DOWN);
			
			//	燃油补贴日志
			if (logType.equals(LogTypeEnum.LOG_TYPE_BOUNS_RETURN.getId())) {
				vo.setAccountTypeId(AccountEnum.BOUNS_RETURN.getId());
				vo.setAccountTypeName(AccountEnum.BOUNS_RETURN.getValue());
				vo.setAmount("￥"+amoutn.toString()+"元");
				//	分享补贴日志(上车补贴)
			}else if(logType.equals(LogTypeEnum.LOG_TYPE_OUT_CAR_RETURN.getId())){
				vo.setAccountTypeId(AccountEnum.RECOMMEND_BONUS.getId());
				vo.setAccountTypeName(AccountEnum.RECOMMEND_BONUS.getValue());
				vo.setAmount("￥"+amoutn.toString()+"元");
				//	投资反股券积分
			}else if(logType.equals(LogTypeEnum.LOG_TYPE_YECO_STOCK.getId())){
				vo.setAccountTypeId(AccountEnum.YECO_STOCK.getId());
				vo.setAccountTypeName(AccountEnum.YECO_STOCK.getValue());
				vo.setAmount(amoutn.toString()+"份");
			}else if(logType.equals(LogTypeEnum.LOG_TYPE_INGEO_STOCK.getId())){
				vo.setAccountTypeId(AccountEnum.INGEO_STOCK.getId());
				vo.setAccountTypeName(AccountEnum.INGEO_STOCK.getValue());
				vo.setAmount(amoutn.toString()+"份");
			}else if(logType.equals(LogTypeEnum.LOG_TYPE_HONG_KONG_STOCK.getId())){
				vo.setAccountTypeId(AccountEnum.HONG_KONG_STOCK.getId());
				vo.setAccountTypeName(AccountEnum.HONG_KONG_STOCK.getValue());
				vo.setAmount(amoutn.toString()+"份");
			}
			vo.setCreateTime(log.getCreateTime());
			msgList.add(vo);
		}
		return msgList;
	}

}
