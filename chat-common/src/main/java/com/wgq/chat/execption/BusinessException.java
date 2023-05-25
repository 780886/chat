package com.wgq.chat.execption;

import com.wgq.chat.common.constant.Constant;
import com.wgq.chat.common.constant.magic.Symbol;
import com.wgq.chat.common.enums.StatusCode;

import java.util.Collections;
import java.util.List;

public class BusinessException extends Exception {


    /**
     * 错误码
     */
    private Integer code;
    /**
     * 用于提示信息国际化的key
     * <p>
     * key=ErrorSupport.name()+suffix ［
     * <p>
     * suffix 对应前端界面  input name
     * <p>
     * 由于 error support 提供的可能是公共错误信息，针对每一个输入可能提示信息不一样
     * <p>
     * 举例:
     * <p>
     * GLOBAL_CONTENT_IS_NULL
     * <p>
     * 提示信息可能为:
     * <p>
     * 1. 用户名不允许为空
     * <p>
     * 2. 密码不能为空...
     * <p>
     * 注:由客户端负责国际化配置
     */
    private String key;
    /**
     * 其他参数
     */
    private List<Object> parameters;

    public BusinessException(StatusCode statusCode, String suffix, List<Object> parameters) {
        super(statusCode.getMsg());
        this.key = statusCode.name();
        if (suffix != null) {
            this.key = this.key + "." + suffix.toLowerCase();
        }
        this.code = statusCode.getCode();
        if (parameters != null && parameters.size() > 0 && !parameters.get(0).equals(Symbol.EMPTY)) {
            this.parameters = parameters;
        }
    }

    public BusinessException(StatusCode statusCode, String suffix, Object parameter) {
        this(statusCode, suffix, Collections.singletonList(parameter));
    }

    public BusinessException(StatusCode statusCode, List<Object> parameters) {
        this(statusCode, Constant.ERROR, parameters);
    }

    public BusinessException(StatusCode statusCode, String suffix) {
        this(statusCode, suffix, "");
    }

    public BusinessException(StatusCode statusCode) {
        this(statusCode, null, Symbol.EMPTY);
    }

    public Integer getCode() {
        return code;
    }

    public String getKey() {
        return key;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return super.fillInStackTrace();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        if (parameters != null) {
            for (Object object : parameters) {
                if (sb.length() > 0) {
                    sb.append(Symbol.COMMA);
                }
                sb.append(object.toString().trim());
            }
        }
        return String.format("key:%s,code:%s,parameters:%s", key, code,
                sb.toString());
    }
}
