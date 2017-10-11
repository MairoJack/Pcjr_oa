package com.pcjr.pcjr_oa.widget;

import android.view.View;

/**
 * 防止连续快速点击
 * Created by mario on 2017/8/3.
 */

public abstract class OnMultiClickListener implements View.OnClickListener {

    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public abstract void onMultiClick(View v);

    @Override
    public void onClick(View v) {
        long curClickTime = System.currentTimeMillis();
        if((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            lastClickTime = curClickTime;
            onMultiClick(v);
        }
    }
}
