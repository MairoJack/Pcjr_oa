package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;

import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.PlatformData;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.core.mvp.MvpView;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/9/28下午3:54
 */
public class CountPresenter extends BasePresenter<MvpView<PlatformData>> {

    public static String TAG = CountPresenter.class.getSimpleName();

    public void getPlatformData() {
        this.mDataManager.getPlatformData()
                .subscribe(new Observer<BaseBean<PlatformData>>() {
                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        CountPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<PlatformData> data) {
                        if (CountPresenter.this.getMvpView() != null)
                            CountPresenter.this.getMvpView().onSuccess(data.getData());
                    }
                });
    }
}
