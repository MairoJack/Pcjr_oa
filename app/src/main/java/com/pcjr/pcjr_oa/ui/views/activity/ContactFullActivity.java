package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.bean.CustomerContact;
import com.pcjr.pcjr_oa.bean.CustomerContactRelation;
import com.pcjr.pcjr_oa.bean.IntentSelect;
import com.pcjr.pcjr_oa.bean.SelectItem;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.constant.Event;
import com.pcjr.pcjr_oa.ui.presenter.ContactPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.ContactView;
import com.pcjr.pcjr_oa.utils.StringUtils;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

/**
 *  完善联系人
 *  Created by Mario on 2017/12/7下午2:33
 */
public class ContactFullActivity extends UnionActivity implements ContactView{

    @BindView(R.id.btn_finish) Button btnFinish;
    @BindView(R.id.txt_title) TextView txtTitle;
    @BindView(R.id.btn_cancel) Button btnCancel;

    @BindView(R.id.rl_belong_customer) RelativeLayout rlBelongCustomer;
    @BindView(R.id.rl_sex) RelativeLayout rlSex;

    @BindView(R.id.txt_name) EditText txtName;
    @BindView(R.id.txt_belong_customer) TextView txtBelongCustomer;
    @BindView(R.id.txt_mobile) EditText txtMobile;
    @BindView(R.id.txt_address) EditText txtAddress;
    @BindView(R.id.txt_company) EditText txtCompany;
    @BindView(R.id.txt_job) EditText txtJob;
    @BindView(R.id.txt_sex) TextView txtSex;
    @BindView(R.id.txt_email) EditText txtEmail;
    @BindView(R.id.txt_remark) EditText txtRemark;

    private ContactPresenter presenter;
    private Contact contact;
    @Override
    protected int getLayoutId() {
        return R.layout.contact_full;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        txtTitle.setText("完善联系人");

        presenter = new ContactPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {

        super.initListeners();

        btnFinish.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            addData();
        });

        btnCancel.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });

        rlBelongCustomer.setOnClickListener(v -> {
            if (ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this, RelationshipListActivity.class);
            CustomerContact data = new CustomerContact();
            data.setId(contact.getId());
            data.setType(2);
            intent.putExtra("customerContact", data);
            startActivity(intent);

        });

        rlSex.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,SimpleSelectActivity.class);
            intent.putExtra("intentSelect",new IntentSelect(
                    "性别",txtSex.getText().toString(), Constant.SELECT_SEX));
            startActivityForResult(intent, Constant.REQUEST_SEX);
        });


    }

    @Override
    protected void initData() {
        contact = (Contact) getIntent().getSerializableExtra("contact");
        if(contact.isUpdate()){
            txtTitle.setText("修改联系人");
        }
        txtName.setText(contact.getName());
        txtMobile.setText(contact.getMobile());
        txtAddress.setText(contact.getAddress());
        txtCompany.setText(contact.getCompany());
        txtJob.setText(contact.getPosition());
        txtSex.setText(Constant.SELECT_SEX[contact.getSex()]);
        txtEmail.setText(contact.getEmail());
        txtRemark.setText(contact.getRemark());

        List<CustomerContactRelation> relations = contact.getRelationship();
        String belongCustom = "";
        for(CustomerContactRelation relation : relations){
            belongCustom += relation.getCustomerName()+"、";
        }
        if(StringUtils.validate(belongCustom)) {
            txtBelongCustomer.setText(belongCustom.substring(0, belongCustom.length() - 1));
        }
    }

    private void addData(){
        String name = txtName.getText().toString();
        String mobile = txtMobile.getText().toString();
        String address = txtAddress.getText().toString();
        String company = txtCompany.getText().toString();
        String position = txtJob.getText().toString();
        String email = txtEmail.getText().toString();
        String remark = txtRemark.getText().toString();

        contact.setName(name);
        contact.setMobile(mobile);
        contact.setAddress(address);
        contact.setCompany(company);
        contact.setPosition(position);
        contact.setEmail(email);
        contact.setRemark(remark);
        presenter.addContact(contact);

        presenter.modifyContact(contact);
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
                    contact.setSex(result.getType());
                    break;
            }
        }
    }

    @Override
    public void onModifyContactSuccess(BaseBean data) {
        if(data.isSuccess()){
            showToast(data.getMsg());
            EventBus.getDefault().postSticky(new Event.MessageEvent());
            finish();
        } else {
            Dialog.show(data.getMsg(),this);
        }
    }

    @Override
    public void onFailure(Throwable e) {
        error(e);
    }

    @Override
    public void onSuccess(Object data) {}
    @Override
    public void onAddContactSuccess(BaseBean<Contact> data) {}
    @Override
    public void onGetContactDetailSuccess(Contact data) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
