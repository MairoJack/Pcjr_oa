package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.WorkItem;

import java.util.List;

/**
 * Created by mario on 2017/7/25.
 */

public class WorkSelectedAdapter extends BaseItemDraggableAdapter<WorkItem, BaseViewHolder> {

    public WorkSelectedAdapter(List<WorkItem> list) {
        super(R.layout.item_work_selected, list);
    }
    @Override
    protected void convert(BaseViewHolder helper, WorkItem item) {
        helper.setText(R.id.txt_work_name,item.getName());
        helper.setImageResource(R.id.img_work,item.getImg());
        helper.addOnClickListener(R.id.btn_delete);
    }
}
