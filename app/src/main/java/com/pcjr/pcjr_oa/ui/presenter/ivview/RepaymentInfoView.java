package com.pcjr.pcjr_oa.ui.presenter.ivview;


import com.pcjr.pcjr_oa.bean.RepaymentInfo;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

/**
 *
 *  Created by Mario on 2017/9/29下午2:46
 */
public interface RepaymentInfoView extends MvpView {

    void onRepaymentDurationInfoSuccess(RepaymentInfo data);

}
