package com.wgq.chat.execption;


import com.wgq.chat.common.enums.BusinessCodeEnum;
import com.wgq.chat.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public Result<Object> BusinessExceptionHandler(BusinessException e){
        log.error("异常类型:{},业务信息:{},原因:{}", e.getClass(),e.toString(),e);
        return Result.fail(e.getCode(),e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public Result<Object> ExceptionHandler(Exception e){
        log.error("异常类型:{},原因:{}", e.getClass(),e);
        return Result.fail(BusinessCodeEnum.FAILED);
    }
}
