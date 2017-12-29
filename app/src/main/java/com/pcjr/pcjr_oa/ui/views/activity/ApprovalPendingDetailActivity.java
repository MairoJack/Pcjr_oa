package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.BusinessApproval;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.presenter.ApprovalBusinessPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.ApprovalBusinessView;
import com.pcjr.pcjr_oa.utils.DateUtils;
import com.pcjr.pcjr_oa.utils.StringUtils;
import com.pcjr.pcjr_oa.widget.Dialog;

import butterknife.BindView;

/**
 *  待办审批-详情
 *  Created by Mario on 2017/12/7下午2:43
 */
public class ApprovalPendingDetailActivity extends BaseToolbarActivity implements ApprovalBusinessView{

    @BindView(R.id.txt_name) TextView txtName;
    @BindView(R.id.txt_avatar) TextView txtAvatar;
    @BindView(R.id.txt_status) TextView txtStatus;
    @BindView(R.id.txt_approval_code) TextView txtApprovalCode;
    @BindView(R.id.txt_time) TextView txtTime;

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
    private ApprovalBusinessPresenter presenter;
    private BusinessApproval businessApproval;
    private MaterialDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.approval_pending_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("业务审批");
        showBack();

        presenter = new ApprovalBusinessPresenter();
        presenter.attachView(this);

        initMoreBottomSheetDialog();
        dialog = Dialog.progress(this);
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
        dialog.show();
        String id = getIntent().getStringExtra("id");
        presenter.getBusinessApproveDetail(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modify,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_modify){
            Intent intent = new Intent(this,ApprovalBusinessFullActivity.class);
            businessApproval.setUpdate(true);
            intent.putExtra("businessApproval",businessApproval);
            finish();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onGetBusinessApprovalDetailSuccess(BusinessApproval data) {
        dialog.dismiss();
        businessApproval = data;
        txtAvatar.setText(StringUtils.getLast2(businessApproval.getApplyPersonName()));
        txtName.setText("申请人：" + businessApproval.getApplyPersonName());
        txtStatus.setText(Constant.SELECT_APPROVAL_STATUS[businessApproval.getStatus()]);
        txtApprovalCode.setText("审批编号：" + businessApproval.getApproveNo());
        txtTime.setText(DateUtils.longTimeToStr(businessApproval.getJoinDate(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
        txtActualBorrower.setText(businessApproval.getActualBorrowerName());
        txtShowBorrower.setText(businessApproval.getFormBorrowerName());
        txtProjectSource.setText(businessApproval.getProjectSource());
        txtProjectType.setText(Constant.SELECT_PROJECT_TYPE[businessApproval.getProjectType()]);
        txtExpectAmount.setText(businessApproval.getWantAmount());
        txtApprovalAmount.setText(businessApproval.getApproveAmount());
        txtGuaranteeMethod.setText(businessApproval.getGuaranteeType());
        txtLoanUsage.setText(businessApproval.getIntro());
        txtMainRisk.setText(businessApproval.getMainRisk());
        txtPrevention.setText(businessApproval.getPrecautions());

        txtGuaranteeAmount.setText(businessApproval.getGuaranteeFeeAmount());
        txtGuaranteePaymentMethod.setText(businessApproval.getGuaranteeFeePayType());
        txtServiceAmount.setText(businessApproval.getServiceFeeAmount());
        txtServicePaymentMethod.setText(businessApproval.getServiceFeePayType());
        txtDepositAmount.setText(businessApproval.getSecurityDepositAmount());
        txtDepositPaymentMethod.setText(businessApproval.getSecurityDepositPayType());

    }

    @Override
    public void onFailure(Throwable e) {
        error(e);
    }

    @Override
    public void onSuccess(Object data) {}
    @Override
    public void onAddBusinessApprovalSuccess(BaseBean<BusinessApproval> data) {}
    @Override
    public void onModifyBusinessApprovalSuccess(BaseBean data) {}
    @Override
    public void onDeleteBusinessApprovalSuccess(BaseBean data) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
