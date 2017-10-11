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
import com.pcjr.pcjr_oa.bean.Department;
import com.pcjr.pcjr_oa.core.BaseFragment;
import com.pcjr.pcjr_oa.ui.adapter.DepartmentAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  公司通讯录-部门
 *  Created by Mario on 2017/8/9上午11:17
 */
public class CompanyContactDepartmentFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    public static final String TAG = CompanyContactDepartmentFragment.class.getSimpleName();

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Department> list;
    private DepartmentAdapter adapter;
    private View notDataView;

    private boolean isPrepared;
    private boolean mHasLoadedOnce;

    @Override
    protected int getLayoutId() {
        return R.layout.company_contack_department;
    }

    @Override
    protected void initViews(LayoutInflater inflater,View self, Bundle savedInstanceState){
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(),1));
        notDataView = inflater.inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);
        adapter = new DepartmentAdapter();
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
        CompanyContactDepartmentFragment fragment = new CompanyContactDepartmentFragment();
        return fragment;
    }

    private void getData(){
        list = new ArrayList<>();

        Department p = new Department();
        p.setName("部门111111");
        p.setNumber(3);
        list.add(p);

        p = new Department();
        p.setName("部门22222");
        p.setNumber(7);
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
