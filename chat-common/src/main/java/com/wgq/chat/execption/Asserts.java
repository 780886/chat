package com.wgq.chat.execption;

import com.wgq.chat.common.enums.StatusCode;
import com.wgq.chat.execption.BusinessException;

import java.util.List;

/**
 * @ClassName Assert
 * @Description TODO
 * @Author wgq
 * @Date 2023/5/24 23:05
 * @Version 1.0
 **/
public class Asserts {

    public static void isTrue(boolean expression, StatusCode statusCode) throws BusinessException {
        isTrue(expression, statusCode, null);
    }

    public static void isTrue(boolean expression, StatusCode statusCode,
                              String suffix) throws BusinessException {
        isTrue(expression, statusCode, suffix, null);
    }

    public static void isTrue(boolean expression, StatusCode statusCode,
                              String suffix, List<Object> parameters) throws BusinessException {
        if (expression) {
            throw new BusinessException(statusCode, suffix, parameters);
        }
    }

    public static void illegalArgument(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }
}
