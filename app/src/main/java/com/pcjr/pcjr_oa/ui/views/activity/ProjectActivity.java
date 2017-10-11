package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;


import butterknife.BindView;


/**
 *  项目基类
 *  Created by Mario on 2017/8/18上午9:14
 */
public abstract class ProjectActivity extends UnionActivity {

    @BindView(R.id.btn_cancel) Button btnCancel;

    @BindView(R.id.txt_project_title) EditText txtProjectTitle;
    @BindView(R.id.txt_project_content) EditText txtProjectContent;

    @BindView(R.id.rl_manager) RelativeLayout rlManager;
    @BindView(R.id.rl_start_time) RelativeLayout rlStartTime;
    @BindView(R.id.rl_end_time) RelativeLayout rlEndTime;
    @BindView(R.id.rl_participants) RelativeLayout rlParticipants;
    @BindView(R.id.rl_sharer) RelativeLayout rlSharer;
    @BindView(R.id.rl_tag) RelativeLayout rlTag;
    @BindView(R.id.rl_attachment) RelativeLayout rlAttachment;
    @BindView(R.id.rl_remind_setting) RelativeLayout rlRemindSetting;
    @BindView(R.id.rl_union_task) RelativeLayout rlUnionTask;

    @BindView(R.id.txt_manager) TextView txtManager;
    @BindView(R.id.txt_start_date) TextView txtStartDate;
    @BindView(R.id.txt_start_time) TextView txtStartTime;
    @BindView(R.id.txt_end_date) TextView txtEndDate;
    @BindView(R.id.txt_end_time) TextView txtEndTime;
    @BindView(R.id.txt_participants) TextView txtParticipants;
    @BindView(R.id.txt_sharer) TextView txtSharer;
    @BindView(R.id.txt_tag) TextView txtTag;
    @BindView(R.id.txt_attachment) TextView txtAttachment;
    @BindView(R.id.switch_remind) SwitchCompat switchRemind;
    @BindView(R.id.txt_remind_setting) TextView txtRemindSetting;
    @BindView(R.id.txt_union_task) TextView txtUnionTask;

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {

        super.initListeners();

        btnCancel.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });


        switchRemind.setOnCheckedChangeListener((compoundButton,b)-> {
            if(b){
                rlRemindSetting.setVisibility(View.VISIBLE);
            }else{
                rlRemindSetting.setVisibility(View.GONE);
            }
        });

        rlTag.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,TagListActivity.class), Constant.REQUEST_TAG);
        });

        rlRemindSetting.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,ScheduleRemindSettingActivity.class),Constant.REQUEST_REMIND_SETTING);
        });

        rlStartTime.setOnClickListener(v-> Dialog.datePicker(this,"开始时间",txtStartDate,txtStartTime));

        rlEndTime.setOnClickListener(v->Dialog.datePicker(this,"结束时间",txtEndDate,txtEndTime));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case Constant.REQUEST_REMIND_SETTING:
                    txtRemindSetting.setText(data.getStringExtra("result"));
            }
        }
    }

}
