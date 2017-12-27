package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.Tag;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.widget.Dialog;
import com.pcjr.pcjr_oa.widget.PopTopDialog;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 *  标签列表
 *  Created by Mario on 2017/8/7上午11:04
 */
public class TagListActivity extends BaseToolbarActivity implements SearchView.OnQueryTextListener{


    @BindView(R.id.btn_down) ImageView btnDown;
    @BindView(R.id.flow_layout) TagFlowLayout flowLayout;

    @BindView(R.id.btn_ok) Button btnOk;
    @BindView(R.id.check_all) CheckBox checkAll;
    @BindView(R.id.txt_check_result) TextView txtCheckResult;

    private List<Tag> tagList;
    private SearchView searchView;
    private TagAdapter tagAdapter;
    private int tagNumber;

    private PopTopDialog.Builder builder;
    @Override
    protected int getLayoutId() {
        return R.layout.tag_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("标签");
    }

    @Override
    protected void initListeners() {

        btnDown.setOnClickListener(v->{
            builder.show();
            backgroundAlpha(0.7f);
        });

        checkAll.setOnCheckedChangeListener((compoundButton,b)-> {
            if(b){
                tagNumber = tagList.size();
                Set<Integer> sets = new HashSet<>();
                for(int i = 0 ; i < tagList.size();i++){
                    sets.add(i);
                }
                tagAdapter.setSelectedList(sets);
                txtCheckResult.setText(Html.fromHtml("已选择<font color='#f54246'>"+tagNumber+"</font>项"));
            }else{
                if(tagNumber == 0 || tagNumber == tagList.size()) {
                    tagNumber = 0;
                    tagAdapter.setSelectedList((Set<Integer>) null);
                }
                txtCheckResult.setText(Html.fromHtml("已选择<font color='#f54246'>"+tagNumber+"</font>项"));
            }
        });

        btnOk.setOnClickListener(v->{
            if(tagNumber == 0){
                Dialog.show("请选择标签",this);
            }else {
                Intent intent = new Intent();
                intent.putExtra("result", tagNumber);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    @Override
    protected void initData() {
        List<Classify> classifyList = new ArrayList<>();
        Classify c = new Classify("全部标签",0);
        classifyList.add(c);
        c = new Classify("我的标签",1);
        classifyList.add(c);
        c = new Classify("公告标签",2);
        classifyList.add(c);

        builder = new PopTopDialog.Builder(this, PopTopDialog.TYPE.LIST)
                .setListData(classifyList)
                .setDropDownBtn(btnDown)
                .setOnCloseListener(result -> {
                    showToast(result);
                    backgroundAlpha(1f);
                }).create();


        tagList = new ArrayList<>();
        Tag tag = new Tag("我们哭了");
        tag.setSelected(true);
        tagList.add(tag);
        tag = new Tag("我们笑着望天望天望天");
        tagList.add(tag);
        tag = new Tag("我们抬头望天空");
        tagList.add(tag);
        tag = new Tag("我们还亮着几颗");
        tagList.add(tag);
        tag = new Tag("我们");
        tagList.add(tag);
        tag = new Tag("我们的歌得得得得得得");
        tagList.add(tag);
        tag = new Tag("我们才懂得拥抱");
        tagList.add(tag);
        tag = new Tag("我们到底是什么");
        tagList.add(tag);

        tagAdapter = new TagAdapter<Tag>(tagList) {
            @Override
            public View getView(FlowLayout parent, int position, Tag tag) {
                TextView tv = (TextView) LayoutInflater.from(TagListActivity.this).inflate(R.layout.item_tag,
                        flowLayout, false);
                tv.setText(tag.getName());
                return tv;
            }
        };

        flowLayout.setAdapter(tagAdapter);
        flowLayout.setOnSelectListener(selectPosSet-> {
            tagNumber = selectPosSet.size();
            txtCheckResult.setText(Html.fromHtml("已选择<font color='#f54246'>"+tagNumber+"</font>项"));
            if(tagNumber == tagList.size()){
                checkAll.setChecked(true);
            } else {
                checkAll.setChecked(false);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) finish();
        return false;

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tag,menu);
        MenuItem menuItem = menu.findItem(R.id.btn_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("搜索标签");
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

        }
        return super.onOptionsItemSelected(item);

    }
}
