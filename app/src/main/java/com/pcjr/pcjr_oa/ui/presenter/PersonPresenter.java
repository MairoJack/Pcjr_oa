package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Person;
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
public class PersonPresenter extends BasePresenter<MvpView<List<Person>>> {

    public static String TAG = PersonPresenter.class.getSimpleName();

    public void getManagerList() {
        this.mDataManager.getManagerList()
                .subscribe(new Observer<BaseBean<List<Person>>>() {
                    @Override
                    public void onComplete() {
                        if (PersonPresenter.this.mCompositeDisposable != null) {
                            PersonPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        PersonPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<List<Person>> data) {
                        if (PersonPresenter.this.getMvpView() != null)
                            PersonPresenter.this.getMvpView().onSuccess(data.getData());
                    }
                });
    }

}
