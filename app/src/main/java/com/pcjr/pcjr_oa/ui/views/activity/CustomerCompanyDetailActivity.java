package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.CustomerCompany;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.utils.DateUtils;
import com.pcjr.pcjr_oa.utils.StringUtils;

import butterknife.BindView;

/**
 *  客户信息 - 企业
 *  Created by Mario on 2017/12/5下午4:03
 */
public class CustomerCompanyDetailActivity extends BaseToolbarActivity{

    @BindView(R.id.txt_avatar) TextView txtAvatar;
    @BindView(R.id.txt_name) TextView txtName;
    @BindView(R.id.txt_credit) TextView txtCredit;
    @BindView(R.id.txt_customer_manager) TextView txtCustomerManager;
    @BindView(R.id.txt_founder) TextView txtFounder;

    @BindView(R.id.txt_mobile) TextView txtMobile;
    @BindView(R.id.txt_fax) TextView txtFax;
    @BindView(R.id.txt_address) TextView txtAddress;
    @BindView(R.id.txt_postcode) TextView txtPostcode;
    @BindView(R.id.txt_bank_card) TextView txtBankCard;
    @BindView(R.id.txt_website) TextView txtWebsite;
    @BindView(R.id.txt_email) TextView txtEmail;
    @BindView(R.id.txt_counter_guarantee) TextView txtCounterGuarantee;
    @BindView(R.id.txt_borrower) TextView txtBorrower;
    @BindView(R.id.txt_credit_record) TextView txtCreditRecord;
    @BindView(R.id.txt_remark) TextView txtRemark;

    @BindView(R.id.txt_actual_operator) TextView txtActualOperator;
    @BindView(R.id.txt_credit_code) TextView txtCreditCode;
    @BindView(R.id.txt_institution_code) TextView txtInstitutionCode;
    @BindView(R.id.txt_business_licence) TextView txtBusinessLicence;
    @BindView(R.id.txt_company_nature) TextView txtCompanyNature;
    @BindView(R.id.txt_main_business) TextView txtMainBusiness;
    @BindView(R.id.txt_founding_time) TextView txtFoundingTime;
    @BindView(R.id.txt_corporate_representative) TextView txtCorporateRepresentative;

    @BindView(R.id.ll_feedback) LinearLayout llFeedback;
    @BindView(R.id.ll_query_records) LinearLayout llQueryRecords;
    @BindView(R.id.ll_log) LinearLayout llLog;
    @BindView(R.id.ll_delete) LinearLayout llDelete;

    private CustomerCompany customer;
    @Override
    protected int getLayoutId() {
        return R.layout.customer_company_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("查看企业客户");
        showBack();
    }


    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        customer = (CustomerCompany) getIntent().getSerializableExtra("customer");
        txtName.setText(customer.getName());
        txtCredit.setText("客户信用:"+customer.getCreditRating());
        txtCustomerManager.setText("客户经理：" + customer.getManagerName());

        txtFounder.setText(DateUtils.longTimeToStr(customer.getJoinDate(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
        txtMobile.setText(customer.getMobile());
        txtFax.setText(customer.getFax());
        txtAddress.setText(customer.getAddress());
        txtPostcode.setText(customer.getPostalCode());
        txtBankCard.setText(customer.getCardNo());
        txtWebsite.setText(customer.getWebsite());
        txtEmail.setText(customer.getEmail());
        txtCounterGuarantee.setText(customer.getCounterGuarantee());
        txtBorrower.setText(customer.getBorrowerIntruduction());
        txtCreditRecord.setText(customer.getCreditHistory());
        txtRemark.setText(customer.getRemarks());

        txtActualOperator.setText(customer.getActualLeader());
        txtCreditCode.setText(customer.getSocialCreditCode());
        txtInstitutionCode.setText(customer.getOrganizationCode());
        txtBusinessLicence.setText(customer.getBusinessNo());
        txtCompanyNature.setText(Constant.SELECT_COMPANY_NATURE[customer.getNature()]);
        txtMainBusiness.setText(customer.getBusiness());
        txtFoundingTime.setText(customer.getRegtime());
        txtCorporateRepresentative.setText(customer.getLeader());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modify,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_modify){
            Intent intent = new Intent(this,CustomerCompanyFullActivity.class);
            customer.setUpdate(true);
            intent.putExtra("customer",customer);
            finish();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
