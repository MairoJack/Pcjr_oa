package com.pcjr.pcjr_oa.ui.views.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.widget.BottomMenuDialog;
import com.pcjr.pcjr_oa.widget.Dialog;

import butterknife.BindView;

/**
 *  任务-提醒设置
 *  Created by Mario on 2017/8/8上午9:13
 */
public class TaskRemindSettingActivity extends BaseToolbarActivity {

    @BindView(R.id.rl_remind_time) RelativeLayout rlRemindTime;
    @BindView(R.id.rl_remind_person) RelativeLayout rlRemindPerson;
    @BindView(R.id.rl_remind_way) RelativeLayout rlRemindWay;

    @BindView(R.id.txt_remind_time) TextView txtRemindTime;
    @BindView(R.id.txt_remind_person) TextView txtRemindPerson;
    @BindView(R.id.txt_remind_way) TextView txtRemindWay;

    private BottomMenuDialog pmd;

    private String[] times = new String[]{"1天", "2天", "3天"};
    private String[] persons = new String[]{"负责人", "参与人", "共享人"};
    private String[] ways = new String[]{"应用内", "短信"};

    private String result = "到期日前$time $ways 提醒$persons";
    private boolean t = false,p = false,w = false;
    @Override
    protected int getLayoutId() {
        return R.layout.remind_setting;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("提醒设置");
    }


    @Override
    protected void initListeners() {

        rlRemindTime.setOnClickListener(v->{
            pmd = new BottomMenuDialog.Builder(TaskRemindSettingActivity.this)
                    .setMenus(times)
                    .setOnItemClickListener((dialog,position)-> {
                        txtRemindTime.setText(times[position]);
                        result = result.replace("$time",times[position]);
                        t = true;
                    }).create();
            pmd.show();
        });

        rlRemindPerson.setOnClickListener(v->{
            pmd = new BottomMenuDialog.Builder(TaskRemindSettingActivity.this)
                    .setMenus(persons)
                    .setOnItemClickListener((dialog,position)-> {
                        txtRemindPerson.setText(persons[position]);
                        result = result.replace("$persons",persons[position]);
                        p = true;
                    }).create();
            pmd.show();
        });

        rlRemindWay.setOnClickListener(v->{
            pmd = new BottomMenuDialog.Builder(TaskRemindSettingActivity.this)
                    .setMenus(ways)
                    .setOnItemClickListener((dialog,position)-> {
                        txtRemindWay.setText(ways[position]);
                        result = result.replace("$ways",ways[position]);
                        w = true;
                    }).create();
            pmd.show();
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ok,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_ok){
            if(!t){
                Dialog.show("您还未选择提醒时间",this);
                return true;
            }
            if(!p){
                Dialog.show("您还未选择提醒人",this);
                return true;
            }
            if(!w){
                Dialog.show("您还未选择提醒方式",this);
                return true;
            }
            if(item.getItemId() == R.id.btn_ok){
                Intent intent = new Intent();
                intent.putExtra("result",result);
                setResult(RESULT_OK,intent);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
