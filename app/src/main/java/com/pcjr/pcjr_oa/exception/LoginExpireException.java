package com.pcjr.pcjr_oa.exception;

/**
 * 登录超时异常
 * Created by mario on 2017/9/30.
 */
public class LoginExpireException extends Exception {

    public LoginExpireException(String msg) {
        super(msg);
    }
}
