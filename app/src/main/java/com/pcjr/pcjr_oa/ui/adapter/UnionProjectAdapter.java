package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Project;
import com.pcjr.pcjr_oa.utils.DateUtils;

/**
 * Created by mario on 2017/7/25.
 */

public class UnionProjectAdapter extends BaseQuickAdapter<Project, BaseViewHolder> {

    public UnionProjectAdapter() {
        super(R.layout.item_union_project);
    }

    @Override
    protected void convert(BaseViewHolder helper, Project item) {
        helper.setText(R.id.txt_title, item.getTitle());
        helper.setText(R.id.txt_content, item.getContent());
        if(item.isSelected()) {
            helper.setVisible(R.id.img_selected, true);
        }else{
            helper.setVisible(R.id.img_selected, false);
        }

    }

}
