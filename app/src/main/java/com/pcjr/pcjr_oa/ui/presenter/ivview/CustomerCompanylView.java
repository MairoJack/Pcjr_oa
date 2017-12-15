package com.pcjr.pcjr_oa.ui.presenter.ivview;


import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.CustomerCompany;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

/**
 *
 *  Created by Mario on 2017/9/29下午2:46
 */
public interface CustomerCompanylView extends MvpView {

    void onAddCompanySuccess(BaseBean<CustomerCompany> data);

    void onModifyCompanySuccess(BaseBean data);

    void onGetCompanyDetailSuccess(CustomerCompany data);
}
