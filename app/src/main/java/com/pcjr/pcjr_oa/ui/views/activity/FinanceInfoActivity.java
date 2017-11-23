package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.TabFragmentAdapter;
import com.pcjr.pcjr_oa.ui.presenter.FinanceInfoWithdrawPresenter;
import com.pcjr.pcjr_oa.ui.views.fragment.FinanceInfoRechargeFragment;
import com.pcjr.pcjr_oa.ui.views.fragment.FinanceInfoWithdrawFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  资金信息
 *  Created by Mario on 2017/10/9下午2:03
 */
public class FinanceInfoActivity extends BaseToolbarActivity {

    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.tab_viewpager) ViewPager viewPager;

    private FragmentPagerAdapter fragmentPagerAdapter;

    private List<Fragment> fragmentList;
    private List<String> titleList;
    private FinanceInfoRechargeFragment financeInfoRechargeFragment;
    private FinanceInfoWithdrawFragment financeInfoWithdrawFragment;
    @Override
    protected int getLayoutId() {
        return R.layout.finance_info;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("资金信息");
        showBack();

        mImmersionBar.titleBar(mToolbar).init();
        fragmentList = new ArrayList<>();
        financeInfoRechargeFragment = (FinanceInfoRechargeFragment) FinanceInfoRechargeFragment.newInstance();
        financeInfoWithdrawFragment = (FinanceInfoWithdrawFragment) FinanceInfoWithdrawFragment.newInstance();
        fragmentList.add(financeInfoRechargeFragment);
        fragmentList.add(financeInfoWithdrawFragment);

        titleList = new ArrayList<>();
        titleList.add("充值");
        titleList.add("提现");

        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));

        fragmentPagerAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    protected void initListeners() {

    }


    @Override
    protected void initData() {

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_date,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_date){
            startActivityForResult(new Intent(this,CountDateSettingActivity.class),Constant.REQUEST_COUNT_DATE);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.REQUEST_COUNT_DATE:
                    String startDate = data.getStringExtra("startDate");
                    String endDate = data.getStringExtra("endDate");
                    financeInfoRechargeFragment.changeDate(startDate,endDate);
                    financeInfoWithdrawFragment.changeDate(startDate,endDate);
                    break;
            }
        }
    }
}
