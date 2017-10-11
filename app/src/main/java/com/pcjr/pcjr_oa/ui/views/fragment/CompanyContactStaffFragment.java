package com.pcjr.pcjr_oa.ui.views.fragment;


import android.content.Intent;
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
import com.pcjr.pcjr_oa.bean.Person;
import com.pcjr.pcjr_oa.core.BaseFragment;
import com.pcjr.pcjr_oa.ui.adapter.StaffAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;
import com.pcjr.pcjr_oa.ui.views.activity.StaffDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  公司通讯录-员工
 *  Created by Mario on 2017/8/9上午11:17
 */
public class CompanyContactStaffFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    public static final String TAG = CompanyContactStaffFragment.class.getSimpleName();

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Person> list;
    private StaffAdapter adapter;
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

        adapter = new StaffAdapter();
        mRecyclerView.setAdapter(adapter);


    }

    @Override
    protected void initListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(this);

        adapter.setOnItemClickListener((a,v,p)->{
            startActivity(new Intent(getContext(), StaffDetailActivity.class));
        });

        adapter.setOnItemChildClickListener((a,v,p)->{
            showToast("拨打电话");
        });
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

    private void getData(){
        list = new ArrayList<>();

        Person p = new Person();
        p.setName("杜拉拉");
        p.setDepartment("部门111111");
        p.setJob("职位11");
        list.add(p);

        p = new Person();
        p.setName("萧拉拉");
        p.setDepartment("部门2222111");
        p.setJob("职位22");
        list.add(p);
        adapter.setNewData(list);
    }

    public static Fragment newInstance() {
        CompanyContactStaffFragment fragment = new CompanyContactStaffFragment();
        return fragment;
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
