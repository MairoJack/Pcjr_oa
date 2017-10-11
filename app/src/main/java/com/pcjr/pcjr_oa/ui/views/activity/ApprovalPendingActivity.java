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

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Approval;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.ClassifySection;
import com.pcjr.pcjr_oa.core.BaseDropDownActivity;
import com.pcjr.pcjr_oa.ui.adapter.ApprovalAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  审批列表
 *  Created by Mario on 2017/9/13上午9:35
 */
public class ApprovalPendingActivity extends BaseDropDownActivity implements SwipeRefreshLayout.OnRefreshListener,SearchView.OnQueryTextListener{


    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    
    private SearchView searchView;
    
    private ApprovalAdapter adapter;
    private List<Approval> list;

    @Override
    protected int getLayoutId() {
        return R.layout.simple_swipe_dropdown_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("待办审批");

        initGridPop();

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ApprovalAdapter();
        mRecyclerView.setAdapter(adapter);
        
    }


    @Override
    protected void initListeners() {
        mPopTop.setOnDismissListener(() -> {
            showToast(closeGridPop());
        });

        adapter.setOnItemClickListener((adapter,view,position)-> {
            //startActivity(new Intent(this,ApprovalEditActivity.class));
        });

    }

    @Override
    protected void initData() {
        classifySectionList = new ArrayList<>();
        ClassifySection cs = new ClassifySection(true, "类型");
        classifySectionList.add(cs);
        Classify c = new Classify("全部审批",0);
        c.setSelected(true);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("通用审批",0);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("立项审批",0);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("担保审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("贷后审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("请假审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("调休审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("加班",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("外出审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("出差审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("用车审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("用印审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        cs = new ClassifySection(true, "审批排序");
        classifySectionList.add(cs);
        c = new Classify("按到期时间",1);
        c.setSelected(true);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按反馈时间",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按申请时间",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按申请人",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        positions = new int[]{1,14};
        initGridPopData();

        list = new ArrayList<>();
        Approval p = new Approval("审批名称审批名称审批名称审批名","杜拉拉",1501055624,0);
        list.add(p);
        p = new Approval("名字显示审批表申请人","杜拉拉",1501055624,1);
        list.add(p);
        p = new Approval("审批名称审批名称审批名称审批名","杜拉拉",1501055624,0);
        list.add(p);
        p= new Approval("名字显示审批表申请人","杜拉拉",1501055624,0);
        list.add(p);
        p= new Approval("审批名称审批名称审批名称审批名","杜拉拉",1501055624,0);
        list.add(p);
        p= new Approval("名字显示审批表申请人","杜拉拉",1501055624,0);
        list.add(p);
        p= new Approval("审批名称审批名称审批名称审批名","杜拉拉",1501055624,0);
        list.add(p);
        p= new Approval("名字显示审批表申请人","杜拉拉",1501055624,1);
        list.add(p);
        p= new Approval("审批名称审批名称审批名称审批名","杜拉拉",1501055624,0);
        list.add(p);
        p= new Approval("名字显示审批表申请人","杜拉拉",1501055624,1);
        list.add(p);
        adapter.setNewData(list);
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
