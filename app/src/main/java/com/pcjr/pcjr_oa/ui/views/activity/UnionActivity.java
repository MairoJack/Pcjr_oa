package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.core.BaseAppCompatActivity;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;

/**
 *  关联
 *  Created by Mario on 2017/8/17上午9:16
 */
public abstract class UnionActivity extends BaseAppCompatActivity {

    @BindView(R.id.ll_open) LinearLayout llOpen;
    @BindView(R.id.ll_content) LinearLayout llContent;
    @BindView(R.id.ll_close) LinearLayout llClose;

    @BindView(R.id.rl_union_item) RelativeLayout rlUnionItem;
    @BindView(R.id.rl_union_product) RelativeLayout rlUnionProduct;
    @BindView(R.id.rl_union_schedule) RelativeLayout rlUnionSchedule;
    @BindView(R.id.rl_union_document) RelativeLayout rlUnionDocument;
    @BindView(R.id.rl_union_contract) RelativeLayout rlUnionContract;
    @BindView(R.id.rl_union_customer) RelativeLayout rlUnionCustomer;
    @BindView(R.id.rl_union_contact) RelativeLayout rlUnionContact;
    @BindView(R.id.rl_union_approve) RelativeLayout rlUnionApprove;
    @BindView(R.id.rl_union_data) RelativeLayout rlUnionData;
    @BindView(R.id.rl_union_form) RelativeLayout rlUnionForm;
    @BindView(R.id.rl_union_share) RelativeLayout rlUnionShare;
    @BindView(R.id.rl_union_activity) RelativeLayout rlUnionActivity;

    @BindView(R.id.txt_union_item) TextView txtUnionItem;
    @BindView(R.id.txt_union_product) TextView txtUnionProduct;
    @BindView(R.id.txt_union_schedule) TextView txtUnionSchedule;
    @BindView(R.id.txt_union_document) TextView txtUnionDocument;
    @BindView(R.id.txt_union_contract) TextView txtUnionContract;
    @BindView(R.id.txt_union_customer) TextView txtUnionCustomer;
    @BindView(R.id.txt_union_contact) TextView txtUnionContact;
    @BindView(R.id.txt_union_approve) TextView txtUnionApprove;
    @BindView(R.id.txt_union_data) TextView txtUnionData;
    @BindView(R.id.txt_union_form) TextView txtUnionForm;
    @BindView(R.id.txt_union_share) TextView txtUnionShare;
    @BindView(R.id.txt_union_activity) TextView txtUnionActivity;

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {

        llOpen.setOnClickListener(v->{
            llContent.setVisibility(View.VISIBLE);
            llClose.setVisibility(View.VISIBLE);
            llOpen.setVisibility(View.GONE);
        });

        llClose.setOnClickListener(v->{
            llContent.setVisibility(View.GONE);
            llClose.setVisibility(View.GONE);
            llOpen.setVisibility(View.VISIBLE);
        });

        rlUnionItem.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,UnionProjectActivity.class), Constant.REQUEST_UNION_ITEM);
        });

        rlUnionProduct.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,UnionActivity.class),Constant.REQUEST_UNION_PRODUCT);
        });

        rlUnionSchedule.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,UnionActivity.class),Constant.REQUEST_UNION_SCHEDULE);
        });

        rlUnionDocument.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,UnionActivity.class),Constant.REQUEST_UNION_DOCUMENT);
        });

        rlUnionContract.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,UnionActivity.class),Constant.REQUEST_UNION_CONTRACT);
        });

        rlUnionCustomer.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,UnionActivity.class),Constant.REQUEST_UNION_CUSTOMER);
        });
        rlUnionContact.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,UnionActivity.class),Constant.REQUEST_UNION_CONTACT);
        });

        rlUnionApprove.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,UnionActivity.class),Constant.REQUEST_UNION_APPROVE);
        });

        rlUnionData.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,UnionActivity.class),Constant.REQUEST_UNION_DATA);
        });

        rlUnionForm.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,UnionActivity.class),Constant.REQUEST_UNION_FORM);
        });

        rlUnionShare.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,UnionActivity.class),Constant.REQUEST_UNION_SHARE);
        });

        rlUnionActivity.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivityForResult(new Intent(this,UnionActivity.class),Constant.REQUEST_UNION_ACTIVITY);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            String result = data.getStringExtra("result");
            switch (requestCode){
                case Constant.REQUEST_UNION_ITEM:
                    txtUnionItem.setText(result);break;
                case Constant.REQUEST_UNION_PRODUCT:
                    txtUnionProduct.setText(result);break;
                case Constant.REQUEST_UNION_SCHEDULE:
                    txtUnionSchedule.setText(result);break;
                case Constant.REQUEST_UNION_DOCUMENT:
                    txtUnionDocument.setText(result);break;
                case Constant.REQUEST_UNION_CONTRACT:
                    txtUnionContract.setText(result);break;
                case Constant.REQUEST_UNION_CUSTOMER:
                    txtUnionCustomer.setText(result);break;
                case Constant.REQUEST_UNION_CONTACT:
                    txtUnionContact.setText(result);break;
                case Constant.REQUEST_UNION_APPROVE:
                    txtUnionApprove.setText(result);break;
                case Constant.REQUEST_UNION_DATA:
                    txtUnionData.setText(result);break;
                case Constant.REQUEST_UNION_FORM:
                    txtUnionForm.setText(result);break;
                case Constant.REQUEST_UNION_SHARE:
                    txtUnionShare.setText(result);break;
                case Constant.REQUEST_UNION_ACTIVITY:
                    txtUnionActivity.setText(result);break;
            }
        }
    }

}
