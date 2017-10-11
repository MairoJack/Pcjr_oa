package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;

import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.StaffCompany;
import com.pcjr.pcjr_oa.bean.Token;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.core.mvp.MvpView;
import com.pcjr.pcjr_oa.ui.presenter.ivview.WorkView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/9/28下午3:54
 */
public class WorkPresenter extends BasePresenter<WorkView> {

    public static String TAG = WorkPresenter.class.getSimpleName();

    public void getStaffCompanyList() {
        this.mDataManager.getStaffCompanyList()
                .subscribe(new Observer<BaseBean<List<StaffCompany>>>() {
                    @Override
                    public void onComplete() {
                        if (WorkPresenter.this.mCompositeDisposable != null) {
                            WorkPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        WorkPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<List<StaffCompany>> data) {
                        if (WorkPresenter.this.getMvpView() != null)
                            WorkPresenter.this.getMvpView().onStaffCompanyListSuccess(data);
                    }
                });
    }

}
