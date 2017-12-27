package com.pcjr.pcjr_oa.api;

import android.util.Log;

import com.pcjr.pcjr_oa.App;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.utils.DeviceUtils;
import com.pcjr.pcjr_oa.utils.MD5Util;
import com.pcjr.pcjr_oa.utils.SPUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;


/**
 * Created by Mario on 2016/7/29.
 */
public class OAuthInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        String access_token = (String) SPUtils.get(App.getContext(), Constant.ACCESS_TOKEN, "");
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String key = (String) SPUtils.get(App.getContext(), Constant.KEY, "");
        String token = MD5Util.encrypt(ApiConstant.SUFFIX + DeviceUtils.getMacAddress() + access_token + timestamp + key);
        String app = "1";

        Request originalRequest = chain.request();
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;

        HttpUrl.Builder authorizedUrlBuilder = originalRequest.url().newBuilder()
                .addQueryParameter(ApiConstant.PARAM_ACCESS_TOKEN, access_token)
                .addQueryParameter(ApiConstant.PARAM_TIMESTAMP, timestamp)
                .addQueryParameter(ApiConstant.PARAM_TOKEN, token)
                .addQueryParameter(ApiConstant.PARAM_APP, app);

        Request authorised = originalRequest.newBuilder()
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("User-Agent", "Android-" + currentApiVersion)
                .url(authorizedUrlBuilder.build())
                .build();
        Buffer buffer = new Buffer();
        try {
            authorised.body().writeTo(buffer);
            Log.i("requestBody", buffer.buffer().readUtf8());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Response response = chain.proceed(authorised);


        return response;
    }

}
