package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;

import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/9/28下午3:54
 */
public class CustomerListPresenter extends BasePresenter<MvpView<BaseBean<List<Customer>>>> {

    public static String TAG = CustomerListPresenter.class.getSimpleName();

    public void getBorrowerList(int page,String query) {
        this.mDataManager.getBorrowerList(page,query)
                .subscribe(new Observer<BaseBean<List<Customer>>>() {
                    @Override
                    public void onComplete() {
                        if (CustomerListPresenter.this.mCompositeDisposable != null) {
                            CustomerListPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        CustomerListPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<List<Customer>> data) {
                        if (CustomerListPresenter.this.getMvpView() != null)
                            CustomerListPresenter.this.getMvpView().onSuccess(data);
                    }
                });
    }

}
