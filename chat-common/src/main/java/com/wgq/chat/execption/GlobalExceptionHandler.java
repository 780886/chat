package com.wgq.chat.execption;


import com.wgq.chat.common.enums.ResultCode;
import com.wgq.chat.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public Result<Object> BusinessExceptionHandler(BusinessException e){
        return Result.fail(e.getCode(),e.getMsg());
    }


    @ExceptionHandler(Exception.class)
    public Result<Object> ExceptionHandler(Exception e){
        return Result.fail(ResultCode.FAILED);
    }
}
