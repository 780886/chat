package com.wgq.chat.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wgq.chat.execption.BusinessException;
import com.wgq.chat.utils.Result;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice("com.wgq.chat")
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //response是ResultVo类型，或者注释了NotControllerResponseAdvice都不进行包装
        return !methodParameter.getParameterType().isAssignableFrom(Result.class) ||
                methodParameter.hasMethodAnnotation(NotControllerResponseAdvice.class);
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //String类型不能直接包装
        if(methodParameter.getGenericParameterType().equals(String.class)){
            ObjectMapper objectMapper=  new ObjectMapper();
            try {
                //将数据包装在ResultVo里后转换为json串进行返回
                return objectMapper.writeValueAsString(Result.success(data));
            }catch (Exception e) {
                throw new BusinessException(ResultCode.RESPONSE_PACK_ERROR,e.getMessage());
            }
        }
        //否则直接包装成Result返回
        return Result.success(data);
    }
}
