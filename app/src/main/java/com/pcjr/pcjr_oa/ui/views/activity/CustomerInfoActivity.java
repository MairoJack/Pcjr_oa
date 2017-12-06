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
public class CustomerInfoActivity extends BaseToolbarActivity {

    @BindView(R.id.txt_name) TextView txtName;
    @BindView(R.id.txt_customer_manager) TextView txtCustomerManager;
    @BindView(R.id.txt_address) TextView txtAddress;
    @BindView(R.id.txt_founder) TextView txtFounder;
    @BindView(R.id.txt_credit) TextView txtCredit;

    @BindView(R.id.ll_customer_linkman) LinearLayout llCustomerLinkman;
    @BindView(R.id.ll_union_agreement) LinearLayout llUnionAgreement;
    @BindView(R.id.ll_union_task) LinearLayout llUnionTask;
    @BindView(R.id.ll_union_project) LinearLayout llUnionProject;
    @BindView(R.id.ll_contact_customer) LinearLayout llContactCustomer;


    @BindView(R.id.ll_feedback) LinearLayout llFeedback;
    @BindView(R.id.ll_query_records) LinearLayout llQueryRecords;
    @BindView(R.id.ll_log) LinearLayout llLog;
    @BindView(R.id.ll_delete) LinearLayout llDelete;

    @Override
    protected int getLayoutId() {
        return R.layout.customer_info;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("客户");
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
        getMenuInflater().inflate(R.menu.menu_detail,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_detail){
            startActivity(new Intent(this,CustomerDetailActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
