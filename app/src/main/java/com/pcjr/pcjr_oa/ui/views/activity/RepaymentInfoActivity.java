package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Recharge;
import com.pcjr.pcjr_oa.bean.Repayment;
import com.pcjr.pcjr_oa.bean.RepaymentInfo;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.RepaymentInfoAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;
import com.pcjr.pcjr_oa.ui.presenter.RepaymentInfoPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.RepaymentInfoView;
import com.pcjr.pcjr_oa.utils.DateUtils;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import java.util.List;

import butterknife.BindView;

/**
 *  回款情况
 *  Created by Mario on 2017/10/9下午2:03
 */
public class RepaymentInfoActivity extends BaseToolbarActivity implements RepaymentInfoView, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.btn_detail) TextView btnDetail;
    @BindView(R.id.txt_today_amount) TextView txtTodayAmount;

    @BindView(R.id.txt_title) TextView txtTitle;
    private RepaymentInfoAdapter adapter;
    private RepaymentInfoPresenter presenter;
    private View notDataView;

    private long start;
    private long end;
    private String today;
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

        presenter = new RepaymentInfoPresenter();
        presenter.attachView(this);
    }


    @Override
    protected void initListeners() {
        adapter.setOnItemClickListener((a,v,p)-> {
            Repayment repayment = (Repayment) a.getItem(p);
            Intent intent = new Intent(this,RepaymentInfoDetailActivity.class);
            intent.putExtra("data",repayment.getDays());
            startActivity(intent);
        });
        btnDetail.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            Intent intent = new Intent(this,RepaymentInfoDetailActivity.class);
            intent.putExtra("data",today);
            startActivity(intent);
        });
    }


    @Override
    protected void initData() {
        start = System.currentTimeMillis() / 1000;
        end = start + 10 * 24 * 3600;
        txtTitle.setText(DateUtils.longTimeToStr(start, DateUtils.DATE_FORMAT_YYYY_MM_DD)
                + "至" + DateUtils.longTimeToStr(end, DateUtils.DATE_FORMAT_YYYY_MM_DD)
                + "日回款本息（元）");
        mSwipeRefreshLayout.post(()->mSwipeRefreshLayout.setRefreshing(true));
        onRefresh();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_date,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_date){
            startActivityForResult(new Intent(this,CountDateSettingActivity.class), Constant.REQUEST_COUNT_DATE);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onRefresh() {

        presenter.getRepaymentDurationInfo(start,end);
    }

    @Override
    public void onFailure(Throwable e) {
        if(mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
        showToast(getString(R.string.network_error));
    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onRepaymentDurationInfoSuccess(RepaymentInfo data) {
        txtTodayAmount.setText(data.getTodayRepaymentAmount());
        today = data.getToday();
        List<Repayment> list = data.getList();
        if(mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
        if(list.size() == 0){
            adapter.setNewData(null);
            adapter.setEmptyView(notDataView);
        } else {
            double max = 0;
            for(Repayment recharge : list){
                Double amount = Double.valueOf(recharge.getAmount());
                if(amount > max){
                    max = amount;
                }
            }
            adapter.setMax(max);
            adapter.setNewData(list);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.REQUEST_COUNT_DATE:
                    String startDate = data.getStringExtra("startDate");
                    String endDate = data.getStringExtra("endDate");
                    txtTitle.setText(startDate + "至" + endDate + "日回款本息（元）");
                    start = DateUtils.getMillisOfDate(startDate);
                    /**
                     * 加一天 后台需要
                     */
                    end = DateUtils.getMillisOfDate(endDate) + 86400;
                    mSwipeRefreshLayout.post(()->mSwipeRefreshLayout.setRefreshing(true));
                    onRefresh();
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
