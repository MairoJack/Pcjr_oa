package com.pcjr.pcjr_oa.ui.views.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Job;
import com.pcjr.pcjr_oa.core.BaseFragment;
import com.pcjr.pcjr_oa.ui.adapter.JobAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  公司通讯录-职位
 *  Created by Mario on 2017/8/9上午11:17
 */
public class CompanyContactJobFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    public static final String TAG = CompanyContactJobFragment.class.getSimpleName();

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Job> list;
    private JobAdapter adapter;
    private View notDataView;

    private boolean isPrepared;
    private boolean mHasLoadedOnce;

    @Override
    protected int getLayoutId() {
        return R.layout.company_contack_staff;
    }

    @Override
    protected void initViews(LayoutInflater inflater,View self, Bundle savedInstanceState){

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(),1));
        notDataView = inflater.inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);

        adapter = new JobAdapter();
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        isPrepared = true;
        lazyLoad();

    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        mSwipeRefreshLayout.post(()->mSwipeRefreshLayout.setRefreshing(true));
        onRefresh();
    }

    public static Fragment newInstance() {
        CompanyContactJobFragment fragment = new CompanyContactJobFragment();
        return fragment;
    }

    private void getData(){
        list = new ArrayList<>();

        Job p = new Job();
        p.setName("职位1111");
        p.setNumber(2);
        list.add(p);

        p = new Job();
        p.setName("职位1222");
        p.setNumber(10);
        list.add(p);
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
