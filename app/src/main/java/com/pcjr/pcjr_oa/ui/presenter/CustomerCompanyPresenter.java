package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;

import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.CustomerCompany;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.CustomerCompanylView;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/9/28下午3:54
 */
public class CustomerCompanyPresenter extends BasePresenter<CustomerCompanylView> {

    public static String TAG = CustomerCompanyPresenter.class.getSimpleName();

    public void addCompany(CustomerCompany customer) {
        this.mDataManager.addCompany(customer)
                .subscribe(new Observer<BaseBean<CustomerCompany>>() {
                    @Override
                    public void onComplete() {
                        if (CustomerCompanyPresenter.this.mCompositeDisposable != null) {
                            CustomerCompanyPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        CustomerCompanyPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<CustomerCompany> data) {
                        if (CustomerCompanyPresenter.this.getMvpView() != null)
                            CustomerCompanyPresenter.this.getMvpView().onAddCompanySuccess(data);
                    }
                });
    }

    public void modifyCompany(CustomerCompany customer) {
        this.mDataManager.modifyCompany(customer)
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onComplete() {
                        if (CustomerCompanyPresenter.this.mCompositeDisposable != null) {
                            CustomerCompanyPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        CustomerCompanyPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean data) {
                        if (CustomerCompanyPresenter.this.getMvpView() != null)
                            CustomerCompanyPresenter.this.getMvpView().onModifyCompanySuccess(data);
                    }
                });
    }

    public void getCompanyDetail(String id) {
        this.mDataManager.getCompanyDetail(id)
                .subscribe(new Observer<BaseBean<CustomerCompany>>() {
                    @Override
                    public void onComplete() {
                        if (CustomerCompanyPresenter.this.mCompositeDisposable != null) {
                            CustomerCompanyPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        CustomerCompanyPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<CustomerCompany> data) {
                        if (CustomerCompanyPresenter.this.getMvpView() != null)
                            CustomerCompanyPresenter.this.getMvpView().onGetCompanyDetailSuccess(data.getData());
                    }
                });
    }
}
