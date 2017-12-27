package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;

import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Contract;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Mario on 2017/9/28下午3:54
 */
public class ContractListPresenter extends BasePresenter<MvpView<BaseBean<List<Contract>>>> {

    public static String TAG = ContractListPresenter.class.getSimpleName();

    public void getContractList(int page, String query) {
        this.mDataManager.getContractList(page, query)
                .subscribe(new Observer<BaseBean<List<Contract>>>() {
                    @Override
                    public void onComplete() {
                        if (ContractListPresenter.this.mCompositeDisposable != null) {
                            ContractListPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ContractListPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<List<Contract>> data) {
                        if (ContractListPresenter.this.getMvpView() != null)
                            ContractListPresenter.this.getMvpView().onSuccess(data);
                    }
                });
    }

}
