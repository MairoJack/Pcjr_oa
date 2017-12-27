package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.bean.CustomerContactRelation;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.constant.Event;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.presenter.ContactPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.ContactView;
import com.pcjr.pcjr_oa.utils.DateUtils;
import com.pcjr.pcjr_oa.utils.StringUtils;
import com.pcjr.pcjr_oa.widget.Dialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 *  联系人信息
 *  Created by Mario on 2017/12/7下午2:43
 */
public class ContactDetailActivity extends BaseToolbarActivity implements ContactView{

    @BindView(R.id.txt_name) TextView txtName;
    @BindView(R.id.txt_belong_customer) TextView txtBelongCustomer;
    @BindView(R.id.txt_time) TextView txtTime;

    @BindView(R.id.txt_mobile) TextView txtMobile;
    @BindView(R.id.txt_address) TextView txtAddress;
    @BindView(R.id.txt_company) TextView txtCompany;
    @BindView(R.id.txt_job) TextView txtJob;
    @BindView(R.id.txt_sex) TextView txtSex;
    @BindView(R.id.txt_email) TextView txtEmail;
    @BindView(R.id.txt_remark) TextView txtRemark;

    @BindView(R.id.ll_feedback) LinearLayout llFeedback;
    @BindView(R.id.ll_query_records) LinearLayout llQueryRecords;
    @BindView(R.id.ll_log) LinearLayout llLog;
    @BindView(R.id.ll_delete) LinearLayout llDelete;

    private ContactPresenter presenter;
    private MaterialDialog dialog;
    private Contact contact;
    @Override
    protected int getLayoutId() {
        return R.layout.contact_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("联系人");
        showBack();

        presenter = new ContactPresenter();
        presenter.attachView(this);

        dialog = Dialog.progress(this);
    }


    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        dialog.show();
        String id = getIntent().getStringExtra("id");
        presenter.getContactDetail(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modify,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_modify){
            Intent intent = new Intent(this,ContactFullActivity.class);
            contact.setUpdate(true);
            intent.putExtra("contact",contact);
            finish();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetContactDetailSuccess(Contact data) {
        dialog.dismiss();
        contact = data;
        txtName.setText("联系人："+contact.getName());
        List<CustomerContactRelation> relations = contact.getRelationship();
        String belongCustom = "";
        for(CustomerContactRelation relation : relations){
            belongCustom += relation.getCustomerName()+"、";
        }
        if(StringUtils.validate(belongCustom)) {
            txtBelongCustomer.setText("所属客户:" + belongCustom.substring(0, belongCustom.length() - 1));
        }
        txtTime.setText(DateUtils.longTimeToStr(contact.getJoinDate(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
        txtMobile.setText(contact.getMobile());
        txtAddress.setText(contact.getAddress());
        txtCompany.setText(contact.getCompany());
        txtJob.setText(contact.getPosition());
        txtSex.setText(Constant.SELECT_SEX[contact.getSex()]);
        txtEmail.setText(contact.getEmail());
        txtRemark.setText(contact.getRemark());
    }

    @Override
    public void onFailure(Throwable e) {
        error(e);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(Event.MessageEvent event) {
        dialog.show();
        EventBus.getDefault().removeStickyEvent(event);
        presenter.getContactDetail(contact.getId());
    }

    @Override
    public void onSuccess(Object data) {}
    @Override
    public void onAddContactSuccess(BaseBean<Contact> data) {}
    @Override
    public void onModifyContactSuccess(BaseBean data) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
