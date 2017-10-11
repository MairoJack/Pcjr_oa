package com.pcjr.pcjr_oa.ui.views.activity;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Button;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.WorkItem;
import com.pcjr.pcjr_oa.bean.WorkSection;
import com.pcjr.pcjr_oa.core.BaseAppCompatActivity;
import com.pcjr.pcjr_oa.ui.adapter.SectionWorkUnselectedAdapter;
import com.pcjr.pcjr_oa.ui.adapter.WorkSelectedAdapter;
import com.pcjr.pcjr_oa.ui.decorator.DividerGridItemDecoration;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  常用应用设置
 *  Created by Mario on 2017/9/14上午9:55
 */
public class WorkSettingActivity extends BaseAppCompatActivity  {


    @BindView(R.id.recycler_view) RecyclerView recyclerViewSelected;
    @BindView(R.id.recycler_view_unselected) RecyclerView recyclerViewUnselected;
    @BindView(R.id.btn_cancel) Button btnCancel;
    @BindView(R.id.btn_finish) Button btnFinish;

    private List<WorkItem> workSelected;
    private WorkSelectedAdapter adapterSelected;

    private List<WorkSection> workUnselected;
    private SectionWorkUnselectedAdapter adapterUnSelected;

    private ItemTouchHelper mItemTouchHelper;
    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;
    private OnItemDragListener listener;

    @Override
    protected int getLayoutId() {
        return R.layout.work_settings;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        recyclerViewSelected.setLayoutManager(new GridLayoutManager(this,4));
        recyclerViewSelected.addItemDecoration(new DividerGridItemDecoration(4,1,false));

        recyclerViewUnselected.setLayoutManager(new GridLayoutManager(this,4));
        recyclerViewUnselected.addItemDecoration(new DividerGridItemDecoration(4,1,false));
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }


    @Override
    protected void initListeners() {

        btnCancel.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick())return;
            finish();
        });

        btnFinish.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick())return;
            finish();
        });

        adapterSelected.setOnItemChildClickListener((a,v,p)->{
            updateUnselectedList((WorkItem) a.getItem(p));
            workSelected.remove(p);
            adapterSelected.notifyItemRemoved(p);
        });

        adapterSelected.setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {

            }
        });

        adapterUnSelected.setOnItemClickListener((a,v,p)->{
            WorkSection ws = (WorkSection) a.getItem(p);
            if(!ws.isHeader){
                WorkItem work = ws.t;
                if(!work.isSelected()){
                    if(workSelected.size()>=7){
                        Dialog.show("常用应用最多添加7个",this);
                        return;
                    }
                    work.setSelected(true);
                    a.notifyItemChanged(p,ws);
                    WorkItem newWork = new WorkItem();
                    newWork.setName(work.getName());
                    newWork.setImg(work.getImg());
                    workSelected.add(newWork);
                    adapterSelected.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void initData() {
        workSelected = new ArrayList<>();
        WorkItem workItem = new WorkItem();
        workItem.setName("客户管理");
        workItem.setImg(R.mipmap.icon_work_custom);
        workSelected.add(workItem);

        workItem = new WorkItem();
        workItem.setName("平台出面人");
        workItem.setImg(R.mipmap.icon_work_front_man);
        workSelected.add(workItem);

        workItem = new WorkItem();
        workItem.setName("担保机构");
        workItem.setImg(R.mipmap.icon_guarantors);
        workSelected.add(workItem);

        workItem = new WorkItem();
        workItem.setName("项目列表");
        workItem.setImg(R.mipmap.icon_project);
        workSelected.add(workItem);

        workItem = new WorkItem();
        workItem.setName("产品列表");
        workItem.setImg(R.mipmap.icon_product);
        workSelected.add(workItem);

        workItem = new WorkItem();
        workItem.setName("还款列表");
        workItem.setImg(R.mipmap.icon_repayment);
        workSelected.add(workItem);

        adapterSelected = new WorkSelectedAdapter(workSelected);

        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapterSelected);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerViewSelected);
        adapterSelected.enableDragItem(mItemTouchHelper);
        recyclerViewSelected.setAdapter(adapterSelected);


        workUnselected = new ArrayList<>();
        WorkSection workSection = new WorkSection(true,"CRM");
        workUnselected.add(workSection);

        WorkItem work = new WorkItem();
        work.setName("客户管理");
        work.setImg(R.mipmap.icon_work_custom);
        work.setSelected(true);
        workSection = new WorkSection(work);
        workUnselected.add(workSection);

        work = new WorkItem();
        work.setName("平台出面人");
        work.setSelected(true);
        work.setImg(R.mipmap.icon_work_front_man);
        workSection = new WorkSection(work);
        workUnselected.add(workSection);

        work = new WorkItem();
        work.setName("担保机构");
        work.setSelected(true);
        work.setImg(R.mipmap.icon_guarantors);
        workSection = new WorkSection(work);
        workUnselected.add(workSection);

        work = new WorkItem();
        work.setName("项目列表");
        work.setSelected(true);
        work.setImg(R.mipmap.icon_project);
        workSection = new WorkSection(work);
        workUnselected.add(workSection);

        work = new WorkItem();
        work.setName("产品列表");
        work.setSelected(true);
        work.setImg(R.mipmap.icon_product);
        workSection = new WorkSection(work);
        workUnselected.add(workSection);

        work = new WorkItem();
        work.setName("还款列表");
        work.setSelected(true);
        work.setImg(R.mipmap.icon_repayment);
        workSection = new WorkSection(work);
        workUnselected.add(workSection);

        work = new WorkItem();
        work.setName("催收记录");
        work.setImg(R.mipmap.icon_urge);
        workSection = new WorkSection(work);
        workUnselected.add(workSection);

        work = new WorkItem();
        work.setName("市场活动");
        work.setImg(R.mipmap.icon_marketing_activity);
        workSection = new WorkSection(work);
        workUnselected.add(workSection);

        workSection = new WorkSection(true,"人力资源");
        workUnselected.add(workSection);

        work = new WorkItem();
        work.setName("考勤管理");
        work.setImg(R.mipmap.icon_work_front_man);
        workSection = new WorkSection(work);
        workUnselected.add(workSection);

        work = new WorkItem();
        work.setName("员工关怀");
        work.setImg(R.mipmap.icon_work_front_man);
        workSection = new WorkSection(work);
        workUnselected.add(workSection);

        work = new WorkItem();
        work.setName("培训管理");
        work.setImg(R.mipmap.icon_work_custom);
        workSection = new WorkSection(work);
        workUnselected.add(workSection);

        work = new WorkItem();
        work.setName("招聘管理");
        work.setImg(R.mipmap.icon_work_front_man);
        workSection = new WorkSection(work);
        workUnselected.add(workSection);

        adapterUnSelected = new SectionWorkUnselectedAdapter(workUnselected);
        recyclerViewUnselected.setAdapter(adapterUnSelected);
    }

    private void updateUnselectedList(WorkItem work){
        for(int i = 0;i < workUnselected.size();i++){
            WorkSection ws = workUnselected.get(i);
            if(!ws.isHeader){
                WorkItem w = ws.t;
                if(w.getName().equals(work.getName())){
                    w.setSelected(false);
                    adapterUnSelected.notifyItemChanged(i,ws);
                    return;
                }
            }
        }
    }
}
