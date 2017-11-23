package com.pcjr.pcjr_oa.ui.views.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Days;
import com.pcjr.pcjr_oa.bean.Member;
import com.pcjr.pcjr_oa.core.BaseFragment;
import com.pcjr.pcjr_oa.ui.adapter.UserCountAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;
import com.pcjr.pcjr_oa.ui.presenter.UserCountPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.MemberView;
import com.pcjr.pcjr_oa.utils.DateUtils;

import java.util.List;

import butterknife.BindView;

/**
 *  用户统计-实名
 *  Created by Mario on 2017/10/10下午3:54
 */
public class UserCountRealNameFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,MemberView{

    public static final String TAG = UserCountRealNameFragment.class.getSimpleName();

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.txt_real_name_number) TextView txtRealNameNumber;
    @BindView(R.id.txt_title) TextView txtTitle;
    private UserCountAdapter adapter;
    private UserCountPresenter presenter;
    private View notDataView;

    private boolean isPrepared;
    private boolean mHasLoadedOnce;
    private String startDate ;
    private String endDate;
    @Override
    protected int getLayoutId() {
        return R.layout.user_count_realname;
    }

    @Override
    protected void initViews(LayoutInflater inflater,View self, Bundle savedInstanceState){

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(),1,20, Color.parseColor("#eeeeee")));
        notDataView = inflater.inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);

        adapter = new UserCountAdapter();
        mRecyclerView.setAdapter(adapter);

        presenter = new UserCountPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void initListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        startDate = DateUtils.getNDayBeforeCurrentDate(10);
        endDate = DateUtils.getCurrentDate();
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
        UserCountRealNameFragment fragment = new UserCountRealNameFragment();
        return fragment;
    }

    public void changeDate(String startDate,String endDate){
        this.startDate = startDate;
        this.endDate = endDate;
        mSwipeRefreshLayout.post(()->mSwipeRefreshLayout.setRefreshing(true));
        onRefresh();
    }


    @Override
    public void onRefresh() {
        long start = DateUtils.getMillisOfDate(startDate);
        long end = DateUtils.getMillisOfDate(endDate) + 86400;
        txtTitle.setText(startDate + "至" + endDate + "日实名人数（人）");
        presenter.getEffectiveMemberNumDurationInfo(start,end);
    }

    @Override
    public void onFailure(Throwable e) {
        if(mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
        showToast(getString(R.string.network_error));
    }

    @Override
    public void onSuccess(Object data) {}

    @Override
    public void onMemberNumDurationInfoSuccess(Member data) {}

    @Override
    public void onEffectiveMemberNumDurationInfoSuccess(Member data) {
        mHasLoadedOnce = true;
        if(mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
        txtRealNameNumber.setText(data.getToday_effective_member_num());
        List<Days> list = data.getList();
        if(list.size() == 0){
            adapter.setNewData(null);
            adapter.setEmptyView(notDataView);
        } else {
            double max = 0;
            for(Days day : list){
                Double num = Double.valueOf(day.getNum());
                if(num > max){
                    max = num;
                }
            }
            adapter.setMax(max);
            adapter.setNewData(list);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
