package com.pcjr.pcjr_oa.ui.views.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.QueryRecord;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.QueryRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  查阅记录列表
 *  Created by Mario on 2017/12/8下午2:55
 */
public class QueryRecordListActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private List<QueryRecord> list;
    private QueryRecordAdapter adapter;
    private View notDataView;

    @Override
    protected int getLayoutId() {
        return R.layout.simple_swipe_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("查阅记录");

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);

        adapter = new QueryRecordAdapter();
        mRecyclerView.setAdapter(adapter);

    }


    @Override
    protected void initListeners() {

        adapter.setOnItemClickListener((adapter,view,position)-> {
            startActivity(new Intent(this,ContactDetailActivity.class));
        });
    }

    @Override
    protected void initData() {
        mSwipeRefreshLayout.post(()->mSwipeRefreshLayout.setRefreshing(true));
        onRefresh();
    }

    private void getData(){
        list = new ArrayList<>();

        QueryRecord c = new QueryRecord("神啦啦",true,1501055624);
        list.add(c);

        c = new QueryRecord("成啦啦",true,1501055624);
        list.add(c);

        c = new QueryRecord("神啦啦",false,1501055624);
        list.add(c);

        c = new QueryRecord("神啦啦",false,1501055624);
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

}
