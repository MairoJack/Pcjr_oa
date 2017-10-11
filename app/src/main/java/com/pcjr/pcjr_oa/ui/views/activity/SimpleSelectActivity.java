package com.pcjr.pcjr_oa.ui.views.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.SelectItem;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.SimpleSelectAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 *  简单选择
 *  Created by Mario on 2017/9/12上午9:16
 */
public class SimpleSelectActivity extends BaseToolbarActivity {


    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private List<SelectItem> list;
    private SimpleSelectAdapter adapter;
    private int lastPosition = 0;

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
        adapter.setOnItemClickListener((adapter,view,position)-> {
            SelectItem lastItem = list.get(lastPosition);
            lastItem.setSelected(false);
            adapter.notifyItemChanged(lastPosition,lastItem);

            SelectItem selectedItem = list.get(position);
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
        list = new ArrayList<>();
        String[] data = getIntent().getStringArrayExtra("data");
        String select = getIntent().getStringExtra("select");
        String title = getIntent().getStringExtra("title");
        setTitle(title);
        for(int i = 0; i < data.length; i++){
            String s = data[i];
            SelectItem si = new SelectItem(s);
            if(s.equals(select)){
                lastPosition = i;
                si.setSelected(true);
            }
            list.add(si);
        }
        adapter = new SimpleSelectAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }
 }
