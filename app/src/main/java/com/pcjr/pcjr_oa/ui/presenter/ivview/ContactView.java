package com.pcjr.pcjr_oa.ui.presenter.ivview;


import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.bean.CustomerPersonal;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

/**
 *
 *  Created by Mario on 2017/12/19下午2:51
 */
public interface ContactView extends MvpView {

    void onAddContactSuccess(BaseBean<Contact> data);

    void onModifyContactSuccess(BaseBean data);

    void onGetContactDetailSuccess(Contact data);
}
