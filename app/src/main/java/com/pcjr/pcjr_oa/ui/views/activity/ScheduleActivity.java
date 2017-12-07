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
import com.pcjr.pcjr_oa.bean.IntentSelect;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.utils.ViewUtil;


import butterknife.BindView;

/**
 *  日程基类
 *  Created by Mario on 2017/8/17下午2:31
 */
public abstract class ScheduleActivity extends UnionActivity {

    @BindView(R.id.btn_cancel) Button btnCancel;

    @BindView(R.id.txt_schedule_title) EditText txtScheduleTitle;
    @BindView(R.id.txt_schedule_content) EditText txtScheduleContent;

    @BindView(R.id.rl_result) RelativeLayout rlResult;
    @BindView(R.id.rl_remind_setting) RelativeLayout rlRemindSetting;
    @BindView(R.id.rl_participants) RelativeLayout rlParticipants;
    @BindView(R.id.rl_openness) RelativeLayout rlOpenness;
    @BindView(R.id.rl_tag) RelativeLayout rlTag;
    @BindView(R.id.rl_attachment) RelativeLayout rlAttachment;

    @BindView(R.id.switch_all_day) SwitchCompat switchAllDay;
    @BindView(R.id.switch_repeat) SwitchCompat switchRepeat;
    @BindView(R.id.switch_remind) SwitchCompat switchRemind;
    @BindView(R.id.txt_start_time) TextView txtStartTime;
    @BindView(R.id.txt_end_time) TextView txtEndTime;
    @BindView(R.id.txt_result) TextView txtResult;
    @BindView(R.id.txt_remind_setting) TextView txtRemindSetting;
    @BindView(R.id.txt_participants) TextView txtParticipants;
    @BindView(R.id.txt_openness) TextView txtOpenness;
    @BindView(R.id.txt_tag) TextView txtTag;
    @BindView(R.id.txt_attachment) TextView txtAttachment;

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

        switchAllDay.setOnCheckedChangeListener((compoundButton,b)-> {

        });

        switchRepeat.setOnCheckedChangeListener((compoundButton,b)-> {
            if(b){
                rlResult.setVisibility(View.VISIBLE);
            }else{
                rlResult.setVisibility(View.GONE);
            }
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
            startActivityForResult(new Intent(this,TagListActivity.class),Constant.REQUEST_TAG);
        });

        rlRemindSetting.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,ScheduleRemindSettingActivity.class), Constant.REQUEST_REMIND_SETTING);
        });

        rlOpenness.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            intent.putExtra("intentSelect",new IntentSelect(
                    "公开程度",txtOpenness.getText().toString(),Constant.SELECT_OPENNESS));
            startActivityForResult(intent,Constant.REQUEST_OPENNESS);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            String result = data.getStringExtra("result");
            switch (requestCode){
                case Constant.REQUEST_REMIND_SETTING:
                    txtRemindSetting.setText(result);
                    break;
                case Constant.REQUEST_TAG:
                    txtTag.setText(result);
                    break;
                case Constant.REQUEST_OPENNESS:
                    txtOpenness.setText(result);
                    break;
            }
        }
    }

}
