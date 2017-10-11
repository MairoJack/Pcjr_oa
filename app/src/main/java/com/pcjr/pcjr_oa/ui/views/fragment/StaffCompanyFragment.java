package com.pcjr.pcjr_oa.ui.views.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.StaffCompany;
import com.pcjr.pcjr_oa.core.BaseFragment;

import butterknife.BindView;

/**
 *  员工公司
 *  Created by Mario on 2017/7/20上午11:14
 */
public class StaffCompanyFragment extends BaseFragment {

    public static final String TAG = StaffCompanyFragment.class.getSimpleName();

    @BindView(R.id.txt_department) TextView txtDepartment;
    @BindView(R.id.txt_job_name) TextView txtJobName;
    @BindView(R.id.txt_status) TextView txtStatus;
    @BindView(R.id.txt_telephone) TextView txtTelephone;
    @BindView(R.id.txt_email) TextView txtEmail;

    @Override
    protected int getLayoutId() {
        return R.layout.staff_company;
    }

    @Override
    protected void initViews(LayoutInflater inflater,View self, Bundle savedInstanceState){
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        StaffCompany object = (StaffCompany) bundle.getSerializable("StaffCompany");
//        txtDepartment.setText(object.getDepartment());
//        txtJobName.setText(object.getJob());
//        txtStatus.setText(object.getStatus());
//        txtTelephone.setText(object.getTelephone());
//        txtEmail.setText(object.getEmail());
    }

    @Override
    protected void lazyLoad() {

    }

    public static Fragment newInstance(StaffCompany staffCompany) {
        StaffCompanyFragment fragment = new StaffCompanyFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("StaffCompany",staffCompany);
        fragment.setArguments(bundle);
        return fragment;
    }
}
