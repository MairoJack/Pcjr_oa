package com.pcjr.pcjr_oa.ui.presenter.ivview;

import com.pcjr.pcjr_oa.bean.Repayment;
import com.pcjr.pcjr_oa.bean.RepaymentInfo;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

import java.util.List;

/**
 *
 *  Created by Mario on 2017/9/29下午2:46
 */
public interface RepaymentInfoDetailView extends MvpView {

    void onOneDayRepaymentDetailSuccess(RepaymentInfo data);

}
