package com.pcjr.pcjr_oa.utils;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RxUtils {

    private static ObservableTransformer ioToMainThreadSchedulerTransformer;


    static {
        ioToMainThreadSchedulerTransformer = createIOToMainThreadScheduler();
    }


    private static <T> ObservableTransformer<T, T> createIOToMainThreadScheduler() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                                         .unsubscribeOn(
                                                 Schedulers.computation())
                                         .observeOn(AndroidSchedulers.mainThread());
    }


    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> applyIOToMainThreadSchedulers() {
        return ioToMainThreadSchedulerTransformer;
    }
}
