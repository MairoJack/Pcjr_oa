package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;

import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Recharge;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/9/28下午3:54
 */
public class FinanceInfoRechargePresenter extends BasePresenter<MvpView<Recharge>> {

    public static String TAG = FinanceInfoRechargePresenter.class.getSimpleName();

    public void getRechargeDurationInfo(long startDate, long endDate) {
        this.mDataManager.getRechargeDurationInfo(startDate,endDate)
                .subscribe(new Observer<BaseBean<Recharge>>() {
                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        FinanceInfoRechargePresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<Recharge> data) {
                        if (FinanceInfoRechargePresenter.this.getMvpView() != null)
                            FinanceInfoRechargePresenter.this.getMvpView().onSuccess(data.getData());
                    }
                });
    }
}
