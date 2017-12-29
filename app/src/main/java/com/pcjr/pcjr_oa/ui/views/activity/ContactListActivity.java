package com.pcjr.pcjr_oa.ui.views.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.Menu;
import android.view.MenuItem;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.core.mvp.BaseSwipeRefreshActivity;
import com.pcjr.pcjr_oa.core.mvp.MvpView;
import com.pcjr.pcjr_oa.ui.adapter.ContactAdapter;
import com.pcjr.pcjr_oa.ui.presenter.ContactListPresenter;
import com.pcjr.pcjr_oa.widget.Dialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;


/**
 *  客户联系人列表
 *  Created by Mario on 2017/10/30上午10:53
 */
public class ContactListActivity extends BaseSwipeRefreshActivity implements MvpView<BaseBean<List<Contact>>> {

    private ContactListPresenter presenter;
    private RxPermissions rxPermissions;

    @Override
    protected int getLayoutId() {
        return R.layout.simple_swipe_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        showBack();
        setTitle("客户联系人");

        adapter = new ContactAdapter();
        adapter.bindToRecyclerView(mRecyclerView);

        presenter = new ContactListPresenter();
        presenter.attachView(this);

        rxPermissions = new RxPermissions(this);
    }

    @Override
    protected void initListeners() {

        adapter.setOnItemClickListener((a, view, position) -> {
            Contact contact = (Contact) a.getItem(position);
            Intent intent = new Intent(this, ContactDetailActivity.class);
            intent.putExtra("id", contact.getId());
            startActivity(intent);
        });

        adapter.setOnItemChildClickListener((a, view, position) -> {
            rxPermissions
                    .requestEach(Manifest.permission.CALL_PHONE)
                    .subscribe(permission -> {
                        if (permission.granted) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:400-101-3339"));
                            //startActivity(intent);
                        }
                        else if (permission.shouldShowRequestPermissionRationale) {
                        } else {
                            Dialog.show("您未开启拨打电话的权限，您可以在“设置-应用”中为此应用打开该权限",this);
                        }
                    });
        });

        adapter.setOnLoadMoreListener(() -> {
            refresh = false;
            presenter.getContactList(++page, query);
        }, mRecyclerView);
    }

    @Override
    protected void initData() {
       super.initData();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        presenter.getContactList(page,query);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_add){
            startActivity(new Intent(this,ContactAddActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFailure(Throwable e) {
        super.error(e);
    }

    @Override
    public void onSuccess(BaseBean<List<Contact>> data) {
        super.success(data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

}
