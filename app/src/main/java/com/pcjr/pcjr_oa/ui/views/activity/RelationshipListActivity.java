package com.pcjr.pcjr_oa.ui.views.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.bean.CustomerContact;
import com.pcjr.pcjr_oa.bean.CustomerContactRelation;
import com.pcjr.pcjr_oa.bean.IntentSelect;
import com.pcjr.pcjr_oa.bean.SelectItem;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.constant.Event;
import com.pcjr.pcjr_oa.core.mvp.BaseSwipeRefreshActivity;
import com.pcjr.pcjr_oa.ui.adapter.RelationshipAdapter;
import com.pcjr.pcjr_oa.ui.presenter.CustomerContactRelationPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.CustomerContactRelationView;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.BottomMenuDialog;
import com.pcjr.pcjr_oa.widget.Dialog;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


/**
 * 联系人 客户 关联设置
 * Created by Mario on 2017/10/30上午10:53
 */
public class RelationshipListActivity extends BaseSwipeRefreshActivity implements CustomerContactRelationView {


    private CustomerContactRelationPresenter presenter;
    private List<CustomerContactRelation> relations;
    private RelationshipAdapter adapter;
    private CustomerContactRelation relation;
    private CustomerContact customerContact;
    private String[] settings = new String[]{"客户联系人关系设置", "删除"};
    private BottomMenuDialog pmd;
    @Override
    protected int getLayoutId() {
        return R.layout.simple_swipe_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        showBack();
        setTitle("客户联系人设置");

        adapter = new RelationshipAdapter();
        adapter.bindToRecyclerView(mRecyclerView);

        presenter = new CustomerContactRelationPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void initListeners() {

        adapter.setOnItemChildClickListener((a, view, position) -> {
            if (ViewUtil.isFastDoubleClick()) return;
            pmd = new BottomMenuDialog.Builder(this)
                    .setMenus(settings)
                    .setOnItemClickListener((dialog, menuPosition) -> {
                        if (menuPosition == 0) {
                            relation = relations.get(position);
                            Intent intent = new Intent(this, SimpleSelectActivity.class);
                            intent.putExtra("intentSelect", new IntentSelect(
                                    "与客户关系", relation.getRoleRelationship(), Constant.SELECT_ROLE));
                            startActivityForResult(intent, Constant.REQUEST_ROLE);
                        } else {
                            relations.remove(position);
                            a.notifyItemRemoved(position);
                        }
                    }).create();
            pmd.show();
        });
    }

    @Override
    protected void initData() {
        customerContact = (CustomerContact) getIntent().getSerializableExtra("customerContact");
        super.initData();
    }

    @Override
    public void onRefresh() {
        presenter.getRelationshipList(customerContact.getId(), customerContact.getType());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_modify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        if (item.getItemId() == R.id.btn_save) {
            customerContact.setRelationship(relations);
            presenter.modifyRelationship(customerContact);
        }
        if (item.getItemId() == R.id.btn_modify) {
            Intent intent;
            if(customerContact.getType() == 2){
                intent = new Intent(this, CustomerSelectListActivity.class);
            } else {
                intent = new Intent(this, ContactSelectListActivity.class);
            }
            intent.putExtra("customerContact", customerContact);
            startActivityForResult(intent, Constant.REQUEST_CUSTOMER_CONTACT);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            SelectItem result;
            switch (requestCode) {
                case Constant.REQUEST_ROLE:
                    result = (SelectItem) data.getSerializableExtra("result");
                    relation.setRoleRelationship(result.getName());
                    adapter.notifyDataSetChanged();
                    break;
                case Constant.REQUEST_CUSTOMER_CONTACT:
                    onRefresh();
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {
        error(e);
    }

    @Override
    public void onModifyRelationshipSuccess(BaseBean data) {
        if (data.isSuccess()) {
            showToast(data.getMsg());
            Event.MessageEvent event = new Event.MessageEvent();
            for (CustomerContactRelation relation : relations) {
                event.message += relation.getCustomerName();
            }
            EventBus.getDefault().postSticky(event);
            finish();
        } else {
            Dialog.show(data.getMsg(), this);
        }
    }

    @Override
    public void onGetRelationshipListSuccess(List<CustomerContactRelation> data) {
        if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
        if (data.size() > 0) {
            relations = data;
            adapter.setType(customerContact.getType());
            adapter.setNewData(data);
        } else {
            adapter.setNewData(null);
            adapter.setEmptyView(empty);
        }
    }

    @Override
    public void onSuccess(Object data) {
    }

    @Override
    public void onGetCustomerListSuccess(BaseBean<List<Customer>> data) {
    }

    @Override
    public void onGetContactListSuccess(BaseBean<List<Contact>> data) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        pmd.dismiss();
    }
}
