package com.lvdao.car.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lvdao.common.CommonConst;
import com.lvdao.entity.DictEntity;
import com.lvdao.entity.UserEntity;
import com.lvdao.service.IDictService;
import com.lvdao.service.IUserService;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IDictService dictService;
	
	/**
	 * 首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/index");
		String userName = request.getParameter("userName");
		DictEntity dictEntity = dictService.queryDitcValueByDictId("app_switch");
		if(!CommonConst.STRING_ONE.equals(dictEntity.getDictValue())) {
			userName = "18800000001";
		}
		
		if (StringUtils.isBlank(userName)) {
			return new ModelAndView("redirect:/user/userLogin.do");
		}
		//测试数据
		UserEntity userEntity = userService.queryByUserName(userName);
		if (userEntity == null || StringUtils.isBlank(userEntity.getUserName())) {
			return new ModelAndView("redirect:/user/userLogin.do");
		}
		
		mav.addObject("isApp", dictEntity.getDictValue());
		request.getSession().setAttribute(CommonConst.SESSION_USER, userEntity);
		return mav;
	}
}
