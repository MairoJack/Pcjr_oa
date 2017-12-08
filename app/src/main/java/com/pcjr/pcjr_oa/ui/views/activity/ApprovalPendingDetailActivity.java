package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;

import butterknife.BindView;

/**
 *  待办审批-详情
 *  Created by Mario on 2017/12/7下午2:43
 */
public class ApprovalPendingDetailActivity extends BaseToolbarActivity {

    @BindView(R.id.txt_name) TextView txtName;
    @BindView(R.id.txt_department) TextView txt_department;
    @BindView(R.id.txt_status) TextView txt_status;
    @BindView(R.id.txt_approval_code) TextView txt_approval_code;
    @BindView(R.id.txt_time) TextView txt_time;

    @BindView(R.id.txt_actual_borrower) TextView txtActualBorrower;
    @BindView(R.id.txt_show_borrower) TextView txtShowBorrower;
    @BindView(R.id.txt_project_source) TextView txtProjectSource;
    @BindView(R.id.txt_project_type) TextView txtProjectType;
    @BindView(R.id.txt_expect_amount) TextView txtExpectAmount;
    @BindView(R.id.txt_approval_amount) TextView txtApprovalAmount;
    @BindView(R.id.txt_guarantee_method) TextView txtGuaranteeMethod;
    @BindView(R.id.txt_loan_usage) TextView txtLoanUsage;
    @BindView(R.id.txt_main_risk) TextView txtMainRisk;
    @BindView(R.id.txt_prevention) TextView txtPrevention;

    @BindView(R.id.txt_guarantee_amount) TextView txtGuaranteeAmount;
    @BindView(R.id.txt_guarantee_payment_method) TextView txtGuaranteePaymentMethod;
    @BindView(R.id.txt_service_amount) TextView txtServiceAmount;
    @BindView(R.id.txt_service_payment_method) TextView txtServicePaymentMethod;
    @BindView(R.id.txt_deposit_amount) TextView txtDepositAmount;
    @BindView(R.id.txt_deposit_payment_method) TextView txtDepositPaymentMethod;


    @BindView(R.id.ll_agree) LinearLayout llAgree;
    @BindView(R.id.ll_return) LinearLayout llReturn;
    @BindView(R.id.ll_reject) LinearLayout llReject;
    @BindView(R.id.ll_feedback) LinearLayout llFeedback;
    @BindView(R.id.ll_more) LinearLayout llMore;

    private BottomSheetDialog moreBottomSheetDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.approval_pending_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("业务审批");
        showBack();

        initMoreBottomSheetDialog();
    }


    @Override
    protected void initListeners() {

        llAgree.setOnClickListener(v->{
            Intent intent = new Intent(this,AgreeOrRejectActivity.class);
            intent.putExtra("title","同意");
            startActivity(intent);
        });

        llReject.setOnClickListener(v->{
            Intent intent = new Intent(this,AgreeOrRejectActivity.class);
            intent.putExtra("title","驳回");
            startActivity(intent);
        });

        llReturn.setOnClickListener(v->{
            startActivity(new Intent(this,FreeReturnActivity.class));
        });

        llMore.setOnClickListener(v->{
            moreBottomSheetDialog.show();
        });
    }

    @Override
    protected void initData() {


    }

    private void initMoreBottomSheetDialog(){
        moreBottomSheetDialog = new BottomSheetDialog(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_bottom_menu_approval, findViewById(R.id.dialog));
        ImageView btnFlow = view.findViewById(R.id.btn_flow);
        ImageView btLog = view.findViewById(R.id.btn_log);
        ImageView btnQueryRecord = view.findViewById(R.id.btn_query_record);
        TextView btnCancel = view.findViewById(R.id.btn_cancel);
        moreBottomSheetDialog.setContentView(view);
        moreBottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet)
                .setBackgroundResource(android.R.color.transparent);


        btnFlow.setOnClickListener(v->{
            moreBottomSheetDialog.dismiss();
            startActivity(new Intent(this,FlowActivity.class));
        });

        btLog.setOnClickListener(v->{
            moreBottomSheetDialog.dismiss();
            startActivity(new Intent(this,LogListActivity.class));
        });

        btnQueryRecord.setOnClickListener(v->{
            moreBottomSheetDialog.dismiss();
            startActivity(new Intent(this,QueryRecordListActivity.class));
        });

        btnCancel.setOnClickListener(v->{
            moreBottomSheetDialog.dismiss();
        });
    }
}
