package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;


/**
 *  完善客户 - 个人
 *  Created by Mario on 2017/9/21上午8:50
 */
public class CustomerFullPersonalActivity extends CustomerActivity {

    @BindView(R.id.btn_confirm) Button btnConfirm;

    @BindView(R.id.rl_sex) RelativeLayout rlSex;
    @BindView(R.id.rl_marital_status) RelativeLayout rlMaritalStatus;

    @BindView(R.id.txt_identity) EditText txtIdentity;
    @BindView(R.id.txt_company) EditText txtCompany;
    @BindView(R.id.txt_job_name) EditText txtJobName;
    @BindView(R.id.txt_sex) TextView txtSex;
    @BindView(R.id.txt_marital_status) TextView txtMaritalStatus;

    @Override
    protected int getLayoutId() {
        return R.layout.customer_full_personal;
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

        rlSex.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            intent.putExtra("title","性别");
            intent.putExtra("select",txtSex.getText());
            intent.putExtra("data", Constant.SELECT_SEX);
            startActivityForResult(intent, Constant.REQUEST_SEX);
        });

        rlMaritalStatus.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            intent.putExtra("title","婚姻状况");
            intent.putExtra("select",txtMaritalStatus.getText());
            intent.putExtra("data", Constant.SELECT_MARITAL_STATUS);
            startActivityForResult(intent, Constant.REQUEST_MARITAL_STATUS);
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
                case Constant.REQUEST_SEX:
                    txtSex.setText(result);
                    break;
                case Constant.REQUEST_MARITAL_STATUS:
                    txtMaritalStatus.setText(result);
                    break;
            }
        }
    }

}
