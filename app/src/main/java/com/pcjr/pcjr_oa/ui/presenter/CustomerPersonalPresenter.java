package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.CustomerPersonal;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.CustomerPersonalView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/9/28下午3:54
 */
public class CustomerPersonalPresenter extends BasePresenter<CustomerPersonalView> {

    public static String TAG = CustomerPersonalPresenter.class.getSimpleName();

    public void addPerson(CustomerPersonal customer) {
        this.mDataManager.addPerson(customer)
                .subscribe(new Observer<BaseBean<CustomerPersonal>>() {
                    @Override
                    public void onComplete() {
                        if (CustomerPersonalPresenter.this.mCompositeDisposable != null) {
                            CustomerPersonalPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        CustomerPersonalPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<CustomerPersonal> data) {
                        if (CustomerPersonalPresenter.this.getMvpView() != null)
                            CustomerPersonalPresenter.this.getMvpView().onAddPersonSuccess(data);
                    }
                });
    }

    public void modifyPerson(CustomerPersonal customer) {
        this.mDataManager.modifyPerson(customer)
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onComplete() {
                        if (CustomerPersonalPresenter.this.mCompositeDisposable != null) {
                            CustomerPersonalPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        CustomerPersonalPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean data) {
                        if (CustomerPersonalPresenter.this.getMvpView() != null)
                            CustomerPersonalPresenter.this.getMvpView().onModifyPersonSuccess(data);
                    }
                });
    }

    public void getPersonDetail(String id) {
        this.mDataManager.getPersonDetail(id)
                .subscribe(new Observer<BaseBean<CustomerPersonal>>() {
                    @Override
                    public void onComplete() {
                        if (CustomerPersonalPresenter.this.mCompositeDisposable != null) {
                            CustomerPersonalPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        CustomerPersonalPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<CustomerPersonal> data) {
                        if (CustomerPersonalPresenter.this.getMvpView() != null)
                            CustomerPersonalPresenter.this.getMvpView().onGetPersonDetailSuccess(data.getData());
                    }
                });
    }
}
