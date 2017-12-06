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
 *  客户信息
 *  Created by Mario on 2017/12/5下午4:03
 */
public class CustomerDetailActivity extends BaseToolbarActivity {

    @BindView(R.id.txt_name) TextView txtName;
    @BindView(R.id.txt_credit) TextView txtCredit;
    @BindView(R.id.txt_customer_manager) TextView txtCustomerManager;
    @BindView(R.id.txt_founder) TextView txtFounder;

    @BindView(R.id.txt_mobile) TextView txtMobile;
    @BindView(R.id.txt_fax) TextView txtFax;
    @BindView(R.id.txt_address) TextView txtAddress;
    @BindView(R.id.txt_postcode) TextView txtPostcode;
    @BindView(R.id.txt_identity) TextView txtIdentity;
    @BindView(R.id.txt_bank_card) TextView txtBankCard;
    @BindView(R.id.txt_company) TextView txtCompany;
    @BindView(R.id.txt_job) TextView txtJob;
    @BindView(R.id.txt_sex) TextView txtSex;
    @BindView(R.id.txt_marital_status) TextView txtMaritalStatus;
    @BindView(R.id.txt_website) TextView txtWebsite;
    @BindView(R.id.txt_email) TextView txtEmail;
    @BindView(R.id.txt_counter_guarantee) TextView txtCounterGuarantee;
    @BindView(R.id.txt_borrower) TextView txtBorrower;
    @BindView(R.id.txt_credit_record) TextView txtCreditRecord;
    @BindView(R.id.txt_remark) TextView txtRemark;

    @BindView(R.id.ll_feedback) LinearLayout llFeedback;
    @BindView(R.id.ll_query_records) LinearLayout llQueryRecords;
    @BindView(R.id.ll_log) LinearLayout llLog;
    @BindView(R.id.ll_delete) LinearLayout llDelete;

    @Override
    protected int getLayoutId() {
        return R.layout.customer_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("查看客户");
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
            startActivity(new Intent(this,CustomerEditPersonalActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
