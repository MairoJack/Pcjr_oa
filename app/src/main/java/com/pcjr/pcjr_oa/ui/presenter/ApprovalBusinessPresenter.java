package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;

import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.BusinessApproval;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.ApprovalBusinessView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/12/19下午2:53
 */
public class ApprovalBusinessPresenter extends BasePresenter<ApprovalBusinessView> {

    public static String TAG = ApprovalBusinessPresenter.class.getSimpleName();

    public void addBusinessApprove(BusinessApproval businessApproval) {
        this.mDataManager.addBusinessApprove(businessApproval)
                .subscribe(new Observer<BaseBean<BusinessApproval>>() {
                    @Override
                    public void onComplete() {
                        if (ApprovalBusinessPresenter.this.mCompositeDisposable != null) {
                            ApprovalBusinessPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ApprovalBusinessPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<BusinessApproval> data) {
                        if (ApprovalBusinessPresenter.this.getMvpView() != null)
                            ApprovalBusinessPresenter.this.getMvpView().onAddBusinessApprovalSuccess(data);
                    }
                });
    }

    public void modifyBusinessApprove(BusinessApproval businessApproval) {
        this.mDataManager.modifyBusinessApprove(businessApproval)
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onComplete() {
                        if (ApprovalBusinessPresenter.this.mCompositeDisposable != null) {
                            ApprovalBusinessPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ApprovalBusinessPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean data) {
                        if (ApprovalBusinessPresenter.this.getMvpView() != null)
                            ApprovalBusinessPresenter.this.getMvpView().onModifyBusinessApprovalSuccess(data);
                    }
                });
    }

    public void getBusinessApproveDetail(String id) {
        this.mDataManager.getBusinessApproveDetail(id)
                .subscribe(new Observer<BaseBean<BusinessApproval>>() {
                    @Override
                    public void onComplete() {
                        if (ApprovalBusinessPresenter.this.mCompositeDisposable != null) {
                            ApprovalBusinessPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ApprovalBusinessPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<BusinessApproval> data) {
                        if (ApprovalBusinessPresenter.this.getMvpView() != null)
                            ApprovalBusinessPresenter.this.getMvpView().onGetBusinessApprovalDetailSuccess(data.getData());
                    }
                });
    }

    public void deleteBusinessApprove(String id) {
        this.mDataManager.deleteBusinessApprove(id)
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onComplete() {
                        if (ApprovalBusinessPresenter.this.mCompositeDisposable != null) {
                            ApprovalBusinessPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ApprovalBusinessPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean data) {
                        if (ApprovalBusinessPresenter.this.getMvpView() != null)
                            ApprovalBusinessPresenter.this.getMvpView().onDeleteBusinessApprovalSuccess(data);
                    }
                });
    }
}
