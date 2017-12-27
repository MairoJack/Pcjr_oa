package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.ContactView;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/12/19下午2:53
 */
public class ContactPresenter extends BasePresenter<ContactView> {

    public static String TAG = ContactPresenter.class.getSimpleName();

    public void addContact(Contact contact) {
        this.mDataManager.addContact(contact)
                .subscribe(new Observer<BaseBean<Contact>>() {
                    @Override
                    public void onComplete() {
                        if (ContactPresenter.this.mCompositeDisposable != null) {
                            ContactPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ContactPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<Contact> data) {
                        if (ContactPresenter.this.getMvpView() != null)
                            ContactPresenter.this.getMvpView().onAddContactSuccess(data);
                    }
                });
    }

    public void modifyContact(Contact contact) {
        this.mDataManager.modifyContact(contact)
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onComplete() {
                        if (ContactPresenter.this.mCompositeDisposable != null) {
                            ContactPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ContactPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean data) {
                        if (ContactPresenter.this.getMvpView() != null)
                            ContactPresenter.this.getMvpView().onModifyContactSuccess(data);
                    }
                });
    }

    public void getContactDetail(String id) {
        this.mDataManager.getContactDetail(id)
                .subscribe(new Observer<BaseBean<Contact>>() {
                    @Override
                    public void onComplete() {
                        if (ContactPresenter.this.mCompositeDisposable != null) {
                            ContactPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ContactPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<Contact> data) {
                        if (ContactPresenter.this.getMvpView() != null)
                            ContactPresenter.this.getMvpView().onGetContactDetailSuccess(data.getData());
                    }
                });
    }
}
