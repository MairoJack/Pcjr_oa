package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Company;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;

import butterknife.BindView;

/**
 *  公司详情
 *  Created by Mario on 2017/7/26下午3:59
 */
public class CompanyDetailActivity extends BaseToolbarActivity {
    @BindView(R.id.txt_company_name) TextView txtCompanyName;
    @BindView(R.id.txt_department) TextView txtDepartment;
    @BindView(R.id.txt_job_name) TextView txtJobName;
    @BindView(R.id.txt_status) TextView txtStatus;

    @Override
    protected int getLayoutId() {
        return R.layout.company_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("公司信息");
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Company object = (Company) intent.getSerializableExtra("data");
        txtCompanyName.setText(object.getName());
        txtDepartment.setText(object.getDepartment());
        txtJobName.setText(object.getJob());
        txtStatus.setText(object.getStatus());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;

    }
}
