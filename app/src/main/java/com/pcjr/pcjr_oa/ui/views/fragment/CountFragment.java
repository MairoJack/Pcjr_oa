package com.pcjr.pcjr_oa.ui.views.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.PlatformData;
import com.pcjr.pcjr_oa.core.BaseFragment;
import com.pcjr.pcjr_oa.core.mvp.MvpView;
import com.pcjr.pcjr_oa.ui.presenter.CountPresenter;
import com.pcjr.pcjr_oa.ui.views.activity.FinanceInfoActivity;
import com.pcjr.pcjr_oa.ui.views.activity.ProductDataActivity;
import com.pcjr.pcjr_oa.ui.views.activity.RepaymentInfoActivity;
import com.pcjr.pcjr_oa.ui.views.activity.UserCountActivity;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;

/**
 *  统计
 *  Created by Mario on 2017/7/20上午11:14
 */
public class CountFragment extends BaseFragment implements MvpView<PlatformData>{

    public static final String TAG = CountFragment.class.getSimpleName();

    @BindView(R.id.txt_available_balance)  TextView txtAvailableBalance;
    @BindView(R.id.txt_yesterday_balance)  TextView txtYesterdayBalance;
    @BindView(R.id.txt_today_recharge)  TextView txtTodayRecharge;
    @BindView(R.id.txt_yesterday_recharge)  TextView txtYesterdayRecharge;
    @BindView(R.id.txt_today_member)  TextView txtTodayMember;
    @BindView(R.id.txt_yesterday_member)  TextView txtYesterdayMember;
    @BindView(R.id.txt_today_withdraw)  TextView txtTodayWithdraw;
    @BindView(R.id.txt_yesterday_withdraw)  TextView txtYesterdayWithdraw;

    @BindView(R.id.rl_product_data)  RelativeLayout rlProductData;
    @BindView(R.id.rl_repayment_info)  RelativeLayout rlRepaymentInfo;
    @BindView(R.id.rl_finance_info)  RelativeLayout rlFinanceInfo;
    @BindView(R.id.rl_user_count)  RelativeLayout rlUserCount;

    private CountPresenter presenter;
    @Override
    protected int getLayoutId() {
        return R.layout.main_tab_count;
    }

    @Override
    protected void initViews(LayoutInflater inflater,View self, Bundle savedInstanceState){
        presenter = new CountPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void initListeners() {
        rlProductData.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick())return;
            startActivity(new Intent(getContext(), ProductDataActivity.class));
        });

        rlRepaymentInfo.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick())return;
            startActivity(new Intent(getContext(), RepaymentInfoActivity.class));
        });

        rlFinanceInfo.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick())return;
            startActivity(new Intent(getContext(), FinanceInfoActivity.class));
        });

        rlUserCount.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick())return;
            startActivity(new Intent(getContext(), UserCountActivity.class));
        });
    }

    @Override
    protected void initData() {
        presenter.getPlatformData();
    }

    @Override
    protected void lazyLoad() {

    }

    public static Fragment newInstance(String content) {
        CountFragment fragment = new CountFragment();
        return fragment;
    }

    @Override
    public void onFailure(Throwable e) {
        error(e);
    }

    @Override
    public void onSuccess(PlatformData data) {
        txtAvailableBalance.setText(data.getAvailableBalance());
        txtYesterdayBalance.setText("昨日 "+data.getYesterdayAvailableBalance());
        txtTodayRecharge.setText(data.getRechargeAmount());
        txtYesterdayRecharge.setText("昨日 "+data.getYesterdayRechargeAmount());
        txtTodayMember.setText(data.getMemberNum());
        txtYesterdayMember.setText("昨日 "+data.getYesterdayMemberNum());
        txtTodayWithdraw.setText(data.getWithdrawAmount());
        txtYesterdayWithdraw.setText("昨日 "+data.getYesterdayWithdrawAmount());
    }
}
