package com.pcjr.pcjr_oa.ui.views.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Reply;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.ReplyAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  平台公告
 *  Created by Mario on 2017/7/26下午3:45
 */
public class CommentDetailActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private List<Reply> list;
    private ReplyAdapter adapter;
    private View notDataView;

    @Override
    protected int getLayoutId() {
        return R.layout.comment_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("反馈详情");
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this,1));

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);
    }


    @Override
    protected void initListeners() {
        adapter.setOnItemChildClickListener((adapter,view,position)-> {
            Reply reply = (Reply) adapter.getItem(position);
            showToast(reply.getName());
        });
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();

        String[] nameList = {"Bob", "Andy", "Lily", "Brown", "Bruce"};
        for(String s : nameList){
            Reply p = new Reply(s,s+"内容内容内容内容内容",1501055624);
            list.add(p);
        }

        adapter = new ReplyAdapter(list);
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
}
