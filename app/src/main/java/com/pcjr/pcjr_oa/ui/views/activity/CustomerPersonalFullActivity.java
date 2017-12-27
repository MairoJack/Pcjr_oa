package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.CustomerPersonal;
import com.pcjr.pcjr_oa.bean.IntentSelect;
import com.pcjr.pcjr_oa.bean.Person;
import com.pcjr.pcjr_oa.bean.SelectItem;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.constant.Event;
import com.pcjr.pcjr_oa.ui.presenter.CustomerPersonalPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.CustomerPersonalView;
import com.pcjr.pcjr_oa.utils.StringUtils;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;


/**
 *  完善客户 - 个人
 *  Created by Mario on 2017/9/21上午8:50
 */
public class CustomerPersonalFullActivity extends CustomerActivity implements CustomerPersonalView {

    @BindView(R.id.btn_confirm) Button btnConfirm;
    @BindView(R.id.txt_title) TextView txtTitle;

    @BindView(R.id.rl_customer_manager) RelativeLayout rlCustomerManager;
    @BindView(R.id.rl_customer_credit) RelativeLayout rlCustomerCredit;
    @BindView(R.id.rl_sex) RelativeLayout rlSex;
    @BindView(R.id.rl_marital_status) RelativeLayout rlMaritalStatus;

    @BindView(R.id.txt_identity) EditText txtIdentity;
    @BindView(R.id.txt_company) EditText txtCompany;
    @BindView(R.id.txt_job_name) EditText txtJobName;
    @BindView(R.id.txt_customer_credit) TextView txtCustomerCredit;
    @BindView(R.id.txt_sex) TextView txtSex;
    @BindView(R.id.txt_marital_status) TextView txtMaritalStatus;
    @BindView(R.id.txt_avatar) TextView txtAvatar;

    private CustomerPersonalPresenter presenter;
    private CustomerPersonal customer;
    @Override
    protected int getLayoutId() {
        return R.layout.customer_personal_full;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        presenter = new CustomerPersonalPresenter();
        presenter.attachView(this);
    }


    @Override
    protected void initListeners() {
        super.initListeners();
        btnConfirm.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            addData();
        });

        rlCustomerManager.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,PersonSingleSelectionActivity.class), Constant.REQUEST_MANAGER);
        });

        rlCustomerCredit.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            IntentSelect intentSelect = new IntentSelect("信用等级",txtCustomerCredit.getText().toString(),Constant.SELECT_CUSTOMER_CREDIT_LEVEL);
            intent.putExtra("intentSelect",intentSelect);
            startActivityForResult(intent, Constant.REQUEST_CUSTOMER_CREDIT_LEVEL);
        });

        rlSex.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            IntentSelect intentSelect = new IntentSelect("性别",txtSex.getText().toString(),Constant.SELECT_SEX);
            intent.putExtra("intentSelect",intentSelect);
            startActivityForResult(intent, Constant.REQUEST_SEX);
        });

        rlMaritalStatus.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            IntentSelect intentSelect = new IntentSelect("婚姻状况",txtMaritalStatus.getText().toString(),Constant.SELECT_MARITAL_STATUS);
            intent.putExtra("intentSelect",intentSelect);
            startActivityForResult(intent, Constant.REQUEST_MARITAL_STATUS);
        });
    }

    @Override
    protected void initData() {
        customer = (CustomerPersonal) getIntent().getSerializableExtra("customer");
        txtCustomerName.setText(customer.getName());
        txtMobile.setText(customer.getMobile());
        txtIdentity.setText(customer.getIdentity());
        String managerName = customer.getManagerName();
        if(!StringUtils.validate(managerName)){
            txtAvatar.setText("--");
            txtCustomerManager.setText("请选择");
        } else {
            txtAvatar.setText(StringUtils.getLast2(customer.getManagerName()));
            txtCustomerManager.setText(customer.getManagerName());
        }
        if(customer.isUpdate()){
            txtTitle.setText("编辑个人客户");
            if(StringUtils.validate(customer.getCreditRating())){
                txtCustomerCredit.setText(customer.getCreditRating());
            } else {
                txtCustomerCredit.setText("请选择");
            }
            txtAvatar.setText(StringUtils.getLast2(customer.getManagerName()));
            txtCustomerManager.setText(customer.getManagerName());
            txtMobile.setText(customer.getMobile());
            txtFax.setText(customer.getFax());
            txtAddress.setText(customer.getAddress());
            txtPostcode.setText(customer.getPostalCode());
            txtIdentity.setText(customer.getIdentity());
            txtBankCard.setText(customer.getCardNo());
            txtCompany.setText(customer.getCompany());
            txtJobName.setText(customer.getPosition());
            if(customer.getSex() == 0) txtSex.setText("男");else txtSex.setText("女");
            if(customer.getMarriage() == 0) txtMaritalStatus.setText("未婚");
            else if(customer.getMarriage() == 1) txtSex.setText("已婚");
            else txtSex.setText("离异");
            txtWebsite.setText(customer.getWebsite());
            txtEmail.setText(customer.getEmail());
            txtCounterGuarantee.setText(customer.getCounterGuarantee());
            txtBorrower.setText(customer.getBorrowerIntroduction());
            txtCreditRecord.setText(customer.getCreditHistory());
            txtRemark.setText(customer.getRemarks());
        }

    }

    private void addData(){
        String name = txtCustomerName.getText().toString();
        String creditRating = txtCustomerCredit.getText().toString();
        String mobile = txtMobile.getText().toString();
        String fax = txtFax.getText().toString();
        String address = txtAddress.getText().toString();
        String postcode = txtPostcode.getText().toString();
        String identity = txtIdentity.getText().toString();
        String bankcard = txtBankCard.getText().toString();

        String company = txtCompany.getText().toString();
        String job = txtJobName.getText().toString();
        String website = txtWebsite.getText().toString();
        String email = txtEmail.getText().toString();
        String counterGuarantee = txtCounterGuarantee.getText().toString();
        String borrower = txtBorrower.getText().toString();
        String creditRecord = txtCreditRecord.getText().toString();
        String remark = txtRemark.getText().toString();

        customer.setName(name);
        customer.setCreditRating(creditRating);
        customer.setMobile(mobile);
        customer.setFax(fax);
        customer.setAddress(address);
        customer.setPostalCode(postcode);
        customer.setIdentity(identity);
        customer.setCardNo(bankcard);
        customer.setCompany(company);
        customer.setPosition(job);
        customer.setWebsite(website);
        customer.setEmail(email);
        customer.setCounterGuarantee(counterGuarantee);
        customer.setBorrowerIntroduction(borrower);
        customer.setCreditHistory(creditRecord);
        customer.setRemarks(remark);

        presenter.modifyPerson(customer);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            SelectItem result;
            switch (requestCode){
                case Constant.REQUEST_SEX:
                    result = (SelectItem) data.getSerializableExtra("result");
                    txtSex.setText(result.getName());
                    customer.setSex(result.getType());
                    break;
                case Constant.REQUEST_MARITAL_STATUS:
                    result = (SelectItem) data.getSerializableExtra("result");
                    txtMaritalStatus.setText(result.getName());
                    customer.setSex(result.getType());
                    break;
                case Constant.REQUEST_CUSTOMER_CREDIT_LEVEL:
                    result = (SelectItem) data.getSerializableExtra("result");
                    txtCustomerCredit.setText(result.getName());
                    break;
                case Constant.REQUEST_MANAGER:
                    Person person = (Person) data.getSerializableExtra("result");
                    txtCustomerManager.setText("客户经理：" + person.getRealname());
                    txtAvatar.setText(StringUtils.getLast2(person.getRealname()));
                    customer.setManager(person.getId());
                    customer.setManagerName(person.getRealname());
                    break;
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {
        error(e);
    }

    @Override
    public void onModifyPersonSuccess(BaseBean data) {
        if(data.isSuccess()){
            showToast(data.getMsg());
            EventBus.getDefault().postSticky(new Event.MessageEvent());
            finish();
        } else {
            Dialog.show(data.getMsg(),this);
        }
    }

    @Override
    public void onSuccess(Object data) {}
    @Override
    public void onAddPersonSuccess(BaseBean data) {}
    @Override
    public void onGetPersonDetailSuccess(CustomerPersonal data) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


}
