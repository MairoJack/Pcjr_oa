package com.pcjr.pcjr_oa.ui.views.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;


/**
 *  完善客户 - 企业
 *  Created by Mario on 2017/9/21上午8:50
 */
public class CustomerFullCompanyActivity extends CustomerActivity {

    @BindView(R.id.btn_confirm) Button btnConfirm;

    @BindView(R.id.txt_credit_code) TextView txtCreditCode;
    @BindView(R.id.txt_institution_code) TextView txtInstitutionCode;
    @BindView(R.id.txt_business_licence) TextView txtBusinessLicence;
    @BindView(R.id.txt_company_nature) TextView txtCompanyNature;
    @BindView(R.id.txt_main_business) TextView txtMainBusiness;
    @BindView(R.id.txt_founding_time) TextView txtFoundingTime;
    @BindView(R.id.txt_corporate_representative) TextView txtCorporateRepresentative;
    @BindView(R.id.txt_actual_operator) TextView txtActualOperator;

    @Override
    protected int getLayoutId() {
        return R.layout.customer_full_company;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }


    @Override
    protected void initListeners() {
        super.initListeners();

        btnConfirm.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });

    }

    @Override
    protected void initData() {


    }

}
