package com.pcjr.pcjr_oa.ui.views.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Repayment;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.RepaymentInfoDetailAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  回款情况-详情
 *  Created by Mario on 2017/10/9下午2:03
 */
public class RepaymentInfoDetailActivity extends BaseToolbarActivity{

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.txt_repayment_amount) TextView txtRepaymentAmount;
    private List<Repayment> list;
    private RepaymentInfoDetailAdapter adapter;
    private View notDataView;

    @Override
    protected int getLayoutId() {
        return R.layout.repayment_info_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("回款情况");
        showBack();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this,1,20, Color.parseColor("#eeeeee")));
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);

        adapter = new RepaymentInfoDetailAdapter();
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    protected void initListeners() {

    }


    @Override
    protected void initData() {
        list = new ArrayList<>();

        Repayment r = new Repayment();
        r.setTitle("大城小爱NO.468-2016");
        r.setActualCapital("0.00元");
        r.setActualInterest("1832.45元");
        list.add(r);

        r = new Repayment();
        r.setTitle("大城小爱NO.35-2016");
        r.setActualCapital("0.00元");
        r.setActualInterest("4922323.45元");
        list.add(r);

        r = new Repayment();
        r.setTitle("大城小爱NO.368-2017");
        r.setActualCapital("0.00元");
        r.setActualInterest("3832.45元");
        list.add(r);

        r = new Repayment();
        r.setTitle("大城小爱NO.128-2017");
        r.setActualCapital("32323.00元");
        r.setActualInterest("1632.45元");
        list.add(r);

        adapter.setNewData(list);
    }
}
