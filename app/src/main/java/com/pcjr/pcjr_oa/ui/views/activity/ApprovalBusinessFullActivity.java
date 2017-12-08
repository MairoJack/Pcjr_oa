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
 *  完善业务审批
 *  Created by Mario on 2017/12/8上午10:00
 */
public class ApprovalBusinessFullActivity extends UnionActivity {


    @BindView(R.id.rl_guarantee_method) RelativeLayout rlGuaranteeMethod;

    @BindView(R.id.txt_actual_borrower) EditText txtActualBorrower;
    @BindView(R.id.txt_show_borrower) EditText txtShowBorrower;
    @BindView(R.id.txt_project_source) EditText txtProjectSource;
    @BindView(R.id.txt_project_type) EditText txtProjectType;
    @BindView(R.id.txt_expect_amount) EditText txtExpectAmount;
    @BindView(R.id.txt_approval_amount) EditText txtApprovalAmount;
    @BindView(R.id.txt_guarantee_method) TextView txtGuaranteeMethod;
    @BindView(R.id.txt_loan_usage) EditText txtLoanUsage;
    @BindView(R.id.txt_main_risk) EditText txtMainRisk;
    @BindView(R.id.txt_prevention) EditText txtPrevention;

    @BindView(R.id.txt_guarantee_amount) EditText txtGuaranteeAmount;
    @BindView(R.id.txt_guarantee_payment_method) EditText txtGuaranteePaymentMethod;
    @BindView(R.id.txt_service_amount) EditText txtServiceAmount;
    @BindView(R.id.txt_service_payment_method) EditText txtServicePaymentMethod;
    @BindView(R.id.txt_deposit_amount) EditText txtDepositAmount;
    @BindView(R.id.txt_deposit_payment_method) EditText txtDepositPaymentMethod;

    @BindView(R.id.btn_cancel) Button btnCancel;
    @BindView(R.id.btn_finish) Button btnFinish;

    @Override
    protected int getLayoutId() {
        return R.layout.approval_business_full;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
    }

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

        btnFinish.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });

        rlGuaranteeMethod.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            intent.putExtra("intentSelect",new IntentSelect(
                    "还款方式",txtGuaranteeMethod.getText().toString(),Constant.SELECT_SEX));
            startActivityForResult(intent, Constant.REQUEST_SEX);
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
                    txtGuaranteeMethod.setText(result);
                    break;
            }
        }
    }

}
