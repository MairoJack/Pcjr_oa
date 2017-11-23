package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.utils.DateUtils;
import com.pcjr.pcjr_oa.widget.Dialog;

import butterknife.BindView;


/**
 *  设置统计起止日期
 *  Created by Mario on 2017/10/9下午3:16
 */
public class CountDateSettingActivity extends BaseToolbarActivity {

    @BindView(R.id.rl_start_time) RelativeLayout rlStartTime;
    @BindView(R.id.rl_end_time) RelativeLayout rlEndTime;

    @BindView(R.id.txt_start_time) TextView txtStartTime;
    @BindView(R.id.txt_end_time) TextView txtEndTime;


    @Override
    protected int getLayoutId() {
        return R.layout.count_date_setting;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("设置统计起止日期");
        showBack();
    }

    @Override
    protected void initListeners() {

        rlStartTime.setOnClickListener(v-> Dialog.dateYMDPicker(this,"开始时间",txtStartTime));

        rlEndTime.setOnClickListener(v->Dialog.dateYMDPicker(this,"结束时间",txtEndTime));
    }

    @Override
    protected void initData() {

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ok,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_ok){
            String startDate = txtStartTime.getText().toString().trim();
            String endDate = txtEndTime.getText().toString().trim();
            if(startDate.equals("请选择")){
                Dialog.show("请设置开始日期",this);
                return false;
            }
            if(endDate.equals("请选择")){
                Dialog.show("请设置结束日期",this);
                return false;
            }
            if(DateUtils.getDaysOfTowDiffDate(startDate,endDate) > 31){
                Dialog.show("起止日期范围不能超过一个月",this);
                return false;
            }
            Intent intent = new Intent();
            intent.putExtra("startDate",startDate);
            intent.putExtra("endDate",endDate);
            setResult(RESULT_OK,intent);
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
