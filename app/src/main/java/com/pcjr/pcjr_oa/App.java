package com.pcjr.pcjr_oa;

import android.app.Application;
import android.content.Context;

/**
 *
 *  Created by Mario on 2017/9/29上午9:20
 */
public class App extends Application {

    private static App mInstance = new App();
    private static Context context;

    public static final long ONE_KB = 1024L;
    public static final long ONE_MB = ONE_KB * 1024L;
    public static final long CACHE_DATA_MAX_SIZE = ONE_MB * 3L;

    public static App getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context=getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }





}
