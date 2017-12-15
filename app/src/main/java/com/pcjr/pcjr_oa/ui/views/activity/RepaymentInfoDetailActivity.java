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
import com.pcjr.pcjr_oa.bean.RepaymentInfo;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.RepaymentInfoDetailAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;
import com.pcjr.pcjr_oa.ui.presenter.RepaymentInfoDetailPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.RepaymentInfoDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  回款情况-详情
 *  Created by Mario on 2017/10/9下午2:03
 */
public class  RepaymentInfoDetailActivity extends BaseToolbarActivity implements RepaymentInfoDetailView{

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.txt_title) TextView txtTitle;
    @BindView(R.id.txt_repayment_amount) TextView txtRepaymentAmount;
    private RepaymentInfoDetailAdapter adapter;
    private RepaymentInfoDetailPresenter presenter;

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

        adapter = new RepaymentInfoDetailAdapter();
        mRecyclerView.setAdapter(adapter);

        presenter = new RepaymentInfoDetailPresenter();
        presenter.attachView(this);
    }


    @Override
    protected void initListeners() {

    }


    @Override
    protected void initData() {
        String oneDay = getIntent().getStringExtra("data");
        txtTitle.setText(oneDay+" 应回本息总和（元）");
        presenter.getOneDayRepaymentDetail(oneDay);
    }

    @Override
    public void onFailure(Throwable e) {
        error(e);
    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onOneDayRepaymentDetailSuccess(RepaymentInfo data) {
        txtRepaymentAmount.setText(data.getAmount());
        List<Repayment> list = data.getList();
        adapter.setNewData(list);
    }
}
