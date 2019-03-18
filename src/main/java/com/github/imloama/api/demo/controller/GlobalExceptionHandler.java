package com.github.imloama.api.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.imloama.api.base.CustomException;
import com.github.imloama.mybatisplus.bootext.base.APIResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	 	@ExceptionHandler(value = CustomException.class)
	    @ResponseBody
	    public APIResult baseErrorHandler(HttpServletRequest req, Exception e) throws Exception {
	        log.error("---CustomException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
	        return APIResult.fail(e.getMessage());
	    }

	    @ExceptionHandler(value = Exception.class)
	    @ResponseBody
	    public APIResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
	        log.error("---DefaultException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
	        return APIResult.fail(e.getMessage());
	    }
	
	
}
