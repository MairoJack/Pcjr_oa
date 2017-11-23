package com.pcjr.pcjr_oa.api;

/**
 *
 *  Created by Mario on 2017/9/28上午10:20
 */
public class ApiConstant {

    /**
     * api接口
     */
    /**
     * test
     */
    public final static String API_URL = "http://192.168.30.205:88/api/";
    public final static String MAPI_URL = "http://192.168.30.205:88/";

    /**
     * pro
     */

    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_RETRY_COUNT = 1;
    public static final String DEFAULT_DATA_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String SUFFIX = "oav1";
    public static final String PARAM_ACCESS_TOKEN = "access_token";
    public static final String PARAM_TOKEN = "token";
    public static final String PARAM_TIMESTAMP = "t";
    public static final String PARAM_APP = "app";

    /**
     * Token 过期
     */
    public final static int LOGIN_EXPIRE = 100101;
    public final static int TOKEN_EXPIRE = 100102;

}
