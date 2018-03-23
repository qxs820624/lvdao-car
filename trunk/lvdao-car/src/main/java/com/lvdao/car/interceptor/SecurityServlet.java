package com.lvdao.car.interceptor;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lvdao.common.CommonConst;

/**
 * 登录过滤Servlet
 * 
 * @author zhxihu2008
 * @since 2016-07-03 15:29
 */
public class SecurityServlet extends HttpServlet implements Filter {

	private static final long serialVersionUID = 1L;

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession(true);
		// 用户名
		String username = (String) request.getRemoteUser();
		// 角色
		String role = (String) session.getAttribute("role");
		String url = request.getRequestURI();
		
		if (username == null || CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS.equals(username) || role == null
				|| CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS.equals(role)) {
			// 判断获取的路径不为空且不是访问登录页面或执行登录操作时跳转
//			if (url != null && !url.equals(CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS)
//					&& (url.indexOf("Login") < CommonConst.DIGIT_ZERO && url.indexOf("login") < CommonConst.DIGIT_ZERO)) {
//				response.sendRedirect(request.getContextPath() + "/user/userLogin.do");
//				return;
//			}
			if (url != null && !url.equals(CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS)) {
				if(url.indexOf("/index.do") > CommonConst.DIGIT_ZERO || url.indexOf("/register.do") > CommonConst.DIGIT_ZERO || url.indexOf("/userLogin.do") > CommonConst.DIGIT_ZERO|| url.indexOf("/login.do") > CommonConst.DIGIT_ZERO 
						|| url.indexOf("/userRegister.do") > CommonConst.DIGIT_ZERO || url.indexOf("/checkUserName.do") > CommonConst.DIGIT_ZERO || url.indexOf("/checkMobile.do") > CommonConst.DIGIT_ZERO ||url.indexOf("/sendCode.do") > CommonConst.DIGIT_ZERO ||url.indexOf("/wxPcnotify.do") > CommonConst.DIGIT_ZERO
						|| url.indexOf("/alipay_result_notify.do") > CommonConst.DIGIT_ZERO || url.indexOf("/system/userList.do") > CommonConst.DIGIT_ZERO ) {
					arg2.doFilter(arg0, arg1);
				} else {
					Object sessionUser = request.getSession().getAttribute(CommonConst.SESSION_USER);
					
					if(sessionUser == null) {
						response.sendRedirect("/index/index.do");
					} else {
						arg2.doFilter(arg0, arg1);
					}
					
					return;
				}
			}
		}
		return;
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}