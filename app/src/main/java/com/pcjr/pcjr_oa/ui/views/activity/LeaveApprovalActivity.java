package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;

import butterknife.BindView;

/**
 *  请假审批
 *  Created by Mario on 2017/9/12上午8:37
 */
public class LeaveApprovalActivity extends UnionActivity {

    @BindView(R.id.btn_save) Button btnSave;
    @BindView(R.id.btn_submit) Button btnSubmit;
    @BindView(R.id.btn_confirm) Button btnConfirm;
    @BindView(R.id.btn_cancel) Button btnCancel;

    @BindView(R.id.rl_leave_type) RelativeLayout rlLeaveType;
    @BindView(R.id.rl_start_time) RelativeLayout rlStartTime;
    @BindView(R.id.rl_end_time) RelativeLayout rlEndTime;
    @BindView(R.id.rl_sharer) RelativeLayout rlSharer;
    @BindView(R.id.rl_tag) RelativeLayout rlTag;
    @BindView(R.id.rl_attachment) RelativeLayout rlAttachment;

    @BindView(R.id.txt_leave_type) TextView txtLeaveType;
    @BindView(R.id.txt_start_time) TextView txtStartTime;
    @BindView(R.id.txt_end_time) TextView txtEndTime;
    @BindView(R.id.txt_sharer) TextView txtSharer;
    @BindView(R.id.txt_tag) TextView txtTag;
    @BindView(R.id.txt_attachment) TextView txtAttachment;

    @Override
    protected int getLayoutId() {
        return R.layout.leave_approval;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {


    }


    @Override
    protected void initListeners() {

        super.initListeners();


        btnCancel.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });

        btnConfirm.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });

        btnSave.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });

        btnSubmit.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });

        rlStartTime.setOnClickListener(v-> Dialog.datePicker(this,"开始时间",txtStartTime));

        rlEndTime.setOnClickListener(v->Dialog.datePicker(this,"结束时间",txtEndTime));

        rlLeaveType.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            intent.putExtra("title","请假类型");
            intent.putExtra("select",txtLeaveType.getText());
            intent.putExtra("data",Constant.SELECT_LEAVE_TYPE);
            startActivityForResult(intent, Constant.REQUEST_LEAVE_TYPE);
        });

        rlTag.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,TagListActivity.class), Constant.REQUEST_TAG);
        });


    }

    @Override
    protected void initData() {


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            String result = data.getStringExtra("result");
            switch (requestCode){
                case Constant.REQUEST_LEAVE_TYPE:
                    txtLeaveType.setText(result);
                    break;
                case Constant.REQUEST_TAG:
                    txtTag.setText(result);
                    break;
            }
        }
    }
}
