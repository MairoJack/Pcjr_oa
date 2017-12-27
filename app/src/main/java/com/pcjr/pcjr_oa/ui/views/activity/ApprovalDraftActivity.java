package com.pcjr.pcjr_oa.ui.views.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BusinessApproval;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.ClassifySection;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.ApprovalAdapter;
import com.pcjr.pcjr_oa.widget.PopTopDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  草稿审批/回收站审批
 *  Created by Mario on 2017/9/13上午9:35
 */
public class ApprovalDraftActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener,SearchView.OnQueryTextListener{

    @BindView(R.id.btn_down) ImageView btnDown;
    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    
    private SearchView searchView;
    
    private ApprovalAdapter adapter;
    private List<BusinessApproval> list;

    private PopTopDialog.Builder builder;
    @Override
    protected int getLayoutId() {
        return R.layout.simple_swipe_dropdown_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("草稿审批");

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ApprovalAdapter();
        mRecyclerView.setAdapter(adapter);
        
    }


    @Override
    protected void initListeners() {
       btnDown.setOnClickListener(v->{
           builder.show();
           backgroundAlpha(0.7f);
       });

        adapter.setOnItemClickListener((adapter,view,position)-> {
            //startActivity(new Intent(this,ApprovalEditActivity.class));
        });

    }

    @Override
    protected void initData() {
        List<ClassifySection> classifySectionList = new ArrayList<>();
        ClassifySection cs = new ClassifySection(true, "类型");
        classifySectionList.add(cs);
        Classify c = new Classify("全部审批", 0);
        c.setSelected(true);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("业务审批", 0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("上线审批", 0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);

        builder = new PopTopDialog.Builder(this, PopTopDialog.TYPE.GRID)
                .setGridData(classifySectionList)
                .setPositions(new int[]{1})
                .setDropDownBtn(btnDown)
                .setOnCloseListener(result -> {
                    showToast(result);
                    backgroundAlpha(1f);
                }).create();

        list = new ArrayList<>();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem menuItem = menu.findItem(R.id.btn_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("搜索审批");
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
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }
}
