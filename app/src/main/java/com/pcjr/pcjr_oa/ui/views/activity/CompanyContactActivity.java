package com.pcjr.pcjr_oa.ui.views.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.TabFragmentAdapter;
import com.pcjr.pcjr_oa.ui.views.fragment.CompanyContactDepartmentFragment;
import com.pcjr.pcjr_oa.ui.views.fragment.CompanyContactJobFragment;
import com.pcjr.pcjr_oa.ui.views.fragment.CompanyContactStaffFragment;
import com.pcjr.pcjr_oa.widget.PopTopDialog;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 *  公司通讯录
 *  Created by Mario on 2017/8/9下午2:05
 */
public class CompanyContactActivity extends BaseToolbarActivity {

    @BindView(R.id.btn_down) ImageView btnDown;
    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.tab_viewpager) ViewPager viewPager;

    private FragmentPagerAdapter fragmentPagerAdapter;

    private List<Fragment> fragmentList;
    private List<String> titleList;

    private PopTopDialog.Builder builder;
    @Override
    protected int getLayoutId() {
        return R.layout.company_contact;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("皮城金融");
        showBack();

        mImmersionBar.titleBar(mToolbar).init();
        fragmentList = new ArrayList<>();
        fragmentList.add(CompanyContactStaffFragment.newInstance());
        fragmentList.add(CompanyContactDepartmentFragment.newInstance());
        fragmentList.add(CompanyContactJobFragment.newInstance());

        titleList = new ArrayList<>();
        titleList.add("人员");
        titleList.add("部门");
        titleList.add("职位");

        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(2)));

        fragmentPagerAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    protected void initListeners() {
        btnDown.setOnClickListener(v->{
            builder.show();
            backgroundAlpha(0.7f);
        });
    }


    @Override
    protected void initData() {
        List<Classify> classifyList = new ArrayList<>();
        Classify c = new Classify("海宁中国皮革城互联网金融服务有限公司",0);
        classifyList.add(c);
        c = new Classify("海宁中国皮革城股份有限公司",1);
        classifyList.add(c);
        c = new Classify("海宁中国皮革城互联网络科技有限公司",2);
        classifyList.add(c);

        builder = new PopTopDialog.Builder(this, PopTopDialog.TYPE.LIST)
                .setListData(classifyList)
                .setDropDownBtn(btnDown)
                .setOnCloseListener(result -> {
                    showToast(result);
                    backgroundAlpha(1f);
                }).create();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) finish();
        return false;

    }

}
