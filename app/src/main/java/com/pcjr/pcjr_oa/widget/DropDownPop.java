package com.pcjr.pcjr_oa.widget;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by Mario on 2016/6/20.
 */
public class DropDownPop {
    public static void init(String msg, Context context) {

    }

    public static void show(String title, String msg, Context context) {
        if (null != context) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(title);
            builder.setMessage(msg);
            builder.setPositiveButton("确定", null);
            builder.show();
        }
    }

}
