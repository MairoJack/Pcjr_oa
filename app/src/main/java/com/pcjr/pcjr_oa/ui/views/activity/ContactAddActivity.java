package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.IntentSelect;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.core.BaseAppCompatActivity;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;

/**
 *  新建联系人
 *  Created by Mario on 2017/10/30上午11:12
 */
public class ContactAddActivity extends BaseAppCompatActivity {


    @BindView(R.id.rl_belong_customer) RelativeLayout rlBelongCustomer;
    @BindView(R.id.rl_sex) RelativeLayout rlSex;
    @BindView(R.id.rl_role) RelativeLayout rlRole;
    @BindView(R.id.rl_intimacy) RelativeLayout rlIntimacy;

    @BindView(R.id.txt_name) EditText txtName;
    @BindView(R.id.txt_belong_customer) TextView txtBelongCustomer;
    @BindView(R.id.txt_mobile) EditText txtMobile;
    @BindView(R.id.txt_address) EditText txtAddress;
    @BindView(R.id.txt_company) EditText txtCompany;
    @BindView(R.id.txt_job) EditText txtJob;
    @BindView(R.id.txt_sex) TextView txtSex;
    @BindView(R.id.txt_email) EditText txtEmail;
    @BindView(R.id.txt_role) TextView txtRole;
    @BindView(R.id.txt_intimacy) TextView txtIntimacy;
    @BindView(R.id.txt_remark) EditText txtRemark;

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

        rlSex.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            intent.putExtra("intentSelect",new IntentSelect(
                    "性别",txtSex.getText().toString(),Constant.SELECT_SEX));
            startActivityForResult(intent, Constant.REQUEST_SEX);
        });

        rlRole.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            intent.putExtra("intentSelect",new IntentSelect(
                    "角色关系",txtRole.getText().toString(),Constant.SELECT_ROLE,true));
            startActivityForResult(intent, Constant.REQUEST_ROLE);
        });

        rlIntimacy.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            intent.putExtra("intentSelect",new IntentSelect(
                    "亲密度",txtIntimacy.getText().toString(),Constant.SELECT_INTIMACY,true));
            startActivityForResult(intent, Constant.REQUEST_INTIMACY);
        });


        btnSave.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivity(new Intent(this,ContactFullActivity.class));
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
                case Constant.REQUEST_ROLE:
                    txtRole.setText(result);
                    break;
                case Constant.REQUEST_INTIMACY:
                    txtIntimacy.setText(result);
                    break;

            }
        }
    }

}
