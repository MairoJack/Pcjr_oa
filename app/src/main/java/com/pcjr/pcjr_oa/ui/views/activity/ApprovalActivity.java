package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.CategorySection;
import com.pcjr.pcjr_oa.bean.CategoryTask;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.ClassifySection;
import com.pcjr.pcjr_oa.bean.WorkItem;
import com.pcjr.pcjr_oa.bean.WorkSection;
import com.pcjr.pcjr_oa.core.BaseDropDownActivity;
import com.pcjr.pcjr_oa.ui.adapter.CategoryTaskAdapter;
import com.pcjr.pcjr_oa.ui.adapter.SectionWorkAdapter;
import com.pcjr.pcjr_oa.ui.decorator.DividerGridItemDecoration;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  审批
 *  Created by Mario on 2017/9/11上午11:30
 */
public class ApprovalActivity extends BaseDropDownActivity implements  SearchView.OnQueryTextListener{


    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    @BindView(R.id.ll_pending_item) LinearLayout llPendingItem;
    @BindView(R.id.ll_submit_item) LinearLayout llSubmitItem;
    @BindView(R.id.ll_share_me) LinearLayout llShareMe;
    @BindView(R.id.ll_finish_item) LinearLayout llFinishItem;

    private SearchView searchView;
    private List<CategorySection> list;
    private SectionWorkAdapter adapter;
    private List<WorkSection> mDatas;

    @Override
    protected int getLayoutId() {
        return R.layout.approval;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("审批");

        initGridPop();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(4,1,false));
    }


    @Override
    protected void initListeners() {
        mPopTop.setOnDismissListener(() -> {
            showToast(closeGridPop());
        });

        llPendingItem.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivity(new Intent(this,ApprovalPendingActivity.class));
        });

        adapter.setOnItemClickListener((a,v,p)->{
            if(ViewUtil.isFastDoubleClick()) return;
            WorkSection object = (WorkSection) a.getItem(p);
            if(!object.isHeader){
                WorkItem work = object.t;
                if(work.getName().equals("请假审批")){
                    startActivity(new Intent(this,LeaveApprovalActivity.class));
                }
            }
        });
    }

    @Override
    protected void initData() {
        classifySectionList = new ArrayList<>();
        ClassifySection cs = new ClassifySection(true, "审批分类");
        classifySectionList.add(cs);
        Classify c = new Classify("全部审批",0);
        c.setSelected(true);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("待办审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("草稿审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我提交的审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("已办审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我关注的审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我共享的审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("下属待办审批",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("回收站",0);
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
        positions = new int[]{1,11};
        initGridPopData();

        mDatas = new ArrayList<>();
        WorkSection workSection = new WorkSection(true,"审批分类一(4)");
        mDatas.add(workSection);

        WorkItem workItem = new WorkItem();
        workItem.setName("通用审批");
        workItem.setImg(R.mipmap.icon_general_item);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("立项审批");
        workItem.setImg(R.mipmap.icon_setup_item);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("担保审批");
        workItem.setImg(R.mipmap.icon_guarantee_item);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("贷后审批");
        workItem.setImg(R.mipmap.icon_daihou_item);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);


        workSection = new WorkSection(true,"审批分类一(7)");
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("请假审批");
        workItem.setImg(R.mipmap.icon_leave);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("调休审批");
        workItem.setImg(R.mipmap.icon_rest);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("加班");
        workItem.setImg(R.mipmap.icon_overtime);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("外出审批");
        workItem.setImg(R.mipmap.icon_out);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("出差审批");
        workItem.setImg(R.mipmap.icon_business_trip);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("用车审批");
        workItem.setImg(R.mipmap.icon_use_car);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("用印审批");
        workItem.setImg(R.mipmap.icon_use_seal);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        adapter = new SectionWorkAdapter(mDatas);
        mRecyclerView.setAdapter(adapter);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_add){
           startActivity(new Intent(this,TaskAddActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }

}
