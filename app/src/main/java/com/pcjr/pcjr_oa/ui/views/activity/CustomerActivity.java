package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.IntentSelect;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;

/**
 *  客户基类
 *  Created by Mario on 2017/9/20下午3:22
 */
public abstract class CustomerActivity extends UnionActivity {

    @BindView(R.id.btn_cancel) Button btnCancel;

    @BindView(R.id.rl_customer_name) RelativeLayout rlCustomerName;
    @BindView(R.id.rl_customer_credit) RelativeLayout rlCustomerCredit;
    @BindView(R.id.rl_customer_manager) RelativeLayout rlCustomerManager;

    @BindView(R.id.rl_attachment) RelativeLayout rlAttachment;

    @BindView(R.id.txt_customer_name) TextView txtCustomerName;
    @BindView(R.id.txt_customer_credit) TextView txtCustomerCredit;
    @BindView(R.id.txt_customer_manager) TextView txtCustomerManager;
    @BindView(R.id.txt_mobile) EditText txtMobile;
    @BindView(R.id.txt_fax) EditText txtFax;
    @BindView(R.id.txt_address) EditText txtAddress;
    @BindView(R.id.txt_postcode) EditText txtPostcode;
    @BindView(R.id.txt_bank_card) EditText txtBankCard;
    @BindView(R.id.txt_email) EditText txtEmail;
    @BindView(R.id.txt_website) EditText txtWebsite;
    @BindView(R.id.txt_counter_guarantee) EditText txtCounterGuarantee;
    @BindView(R.id.txt_borrower) EditText txtBorrower;
    @BindView(R.id.txt_credit_record) EditText txtCreditRecord;
    @BindView(R.id.txt_remark) EditText txtRemark;
    @BindView(R.id.txt_attachment) TextView txtAttachment;

    @BindView(R.id.ll_customer_open) LinearLayout llCustomerOpen;
    @BindView(R.id.ll_customer_content) LinearLayout llCustomerContent;
    @BindView(R.id.ll_customer_close) LinearLayout llCustomerClose;

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


        rlCustomerCredit.setOnClickListener(v->{
           if(ViewUtil.isFastDoubleClick()) return;
           Intent intent = new Intent(this,SimpleSelectActivity.class);
           intent.putExtra("intentSelect",new IntentSelect(
                    "客户信用等级",txtCustomerCredit.getText().toString(),Constant.SELECT_CUSTOMER_CREDIT_LEVEL));
           startActivityForResult(intent, Constant.REQUEST_CUSTOMER_CREDIT_LEVEL);
       });


        llCustomerOpen.setOnClickListener(v->{
            llCustomerContent.setVisibility(View.VISIBLE);
            llCustomerClose.setVisibility(View.VISIBLE);
            llCustomerOpen.setVisibility(View.GONE);
        });

        llCustomerClose.setOnClickListener(v->{
            llCustomerContent.setVisibility(View.GONE);
            llCustomerClose.setVisibility(View.GONE);
            llCustomerOpen.setVisibility(View.VISIBLE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            String result = data.getStringExtra("result");
            switch (requestCode){
                case Constant.REQUEST_CUSTOMER_CREDIT_LEVEL:
                    txtCustomerCredit.setText(result);
                    break;

            }
        }
    }

}
