package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.CustomerCompany;
import com.pcjr.pcjr_oa.bean.IntentSelect;
import com.pcjr.pcjr_oa.bean.Person;
import com.pcjr.pcjr_oa.bean.SelectItem;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.constant.Event;
import com.pcjr.pcjr_oa.ui.presenter.CustomerCompanyPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.CustomerCompanylView;
import com.pcjr.pcjr_oa.utils.StringUtils;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;
import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;


/**
 *  完善客户 - 企业
 *  Created by Mario on 2017/9/21上午8:50
 */
public class CustomerCompanyFullActivity extends CustomerActivity implements CustomerCompanylView {

    @BindView(R.id.btn_confirm) Button btnConfirm;
    @BindView(R.id.txt_title) TextView txtTitle;

    @BindView(R.id.rl_customer_manager) RelativeLayout rlCustomerManager;
    @BindView(R.id.rl_customer_credit) RelativeLayout rlCustomerCredit;
    @BindView(R.id.rl_company_nature) RelativeLayout rlCompanyNature;
    @BindView(R.id.rl_founding_time) RelativeLayout rlFoundingTime;

    @BindView(R.id.txt_avatar) TextView txtAvatar;
    @BindView(R.id.txt_customer_credit) TextView txtCustomerCredit;

    @BindView(R.id.txt_actual_operator) EditText txtActualOperator;
    @BindView(R.id.txt_credit_code) EditText txtCreditCode;
    @BindView(R.id.txt_institution_code) EditText txtInstitutionCode;
    @BindView(R.id.txt_business_licence) EditText txtBusinessLicence;
    @BindView(R.id.txt_company_nature) TextView txtCompanyNature;
    @BindView(R.id.txt_main_business) EditText txtMainBusiness;
    @BindView(R.id.txt_founding_time) TextView txtFoundingTime;
    @BindView(R.id.txt_corporate_representative) TextView txtCorporateRepresentative;

    private CustomerCompanyPresenter presenter;
    private CustomerCompany customer;
    @Override
    protected int getLayoutId() {
        return R.layout.customer_company_full;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        presenter = new CustomerCompanyPresenter();
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

        rlCustomerCredit.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            IntentSelect intentSelect = new IntentSelect("信用等级",txtCustomerCredit.getText().toString(),Constant.SELECT_CUSTOMER_CREDIT_LEVEL);
            intent.putExtra("intentSelect",intentSelect);
            startActivityForResult(intent, Constant.REQUEST_CUSTOMER_CREDIT_LEVEL);
        });

        rlCompanyNature.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            IntentSelect intentSelect = new IntentSelect("公司性质",txtCompanyNature.getText().toString(),Constant.SELECT_COMPANY_NATURE);
            intent.putExtra("intentSelect",intentSelect);
            startActivityForResult(intent, Constant.REQUEST_COMPANY_NATURE);
        });

        rlFoundingTime.setOnClickListener(v-> {
            if (ViewUtil.isFastDoubleClick()) return;
            Dialog.dateYMDPicker(this,"成立时间",txtFoundingTime);
        });
    }

    @Override
    protected void initData() {
        customer = (CustomerCompany) getIntent().getSerializableExtra("customer");
        txtCustomerName.setText(customer.getName());
        txtMobile.setText(customer.getMobile());
        txtActualOperator.setText(customer.getActualLeader());
        String managerName = customer.getManagerName();
        if(!StringUtils.validate(managerName)){
            txtAvatar.setText("--");
            txtCustomerManager.setText("请选择");
        } else {
            txtAvatar.setText(StringUtils.getLast2(customer.getManagerName()));
            txtCustomerManager.setText(customer.getManagerName());
        }
        if(customer.isUpdate()){
            txtTitle.setText("编辑企业客户");
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
            txtActualOperator.setText(customer.getActualLeader());
            txtCreditCode.setText(customer.getSocialCreditCode());
            txtInstitutionCode.setText(customer.getOrganizationCode());
            txtBusinessLicence.setText(customer.getBusinessNo());
            txtBankCard.setText(customer.getCardNo());

            txtCompanyNature.setText(Constant.SELECT_COMPANY_NATURE[customer.getNature()]);
            txtMainBusiness.setText(customer.getBusiness());
            if(StringUtils.validate(customer.getCreditRating())){
                txtFoundingTime.setText(customer.getRegtime());
            } else {
                txtFoundingTime.setText("请选择");
            }
            txtCorporateRepresentative.setText(customer.getLeader());
            txtWebsite.setText(customer.getWebsite());
            txtEmail.setText(customer.getEmail());
            txtCounterGuarantee.setText(customer.getCounterGuarantee());
            txtBorrower.setText(customer.getBorrowerIntruduction());
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
        String actualLeader = txtActualOperator.getText().toString();
        String socialCreditCode = txtCreditCode.getText().toString();
        String organizationCode = txtInstitutionCode.getText().toString();
        String businessNo = txtBusinessLicence.getText().toString();
        String cardNo = txtBankCard.getText().toString();

        String business = txtMainBusiness.getText().toString();
        String regtime = txtFoundingTime.getText().toString();
        String leader = txtCorporateRepresentative.getText().toString();
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
        customer.setActualLeader(actualLeader);
        customer.setLeader(leader);
        customer.setBusinessNo(businessNo);
        customer.setBusiness(business);
        customer.setRegtime(regtime);
        customer.setSocialCreditCode(socialCreditCode);
        customer.setOrganizationCode(organizationCode);
        customer.setCardNo(cardNo);
        customer.setWebsite(website);
        customer.setEmail(email);
        customer.setCounterGuarantee(counterGuarantee);
        customer.setBorrowerIntruduction(borrower);
        customer.setCreditHistory(creditRecord);
        customer.setRemarks(remark);

        presenter.modifyCompany(customer);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            SelectItem result;
            switch (requestCode){
                case Constant.REQUEST_CUSTOMER_CREDIT_LEVEL:
                    result = (SelectItem) data.getSerializableExtra("result");
                    txtCustomerCredit.setText(result.getName());
                    break;
                case Constant.REQUEST_COMPANY_NATURE:
                    result = (SelectItem) data.getSerializableExtra("result");
                    txtCompanyNature.setText(result.getName());
                    customer.setNature(result.getType());
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
    public void onModifyCompanySuccess(BaseBean data) {
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
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


    @Override
    public void onAddCompanySuccess(BaseBean<CustomerCompany> data) {

    }



    @Override
    public void onGetCompanyDetailSuccess(CustomerCompany data) {

    }
}
