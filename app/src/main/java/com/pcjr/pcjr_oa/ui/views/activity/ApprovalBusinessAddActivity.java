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
 *  新建业务审批
 *  Created by Mario on 2017/12/8上午10:00
 */
public class ApprovalBusinessAddActivity extends BaseAppCompatActivity implements ApprovalBusinessView{


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

    @BindView(R.id.btn_cancel) Button btnCancel;
    @BindView(R.id.btn_finish) Button btnFinish;
    @BindView(R.id.btn_save) Button btnSave;
    @BindView(R.id.btn_submit) Button btnSubmit;

    private ApprovalBusinessPresenter presenter;
    private BusinessApproval businessApproval;
    private boolean onlySave = true;
    @Override
    protected int getLayoutId() {
        return R.layout.approval_business_add;
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

        btnSave.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivity(new Intent(this,ApprovalDraftActivity.class));
        });

        btnSubmit.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            addData();
            onlySave = false;
        });

    }

    @Override
    protected void initData() {
        businessApproval = new BusinessApproval();
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
        businessApproval.setProjectSource(projectSource);
        businessApproval.setWantAmount(expectAmount);
        businessApproval.setApproveAmount(approvalAmount);
        businessApproval.setGuaranteeType(guaranteeMethod);
        businessApproval.setIntro(loanUsage);
        businessApproval.setMainRisk(mainRisk);
        businessApproval.setPrecautions(prevention);

        presenter.addBusinessApprove(businessApproval);
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
                    businessApproval.setActualBorrowerType(actual.getCustomerType());
                    break;
                case Constant.REQUEST_SHOW_CUSTOMER:
                    Customer show = (Customer) data.getSerializableExtra("result");
                    txtShowBorrower.setText(show.getName());
                    businessApproval.setFormBorrowerId(show.getId());
                    businessApproval.setFormBorrowerName(show.getName());
                    businessApproval.setFormBorrowerType(show.getCustomerType());
                    break;
            }
        }
    }

    @Override
    public void onAddBusinessApprovalSuccess(BaseBean<BusinessApproval> data) {
        if (data.isSuccess()) {
            showToast(data.getMsg());
            EventBus.getDefault().post(new Event.MessageEvent());
            finish();
            if(!onlySave){
                businessApproval.setId(data.getData().getId());
                Intent intent = new Intent(this, ApprovalBusinessFullActivity.class);
                intent.putExtra("businessApproval",businessApproval);
                startActivity(intent);
            }
        } else {
            Dialog.show(data.getMsg(), this);
        }
    }

    @Override
    public void onFailure(Throwable e) {
        error(e);
    }

    @Override
    public void onSuccess(Object data) {}
    @Override
    public void onModifyBusinessApprovalSuccess(BaseBean data) {}
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
