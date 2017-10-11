package com.pcjr.pcjr_oa.model.impl;



import com.pcjr.pcjr_oa.api.RetrofitManager;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.StaffCompany;
import com.pcjr.pcjr_oa.model.IOAuthModel;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Mario on 2016/7/25.
 */
public class OAuthModel implements IOAuthModel {

    private static final OAuthModel mInstance = new OAuthModel();


    public static OAuthModel getInstance() {
        return mInstance;
    }


    private OAuthModel() {
    }


    @Override
    public Observable<BaseBean<List<StaffCompany>>> getStaffCompanyList() {
        return RetrofitManager.getInstance().getAuthService().getStaffCompanyList();
    }


}
