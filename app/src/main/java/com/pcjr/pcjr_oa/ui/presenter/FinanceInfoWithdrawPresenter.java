package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;

import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Withdraw;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/9/28下午3:54
 */
public class FinanceInfoWithdrawPresenter extends BasePresenter<MvpView<Withdraw>> {

    public static String TAG = FinanceInfoWithdrawPresenter.class.getSimpleName();

    public void getWithdrawDurationInfo(long startDate, long endDate) {
        this.mDataManager.getWithdrawDurationInfo(startDate,endDate)
                .subscribe(new Observer<BaseBean<Withdraw>>() {
                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        FinanceInfoWithdrawPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<Withdraw> data) {
                        if (FinanceInfoWithdrawPresenter.this.getMvpView() != null)
                            FinanceInfoWithdrawPresenter.this.getMvpView().onSuccess(data.getData());
                    }
                });
    }
}
