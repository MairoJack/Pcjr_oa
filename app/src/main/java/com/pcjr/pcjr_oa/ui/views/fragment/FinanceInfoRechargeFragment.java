package com.pcjr.pcjr_oa.ui.views.fragment;


import android.graphics.Color;
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
import com.pcjr.pcjr_oa.bean.Recharge;
import com.pcjr.pcjr_oa.core.BaseFragment;
import com.pcjr.pcjr_oa.ui.adapter.FinanceInfoRechargeAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  资金信息-充值
 *  Created by Mario on 2017/10/9下午2:05
 */
public class FinanceInfoRechargeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    public static final String TAG = FinanceInfoRechargeFragment.class.getSimpleName();

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Recharge> list;
    private FinanceInfoRechargeAdapter adapter;
    private View notDataView;

    private boolean isPrepared;
    private boolean mHasLoadedOnce;

    @Override
    protected int getLayoutId() {
        return R.layout.finance_info_recharge;
    }

    @Override
    protected void initViews(LayoutInflater inflater,View self, Bundle savedInstanceState){

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(),1,20, Color.parseColor("#eeeeee")));
        notDataView = inflater.inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);

        adapter = new FinanceInfoRechargeAdapter();
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
        FinanceInfoRechargeFragment fragment = new FinanceInfoRechargeFragment();
        return fragment;
    }

    private void getData(){
        list = new ArrayList<>();

        Recharge r = new Recharge();
        r.setDate(1483203661);
        r.setAmount("1");
        list.add(r);

        r = new Recharge();
        r.setDate(1483203661);
        r.setAmount("2");
        list.add(r);

        r = new Recharge();
        r.setDate(1483203661);
        r.setAmount("3");
        list.add(r);

        r = new Recharge();
        r.setDate(1483203661);
        r.setAmount("4");
        list.add(r);

        r = new Recharge();
        r.setDate(1483203661);
        r.setAmount("5");
        list.add(r);
        double max = 0;
        for(Recharge recharge : list){
            Double amount = Double.valueOf(recharge.getAmount());
            if(amount > max){
                max = amount;
            }
        }
        adapter.setMax(max);
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
