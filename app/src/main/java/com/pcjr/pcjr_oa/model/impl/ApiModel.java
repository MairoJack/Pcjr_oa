package com.pcjr.pcjr_oa.model.impl;


import com.pcjr.pcjr_oa.App;
import com.pcjr.pcjr_oa.api.ApiConstant;
import com.pcjr.pcjr_oa.api.RetrofitManager;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Token;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.model.IApiModel;
import com.pcjr.pcjr_oa.utils.DeviceUtils;
import com.pcjr.pcjr_oa.utils.MD5Util;
import com.pcjr.pcjr_oa.utils.SPUtils;

import io.reactivex.Observable;

/**
 * Created by Mario on 2016/7/25.
 */
public class ApiModel implements IApiModel {

    private static final ApiModel mInstance = new ApiModel();


    public static ApiModel getInstance() {
        return mInstance;
    }


    private ApiModel() {
    }


    @Override
    public Observable<BaseBean<Token>> login(String mobile, String password, String key) {
        return RetrofitManager.getInstance().getApiService().login(mobile, password, DeviceUtils.getMacAddress(), key);
    }

    @Override
    public Observable<BaseBean<Token>> refreshToken() {
        String refreshToken = (String) SPUtils.get(App.getContext(), Constant.REFRESH_TOKEN, "");
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String key = (String) SPUtils.get(App.getContext(), Constant.KEY, "");
        String token = MD5Util.encrypt(ApiConstant.SUFFIX + DeviceUtils.getMacAddress() + refreshToken + timestamp + key);
        return RetrofitManager.getInstance().getApiService().refreshToken(refreshToken, token, timestamp, ApiConstant.PARAM_APP);
    }

}
