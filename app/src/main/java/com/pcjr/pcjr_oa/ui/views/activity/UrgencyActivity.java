package com.pcjr.pcjr_oa.ui.views.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Urgency;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.UrgencyAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;

/**
 *  紧急程度
 *  Created by Mario on 2017/8/16下午4:33
 */
public class UrgencyActivity extends BaseToolbarActivity {


    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private List<Urgency> list;
    private UrgencyAdapter adapter;
    private int lastPosition = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.simple_select_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("紧急程度");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initListeners() {
        adapter.setOnItemClickListener((adapter,view,position)-> {
            Urgency lastItem = list.get(lastPosition);
            lastItem.setSelected(false);
            adapter.notifyItemChanged(lastPosition,lastItem);

            Urgency selectedItem = list.get(position);
            selectedItem.setSelected(true);
            adapter.notifyItemChanged(position,selectedItem);
            lastPosition = position;

            Intent intent = new Intent();
            intent.putExtra("result",selectedItem.getName());
            setResult(RESULT_OK,intent);
            finish();
        });
    }

    @Override
    protected void initData() {
        String level = getIntent().getStringExtra("level");
        if(level.equals("请选择")){
            level = "正常";
        }
        list = new ArrayList<>();

        Urgency u = new Urgency("正常",1);
        list.add(u);

        u = new Urgency("紧急",2);
        list.add(u);

        u = new Urgency("非常紧急",3);
        list.add(u);

        for(Urgency ur : list){
            if(ur.getName().equals(level)) {
                ur.setSelected(true);
            }
        }
        adapter = new UrgencyAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }
 }
