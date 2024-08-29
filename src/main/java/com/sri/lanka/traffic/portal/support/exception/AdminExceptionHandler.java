package com.sri.lanka.traffic.portal.support.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestControllerAdvice
public class AdminExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView exceptionHandler(Exception e){
		e.printStackTrace();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("views/common/errorPage");
		return mav;
	}
	
	// customRuntimeException 
	@ExceptionHandler(value = CommonResponseException.class)
	public ResponseEntity<ErrorResponse> adminExceptionHandler(CommonException ce, String message) {
		ErrorResponse response = new ErrorResponse(ce.getErrorCode());
		return new ResponseEntity<>(response, HttpStatus.valueOf(ce.getErrorCode().getStatus()));
	}
	
}
