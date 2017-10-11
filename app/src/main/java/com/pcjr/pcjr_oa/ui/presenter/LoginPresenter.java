package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Token;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.core.mvp.MvpView;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;



/**
 *
 *  Created by Mario on 2017/9/28下午3:54
 */
public class LoginPresenter extends BasePresenter<MvpView<BaseBean<Token>>> {

    public static String TAG = LoginPresenter.class.getSimpleName();

    public LoginPresenter() {
        long time = System.currentTimeMillis();
    }

    public void login(String username, String password,String key) {
        this.mDataManager.login(username, password, key)
                .subscribe(new Observer<BaseBean<Token>>() {
                    @Override
                    public void onComplete() {
                        if (LoginPresenter.this.mCompositeDisposable != null) {
                            LoginPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        LoginPresenter.this.getMvpView().onFailure(e);
                    }


                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<Token> data) {
                        if (LoginPresenter.this.getMvpView() != null)
                            LoginPresenter.this.getMvpView().onSuccess(data);
                    }
                });
    }

}
