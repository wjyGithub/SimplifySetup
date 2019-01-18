package com.simplify.setup.Exception;

/**
 * 不合法的字段异常
 * @author jianyuan.wei@hand-china.com
 * @date 2019/1/18 23:54
 */
public class IllegalFieldException extends RuntimeException {

    public IllegalFieldException() {}

    public IllegalFieldException(String message) {
        super(message);
    }
}
