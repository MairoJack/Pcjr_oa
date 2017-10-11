package com.pcjr.pcjr_oa.ui.adapter;

import android.support.v4.app.Fragment;

import com.pcjr.pcjr_oa.ui.views.fragment.ContactFragment;
import com.pcjr.pcjr_oa.ui.views.fragment.CountFragment;
import com.pcjr.pcjr_oa.ui.views.fragment.MsgFragment;
import com.pcjr.pcjr_oa.ui.views.fragment.PersonalFragment;
import com.pcjr.pcjr_oa.ui.views.fragment.WorkFragment;
import com.pcjr.pcjr_oa.widget.FragmentNavigatorAdapter;


/**
 * Fragement适配器
 * Created by Mario on 2016/7/21.
 */
public class FragmentAdapter implements FragmentNavigatorAdapter {

    private static final String TABS[] = {"msg", "work", "count", "contact","personal"};

    @Override
    public Fragment onCreateFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:fragment = MsgFragment.newInstance("MsgFragment");break;
            case 1:fragment = WorkFragment.newInstance("WorkFragment");break;
            case 2:fragment = CountFragment.newInstance("CountFragment");break;
            case 3:fragment = ContactFragment.newInstance("ContactFragment");break;
            case 4:fragment = PersonalFragment.newInstance("PersonalFragment");break;
        }
        return fragment;
    }

    @Override
    public String getTag(int position) {

        String tag = new String();
        switch (position){
            case 0:tag = MsgFragment.TAG;break;
            case 1:tag = WorkFragment.TAG;break;
            case 2:tag = CountFragment.TAG;break;
            case 3:tag = ContactFragment.TAG;break;
            case 4:tag = PersonalFragment.TAG;break;
        }
        return tag;
    }

    @Override
    public int getCount() {
        return TABS.length;
    }
}
