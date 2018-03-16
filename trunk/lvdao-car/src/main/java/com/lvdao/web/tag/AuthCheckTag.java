package com.lvdao.web.tag;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.entity.UserEntity;

@Repository
public class AuthCheckTag extends BodyTagSupport {
   
    private static final long serialVersionUID = -3546193405180713119L;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthCheckTag.class);
    
    private String permCode;
    private String userName;

    @Override
    public int doStartTag() throws JspException {

	HttpSession httpSession = super.pageContext.getSession();

	/** 检查用户是否已经登录 */
	UserEntity entity = (UserEntity)httpSession.getAttribute(CommonConst.SESSION_USER);
	
	LOGGER.info("httpSession sessionId={}", httpSession.getId());

	if (entity == null) {
	    return SKIP_BODY;
	}
	
	/** 检查用户是否拥有权限 */
	if (!hasPermission(permCode)) {
	    LOGGER.info("没有权限{}", permCode);
	    return SKIP_BODY;
	}

	return EVAL_BODY_INCLUDE;

    }

    /** 判断当前用户是否具有指定的访问权限。 */
    @SuppressWarnings("unchecked")
    public boolean hasPermission(String permCode) {

	HttpSession httpSession = super.pageContext.getSession();
	/** 检查用户是否已经登录 */
	//获取session中的用户
		UserEntity user = (UserEntity) httpSession.getAttribute(CommonConst.SESSION_USER);

		if (user != null && permCode != null) {
			List<String> permissions = (List<String>) httpSession.getAttribute(CommonConst.PERMISSION);

			if (permissions != null) {
				if (permissions.contains(permCode)) {
					return true;
				}
			}
		}
		return false;
	}

    public String getPermCode() {
    	return permCode;
    }

    public void setPermCode(String permCode) {
    	this.permCode = permCode;
    }

	public synchronized String getUserName() {
		return userName;
	}

	public synchronized void setUserName(String userName) {
		this.userName = userName;
	}
	
}
