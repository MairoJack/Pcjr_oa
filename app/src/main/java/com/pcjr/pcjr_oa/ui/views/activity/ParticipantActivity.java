package com.pcjr.pcjr_oa.ui.views.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Person;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.ParticipantAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  参与人
 *  Created by Mario on 2017/8/4上午8:54
 */
public class ParticipantActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener,SearchView.OnQueryTextListener {


    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private List<Person> list;
    private ParticipantAdapter adapter;
    private SearchView searchView;
    private int lastPosition = -1;
    private Person selectedItem;
    @Override
    protected int getLayoutId() {
        return R.layout.participant_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("参与人");
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void initListeners() {
        adapter.setOnItemClickListener((adapter,view,position)-> {
            if(lastPosition != -1) {
                Person lastItem = list.get(lastPosition);
                lastItem.setSelected(false);
                adapter.notifyItemChanged(lastPosition,lastItem);
            }
            selectedItem = list.get(position);
            selectedItem.setSelected(true);
            adapter.notifyItemChanged(position,selectedItem);
            lastPosition = position;
        });
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();

        Person p = new Person();
        p.setName("杜拉拉");
        p.setDepartment("部门111111");
        p.setJob("职位11");
        list.add(p);

        p = new Person();
        p.setName("萧拉拉");
        p.setDepartment("部门2222111");
        p.setJob("职位22");
        list.add(p);
        adapter = new ParticipantAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_ok,menu);
        MenuItem menuItem = menu.findItem(R.id.btn_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("搜索参与人");
        searchView.setIconifiedByDefault(true);
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        showToast("onQueryTextSubmit");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        showToast("onQueryTextChange");
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.btn_ok) {
            Intent intent = new Intent();
            intent.putExtra("result",selectedItem.getName());
            setResult(RESULT_OK,intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
