package com.pcjr.pcjr_oa.ui.views.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.StaffCompany;
import com.pcjr.pcjr_oa.bean.WorkItem;
import com.pcjr.pcjr_oa.bean.WorkSection;
import com.pcjr.pcjr_oa.core.BaseFragment;
import com.pcjr.pcjr_oa.ui.adapter.SectionWorkAdapter;
import com.pcjr.pcjr_oa.ui.decorator.DividerGridItemDecoration;
import com.pcjr.pcjr_oa.ui.presenter.WorkPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.WorkView;
import com.pcjr.pcjr_oa.ui.views.activity.ContractListActivity;
import com.pcjr.pcjr_oa.ui.views.activity.ApprovalActivity;
import com.pcjr.pcjr_oa.ui.views.activity.ContactListActivity;
import com.pcjr.pcjr_oa.ui.views.activity.CustomerListActivity;
import com.pcjr.pcjr_oa.ui.views.activity.ProjectManagerActivity;
import com.pcjr.pcjr_oa.ui.views.activity.SearchActivity;
import com.pcjr.pcjr_oa.ui.views.activity.TaskManagerActivity;
import com.pcjr.pcjr_oa.ui.views.activity.WorkSettingActivity;
import com.pcjr.pcjr_oa.widget.PopTopDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 工作
 * Created by Mario on 2017/7/20上午11:14
 */
public class WorkFragment extends BaseFragment implements WorkView {

    public static final String TAG = WorkFragment.class.getSimpleName();

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.btn_search) ImageView btnSearch;
    @BindView(R.id.btn_down) ImageView btnDown;

    private List<WorkSection> mDatas;
    private SectionWorkAdapter adapter;

    protected ImmersionBar mImmersionBar;

    private WorkPresenter presenter;

    private PopTopDialog.Builder builder;

    @Override
    protected int getLayoutId() {
        return R.layout.main_tab_work;
    }

    @Override
    protected void initViews(LayoutInflater inflater, View self, Bundle savedInstanceState) {


        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.colorPrimary).init();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(4, 1, false));


    }

    @Override
    protected void initListeners() {

        btnDown.setOnClickListener(v -> {
            builder.show();
            backgroundAlpha(0.7f);
        });

        btnSearch.setOnClickListener(v -> startActivity(new Intent(getContext(), SearchActivity.class)));
        adapter.setOnItemClickListener((a, v, p) -> {
            WorkSection object = (WorkSection) a.getItem(p);
            if (!object.isHeader) {
                switch (object.t.getName()) {
                    case "客户管理":
                        startActivity(new Intent(getContext(), CustomerListActivity.class));
                        break;
                    case "客户联系人":
                        startActivity(new Intent(getContext(), ContactListActivity.class));
                        break;
                    case "合同管理":
                        startActivity(new Intent(getContext(), ContractListActivity.class));
                        break;
                    case "审批":
                        startActivity(new Intent(getContext(), ApprovalActivity.class));
                        break;
                    case "任务管理":
                        startActivity(new Intent(getContext(), TaskManagerActivity.class));
                        break;
                    case "项目管理":
                        startActivity(new Intent(getContext(), ProjectManagerActivity.class));
                        break;
                    case "应用设置":
                        startActivity(new Intent(getContext(), WorkSettingActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {

        presenter = new WorkPresenter();
        presenter.attachView(this);

        presenter.getStaffCompanyList();

        mDatas = new ArrayList<>();
        WorkSection workSection = new WorkSection(true, "常用应用");
        mDatas.add(workSection);

        WorkItem workItem = new WorkItem();
        workItem.setName("客户管理");
        workItem.setImg(R.mipmap.icon_work_custom);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("客户联系人");
        workItem.setImg(R.mipmap.icon_product);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("合同管理");
        workItem.setImg(R.mipmap.icon_make_task);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("审批");
        workItem.setImg(R.mipmap.icon_project);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("日程安排");
        workItem.setImg(R.mipmap.icon_product);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("任务管理");
        workItem.setImg(R.mipmap.icon_repayment);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("项目管理");
        workItem.setImg(R.mipmap.icon_urge);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("应用设置");
        workItem.setImg(R.mipmap.icon_personal_setting);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workSection = new WorkSection(true, "协作");
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("日程管理");
        workItem.setImg(R.mipmap.icon_work_front_man);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("任务管理");
        workItem.setImg(R.mipmap.icon_work_front_man);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("项目管理");
        workItem.setImg(R.mipmap.icon_work_front_man);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("文件夹");
        workItem.setImg(R.mipmap.icon_work_front_man);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workSection = new WorkSection(true, "CRM");
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("客户管理");
        workItem.setImg(R.mipmap.icon_work_front_man);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("客户联系人");
        workItem.setImg(R.mipmap.icon_work_front_man);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        workItem = new WorkItem();
        workItem.setName("合同管理");
        workItem.setImg(R.mipmap.icon_work_front_man);
        workSection = new WorkSection(workItem);
        mDatas.add(workSection);

        adapter = new SectionWorkAdapter(mDatas);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void lazyLoad() {

    }

    public static Fragment newInstance(String content) {
        WorkFragment fragment = new WorkFragment();
        return fragment;
    }

    @Override
    public void onFailure(Throwable e) {
        error(e);
    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onStaffCompanyListSuccess(BaseBean<List<StaffCompany>> data) {
        List<StaffCompany> children = data.getData().get(0).getChildren();
        List<Classify> classifyList = new ArrayList<>();
        for (StaffCompany sc : children) {
            Classify c = new Classify(sc.getCompany(), sc.getId());
            classifyList.add(c);
        }
        builder = new PopTopDialog.Builder(getContext(), PopTopDialog.TYPE.LIST);
        builder.setListData(classifyList)
                .setDropDownBtn(btnDown)
                .setOnCloseListener(result -> {
                    showToast(result);
                    backgroundAlpha(1f);
                }).create();
    }
}
