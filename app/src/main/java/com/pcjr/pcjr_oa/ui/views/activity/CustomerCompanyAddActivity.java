package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.bean.CustomerCompany;
import com.pcjr.pcjr_oa.bean.Person;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.core.BaseAppCompatActivity;
import com.pcjr.pcjr_oa.ui.presenter.CustomerCompanyPresenter;
import com.pcjr.pcjr_oa.ui.presenter.CustomerPersonalPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.CustomerCompanylView;
import com.pcjr.pcjr_oa.ui.presenter.ivview.CustomerPersonalView;
import com.pcjr.pcjr_oa.utils.StringUtils;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;

import butterknife.BindView;

/**
 *  新建客户 - 企业
 *  Created by Mario on 2017/9/20下午2:32
 */
public class CustomerCompanyAddActivity extends BaseAppCompatActivity implements CustomerCompanylView {

    @BindView(R.id.rl_manager) RelativeLayout rlManager;

    @BindView(R.id.txt_customer_name) EditText txtCustomerName;
    @BindView(R.id.txt_mobile) EditText txtMobile;
    @BindView(R.id.txt_actual_operator) EditText txtActualOperator;

    @BindView(R.id.txt_avatar) TextView txtAvatar;
    @BindView(R.id.txt_manager) TextView txtManager;

    @BindView(R.id.btn_cancel) Button btnCancel;
    @BindView(R.id.btn_finish) Button btnFinish;
    @BindView(R.id.btn_save) Button btnSave;

    private CustomerCompanyPresenter presenter;
    private boolean onlySave = true;
    private CustomerCompany customer;
    @Override
    protected int getLayoutId() {
        return R.layout.customer_company_add;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        presenter = new CustomerCompanyPresenter();
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

        btnSave.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            onlySave = false;
            addData();
        });

        rlManager.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,PersonSingleSelectionActivity.class), Constant.REQUEST_MANAGER);
        });

    }

    @Override
    protected void initData() {
        customer = new CustomerCompany();
    }


    private void addData(){
        String name = txtCustomerName.getText().toString();
        String mobile = txtMobile.getText().toString();
        String actualLeader = txtActualOperator.getText().toString();

        customer.setName(name);
        customer.setMobile(mobile);
        customer.setActualLeader(actualLeader);
        presenter.addCompany(customer);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case Constant.REQUEST_MANAGER:
                    Person person = (Person) data.getSerializableExtra("result");
                    txtManager.setText(person.getRealname());
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
    public void onSuccess(Object data) {}

    @Override
    public void onAddCompanySuccess(BaseBean<CustomerCompany> data) {
        if(data.isSuccess()){
            customer.setId(data.getData().getId());
            showToast(data.getMsg());
            finish();
            if (!onlySave) {
                Intent intent = new Intent(this, CustomerCompanyFullActivity.class);
                intent.putExtra("customer",customer);
                startActivity(intent);
            }
        } else {
            Dialog.show(data.getMsg(),this);
        }
    }

    @Override
    public void onModifyCompanySuccess(BaseBean data) {

    }

    @Override
    public void onGetCompanyDetailSuccess(CustomerCompany data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
