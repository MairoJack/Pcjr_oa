package com.pcjr.pcjr_oa.ui.views.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.ClassifySection;
import com.pcjr.pcjr_oa.bean.Schedule;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.ScheduleAdapter;
import com.pcjr.pcjr_oa.ui.decorator.DotDecorator;
import com.pcjr.pcjr_oa.ui.decorator.DotGrayDecorator;
import com.pcjr.pcjr_oa.widget.PopTopDialog;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 *  日程列表
 *  Created by Mario on 2017/8/10上午11:15
 */
public class ScheduleListActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    @BindView(R.id.btn_down) ImageView btnDown;
    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.calendarView) MaterialCalendarView calendarView;

    private SearchView searchView;

    private List<Schedule> list;
    private ScheduleAdapter adapter;
    private View notDataView;

    private PopTopDialog.Builder builder;

    @Override
    protected int getLayoutId() {
        return R.layout.schedule;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("日程");

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);

        adapter = new ScheduleAdapter();
        mRecyclerView.setAdapter(adapter);

        DotGrayDecorator dotGrayDecorator = new DotGrayDecorator();
        calendarView.addDecorator(dotGrayDecorator);
    }


    @Override
    protected void initListeners() {
        btnDown.setOnClickListener(v->{
            builder.show();
            backgroundAlpha(0.7f);
        });

        adapter.setOnItemClickListener((adapter,view,position)-> {
            startActivity(new Intent(this,ScheduleEditActivity.class));
        });
    }

    @Override
    protected void initData() {

        List<ClassifySection> classifySectionList = new ArrayList<>();
        ClassifySection cs = new ClassifySection(true, "项目分类");
        classifySectionList.add(cs);
        Classify c = new Classify("直接下属",0);
        c.setSelected(true);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("间接下属",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我关注的人",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("部门选择",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);

        cs = new ClassifySection(true, "类型");
        classifySectionList.add(cs);
        c = new Classify("日程",1);
        c.setSelected(true);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("任务",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("项目",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);

        builder = new PopTopDialog.Builder(this, PopTopDialog.TYPE.GRID)
                .setGridData(classifySectionList)
                .setPositions(new int[]{1,6})
                .setDropDownBtn(btnDown)
                .setOnCloseListener(result -> {
                    showToast(result);
                    backgroundAlpha(1f);
                }).create();

        DotDecorator dot = new DotDecorator();
        dot.setDate(new Date(1502380800000l));
        calendarView.addDecorator(dot);

        mSwipeRefreshLayout.post(()->mSwipeRefreshLayout.setRefreshing(true));
        onRefresh();


    }

    private void getData(){
        list = new ArrayList<>();

        Schedule p = new Schedule();
        p.setTime(1501055624);
        p.setTitle("标题标题标题标题标题1");
        p.setContent("内容内1容容");
        list.add(p);

        p = new Schedule();
        p.setTime(1501055624);
        p.setTitle("标题标题标题标题标题12");
        p.setContent("容内容2内容");
        list.add(p);
        adapter.setNewData(list);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_add,menu);
        MenuItem menuItem = menu.findItem(R.id.btn_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("搜索任务");
        searchView.setIconifiedByDefault(true);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_add){
            startActivity(new Intent(this,ScheduleAddActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) finish();
        return false;
    }
}
