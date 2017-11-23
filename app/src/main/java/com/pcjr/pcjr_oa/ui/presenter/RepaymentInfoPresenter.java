package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Repayment;
import com.pcjr.pcjr_oa.bean.RepaymentInfo;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.RepaymentInfoView;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/9/28下午3:54
 */
public class RepaymentInfoPresenter extends BasePresenter<RepaymentInfoView> {

    public static String TAG = RepaymentInfoPresenter.class.getSimpleName();

    public void getRepaymentDurationInfo(long startDate, long endDate) {
        this.mDataManager.getRepaymentDurationInfo(startDate,endDate)
                .subscribe(new Observer<BaseBean<RepaymentInfo>>() {
                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        RepaymentInfoPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<RepaymentInfo> data) {
                        if (RepaymentInfoPresenter.this.getMvpView() != null)
                            RepaymentInfoPresenter.this.getMvpView().onRepaymentDurationInfoSuccess(data.getData());
                    }
                });
    }
}
