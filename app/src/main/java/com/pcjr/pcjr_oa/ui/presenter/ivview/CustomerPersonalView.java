package com.pcjr.pcjr_oa.ui.presenter.ivview;


import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.bean.CustomerPersonal;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

/**
 *
 *  Created by Mario on 2017/9/29下午2:46
 */
public interface CustomerPersonalView extends MvpView {

    void onAddPersonSuccess(BaseBean<CustomerPersonal> data);

    void onModifyPersonSuccess(BaseBean data);

    void onGetPersonDetailSuccess(CustomerPersonal data);
}
