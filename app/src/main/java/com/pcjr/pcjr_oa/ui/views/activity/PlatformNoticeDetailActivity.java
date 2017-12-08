package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;


import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.PlatformNotice;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.utils.DateUtils;

import butterknife.BindView;

/**
 *  平台公告详情
 *  Created by Mario on 2017/7/26下午3:59
 */
public class PlatformNoticeDetailActivity extends BaseToolbarActivity {
    @BindView(R.id.txt_title) TextView title;
    @BindView(R.id.txt_content) TextView content;
    @BindView(R.id.txt_time) TextView time;
    @BindView(R.id.txt_author) TextView author;
    @BindView(R.id.txt_author_time) TextView authorTime;

    @Override
    protected int getLayoutId() {
        return R.layout.platform_notice_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("平台公告");
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        PlatformNotice object = (PlatformNotice) intent.getSerializableExtra("data");
        title.setText(object.getTitle());
        time.setText(DateUtils.longTimeToStr(object.getSend_date(),DateUtils.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS));
        content.setText(object.getContent());
        author.setText(object.getAuthor());
        authorTime.setText(DateUtils.longTimeToStr(object.getSend_date(),DateUtils.DATE_FORMAT_NIAN_YUE_RI));

    }
}
