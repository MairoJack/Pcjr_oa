package com.pcjr.pcjr_oa.core.mvp;

/**
 * Created by Mario on 2016/7/8.
 */
public interface MvpView<T> {
     void onFailure(Throwable e);
     void onSuccess(T data);
}
