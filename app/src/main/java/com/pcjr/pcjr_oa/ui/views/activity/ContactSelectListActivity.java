package com.pcjr.pcjr_oa.ui.views.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.bean.CustomerContact;
import com.pcjr.pcjr_oa.bean.CustomerContactRelation;
import com.pcjr.pcjr_oa.constant.Event;
import com.pcjr.pcjr_oa.core.mvp.BaseSwipeRefreshActivity;
import com.pcjr.pcjr_oa.ui.adapter.ContactSelectAdapter;
import com.pcjr.pcjr_oa.ui.presenter.CustomerContactRelationPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.CustomerContactRelationView;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * 关联联系人 - 选择
 * Created by Mario on 2017/10/30上午10:53
 */
public class ContactSelectListActivity extends BaseSwipeRefreshActivity implements CustomerContactRelationView {

    @BindView(R.id.txt_check_result)
    TextView txtCheckResult;
    @BindView(R.id.btn_ok)
    Button btnOk;

    private CustomerContactRelationPresenter presenter;
    private List<Contact> list;
    private CustomerContact customerContact;
    private Map<String, CustomerContactRelation> map;

    @Override
    protected int getLayoutId() {
        return R.layout.customer_contact_select_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        showBack();
        setTitle("选择联系人");

        adapter = new ContactSelectAdapter();
        adapter.bindToRecyclerView(mRecyclerView);

        presenter = new CustomerContactRelationPresenter();
        presenter.attachView(this);

    }

    @Override
    protected void initListeners() {

        adapter.setOnItemClickListener((a, view, position) -> {
            Contact current = list.get(position);
            if (current.getIsSelected()) {
                current.setIsSelected(false);
                map.remove(current.getId());
            } else {
                current.setIsSelected(true);
                CustomerContactRelation relation = new CustomerContactRelation();
                relation.setId("0");
                relation.setContactId(current.getId());
                map.put(current.getId(), relation);
            }
            adapter.notifyItemChanged(position, current);
            txtCheckResult.setText(Html.fromHtml("已选择 <font color='#f54246'>" + map.size() + "</font> 项"));
        });

        btnOk.setOnClickListener(v -> {
            if (ViewUtil.isFastDoubleClick()) return;
            customerContact.setRelationship(new ArrayList<>(map.values()));
            presenter.modifyRelationship(customerContact);

        });

    }

    @Override
    protected void initData() {
        map = new HashMap<>();
        customerContact = (CustomerContact) getIntent().getSerializableExtra("customerContact");
        presenter.getRelationshipList(customerContact.getId(), customerContact.getType());
        super.initData();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        presenter.getContactList(page, query, customerContact.getId(), customerContact.getType());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public void onGetCustomerListSuccess(BaseBean<List<Customer>> data) {

    }

    @Override
    public void onGetContactListSuccess(BaseBean<List<Contact>> data) {
        list = data.getData();
        super.success(data);
    }

    @Override
    public void onModifyRelationshipSuccess(BaseBean data) {
        if (data.isSuccess()) {
            showToast(data.getMsg());
            EventBus.getDefault().postSticky(new Event.MessageEvent());
            setResult(RESULT_OK);
            finish();
        } else {
            Dialog.show(data.getMsg(), this);
        }
    }

    @Override
    public void onGetRelationshipListSuccess(List<CustomerContactRelation> data) {
        if (data.size() > 0) {
            for (CustomerContactRelation relation : data) {
                map.put(relation.getContactId(), relation);
            }
        }
        txtCheckResult.setText(Html.fromHtml("已选择 <font color='#f54246'>" + map.size() + "</font> 项"));
    }

    @Override
    public void onFailure(Throwable e) {
        super.error(e);
    }

    @Override
    public void onSuccess(Object data) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

}
