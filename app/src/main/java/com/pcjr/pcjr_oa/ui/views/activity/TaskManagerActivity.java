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

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.CategorySection;
import com.pcjr.pcjr_oa.bean.CategoryTask;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.ClassifySection;
import com.pcjr.pcjr_oa.core.BaseDropDownActivity;
import com.pcjr.pcjr_oa.ui.adapter.CategoryTaskAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 任务管理
 * Created by Mario on 2017/7/31下午2:34
 */
public class TaskManagerActivity extends BaseDropDownActivity implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener{

    @BindView(R.id.tab_layout) TabLayout tabLayout;

    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    
    private SearchView searchView;
    private List<CategorySection> list;
    private CategoryTaskAdapter adapter;
    private List<String> titleList;

    @Override
    protected int getLayoutId() {
        return R.layout.task_manager;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("任务管理");

        initGridPop();

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        
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
        mPopTop.setOnDismissListener(() -> {
            showToast(closeGridPop());
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

        adapter.setOnItemClickListener((adapter,view,position)-> {
            startActivity(new Intent(this,TaskEditActivity.class));
        });
    }

    @Override
    protected void initData() {
        classifySectionList = new ArrayList<>();
        ClassifySection cs = new ClassifySection(true, "任务分类");
        classifySectionList.add(cs);
        Classify c = new Classify("全部任务",0);
        c.setSelected(true);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我负责的任务",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我参与的任务",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我关注的任务",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("下属的任务",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("分享给我的任务",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我创建的任务",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("参与评论的任务",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        cs = new ClassifySection(true, "任务排序");
        classifySectionList.add(cs);
        c = new Classify("按创建时间",1);
        c.setSelected(true);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按进度更新时间",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按到期时间正序",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按到期时间倒序",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按紧急程度",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        positions = new int[]{1,10};
        initGridPopData();

        list = new ArrayList<>();
        CategorySection cas = new CategorySection(true,"任务1");
        list.add(cas);
        CategoryTask ct = new CategoryTask("杜拉拉",1501055624,"容内容内容内容容内容内容内容容内容内容内容容内容内容内容容");
        cas = new CategorySection(ct);
        list.add(cas);
        ct= new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cas = new CategorySection(ct);
        list.add(cas);
        ct= new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cas = new CategorySection(ct);
        list.add(cas);
        ct= new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cas = new CategorySection(ct);
        list.add(cas);
        ct= new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cas = new CategorySection(ct);
        list.add(cas);
        ct= new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cas = new CategorySection(ct);
        list.add(cas);
        ct= new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cas = new CategorySection(ct);
        list.add(cas);
        ct= new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cas = new CategorySection(ct);
        list.add(cas);
        ct= new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cas = new CategorySection(ct);
        list.add(cas);
        ct= new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cas = new CategorySection(ct);
        list.add(cas);
        adapter = new CategoryTaskAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_manager,menu);
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
           startActivity(new Intent(this,TaskAddActivity.class));
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
