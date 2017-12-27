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
import com.pcjr.pcjr_oa.bean.IntentSelect;
import com.pcjr.pcjr_oa.bean.SelectItem;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.constant.Event;
import com.pcjr.pcjr_oa.core.BaseAppCompatActivity;
import com.pcjr.pcjr_oa.ui.presenter.ContactPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.ContactView;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 新建联系人
 * Created by Mario on 2017/10/30上午11:12
 */
public class ContactAddActivity extends BaseAppCompatActivity implements ContactView {


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

    @BindView(R.id.btn_cancel) Button btnCancel;
    @BindView(R.id.btn_finish) Button btnFinish;
    @BindView(R.id.btn_save) Button btnSave;

    private ContactPresenter presenter;
    private boolean onlySave = true;
    private Contact contact;

    @Override
    protected int getLayoutId() {
        return R.layout.contact_add;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        presenter = new ContactPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    private void addData() {
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
    }

    @Override
    protected void initListeners() {

        btnCancel.setOnClickListener(v -> {
            if (ViewUtil.isFastDoubleClick()) return;
            finish();
        });

        btnFinish.setOnClickListener(v -> {
            if (ViewUtil.isFastDoubleClick()) return;
            addData();
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

        rlSex.setOnClickListener(v -> {
            if (ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this, SimpleSelectActivity.class);
            intent.putExtra("intentSelect", new IntentSelect(
                    "性别", txtSex.getText().toString(), Constant.SELECT_SEX));
            startActivityForResult(intent, Constant.REQUEST_SEX);
        });

        btnSave.setOnClickListener(v -> {
            if (ViewUtil.isFastDoubleClick()) return;
            onlySave = false;
            addData();
        });

    }

    @Override
    protected void initData() {
        contact = new Contact();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            SelectItem result;
            switch (requestCode) {
                case Constant.REQUEST_SEX:
                    result = (SelectItem) data.getSerializableExtra("result");
                    txtSex.setText(result.getName());
                    contact.setSex(result.getType());
                    break;
                case Constant.REQUEST_RELATIONSHIP:
                    result = (SelectItem) data.getSerializableExtra("result");
                    txtSex.setText(result.getName());
                    contact.setSex(result.getType());
                    break;
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {
        error(e);
    }

    @Override
    public void onAddContactSuccess(BaseBean<Contact> data) {
        if (data.isSuccess()) {
            contact.setId(data.getData().getId());
            showToast(data.getMsg());
            finish();
            if (!onlySave) {
                Intent intent = new Intent(this, ContactFullActivity.class);
                intent.putExtra("contact", contact);
                startActivity(intent);
            }
        } else {
            Dialog.show(data.getMsg(), this);
        }
    }

    @Override
    public void onSuccess(Object data) {
    }

    @Override
    public void onModifyContactSuccess(BaseBean data) {
    }

    @Override
    public void onGetContactDetailSuccess(Contact data) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(Event.MessageEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        txtBelongCustomer.setText(event.message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
