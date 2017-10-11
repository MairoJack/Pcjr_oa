package com.pcjr.pcjr_oa.ui.views.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.StaffCompany;
import com.pcjr.pcjr_oa.core.BaseAppCompatActivity;
import com.pcjr.pcjr_oa.ui.adapter.TabFragmentAdapter;
import com.pcjr.pcjr_oa.ui.views.fragment.StaffCompanyFragment;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  员工详情
 *  Created by Mario on 2017/7/27下午4:57
 */
public class StaffDetailActivity extends BaseAppCompatActivity{

    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.tab_viewpager) ViewPager viewPager;

    private FragmentPagerAdapter fragmentPagerAdapter;

    private List<Fragment> fragmentList;
    private List<String> titleList;

    private List<StaffCompany> list;
    @Override
    protected int getLayoutId() {
        return R.layout.staff_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
//        list = new ArrayList<>();
//        StaffCompany s = new StaffCompany("皮革城总公司","部门1","职位1","在职","0574-1213131","12131@qq.com");
//        list.add(s);
//        s = new StaffCompany("皮城金融","部门2","职位2","离职","0574-3232323","3232323@qq.com");
//        list.add(s);
//
//        for(StaffCompany sc:list){
//            fragmentList.add(StaffCompanyFragment.newInstance(sc));
//            titleList.add(sc.getCompany_name());
//            tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
//        }
//        fragmentPagerAdapter = new TabFragmentAdapter(getSupportFragmentManager(),fragmentList,titleList);
//        viewPager.setAdapter(fragmentPagerAdapter);
//        viewPager.setOffscreenPageLimit(list.size());
//        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK ) finish();
        return false;

    }
}
