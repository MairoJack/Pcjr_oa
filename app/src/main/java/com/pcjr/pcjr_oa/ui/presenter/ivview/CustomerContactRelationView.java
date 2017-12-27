package com.pcjr.pcjr_oa.ui.presenter.ivview;


import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.bean.CustomerContactRelation;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

import java.util.List;

/**
 * Created by Mario on 2017/12/19下午2:51
 */
public interface CustomerContactRelationView extends MvpView {

    void onGetCustomerListSuccess(BaseBean<List<Customer>> data);

    void onGetContactListSuccess(BaseBean<List<Contact>> data);

    void onModifyRelationshipSuccess(BaseBean data);

    void onGetRelationshipListSuccess(List<CustomerContactRelation> data);
}
