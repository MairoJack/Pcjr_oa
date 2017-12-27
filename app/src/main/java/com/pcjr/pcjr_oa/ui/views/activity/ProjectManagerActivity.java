package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.ClassifySection;
import com.pcjr.pcjr_oa.bean.Project;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.ProjectAdapter;
import com.pcjr.pcjr_oa.widget.PopTopDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  项目管理
 *  Created by Mario on 2017/8/17下午2:42
 */
public class ProjectManagerActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener,SearchView.OnQueryTextListener{

    @BindView(R.id.btn_down) ImageView btnDown;
    @BindView(R.id.tab_layout) TabLayout tabLayout;

    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    
    private SearchView searchView;
    
    private ProjectAdapter adapter;
    private List<Project> list;
    private List<String> titleList;

    private PopTopDialog.Builder builder;

    @Override
    protected int getLayoutId() {
        return R.layout.project_manager;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("项目管理");

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectAdapter();
        mRecyclerView.setAdapter(adapter);
        
        titleList = new ArrayList<>();
        titleList.add("进行中");
        titleList.add("已完结");
        titleList.add("未完结");

        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(2)));

    }


    @Override
    protected void initListeners() {
        btnDown.setOnClickListener(v->{
            builder.show();
            backgroundAlpha(0.7f);
        });

        adapter.setOnItemClickListener((adapter,view,position)-> {
            startActivity(new Intent(this,ProjectEditActivity.class));
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                showToast(tab.getPosition() + "");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData() {
        List<ClassifySection> classifySectionList = new ArrayList<>();
        ClassifySection cs = new ClassifySection(true, "项目分类");
        classifySectionList.add(cs);
        Classify c = new Classify("全部项目",0);
        c.setSelected(true);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我负责的项目",0);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我参与的项目",0);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我关注的项目",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("下属的项目",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("分享给我的项目",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我创建的项目",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("参与评论的项目",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        cs = new ClassifySection(true, "项目排序");
        classifySectionList.add(cs);
        c = new Classify("按创建时间",1);
        c.setSelected(true);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按进度更新时间",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按结束时间正序",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按结束时间倒序",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);

        builder = new PopTopDialog.Builder(this, PopTopDialog.TYPE.GRID)
                .setGridData(classifySectionList)
                .setPositions(new int[]{1,10})
                .setDropDownBtn(btnDown)
                .setOnCloseListener(result -> {
                    showToast(result);
                    backgroundAlpha(1f);
                }).create();

        list = new ArrayList<>();
        Project p = new Project("杜拉拉",1501055624,"容内容内容内容容内容内容内容容内容内容内容容内容内容内容容");
        list.add(p);
        p = new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p = new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p= new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p= new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p= new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p= new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p= new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p= new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        p= new Project("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        list.add(p);
        adapter.setNewData(list);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_manager,menu);
        MenuItem menuItem = menu.findItem(R.id.btn_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("搜索项目");
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
           startActivity(new Intent(this,ProjectAddActivity.class));
        }
        return super.onOptionsItemSelected(item);

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
}
