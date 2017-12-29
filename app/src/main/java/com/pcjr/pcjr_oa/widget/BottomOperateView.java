package com.pcjr.pcjr_oa.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Operate;


/**
 * 自定义底部操作view
 * Created by mario on 2017/12/29.
 */
public class BottomOperateView extends LinearLayout {

    private Context context;
    private BottomSheetDialog moreBottomSheetDialog;
    private GridLayout gridLayout;
    public BottomOperateView(Context context) {
        super(context);
    }

    public BottomOperateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setData(String[] operate) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.weight = 1;

        if(operate.length > 5) {
            initMoreBottomSheetDialog();
        }
        for (int i = 0; i < operate.length; i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_bottom_operate, null);
            LinearLayout llOperate = view.findViewById(R.id.ll_operate);
            ImageView imgOperate = view.findViewById(R.id.img_operate);
            TextView txtOperate = view.findViewById(R.id.txt_operate);
            txtOperate.setText(operate[i]);
            imgOperate.setImageResource(Operate.getLightIcon(operate[i]));
            if(operate.length > 5 && i >= 4) {
                putBottomDialogData(operate[i]);
            } else {
                addView(view, lp);
            }
            if(operate.length > 5 && i == 4){
                txtOperate.setText("更多");
                imgOperate.setImageResource(R.mipmap.icon_more_48);
                llOperate.setOnClickListener(v-> moreBottomSheetDialog.show());
                addView(view, lp);
            }
        }
    }

    private void initMoreBottomSheetDialog(){
        moreBottomSheetDialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_menu, findViewById(R.id.dialog));
        gridLayout = view.findViewById(R.id.gl_operate);
        TextView btnCancel = view.findViewById(R.id.btn_cancel);
        moreBottomSheetDialog.setContentView(view);
        moreBottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet)
                .setBackgroundResource(android.R.color.transparent);

        btnCancel.setOnClickListener(v-> moreBottomSheetDialog.dismiss());
    }

    private void putBottomDialogData(String name){

        View view = LayoutInflater.from(context).inflate(R.layout.item_bottom_dialog, null);
        LinearLayout llItemDialog = view.findViewById(R.id.ll_item_dialog);
        ImageView imgOperate = view.findViewById(R.id.img_operate);
        TextView txtOperate = view.findViewById(R.id.txt_operate);
        LinearLayout.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(dip2Px(20),0,0,0);
        llItemDialog.setLayoutParams(lp);
        txtOperate.setText(name);
        imgOperate.setImageResource(Operate.getDarkIcon(name));
        gridLayout.addView(view);

    }

    private int dip2Px(float dip) {
        return (int) (dip * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

}
