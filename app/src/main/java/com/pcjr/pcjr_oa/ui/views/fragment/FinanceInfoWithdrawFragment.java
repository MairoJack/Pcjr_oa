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
import com.pcjr.pcjr_oa.bean.Withdraw;
import com.pcjr.pcjr_oa.core.BaseFragment;
import com.pcjr.pcjr_oa.core.mvp.MvpView;
import com.pcjr.pcjr_oa.ui.adapter.FinanceInfoAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;
import com.pcjr.pcjr_oa.ui.presenter.FinanceInfoWithdrawPresenter;
import com.pcjr.pcjr_oa.utils.DateUtils;

import java.util.List;

import butterknife.BindView;

/**
 *  资金信息-提现
 *  Created by Mario on 2017/10/9下午2:05
 */
public class FinanceInfoWithdrawFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,MvpView<Withdraw>{

    public static final String TAG = FinanceInfoWithdrawFragment.class.getSimpleName();

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.txt_today_withdraw) TextView txtTodayWithdraw;
    @BindView(R.id.txt_title) TextView txtTitle;
    private FinanceInfoAdapter adapter;
    private FinanceInfoWithdrawPresenter presenter;
    private View notDataView;

    private boolean isPrepared;
    private boolean mHasLoadedOnce;
    private String startDate ;
    private String endDate;
    @Override
    protected int getLayoutId() {
        return R.layout.finance_info_withdraw;
    }

    @Override
    protected void initViews(LayoutInflater inflater,View self, Bundle savedInstanceState){

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(),1,20, Color.parseColor("#eeeeee")));
        notDataView = inflater.inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);

        adapter = new FinanceInfoAdapter();
        mRecyclerView.setAdapter(adapter);

        presenter = new FinanceInfoWithdrawPresenter();
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
        FinanceInfoWithdrawFragment fragment = new FinanceInfoWithdrawFragment();
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
        txtTitle.setText(startDate + "至" + endDate + "日提现数据（元）");
        presenter.getWithdrawDurationInfo(start,end);
    }

    @Override
    public void onFailure(Throwable e) {
        if(mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
        showToast(getString(R.string.network_error));
    }

    @Override
    public void onSuccess(Withdraw data) {
        mHasLoadedOnce = true;
        txtTodayWithdraw.setText(data.getToday_withdraw_amount());
        List<Days> list = data.getList();
        if(mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
        if(list.size() == 0){
            adapter.setNewData(null);
            adapter.setEmptyView(notDataView);
        } else {
            double max = 0;
            for(Days day : list){
                Double amount = Double.valueOf(day.getAmount());
                if(amount > max){
                    max = amount;
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
