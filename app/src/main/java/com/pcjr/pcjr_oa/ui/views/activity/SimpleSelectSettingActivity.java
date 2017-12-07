package com.pcjr.pcjr_oa.ui.views.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.IntentSelect;
import com.pcjr.pcjr_oa.bean.SelectItem;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.SimpleSelectSettingAdapter;
import com.pcjr.pcjr_oa.widget.Dialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  简单选择-设置
 *  Created by Mario on 2017/12/7下午3:13
 */
public class SimpleSelectSettingActivity extends BaseToolbarActivity {


    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private List<SelectItem> list;
    private SimpleSelectSettingAdapter adapter;
    private IntentSelect intentSelect;
    @Override
    protected int getLayoutId() {
        return R.layout.simple_select_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initListeners() {

        adapter.setOnItemChildClickListener((a,v,p)-> {
            a.remove(p);
        });
    }

    @Override
    protected void initData() {
        intentSelect = (IntentSelect) getIntent().getSerializableExtra("intentSelect");
        list = new ArrayList<>();
        String[] data = intentSelect.getData();
        setTitle(intentSelect.getTitle()+"设置");
        for(int i = 0; i < data.length; i++){
            String s = data[i];
            SelectItem si = new SelectItem(s);
            list.add(si);
        }
        adapter = new SimpleSelectSettingAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_add) {
            new MaterialDialog.Builder(this)
                    .title("新建"+intentSelect.getTitle())
                    .positiveColor(Color.parseColor("#ff4341"))
                    .negativeText("取消")
                    .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)
                    .input("请输入"+intentSelect.getTitle(), "", (dialog, input) -> {
                        if (input.toString().equals("")) {
                            Dialog.show("请输入"+intentSelect.getTitle(),this);
                        } else {
                            adapter.addData(new SelectItem(input.toString()));
                        }
                    }).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
