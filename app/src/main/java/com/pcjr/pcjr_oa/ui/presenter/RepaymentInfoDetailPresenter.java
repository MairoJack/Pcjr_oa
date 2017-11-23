package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;

import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Repayment;
import com.pcjr.pcjr_oa.bean.RepaymentInfo;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.RepaymentInfoDetailView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/9/28下午3:54
 */
public class RepaymentInfoDetailPresenter extends BasePresenter<RepaymentInfoDetailView> {

    public static String TAG = RepaymentInfoDetailPresenter.class.getSimpleName();

    public void getOneDayRepaymentDetail(String oneDay) {
        this.mDataManager.getOneDayRepaymentDetail(oneDay)
                .subscribe(new Observer<BaseBean<RepaymentInfo>>() {
                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        RepaymentInfoDetailPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<RepaymentInfo> data) {
                        if (RepaymentInfoDetailPresenter.this.getMvpView() != null)
                            RepaymentInfoDetailPresenter.this.getMvpView().onOneDayRepaymentDetailSuccess(data.getData());
                    }
                });
    }
}
