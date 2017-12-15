package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.view.ViewCompat;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.core.BaseAppCompatActivity;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;

import butterknife.BindView;


/**
 *  新建任务
 *  Created by Mario on 2017/8/3上午10:39
 */
public class TaskAddActivity extends BaseAppCompatActivity {

    @BindView(R.id.rl_manager) RelativeLayout rlManager;
    @BindView(R.id.rl_start_time) RelativeLayout rlStartTime;
    @BindView(R.id.rl_end_time) RelativeLayout rlEndTime;

    @BindView(R.id.txt_start_time) TextView txtStartTime;
    @BindView(R.id.txt_end_time) TextView txtEndTime;
    @BindView(R.id.txt_avatar) TextView txtAvatar;

    //@BindView(R.id.img_avatar) ImageView imgAvatar;
    @BindView(R.id.txt_manager) TextView txtManager;
    @BindView(R.id.txt_task_title) EditText txtTaskTitle;

    @BindView(R.id.btn_cancel) Button btnCancel;
    @BindView(R.id.btn_finish) Button btnFinish;
    @BindView(R.id.btn_save) Button btnSave;

    @Override
    protected int getLayoutId() {
        return R.layout.task_add;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ViewCompat.setBackgroundTintList(txtAvatar,ColorStateList.valueOf(Color.parseColor("#2b313e")));
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {
        btnCancel.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });

        btnFinish.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });

        btnSave.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivity(new Intent(this,TaskFullActivity.class));
        });

        rlManager.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,PersonSingleSelectionActivity.class), Constant.REQUEST_MANAGER);
        });

        rlStartTime.setOnClickListener(v-> Dialog.datePicker(this,"开始时间",txtStartTime));

        rlEndTime.setOnClickListener(v->Dialog.datePicker(this,"结束时间",txtEndTime));
    }

    @Override
    protected void initData() {


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            String result = data.getStringExtra("result");
            switch (requestCode){
                case Constant.REQUEST_MANAGER:
                    txtManager.setText(result);
                    break;
            }
        }
    }

}
