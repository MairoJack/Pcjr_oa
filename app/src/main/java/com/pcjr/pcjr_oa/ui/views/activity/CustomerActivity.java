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
    @BindView(R.id.rl_customer_status) RelativeLayout rlCustomerStatus;
    @BindView(R.id.rl_customer_manager) RelativeLayout rlCustomerManager;
    @BindView(R.id.rl_customer_type) RelativeLayout rlCustomerType;
    @BindView(R.id.rl_participants) RelativeLayout rlParticipants;
    @BindView(R.id.rl_sharer) RelativeLayout rlSharer;
    @BindView(R.id.rl_tag) RelativeLayout rlTag;
    @BindView(R.id.rl_attachment) RelativeLayout rlAttachment;

    @BindView(R.id.txt_customer_name) TextView txtCustomerName;
    @BindView(R.id.txt_customer_status) TextView txtCustomerStatus;
    @BindView(R.id.txt_customer_manager) TextView txtCustomerManager;
    @BindView(R.id.txt_mobile) EditText txtMobile;
    @BindView(R.id.txt_telephone) EditText txtTelephone;
    @BindView(R.id.txt_customer_type) TextView txtCustomerType;
    @BindView(R.id.txt_id_card) EditText txtIdCard;
    @BindView(R.id.txt_enterprise_code) EditText txtEnterpriseCode;
    @BindView(R.id.txt_bank_card) EditText txtBankCard;

    @BindView(R.id.txt_company) EditText txtCompany;
    @BindView(R.id.txt_job_name) EditText txtJobName;
    @BindView(R.id.txt_email) EditText txtEmail;
    @BindView(R.id.txt_sex) EditText txtSex;
    @BindView(R.id.txt_website) EditText txtWebsite;
    @BindView(R.id.txt_contact_way) EditText txtContactWay;
    @BindView(R.id.txt_contact_address) EditText txtContactAddress;
    @BindView(R.id.txt_remark) EditText txtRemark;

    @BindView(R.id.txt_things_invest) TextView txtThingsInvest;
    @BindView(R.id.txt_manage_stock) TextView txtManageStock;
    @BindView(R.id.txt_enterprise_stock) TextView txtEnterpriseStock;
    @BindView(R.id.txt_finance_guarantee) TextView txtFinanceGuarantee;
    @BindView(R.id.txt_management_status) TextView txtManagementStatus;

    @BindView(R.id.txt_participants) TextView txtParticipants;
    @BindView(R.id.txt_sharer) TextView txtSharer;
    @BindView(R.id.txt_tag) TextView txtTag;
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

        rlTag.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,TagListActivity.class), Constant.REQUEST_TAG);
        });

       rlCustomerStatus.setOnClickListener(v->{
           if(ViewUtil.isFastDoubleClick()) return;
           Intent intent = new Intent(this,SimpleSelectActivity.class);
           intent.putExtra("title","客户状态");
           intent.putExtra("select",txtCustomerStatus.getText());
           intent.putExtra("data",Constant.SELECT_CUSTOMER_STATUS);
           startActivityForResult(intent, Constant.REQUEST_CUSTOMER_STATUS);
       });

        rlCustomerType.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            intent.putExtra("title","客户类型");
            intent.putExtra("select",txtCustomerType.getText());
            intent.putExtra("data",Constant.SELECT_CUSTOMER_TYPE);
            startActivityForResult(intent, Constant.REQUEST_CUSTOMER_TYPE);
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
                case Constant.REQUEST_CUSTOMER_STATUS:
                    txtCustomerStatus.setText(result);
                    break;
                case Constant.REQUEST_CUSTOMER_TYPE:
                    txtCustomerType.setText(result);
                    break;
            }
        }
    }

}
