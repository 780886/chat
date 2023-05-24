package com.wgq.chat.utils;

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
        if (expression) {
            throw new BusinessException(statusCode);
        }
    }

    public static void illegalArgument(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }
}
