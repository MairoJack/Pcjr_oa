package com.pcjr.pcjr_oa.model;


import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Token;

import io.reactivex.Observable;

/**
 *
 *  Created by Mario on 2017/9/28上午10:54
 */
public interface IApiModel {
    Observable<BaseBean<Token>> login(String username, String password,String key);
    Observable<BaseBean<Token>> refreshToken();
}
