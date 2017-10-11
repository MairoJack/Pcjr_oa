package com.pcjr.pcjr_oa.api;


import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.StaffCompany;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 *
 *  Created by Mario on 2017/9/29上午10:56
 */
public interface OAuthService {
    @GET("company/select")
    Observable<BaseBean<List<StaffCompany>>> getStaffCompanyList();
}
