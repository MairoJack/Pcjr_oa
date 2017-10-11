package com.pcjr.pcjr_oa.ui.views.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseFragment;
import com.pcjr.pcjr_oa.ui.views.activity.MyFollowActivity;
import com.pcjr.pcjr_oa.ui.views.activity.PlatformNoticeActivity;
import com.pcjr.pcjr_oa.ui.views.activity.ScheduleListActivity;

import butterknife.BindView;

/**
 *  消息
 *  Created by Mario on 2017/7/20上午11:14
 */
public class MsgFragment extends BaseFragment {

    public static final String TAG = MsgFragment.class.getSimpleName();

    @BindView(R.id.title) protected TextView mTitle;

    @BindView(R.id.rl_pending_task) protected RelativeLayout pendingTask;
    @BindView(R.id.rl_pending_item) protected RelativeLayout pendingItem;
    @BindView(R.id.rl_platform_notice) protected RelativeLayout platformNotice;
    @BindView(R.id.rl_my_schedule) protected RelativeLayout mySchedule;
    @BindView(R.id.rl_my_follow) protected RelativeLayout myFollow;

    @Override
    protected int getLayoutId() {
        return R.layout.main_tab_msg;
    }

    @Override
    protected void initViews(LayoutInflater inflater,View self, Bundle savedInstanceState) {
        mTitle.setText("消息中心");
    }

    @Override
    protected void initListeners() {
        platformNotice.setOnClickListener(v-> startActivity(new Intent(getContext(), PlatformNoticeActivity.class)));
        mySchedule.setOnClickListener(v-> startActivity(new Intent(getContext(), ScheduleListActivity.class)));
        myFollow.setOnClickListener(v-> startActivity(new Intent(getContext(), MyFollowActivity.class)));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void lazyLoad() {

    }

    public static Fragment newInstance(String content) {
        MsgFragment fragment = new MsgFragment();
        return fragment;
    }


}
