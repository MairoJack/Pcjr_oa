package com.pcjr.pcjr_oa.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.gyf.barlibrary.ImmersionBar;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * 基础Fragment
 * Created by Mario on 2016/7/21.
 */
public abstract class BaseFragment extends Fragment {

    protected View self;
    protected boolean isVisible;
    protected ImmersionBar mImmersionBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (this.self == null) {
            this.self = inflater.inflate(this.getLayoutId(), container, false);
        }
        if (this.self.getParent() != null) {
            ViewGroup parent = (ViewGroup) this.self.getParent();
            parent.removeView(this.self);
        }
        ButterKnife.bind(this, self);
        initImmersionBar();
        this.initViews(inflater,this.self, savedInstanceState);
        this.initData();
        this.initListeners();
        return this.self;
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(LayoutInflater inflater,View self, Bundle savedInstanceState);

    protected abstract void initListeners();

    protected abstract void initData();

    protected <V extends View> V findView(int id) {
        return (V) this.self.findViewById(id);
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        getActivity().startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


    public void showToast(String msg) {
        this.showToast(msg, Toast.LENGTH_SHORT);
    }


    public void showToast(String msg, int duration) {
        if (msg == null) return;
        if (duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            ToastUtils.show(this.getActivity(), msg, duration);
        } else {
            ToastUtils.show(this.getActivity(), msg, ToastUtils.LENGTH_SHORT);
        }
    }


    public void showToast(int resId) {
        this.showToast(resId, Toast.LENGTH_SHORT);
    }


    public void showToast(int resId, int duration) {
        if (duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            ToastUtils.show(this.getActivity(), resId, duration);
        } else {
            ToastUtils.show(this.getActivity(), resId, ToastUtils.LENGTH_SHORT);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {


    }

    protected abstract void lazyLoad();

    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.colorPrimary).keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }


    protected void setBackData(String msg){
        Intent intent = new Intent();
        intent.putExtra("msg",msg);
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null)
            mImmersionBar.init();
    }
}

