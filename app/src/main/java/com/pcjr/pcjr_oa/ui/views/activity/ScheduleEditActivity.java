package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;

import butterknife.BindView;


/**
 *  编辑日程
 *  Created by Mario on 2017/8/16上午10:09
 */
public class ScheduleEditActivity extends ScheduleActivity {

    @BindView(R.id.btn_confirm) Button btnConfirm;

    @BindView(R.id.rl_start_time) RelativeLayout rlStartTime;
    @BindView(R.id.rl_end_time) RelativeLayout rlEndTime;

    @BindView(R.id.txt_start_date) TextView txtStartDate;
    @BindView(R.id.txt_start_time) TextView txtStartTime;
    @BindView(R.id.txt_end_date) TextView txtEndDate;
    @BindView(R.id.txt_end_time) TextView txtEndTime;

    @BindView(R.id.ll_feedback) LinearLayout llFeedback;
    @BindView(R.id.ll_follow) LinearLayout llFollow;
    @BindView(R.id.ll_delete) LinearLayout llDelete;


    @Override
    protected int getLayoutId() {
        return R.layout.schedule_edit;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {


    }


    @Override
    protected void initListeners() {

        super.initListeners();

        btnConfirm.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });

        rlStartTime.setOnClickListener(v-> Dialog.datePicker(this,"开始时间",txtStartDate,txtStartTime));

        rlEndTime.setOnClickListener(v->Dialog.datePicker(this,"结束时间",txtEndDate,txtEndTime));


    }

    @Override
    protected void initData() {


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            switch (requestCode){

            }
        }
    }
}
