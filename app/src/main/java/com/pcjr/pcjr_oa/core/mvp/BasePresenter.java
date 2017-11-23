package com.pcjr.pcjr_oa.core.mvp;



import com.pcjr.pcjr_oa.data.DataManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Mario on 2016/7/8.
 */
public class BasePresenter<T extends MvpView> implements Presenter<T>{

    private T mMvpView;
    public CompositeDisposable mCompositeDisposable;
    public DataManager mDataManager;

    @Override
    public void attachView(T mvpView) {
        this.mMvpView = mvpView;
        this.mCompositeDisposable = new CompositeDisposable();
        this.mDataManager = DataManager.getInstance();
    }

    @Override
    public void detachView() {
        this.mMvpView = null;
        this.mCompositeDisposable.clear();
        this.mCompositeDisposable.dispose();
        this.mCompositeDisposable = null;
        this.mDataManager = null;
    }

    public boolean isViewAttached(){
        return mMvpView != null;
    }

    public T getMvpView(){
        return mMvpView;
    }

    public void checkViewAttached(){
        if(!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException(){
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
