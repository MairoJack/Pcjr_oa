package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;

import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Contract;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.ContractView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/12/27上午10:02
 */
public class ContractPresenter extends BasePresenter<ContractView> {

    public static String TAG = ContractPresenter.class.getSimpleName();

    public void addContract(Contract contract) {
        this.mDataManager.addContract(contract)
                .subscribe(new Observer<BaseBean<Contract>>() {
                    @Override
                    public void onComplete() {
                        if (ContractPresenter.this.mCompositeDisposable != null) {
                            ContractPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ContractPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<Contract> data) {
                        if (ContractPresenter.this.getMvpView() != null)
                            ContractPresenter.this.getMvpView().onAddContractSuccess(data);
                    }
                });
    }

    public void modifyContract(Contract contract) {
        this.mDataManager.modifyContract(contract)
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onComplete() {
                        if (ContractPresenter.this.mCompositeDisposable != null) {
                            ContractPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ContractPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean data) {
                        if (ContractPresenter.this.getMvpView() != null)
                            ContractPresenter.this.getMvpView().onModifyContractSuccess(data);
                    }
                });
    }

    public void getContractDetail(String id) {
        this.mDataManager.getContractDetail(id)
                .subscribe(new Observer<BaseBean<Contract>>() {
                    @Override
                    public void onComplete() {
                        if (ContractPresenter.this.mCompositeDisposable != null) {
                            ContractPresenter.this.mCompositeDisposable.clear();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ContractPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<Contract> data) {
                        if (ContractPresenter.this.getMvpView() != null)
                            ContractPresenter.this.getMvpView().onGetContractDetailSuccess(data.getData());
                    }
                });
    }
}
