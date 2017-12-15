package com.pcjr.pcjr_oa.ui.views.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Person;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.core.mvp.MvpView;
import com.pcjr.pcjr_oa.ui.adapter.PersonAdapter;
import com.pcjr.pcjr_oa.ui.presenter.PersonPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  人员单选
 *  Created by Mario on 2017/12/14下午4:28
 */
public class PersonSingleSelectionActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener,SearchView.OnQueryTextListener,MvpView<List<Person>> {


    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private List<Person> list;
    private PersonAdapter adapter;
    private SearchView searchView;
    private int lastPosition = -1;
    private Person selectedItem;
    private PersonPresenter presenter;
    @Override
    protected int getLayoutId() {
        return R.layout.participant_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("客户经理");
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PersonAdapter();
        adapter.bindToRecyclerView(mRecyclerView);

        presenter = new PersonPresenter();
        presenter.attachView(this);
    }


    @Override
    protected void initListeners() {
        adapter.setOnItemClickListener((adapter,view,position)-> {
            if(lastPosition != -1) {
                Person lastItem = list.get(lastPosition);
                lastItem.setSelected(false);
                adapter.notifyItemChanged(lastPosition,lastItem);
            }
            selectedItem = list.get(position);
            selectedItem.setSelected(true);
            adapter.notifyItemChanged(position,selectedItem);
            lastPosition = position;
        });
    }

    @Override
    protected void initData() {
        mSwipeRefreshLayout.post(()->mSwipeRefreshLayout.setRefreshing(true));
        onRefresh();
    }

    @Override
    public void onRefresh() {
        presenter.getManagerList();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_ok,menu);
        MenuItem menuItem = menu.findItem(R.id.btn_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("搜索参与人");
        searchView.setIconifiedByDefault(true);
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        showToast("onQueryTextSubmit");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        showToast("onQueryTextChange");
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.btn_ok) {
            Intent intent = new Intent();
            intent.putExtra("result",selectedItem);
            setResult(RESULT_OK,intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFailure(Throwable e) {
        mSwipeRefreshLayout.setRefreshing(false);
        error(e);
    }

    @Override
    public void onSuccess(List<Person> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        list = data;
        adapter.setNewData(list);
    }
}
