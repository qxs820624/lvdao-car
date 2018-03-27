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
import com.lvdao.entity.UserEntity;
import com.lvdao.service.IUserService;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private IUserService userService;
	
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
		if (StringUtils.isBlank(userName)) {
			userName = "18800000001";
		}
		//测试数据
		UserEntity userEntity = userService.queryByUserName(userName);
		request.getSession().setAttribute(CommonConst.SESSION_USER, userEntity);
		return mav;
	}
}
