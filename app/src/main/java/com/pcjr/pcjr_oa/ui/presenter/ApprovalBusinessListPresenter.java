package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;

import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.BusinessApproval;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/12/27下午2:00
 */
public class ApprovalBusinessListPresenter extends BasePresenter<MvpView<BaseBean<List<BusinessApproval>>>> {

    public static String TAG = ApprovalBusinessListPresenter.class.getSimpleName();

    public void getBusinessApproveList(int page, String query) {
        this.mDataManager.getBusinessApproveList(page, query)
                .subscribe(new Observer<BaseBean<List<BusinessApproval>>>() {
                    @Override
                    public void onComplete() {
                        if (ApprovalBusinessListPresenter.this.mCompositeDisposable != null) {
                            ApprovalBusinessListPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ApprovalBusinessListPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<List<BusinessApproval>> data) {
                        if (ApprovalBusinessListPresenter.this.getMvpView() != null)
                            ApprovalBusinessListPresenter.this.getMvpView().onSuccess(data);
                    }
                });
    }

}
