package com.pcjr.pcjr_oa.ui.presenter.ivview;


import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.BusinessApproval;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

/**
 *
 *  Created by Mario on 2017/12/27下午2:16
 */
public interface ApprovalBusinessView extends MvpView {

    void onAddBusinessApprovalSuccess(BaseBean<BusinessApproval> data);

    void onModifyBusinessApprovalSuccess(BaseBean data);

    void onDeleteBusinessApprovalSuccess(BaseBean data);

    void onGetBusinessApprovalDetailSuccess(BusinessApproval data);
}
