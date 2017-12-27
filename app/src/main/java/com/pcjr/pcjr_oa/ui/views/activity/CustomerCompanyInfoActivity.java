package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.CustomerCompany;
import com.pcjr.pcjr_oa.bean.CustomerContact;
import com.pcjr.pcjr_oa.bean.CustomerContactRelation;
import com.pcjr.pcjr_oa.constant.Event;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.presenter.CustomerCompanyPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.CustomerCompanylView;
import com.pcjr.pcjr_oa.utils.DateUtils;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * 客户信息 - 企业
 * Created by Mario on 2017/12/5下午4:03
 */
public class CustomerCompanyInfoActivity extends BaseToolbarActivity implements CustomerCompanylView {

    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_customer_manager)
    TextView txtCustomerManager;
    @BindView(R.id.txt_address)
    TextView txtAddress;
    @BindView(R.id.txt_founder)
    TextView txtFounder;
    @BindView(R.id.txt_credit)
    TextView txtCredit;
    @BindView(R.id.txt_contact_num)
    TextView txtContactNum;

    @BindView(R.id.ll_customer_linkman)
    LinearLayout llCustomerLinkman;
    @BindView(R.id.ll_union_agreement)
    LinearLayout llUnionAgreement;
    @BindView(R.id.ll_union_task)
    LinearLayout llUnionTask;
    @BindView(R.id.ll_union_project)
    LinearLayout llUnionProject;
    @BindView(R.id.ll_contact_customer)
    LinearLayout llContactCustomer;


    @BindView(R.id.ll_feedback)
    LinearLayout llFeedback;
    @BindView(R.id.ll_query_records)
    LinearLayout llQueryRecords;
    @BindView(R.id.ll_log)
    LinearLayout llLog;
    @BindView(R.id.ll_delete)
    LinearLayout llDelete;

    private CustomerCompanyPresenter presenter;
    private MaterialDialog dialog;
    private CustomerCompany customer;

    @Override
    protected int getLayoutId() {
        return R.layout.customer_company_info;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("企业客户");
        showBack();

        presenter = new CustomerCompanyPresenter();
        presenter.attachView(this);

        dialog = Dialog.progress(this);
    }


    @Override
    protected void initListeners() {
        llCustomerLinkman.setOnClickListener(v -> {
            if (ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this, RelationshipListActivity.class);
            CustomerContact data = new CustomerContact();
            data.setId(customer.getId());
            data.setType(1);
            intent.putExtra("customerContact", data);
            startActivity(intent);

        });
    }

    @Override
    protected void initData() {
        String id = getIntent().getStringExtra("id");
        presenter.getCompanyDetail(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btn_detail) {
            Intent intent = new Intent(this, CustomerCompanyDetailActivity.class);
            intent.putExtra("customer", customer);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetCompanyDetailSuccess(CustomerCompany data) {
        dialog.dismiss();
        customer = data;
        txtName.setText(customer.getName());
        txtCredit.setText("客户信用:" + customer.getCreditRating());
        txtCustomerManager.setText("客户经理：" + customer.getManagerName());
        txtAddress.setText(customer.getAddress());
        txtFounder.setText(DateUtils.longTimeToStr(customer.getJoinDate(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
        List<CustomerContactRelation> relations = customer.getRelationship();
        if (relations.size() > 0) {
            txtContactNum.setText(String.valueOf(relations.size()));
            txtContactNum.setVisibility(View.VISIBLE);
        } else {
            txtContactNum.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailure(Throwable e) {
        dialog.dismiss();
        error(e);
    }

    @Override
    public void onSuccess(Object data) {
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(Event.MessageEvent event) {
        dialog.show();
        EventBus.getDefault().removeStickyEvent(event);
        presenter.getCompanyDetail(customer.getId());
    }

    @Override
    public void onAddCompanySuccess(BaseBean<CustomerCompany> data) {
    }

    @Override
    public void onModifyCompanySuccess(BaseBean data) {
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


}
