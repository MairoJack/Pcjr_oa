package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.BusinessApproval;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.bean.IntentSelect;
import com.pcjr.pcjr_oa.bean.SelectItem;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.constant.Event;
import com.pcjr.pcjr_oa.core.BaseAppCompatActivity;
import com.pcjr.pcjr_oa.ui.presenter.ApprovalBusinessPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.ApprovalBusinessView;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 *  完善业务审批
 *  Created by Mario on 2017/12/8上午10:00
 */
public class ApprovalBusinessFullActivity extends UnionActivity implements ApprovalBusinessView{


    @BindView(R.id.txt_title) TextView txtTitle;

    @BindView(R.id.rl_actual_borrower) RelativeLayout rlActualBorrower;
    @BindView(R.id.rl_show_borrower) RelativeLayout rlShowBorrower;
    @BindView(R.id.rl_project_type) RelativeLayout rlProjectType;

    @BindView(R.id.txt_approval_name) TextView txtApprovalName;
    @BindView(R.id.txt_actual_borrower) TextView txtActualBorrower;
    @BindView(R.id.txt_show_borrower) TextView txtShowBorrower;
    @BindView(R.id.txt_project_source) EditText txtProjectSource;
    @BindView(R.id.txt_project_type) TextView txtProjectType;
    @BindView(R.id.txt_expect_amount) EditText txtExpectAmount;
    @BindView(R.id.txt_approval_amount) EditText txtApprovalAmount;
    @BindView(R.id.txt_guarantee_method) EditText txtGuaranteeMethod;
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

    private ApprovalBusinessPresenter presenter;
    private BusinessApproval businessApproval;

    @Override
    protected int getLayoutId() {
        return R.layout.approval_business_full;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        presenter = new ApprovalBusinessPresenter();
        presenter.attachView(this);
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
            addData();
        });

        rlActualBorrower.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,CustomerSingleSelectionActivity.class),Constant.REQUEST_ACTUAL_CUSTOMER);
        });

        rlShowBorrower.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,CustomerSingleSelectionActivity.class),Constant.REQUEST_SHOW_CUSTOMER);
        });

        rlProjectType.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            intent.putExtra("intentSelect",new IntentSelect(
                    "项目类型",txtProjectType.getText().toString(),Constant.SELECT_PROJECT_TYPE));
            startActivityForResult(intent, Constant.REQUEST_PROJECT_TYPE);
        });

    }

    @Override
    protected void initData() {
        businessApproval = (BusinessApproval) getIntent().getSerializableExtra("businessApproval");
        if(businessApproval.isUpdate()){
            txtTitle.setText("修改业务审批");
        }
        txtApprovalName.setText(businessApproval.getTitle());
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
    }

    private void addData(){
        String approvalName = txtApprovalName.getText().toString();
        String projectSource = txtProjectSource.getText().toString();
        String expectAmount = txtExpectAmount.getText().toString();
        String approvalAmount = txtApprovalAmount.getText().toString();
        String guaranteeMethod = txtGuaranteeMethod.getText().toString();
        String loanUsage = txtLoanUsage.getText().toString();
        String mainRisk = txtMainRisk.getText().toString();
        String prevention = txtPrevention.getText().toString();

        businessApproval.setTitle(approvalName);
        String guaranteeAmount = txtGuaranteeAmount.getText().toString();
        String guaranteePaymentMethod = txtGuaranteePaymentMethod.getText().toString();
        String serviceAmount = txtServiceAmount.getText().toString();
        String servicePaymentMethod = txtServicePaymentMethod.getText().toString();
        String depositAmount = txtDepositAmount.getText().toString();
        String depositPaymentMethod = txtDepositPaymentMethod.getText().toString();

        businessApproval.setProjectSource(projectSource);
        businessApproval.setWantAmount(expectAmount);
        businessApproval.setApproveAmount(approvalAmount);
        businessApproval.setGuaranteeType(guaranteeMethod);
        businessApproval.setIntro(loanUsage);
        businessApproval.setMainRisk(mainRisk);
        businessApproval.setPrecautions(prevention);

        businessApproval.setGuaranteeFeeAmount(guaranteeAmount);
        businessApproval.setGuaranteeFeePayType(guaranteePaymentMethod);
        businessApproval.setServiceFeeAmount(serviceAmount);
        businessApproval.setServiceFeePayType(servicePaymentMethod);
        businessApproval.setSecurityDepositAmount(depositAmount);
        businessApproval.setSecurityDepositPayType(depositPaymentMethod);

        presenter.modifyBusinessApprove(businessApproval);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case Constant.REQUEST_PROJECT_TYPE:
                    SelectItem result = (SelectItem) data.getSerializableExtra("result");
                    txtProjectType.setText(result.getName());
                    businessApproval.setProjectType(result.getType());
                    break;
                case Constant.REQUEST_ACTUAL_CUSTOMER:
                    Customer actual = (Customer) data.getSerializableExtra("result");
                    txtActualBorrower.setText(actual.getName());
                    businessApproval.setActualBorrowerId(actual.getId());
                    businessApproval.setActualBorrowerName(actual.getName());
                    break;
                case Constant.REQUEST_SHOW_CUSTOMER:
                    Customer show = (Customer) data.getSerializableExtra("result");
                    txtShowBorrower.setText(show.getName());
                    businessApproval.setFormBorrowerId(show.getId());
                    businessApproval.setFormBorrowerName(show.getName());
                    break;
            }
        }
    }

    @Override
    public void onModifyBusinessApprovalSuccess(BaseBean data) {
        if(data.isSuccess()){
            showToast(data.getMsg());
            EventBus.getDefault().postSticky(new Event.MessageEvent());
            finish();
        } else {
            Dialog.show(data.getMsg(),this);
        }
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
    public void onDeleteBusinessApprovalSuccess(BaseBean data) {}
    @Override
    public void onGetBusinessApprovalDetailSuccess(BusinessApproval data) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
