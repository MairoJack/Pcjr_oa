package com.pcjr.pcjr_oa.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.TimePickerView;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.utils.DateUtils;

import java.util.Calendar;

/**
 * Created by Mario on 2016/6/20.
 */
public class Dialog {

    public static void datePicker(Context context,String title, TextView txtDate, TextView txtTime) {
        boolean[] type = new boolean[]{true, true, true, true, true, false};
        TimePickerView timePickerView = new TimePickerView.Builder(context, (date, view) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            txtDate.setText(DateUtils.dateTimeToStr(date, DateUtils.DATE_FORMAT_YYYY_MM_DD_WEEK));
            txtTime.setText(DateUtils.dateTimeToStr(date, DateUtils.HH_MI_SS));
        }).setTitleText(title)
                .setSubmitColor(Color.parseColor("#ff4341"))
                .setCancelColor(Color.parseColor("#ff4341"))
                .setType(type)
                .build();
        timePickerView.setDate(Calendar.getInstance());
        timePickerView.show();
    }

    public static void datePicker(Context context,String title, TextView txtDate) {
        boolean[] type = new boolean[]{true, true, true, true, true, false};
        TimePickerView timePickerView = new TimePickerView.Builder(context, (date, view) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            txtDate.setText(DateUtils.dateTimeToStr(date, DateUtils.YYYY_MM_DD_HH_MI));
        }).setTitleText(title)
                .setSubmitColor(Color.parseColor("#ff4341"))
                .setCancelColor(Color.parseColor("#ff4341"))
                .setType(type)
                .build();
        timePickerView.setDate(Calendar.getInstance());
        timePickerView.show();
    }

    public static void show(String msg, Context context) {
        if (null != context) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("提示");
            builder.setMessage(msg);
            builder.setPositiveButton("确定", null);
            builder.show();
        }
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

    public static MaterialDialog progress(Context context,String title){
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.content(title).progress(true, 0);
        MaterialDialog dialog = builder.build();
        return dialog;
    }

    public static MaterialDialog progress(Context context){
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.content(R.string.progress_dialog).progress(true, 0);
        MaterialDialog dialog = builder.build();
        return dialog;
    }

}
