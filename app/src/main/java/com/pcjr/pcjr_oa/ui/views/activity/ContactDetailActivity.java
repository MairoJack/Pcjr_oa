package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;

import butterknife.BindView;

/**
 *  联系人信息
 *  Created by Mario on 2017/12/7下午2:43
 */
public class ContactDetailActivity extends BaseToolbarActivity {

    @BindView(R.id.txt_name) TextView txtName;
    @BindView(R.id.txt_belong_customer) TextView txtBelongCustomer;
    @BindView(R.id.txt_time) TextView txtTime;

    @BindView(R.id.txt_mobile) TextView txtMobile;
    @BindView(R.id.txt_address) TextView txtAddress;
    @BindView(R.id.txt_company) TextView txtCompany;
    @BindView(R.id.txt_job) TextView txtJob;
    @BindView(R.id.txt_sex) TextView txtSex;
    @BindView(R.id.txt_email) TextView txtEmail;
    @BindView(R.id.txt_role) TextView txtRole;
    @BindView(R.id.txt_intimacy) TextView txtIntimacy;
    @BindView(R.id.txt_remark) TextView txtRemark;

    @BindView(R.id.ll_feedback) LinearLayout llFeedback;
    @BindView(R.id.ll_query_records) LinearLayout llQueryRecords;
    @BindView(R.id.ll_log) LinearLayout llLog;
    @BindView(R.id.ll_delete) LinearLayout llDelete;

    @Override
    protected int getLayoutId() {
        return R.layout.contact_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("联系人");
        showBack();
    }


    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modify,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_modify){
            startActivity(new Intent(this,ContactEditActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
