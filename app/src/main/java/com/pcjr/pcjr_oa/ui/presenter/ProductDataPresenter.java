package com.pcjr.pcjr_oa.ui.presenter;

import android.util.Log;

import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Product;
import com.pcjr.pcjr_oa.bean.ProductSummary;
import com.pcjr.pcjr_oa.core.mvp.BasePresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.ProductDataView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 *
 *  Created by Mario on 2017/9/28下午3:54
 */
public class ProductDataPresenter extends BasePresenter<ProductDataView> {

    public static String TAG = ProductDataPresenter.class.getSimpleName();

    public void getProductDurationInfo(long startDate, long endDate) {
        this.mDataManager.getProductDurationInfo(startDate,endDate)
                .subscribe(new Observer<BaseBean<ProductSummary>>() {
                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ProductDataPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<ProductSummary> data) {
                        if (ProductDataPresenter.this.getMvpView() != null)
                            ProductDataPresenter.this.getMvpView().onProductDurationInfoSuccess(data.getData());
                    }
                });
    }

    public void getTodayProductInfo() {
        this.mDataManager.getTodayProductInfo()
                .subscribe(new Observer<BaseBean<List<Product>>>() {
                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        ProductDataPresenter.this.getMvpView().onFailure(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseBean<List<Product>> data) {
                        if (ProductDataPresenter.this.getMvpView() != null)
                            ProductDataPresenter.this.getMvpView().onTodayProductInfoSuccess(data.getData());
                    }
                });
    }
}
