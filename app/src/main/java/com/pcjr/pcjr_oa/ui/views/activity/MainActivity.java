package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseAppCompatActivity;
import com.pcjr.pcjr_oa.ui.adapter.FragmentAdapter;
import com.pcjr.pcjr_oa.widget.FragmentNavigator;


import butterknife.BindView;


public class MainActivity extends BaseAppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    private FragmentNavigator mNavigator;

    private static final int DEFAULT_POSITION = 0;
    private long exitTime = 0;

    BottomNavigationItem msgItem;
    TextBadgeItem number;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        number = new TextBadgeItem()
                .setBorderWidth(2)
                .setBorderColor(Color.WHITE)
                .setBackgroundColorResource(R.color.color_selected)
                .setText("5")
                .setTextColor(Color.WHITE);


        msgItem = new BottomNavigationItem(R.mipmap.icon_bottom_msg_active, "消息")
                .setInactiveIconResource(R.mipmap.icon_bottom_msg)
                .setActiveColorResource(R.color.color_selected)
                .setBadgeItem(number);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);

        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar
                .addItem(msgItem)
                .addItem(new BottomNavigationItem(R.mipmap.icon_bottom_work_active, "工作").setInactiveIconResource(R.mipmap.icon_bottom_work).setActiveColorResource(R.color.color_selected))
                .addItem(new BottomNavigationItem(R.mipmap.icon_bottom_count_active, "统计").setInactiveIconResource(R.mipmap.icon_bottom_count).setActiveColorResource(R.color.color_selected))
                .addItem(new BottomNavigationItem(R.mipmap.icon_bottom_contact_active, "通讯录").setInactiveIconResource(R.mipmap.icon_bottom_contact).setActiveColorResource(R.color.color_selected))
                .addItem(new BottomNavigationItem(R.mipmap.icon_bottom_personal_active, "我的").setInactiveIconResource(R.mipmap.icon_bottom_personal).setActiveColorResource(R.color.color_selected))
                .setFirstSelectedPosition(DEFAULT_POSITION)
                .initialise();

        mNavigator = new FragmentNavigator(getSupportFragmentManager(), new FragmentAdapter(), R.id.layFrame);
        mNavigator.onCreate(savedInstanceState);
        mNavigator.setDefaultPosition(DEFAULT_POSITION);
        mNavigator.showFragment(DEFAULT_POSITION);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {
        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNavigator.onSaveInstanceState(outState);
    }

    @Override
    public void onTabSelected(int position) {
        mNavigator.showFragment(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
