package com.github.imloama.api.base;


import com.github.imloama.mybatisplus.bootext.base.APIResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理类
 * controller层异常无法捕获处理，需要自己处理
 */
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    /**
     * 处理所有自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public APIResult handleCustomException(CustomException e){
        log.error(e.getMessage(),e );
        return APIResult.fail(e.getMessage());
    }
}