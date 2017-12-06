package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.core.BaseAppCompatActivity;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;

/**
 *  新建客户
 *  Created by Mario on 2017/9/20下午2:32
 */
public class CustomerAddActivity extends BaseAppCompatActivity {

    @BindView(R.id.rl_manager) RelativeLayout rlManager;

    @BindView(R.id.txt_customer_name) EditText txtCustomerName;
    @BindView(R.id.txt_mobile) EditText txtMobile;
    @BindView(R.id.txt_telephone) EditText txtTelephone;

    @BindView(R.id.txt_avatar) TextView txtAvatar;
    //@BindView(R.id.img_avatar) ImageView imgAvatar;
    @BindView(R.id.txt_manager) TextView txtManager;

    @BindView(R.id.btn_cancel) Button btnCancel;
    @BindView(R.id.btn_finish) Button btnFinish;
    @BindView(R.id.btn_save) Button btnSave;

    @Override
    protected int getLayoutId() {
        return R.layout.customer_add;
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
            int type = getIntent().getIntExtra("type",0);
            if(type == 0 ) {
                startActivity(new Intent(this,CustomerFullPersonalActivity.class));
            } else {
                startActivity(new Intent(this,CustomerFullCompanyActivity.class));
            }

        });

        rlManager.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,ParticipantActivity.class), Constant.REQUEST_MANAGER);
        });

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
