package com.pcjr.pcjr_oa.ui.views.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.ContactAdapter;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  客户联系人列表
 *  Created by Mario on 2017/10/30上午10:53
 */
public class ContactListActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {


    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private SearchView searchView;

    private List<Contact> list;
    private ContactAdapter adapter;
    private View notDataView;

    @Override
    protected int getLayoutId() {
        return R.layout.simple_swipe_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("客户联系人");

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);

        adapter = new ContactAdapter();
        mRecyclerView.setAdapter(adapter);

    }


    @Override
    protected void initListeners() {

        adapter.setOnItemClickListener((adapter,view,position)-> {
            startActivity(new Intent(this,ScheduleEditActivity.class));
        });
    }

    @Override
    protected void initData() {
        mSwipeRefreshLayout.post(()->mSwipeRefreshLayout.setRefreshing(true));
        onRefresh();
    }

    private void getData(){
        list = new ArrayList<>();

        Contact c = new Contact("神啦啦","无所属客户",1501055624);
        list.add(c);

        c = new Contact("成啦啦","神啦啦",1501055624);
        list.add(c);

        c = new Contact("神啦啦","无所属客户",1501055624);
        list.add(c);

        c = new Contact("神啦啦","成啦啦",1501055624);
        list.add(c);
        adapter.setNewData(list);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_add,menu);
        MenuItem menuItem = menu.findItem(R.id.btn_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("搜索联系人");
        searchView.setIconifiedByDefault(true);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_add){
            startActivity(new Intent(this,ContactAddActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
