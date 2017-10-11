package com.pcjr.pcjr_oa.api;


import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Token;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 *  不需要auth认证 api
 *  Created by Mario on 2017/9/30下午2:33
 */
public interface ApiService {


    @FormUrlEncoded
    @POST("login/app")
    Observable<BaseBean<Token>> login(@Field("mobile") String mobile, @Field("password") String password, @Field("uuid") String uuid, @Field("key") String key);


    /**
     * 刷新token
     *
     * @param refreshToken
     * @param token
     * @param t
     * @param scope
     * @return
     */
    @FormUrlEncoded
    @POST("auth/refresh_token")
    Observable<BaseBean<Token>> refreshToken(@Field("refresh_token") String refreshToken, @Field("token") String token, @Field("t") String t, @Field("scope") String scope);

}
