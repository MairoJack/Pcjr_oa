package com.pcjr.pcjr_oa.ui.views.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.CategorySection;
import com.pcjr.pcjr_oa.bean.CategoryTask;
import com.pcjr.pcjr_oa.core.BaseFragment;
import com.pcjr.pcjr_oa.ui.adapter.CategoryTaskAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 *  任务
 *  Created by Mario on 2017/7/31下午2:36
 */
public class CategoryTaskFragment extends BaseFragment {


    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private List<CategorySection> list;
    private CategoryTaskAdapter adapter;
    private View notDataView;



    @Override
    protected int getLayoutId() {
        return R.layout.category_task;
    }

    @Override
    protected void initViews(LayoutInflater inflater, View self, Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        notDataView = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);
    }

    @Override
    protected void initListeners() {
        adapter.setOnItemClickListener((adapter,view,position)-> {
           showToast(position+"");
        });
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        CategorySection cs = new CategorySection(true,"任务1");
        list.add(cs);
        CategoryTask c = new CategoryTask("杜拉拉",1501055624,"容内容内容内容容内容内容内容容内容内容内容容内容内容内容容");
        cs = new CategorySection(c);
        list.add(cs);
        c = new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cs = new CategorySection(c);
        list.add(cs);
        c = new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cs = new CategorySection(c);
        list.add(cs);
        c = new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cs = new CategorySection(c);
        list.add(cs);
        c = new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cs = new CategorySection(c);
        list.add(cs);
        c = new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cs = new CategorySection(c);
        list.add(cs);
        c = new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cs = new CategorySection(c);
        list.add(cs);
        c = new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cs = new CategorySection(c);
        list.add(cs);
        c = new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cs = new CategorySection(c);
        list.add(cs);
        c = new CategoryTask("杜拉拉2",1501055624,"容2内容2内容内容容内2容内2容内容容2内容内容内容容内2容内容内2容容");
        cs = new CategorySection(c);
        list.add(cs);
        adapter = new CategoryTaskAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void lazyLoad() {

    }


    public static Fragment newInstance(CategoryTask object) {
        CategoryTaskFragment fragment = new CategoryTaskFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", object);
        fragment.setArguments(bundle);
        return fragment;
    }


}
