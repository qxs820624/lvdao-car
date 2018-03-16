package com.lvdao.web.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lvdao.common.CommonConst;
import com.lvdao.entity.UserEntity;
import com.lvdao.entity.UserRoleEntity;

/**
 * 控制普通用户商家用户直接通过url访问   /system的操作
 * 
 * @author hexiang
 * @since 2016-09-19 11:21
 */
public class UserURLPermissionServlet implements Filter {
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession httpSession = request.getSession(true);
		
		//用户名
		UserEntity user = (UserEntity) httpSession.getAttribute(CommonConst.SESSION_USER);
		
		if(null != user) {
			
			UserRoleEntity userRole = (UserRoleEntity) httpSession.getAttribute(CommonConst.SESSION_ROLE);
			
			if(userRole != null) {
				//是系统管理员权限
				if("5".equals(userRole.getRoleId())){
					arg2.doFilter(arg0, arg1);
					return;
				}/* else {
					System.out.println("123123123");
					response.sendRedirect("/user/userLogin.do");
				}*/
			}
		} else {
			response.sendRedirect("/user/userLogin.do");
		}
		
		arg2.doFilter(arg0, arg1);
		
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
