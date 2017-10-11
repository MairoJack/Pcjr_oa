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

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Project;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.UnionProjectAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  关联项目
 *  Created by Mario on 2017/8/29上午8:53
 */
public class UnionProjectActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener,SearchView.OnQueryTextListener {


    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private List<Project> list;
    private UnionProjectAdapter adapter;
    private SearchView searchView;
    private int lastPosition = -1;
    private Project selectedItem;
    @Override
    protected int getLayoutId() {
        return R.layout.simple_swipe_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("项目");
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new UnionProjectAdapter();
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    protected void initListeners() {
        adapter.setOnItemClickListener((adapter,view,position)-> {
            if(lastPosition != -1) {
                Project lastItem = list.get(lastPosition);
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
        list = new ArrayList<>();
        Project p = new Project("杜拉拉",1501055624,"容内容内容内容容内容内容内容容内容内容内容容内容内容内容容");
        list.add(p);
        p = new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p = new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p= new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p= new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p= new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p= new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p= new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p= new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p= new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        adapter.setNewData(list);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_ok,menu);
        MenuItem menuItem = menu.findItem(R.id.btn_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("搜索项目");
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
            intent.putExtra("name",selectedItem.getName());
            setResult(RESULT_OK,intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
