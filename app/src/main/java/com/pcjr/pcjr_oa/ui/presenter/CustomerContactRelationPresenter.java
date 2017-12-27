package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;

import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.bean.CustomerContact;
import com.pcjr.pcjr_oa.bean.CustomerContactRelation;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.CustomerContactRelationView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Mario on 2017/12/19下午2:50
 */
public class CustomerContactRelationPresenter extends BasePresenter<CustomerContactRelationView> {

    public static String TAG = CustomerContactRelationPresenter.class.getSimpleName();

    public void getContactList(int page, String query, String id, int type) {
        this.mDataManager.getContactList(page, query, id, type)
                .subscribe(new Observer<BaseBean<List<Contact>>>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        CustomerContactRelationPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<List<Contact>> data) {
                        if (CustomerContactRelationPresenter.this.getMvpView() != null)
                            CustomerContactRelationPresenter.this.getMvpView().onGetContactListSuccess(data);
                    }
                });
    }

    public void getCustomerList(int page, String query, String id, int type) {
        this.mDataManager.getBorrowerList(page, query, id, type)
                .subscribe(new Observer<BaseBean<List<Customer>>>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        CustomerContactRelationPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<List<Customer>> data) {
                        if (CustomerContactRelationPresenter.this.getMvpView() != null)
                            CustomerContactRelationPresenter.this.getMvpView().onGetCustomerListSuccess(data);
                    }
                });
    }

    public void modifyRelationship(CustomerContact customerContact) {
        this.mDataManager.modifyRelationship(customerContact)
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        CustomerContactRelationPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean data) {
                        if (CustomerContactRelationPresenter.this.getMvpView() != null)
                            CustomerContactRelationPresenter.this.getMvpView().onModifyRelationshipSuccess(data);
                    }
                });
    }

    public void getRelationshipList(String id, int type) {
        this.mDataManager.getRelationshipList(id, type)
                .subscribe(new Observer<BaseBean<List<CustomerContactRelation>>>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        CustomerContactRelationPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<List<CustomerContactRelation>> data) {
                        if (CustomerContactRelationPresenter.this.getMvpView() != null)
                            CustomerContactRelationPresenter.this.getMvpView().onGetRelationshipListSuccess(data.getData());
                    }
                });
    }

}
