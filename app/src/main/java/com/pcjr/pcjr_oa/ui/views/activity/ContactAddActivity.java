package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseAppCompatActivity;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;

/**
 *  新建联系人
 *  Created by Mario on 2017/10/30上午11:12
 */
public class ContactAddActivity extends BaseAppCompatActivity {

    @BindView(R.id.rl_avatar) RelativeLayout rlAvatar;

    @BindView(R.id.txt_customer_name) EditText txtCustomerName;
    @BindView(R.id.txt_mobile) EditText txtMobile;
    @BindView(R.id.txt_telephone) EditText txtTelephone;
    @BindView(R.id.txt_company) EditText txtCompany;
    @BindView(R.id.txt_job) EditText txtJob;
    @BindView(R.id.txt_email) EditText txtEmail;

    @BindView(R.id.btn_cancel) Button btnCancel;
    @BindView(R.id.btn_finish) Button btnFinish;
    @BindView(R.id.btn_save) Button btnSave;

    @Override
    protected int getLayoutId() {
        return R.layout.contact_add;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
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
            startActivity(new Intent(this,CustomerFullPersonalActivity.class));
        });

    }

    @Override
    protected void initData() {

    }

}
