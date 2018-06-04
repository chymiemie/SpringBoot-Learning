package com.iflytek.chy.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyControllerAdvice {

	@ExceptionHandler(value = MyException.class)
	@ResponseBody
	public MyException defultExcepitonHandler(HttpServletRequest request, Exception ex) {

		String status = getStatus(request);

		if (ex instanceof MyException) {
			MyException myException = (MyException) ex;
			return new MyException(status, myException.getMessage());
		}

		return new MyException(status, "未知异常错误");
	}

	private String getStatus(HttpServletRequest request) {
		String statusCode = (String) request.getAttribute("javax.servlet.error.status_code");

		if (StringUtils.isEmpty(statusCode)) {
			return "500 / Invalid request";
		}

		try {
			return "500 / Invalid request";
		} catch (Exception ex) {
			return "500 / Invalid request";
		}
	}

}
