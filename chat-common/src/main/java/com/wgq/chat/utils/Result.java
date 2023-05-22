package com.wgq.chat.utils;


import com.wgq.chat.common.enums.ResultCode;
import com.wgq.chat.common.enums.StatusCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("返回结果对象")
public class Result<T> {

    @ApiModelProperty("成功标识")
    private  boolean success; //是否成功
    @ApiModelProperty("返回码")
    private Integer code;
    @ApiModelProperty("返回消息")
    private  String message;
    @ApiModelProperty("返回对象")
    private  T  data ;





    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMsg());
        result.setData(null);
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMsg());
        result.setData(data);

        return result;
    }
    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.FALSE);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> fail(StatusCode statusCode) {
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.FALSE);
        result.setCode(statusCode.getCode());
        result.setMessage(statusCode.getMsg());
        return result;
    }

}
