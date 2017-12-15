package com.pcjr.pcjr_oa.ui.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Person;
import com.pcjr.pcjr_oa.bean.PlatformNotice;
import com.pcjr.pcjr_oa.utils.DateUtils;
import com.pcjr.pcjr_oa.utils.StringUtils;

import java.util.List;

/**
 * Created by mario on 2017/7/25.
 */

public class PersonAdapter extends BaseQuickAdapter<Person, BaseViewHolder> {


    public PersonAdapter() {
        super(R.layout.item_participant);
    }

    @Override
    protected void convert(BaseViewHolder helper, Person item) {
        String name = item.getRealname();
        helper.setText(R.id.txt_avatar, StringUtils.getLast2(name));
        helper.setText(R.id.txt_name, name);
        helper.setText(R.id.txt_department, item.getDepartment());
        helper.setText(R.id.txt_job_name, item.getJob());
        if(item.isSelected()) {
            helper.setVisible(R.id.img_selected, true);
        }else{
            helper.setVisible(R.id.img_selected, false);
        }
        TextView txt_avatar = helper.getView(R.id.txt_avatar);
        ViewCompat.setBackgroundTintList(txt_avatar, ColorStateList.valueOf(Color.parseColor("#ff4341")));
    }
}
