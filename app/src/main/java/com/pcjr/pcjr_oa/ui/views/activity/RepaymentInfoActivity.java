package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Recharge;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.RepaymentInfoAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  回款情况
 *  Created by Mario on 2017/10/9下午2:03
 */
public class RepaymentInfoActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.btn_detail) TextView btnDetail;
    private List<Recharge> list;
    private RepaymentInfoAdapter adapter;
    private View notDataView;

    @Override
    protected int getLayoutId() {
        return R.layout.repayment_info;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("回款情况");
        showBack();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this,1,20, Color.parseColor("#eeeeee")));
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);

        adapter = new RepaymentInfoAdapter();
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    protected void initListeners() {
        btnDetail.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivity(new Intent(this,RepaymentInfoDetailActivity.class));
        });
    }


    @Override
    protected void initData() {
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_date,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_date){
            startActivity(new Intent(this,CountDateSettingActivity.class));
        }
        return super.onOptionsItemSelected(item);

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
}
