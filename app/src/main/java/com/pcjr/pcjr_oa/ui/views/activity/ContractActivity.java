package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.IntentSelect;
import com.pcjr.pcjr_oa.bean.SelectItem;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;

import butterknife.BindView;


/**
 *  合同基类
 *  Created by Mario on 2017/12/25下午4:12
 */
public abstract class ContractActivity extends UnionActivity {

    @BindView(R.id.btn_cancel) Button btnCancel;

    @BindView(R.id.txt_agreement_code) EditText txtAgreementCode;
    @BindView(R.id.txt_borrower) EditText txtBorrower;
    @BindView(R.id.txt_borrowing_amount) EditText txtBorrowingAmount;
    @BindView(R.id.txt_split) EditText txtSplit;
    @BindView(R.id.txt_split_amount) EditText txtSplitAmount;
    @BindView(R.id.txt_expected_rate) EditText txtExpectedRate;
    @BindView(R.id.txt_deadline) EditText txtDeadline;
    @BindView(R.id.txt_remark) EditText txtRemark;

    @BindView(R.id.rl_start_date) RelativeLayout rlStartDate;
    @BindView(R.id.rl_end_date) RelativeLayout rlEndDate;
    @BindView(R.id.rl_repayment_method) RelativeLayout rlRepaymentMethod;

    @BindView(R.id.txt_start_date) TextView txtStartDate;
    @BindView(R.id.txt_end_date) TextView txtEndDate;
    @BindView(R.id.txt_repayment_method) TextView txtRepaymentMethod;


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

        rlRepaymentMethod.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            IntentSelect intentSelect = new IntentSelect("还款方式",txtRepaymentMethod.getText().toString(),Constant.SELECT_REPAYMENT_METHOD);
            intent.putExtra("intentSelect",intentSelect);
            startActivityForResult(intent, Constant.REQUEST_REPAYMENT_METHOD);
        });

        rlStartDate.setOnClickListener(v-> Dialog.dateYMDPicker(this,"开始时间",txtStartDate));

        rlEndDate.setOnClickListener(v->Dialog.dateYMDPicker(this,"结束时间",txtEndDate));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case Constant.REQUEST_REPAYMENT_METHOD:
                    SelectItem result = (SelectItem) data.getSerializableExtra("result");
                    txtRepaymentMethod.setText(result.getName());
            }
        }
    }

}
