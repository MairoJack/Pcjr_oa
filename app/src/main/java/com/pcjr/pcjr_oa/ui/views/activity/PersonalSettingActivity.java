package com.pcjr.pcjr_oa.ui.views.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Company;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.CompanyAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  个人设置
 *  Created by Mario on 2017/7/26下午3:45
 */
public class PersonalSettingActivity extends BaseToolbarActivity {


    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private List<Company> list;
    private CompanyAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.personal_setting;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("个人设置");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this,1));
    }


    @Override
    protected void initListeners() {
        adapter.setOnItemClickListener((adapter,view,position)-> {
            Intent intent = new  Intent(PersonalSettingActivity.this, CompanyDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", list.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();

        Company p = new Company();
        p.setName("海宁中国皮革城股份有限公司");
        list.add(p);

        p = new Company();
        p.setName("海宁中国皮革城互联网金融服务有限公司");
        list.add(p);
        adapter = new CompanyAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) finish();
        return false;

    }
}
