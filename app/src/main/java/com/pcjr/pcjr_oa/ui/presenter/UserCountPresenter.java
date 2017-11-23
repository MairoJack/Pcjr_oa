package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Member;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.MemberView;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/9/28下午3:54
 */
public class UserCountPresenter extends BasePresenter<MemberView> {

    public static String TAG = UserCountPresenter.class.getSimpleName();

    public void getMemberNumDurationInfo(long startDate, long endDate) {
        this.mDataManager.getMemberNumDurationInfo(startDate,endDate)
                .subscribe(new Observer<BaseBean<Member>>() {
                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        UserCountPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<Member> data) {
                        if (UserCountPresenter.this.getMvpView() != null)
                            UserCountPresenter.this.getMvpView().onMemberNumDurationInfoSuccess(data.getData());
                    }
                });
    }

    public void getEffectiveMemberNumDurationInfo(long startDate, long endDate) {
        this.mDataManager.getEffectiveMemberNumDurationInfo(startDate,endDate)
                .subscribe(new Observer<BaseBean<Member>>() {
                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        UserCountPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<Member> data) {
                        if (UserCountPresenter.this.getMvpView() != null)
                            UserCountPresenter.this.getMvpView().onEffectiveMemberNumDurationInfoSuccess(data.getData());
                    }
                });
    }
}
