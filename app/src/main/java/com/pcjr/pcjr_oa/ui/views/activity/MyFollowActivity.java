package com.pcjr.pcjr_oa.ui.views.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.ViewGroup;


import com.gyf.barlibrary.ImmersionBar;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.TabFragmentAdapter;
import com.pcjr.pcjr_oa.ui.views.fragment.CategoryTaskFragment;
import com.pcjr.pcjr_oa.widget.BadgeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  我的关注
 *  Created by Mario on 2017/7/31下午2:34
 */
public class MyFollowActivity extends BaseToolbarActivity {

    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.tab_viewpager) ViewPager viewPager;

    private FragmentPagerAdapter fragmentPagerAdapter;

    private List<Fragment> fragmentList;
    private List<String> titleList;
    private List<String> badgeList = new ArrayList<>();

    private List<BadgeView> mBadgeViews;
    private BadgeView badgeView;

    @Override
    protected int getLayoutId() {
        return R.layout.my_follow;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("我的关注");

        fragmentList = new ArrayList<>();
        fragmentList.add(CategoryTaskFragment.newInstance(null));
        fragmentList.add(CategoryTaskFragment.newInstance(null));
        fragmentList.add(CategoryTaskFragment.newInstance(null));
        fragmentList.add(CategoryTaskFragment.newInstance(null));
        fragmentList.add(CategoryTaskFragment.newInstance(null));
        titleList = new ArrayList<>();
        titleList.add("任务");
        titleList.add("项目");
        titleList.add("日程");
        titleList.add("客户");
        titleList.add("文件");

        badgeList.add("");
        badgeList.add("");
        badgeList.add("");
        badgeList.add("");
        badgeList.add("");

        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(4)));

        fragmentPagerAdapter = new TabFragmentAdapter(getSupportFragmentManager(),fragmentList,titleList);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(5);
        tabLayout.setupWithViewPager(viewPager);

        setUpTabBadge();
    }

    @Override
    protected void initListeners() {
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK ) finish();
        return false;

    }


    private void setUpTabBadge() {
        for (int i = 0; i < fragmentList.size(); i++) {
            if(i % 2 == 0){
                badgeView = new BadgeView(this);
                badgeView.setTargetView(((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i));
            }

        }
    }

    @Override
    protected void initImmersionBar(){
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.titleBar(mToolbar);
    }

}
