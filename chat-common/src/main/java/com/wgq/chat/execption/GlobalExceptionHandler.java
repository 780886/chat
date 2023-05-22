package com.wgq.chat.execption;


import com.wgq.chat.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public Result<Object> BusinessExceptionHandler(BusinessException e){
        return Result.fail(e.getCode(),e.getMsg());
    }
}
