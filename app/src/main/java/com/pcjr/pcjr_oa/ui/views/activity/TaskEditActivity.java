package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Comment;
import com.pcjr.pcjr_oa.bean.Reply;
import com.pcjr.pcjr_oa.ui.adapter.CommentAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 *  编辑任务
 *  Created by Mario on 2017/8/2下午3:42
 */
public class TaskEditActivity extends TaskActivity {

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.btn_confirm) Button btnConfirm;
    @BindView(R.id.ll_feedback) LinearLayout llFeedback;
    @BindView(R.id.ll_urge) LinearLayout llUrge;
    @BindView(R.id.ll_follow) LinearLayout llFollow;
    @BindView(R.id.ll_finish) LinearLayout llFinish;
    @BindView(R.id.ll_more) LinearLayout llMore;

    private List<MultiItemEntity> list;
    private CommentAdapter adapter;

    private BottomSheetDialog moreBottomSheetDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.task_edit;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        initMoreBottomSheetDialog();

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        btnConfirm.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });

        llMore.setOnClickListener(v->{
            moreBottomSheetDialog.show();
        });

        adapter.setOnItemChildClickListener((adapter,view,position)-> {
            Reply reply = (Reply) adapter.getItem(position);
            Intent intent = new Intent(this,CommentDetailActivity.class);
            intent.putExtra("id",reply.getCommentId());
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {

        list = generateData();
        adapter = new CommentAdapter(list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.expandAll();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
            }
        }
    }

    private void initMoreBottomSheetDialog(){
        moreBottomSheetDialog = new BottomSheetDialog(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_bottom_menu_grid, (ViewGroup) findViewById(R.id.dialog));
        ImageView btnCopy = view.findViewById(R.id.btn_copy);
        ImageView btnDelete = view.findViewById(R.id.btn_delete);
        TextView btnCancel = view.findViewById(R.id.btn_cancel);
        moreBottomSheetDialog.setContentView(view);
        moreBottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet)
                .setBackgroundResource(android.R.color.transparent);


        btnCopy.setOnClickListener(v->{
            showToast("copy");
        });

        btnDelete.setOnClickListener(v->{
            showToast("delete");
        });

        btnCancel.setOnClickListener(v->{
            moreBottomSheetDialog.cancel();
        });
    }

    private List<MultiItemEntity> generateData() {

        String[] nameList = {"Bob", "Andy", "Lily", "Brown", "Bruce"};

        String[] subList = {"Mario", "XIAOP", "猪", "啦啦啦啦"};

        List<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < nameList.length; i++) {
            Comment comment = new Comment(nameList[i], "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容 " + nameList[i],1501055624);
            if(i == 0){
                Reply reply = new Reply(subList[0], subList[0]+":回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复");
                comment.addSubItem(reply);
            }
            else if(i == 1){
                Reply reply = new Reply(subList[0], subList[0]+":回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复");
                comment.addSubItem(reply);

                reply = new Reply(subList[1], subList[1]+":回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复");
                comment.addSubItem(reply);
            }
            else {
                for (int j = 0; j < subList.length; j++) {
                    Reply reply;
                    if(j == 2){
                        reply = new Reply(true,i+"");
                        j = subList.length;
                    } else {
                        reply = new Reply(subList[j], subList[j] + ":回复回复回复回复回复回复回复回复回复回复回复回复回复回复回复");
                    }
                    comment.addSubItem(reply);
                }
            }
            res.add(comment);
        }
        return res;
    }
}
