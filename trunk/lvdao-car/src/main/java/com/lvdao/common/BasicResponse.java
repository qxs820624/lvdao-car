package com.lvdao.common;

import java.io.Serializable;

/**
 * Basic Response
 *
 * @author hx
 * @since 2016-09-09 11:34
 */
public class BasicResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int status;//1成功，0失败
    private String message;
    private Object data;
    private Long total;
    
    public static BasicResponse returnResponse(int status, String msg, Object data, Long total) {
    	BasicResponse response = new BasicResponse();
    	response.setStatus(status);
    	response.setMessage(msg);
    	response.setData(data);
    	response.setTotal(total);
    	return response;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public Long getTotal() {
		return total;
	}
	
	public void setTotal(Long total) {
		this.total = total;
	}
	
}

