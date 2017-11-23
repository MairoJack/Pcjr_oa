package com.pcjr.pcjr_oa.ui.views.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseFragment;
import com.pcjr.pcjr_oa.ui.views.activity.CompanyContactActivity;
import com.pcjr.pcjr_oa.ui.views.activity.ContactListActivity;
import com.pcjr.pcjr_oa.ui.views.activity.SearchActivity;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;

/**
 *  通讯录
 *  Created by Mario on 2017/7/20上午11:14
 */
public class ContactFragment extends BaseFragment {

    public static final String TAG = ContactFragment.class.getSimpleName();

    @BindView(R.id.ll_search) LinearLayout llSearch;

    @BindView(R.id.ll_company_contact) LinearLayout llCompanyContact;
    @BindView(R.id.ll_mobile_contact) LinearLayout llMobileContact;
    @BindView(R.id.ll_customer_contact) LinearLayout llCustomerContact;
    @BindView(R.id.ll_special_follow) LinearLayout llSpecialFollow;

    @BindView(R.id.icon_search) ImageView iconSearch;

    @Override
    protected int getLayoutId() {
        return R.layout.main_tab_contact;
    }

    @Override
    protected void initViews(LayoutInflater inflater,View self, Bundle savedInstanceState) {
    }

    @Override
    protected void initListeners() {
        llCompanyContact.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick())return;
            startActivity(new Intent(getContext(), CompanyContactActivity.class));
        });

        llCustomerContact.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick())return;
            startActivity(new Intent(getContext(), ContactListActivity.class));
        });

        llSearch.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick())return;
            Intent intent = new Intent(getContext(), SearchActivity.class);
            int location[] = new int[2];
            llSearch.getLocationOnScreen(location);
            intent.putExtra("x",location[0]);
            intent.putExtra("y",location[1]);
            startActivity(intent);
            getActivity().overridePendingTransition(0,0);
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void lazyLoad() {

    }

    public static Fragment newInstance(String content) {
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }
}
