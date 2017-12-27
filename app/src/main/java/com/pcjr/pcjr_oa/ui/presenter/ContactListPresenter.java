package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;

import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Mario on 2017/12/19下午2:50
 */
public class ContactListPresenter extends BasePresenter<MvpView<BaseBean<List<Contact>>>> {

    public static String TAG = ContactListPresenter.class.getSimpleName();

    public void getContactList(int page, String query) {
        this.mDataManager.getContactList(page, query, "0", 0)
                .subscribe(new Observer<BaseBean<List<Contact>>>() {
                    @Override
                    public void onComplete() {
                        if (ContactListPresenter.this.mCompositeDisposable != null) {
                            ContactListPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ContactListPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<List<Contact>> data) {
                        if (ContactListPresenter.this.getMvpView() != null)
                            ContactListPresenter.this.getMvpView().onSuccess(data);
                    }
                });
    }

}
