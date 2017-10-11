package com.pcjr.pcjr_oa.ui.views.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.PlatformNotice;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.PlatformNoticeAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  平台公告
 *  Created by Mario on 2017/7/26下午3:45
 */
public class PlatformNoticeActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private List<PlatformNotice> list;
    private PlatformNoticeAdapter adapter;
    private View notDataView;

    @Override
    protected int getLayoutId() {
        return R.layout.platform_notice;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("平台公告");
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this,1));

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);
    }


    @Override
    protected void initListeners() {
        adapter.setOnItemClickListener((adapter,view,position)-> {
            Intent intent = new  Intent(PlatformNoticeActivity.this, PlatformNoticeDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", list.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();

        PlatformNotice p = new PlatformNotice();
        p.setSend_date(1501055624);
        p.setTitle("公告1");
        p.setAuthor("啦啦啦1");
        p.setContent("内容内容内容内容");
        list.add(p);

        p = new PlatformNotice();
        p.setSend_date(1501055624);
        p.setTitle("公告2");
        p.setAuthor("啦啦啦2");
        p.setContent("容内容内容内容容内容内容内容容内容内容内容容内容内容内容容内容内容内容容内容内容内容容内容内容内容容内容内容内容");
        list.add(p);
        adapter = new PlatformNoticeAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }

    int i = 0;
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i%2 == 0) {
                    adapter.setNewData(null);
                    adapter.setEmptyView(notDataView);
                }else{
                    adapter.setNewData(list);
                }
                i++;
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) finish();
        return false;
    }
}
