package com.pcjr.pcjr_oa.ui.views.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.IntentSelect;
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
        adapter.setOnItemClickListener((adapter,view,position)-> {
            SelectItem lastItem = list.get(lastPosition);
            lastItem.setSelected(false);
            adapter.notifyItemChanged(lastPosition,lastItem);

            SelectItem selectedItem = list.get(position);
            selectedItem.setSelected(true);
            adapter.notifyItemChanged(position,selectedItem);
            lastPosition = position;

            Intent intent = new Intent();
            intent.putExtra("result",selectedItem);
            setResult(RESULT_OK,intent);
            finish();
        });
    }

    @Override
    protected void initData() {
        intentSelect = (IntentSelect) getIntent().getSerializableExtra("intentSelect");
        list = new ArrayList<>();
        String[] data = intentSelect.getData();
        setTitle(intentSelect.getTitle());
        for(int i = 0; i < data.length; i++){
            String s = data[i];
            SelectItem si = new SelectItem(s,i);
            if(s.equals(intentSelect.getSelect())){
                lastPosition = i;
                si.setSelected(true);
            }
            list.add(si);
        }
        adapter = new SimpleSelectAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(!intentSelect.isSetting()) {
            for (int i = 0; i < menu.size(); i++){
                menu.getItem(i).setVisible(false);
                menu.getItem(i).setEnabled(false);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_setting){
            Intent intent = new Intent(this,SimpleSelectSettingActivity.class);
            intent.putExtra("intentSelect",intentSelect);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
