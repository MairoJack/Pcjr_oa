package com.pcjr.pcjr_oa.ui.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Person;

import java.util.List;

/**
 * Created by mario on 2017/7/25.
 */

public class StaffAdapter extends BaseQuickAdapter<Person, BaseViewHolder> {


    public StaffAdapter() {
        super(R.layout.item_staff);
    }

    @Override
    protected void convert(BaseViewHolder helper, Person item) {
        helper.setText(R.id.txt_name, item.getName());
        helper.setText(R.id.txt_department, item.getDepartment());
        helper.setText(R.id.txt_job_name, item.getJob());
        TextView txt_avatar = helper.getView(R.id.txt_avatar);
        ViewCompat.setBackgroundTintList(txt_avatar, ColorStateList.valueOf(Color.parseColor("#ff4341")));
        helper.addOnClickListener(R.id.btn_telephone);
    }
}
