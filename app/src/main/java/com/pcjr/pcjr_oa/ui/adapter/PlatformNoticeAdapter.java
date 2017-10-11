package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.PlatformNotice;
import com.pcjr.pcjr_oa.utils.DateUtils;
import java.util.List;

/**
 * Created by mario on 2017/7/25.
 */

public class PlatformNoticeAdapter extends BaseQuickAdapter<PlatformNotice, BaseViewHolder> {


    public PlatformNoticeAdapter(List<PlatformNotice> list) {
        super(R.layout.item_platform_notice, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlatformNotice item) {
        helper.setText(R.id.txt_title, item.getTitle());
        helper.setText(R.id.txt_content, item.getContent());
        helper.setText(R.id.txt_time, DateUtils.longTimeToStr(item.getSend_date(), DateUtils.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS));
    }
}
