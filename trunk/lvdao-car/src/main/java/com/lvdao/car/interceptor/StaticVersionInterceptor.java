package com.lvdao.car.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lvdao.common.CommonConst;

public class StaticVersionInterceptor extends HandlerInterceptorAdapter{
	
	@Override  
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {  
		
		request.setAttribute("version", CommonConst.STATIC_VERSION);
    }
}
