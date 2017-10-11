package com.pcjr.pcjr_oa.ui.views.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseAppCompatActivity;

import butterknife.BindView;

/**
 *  搜索
 *  Created by Mario on 2017/7/26下午3:59
 */
public class SearchActivity extends BaseAppCompatActivity {

    @BindView(R.id.ll_search) LinearLayout llSearch;
    @BindView(R.id.ll_content) LinearLayout llContent;
    @BindView(R.id.rl_search) RelativeLayout rlSearch;
    @BindView(R.id.icon_search) ImageView iconSearch;
    @BindView(R.id.btn_delete) ImageView btnDelete;
    @BindView(R.id.btn_cancel) TextView btnCancel;
    @BindView(R.id.txt_search) EditText txtSearch;

    @Override
    protected int getLayoutId() {
        return R.layout.search;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        txtSearch.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                txtSearch.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                performEnterAnimation();
            }
        });
    }
    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {
        btnDelete.setOnClickListener(v->txtSearch.setText(""));
        btnCancel.setOnClickListener(v->performExitAnimation());
    }

    @Override
    protected void initData() {

    }

    private void performEnterAnimation() {
        float originY = getIntent().getIntExtra("y",0);

        int rl[] = new int[2];
        int icon[] = new int[2];
        rlSearch.getLocationOnScreen(rl);
        iconSearch.getLocationOnScreen(icon);

        rlSearch.setY(originY-rlSearch.getHeight());
        float top = getResources().getDisplayMetrics().density * 15;
        float margin = getResources().getDisplayMetrics().density * 5;

        ValueAnimator translateVa = ValueAnimator.ofFloat(originY-rlSearch.getHeight(),top);
        translateVa.addUpdateListener(valueAnimator->{
            rlSearch.setY((Float) valueAnimator.getAnimatedValue());
            iconSearch.setX((Float) valueAnimator.getAnimatedValue());
            txtSearch.setX((Float) valueAnimator.getAnimatedValue() + iconSearch.getWidth());
            btnCancel.setY((Float) valueAnimator.getAnimatedValue()+margin);
        });


        ValueAnimator alphaVa = ValueAnimator.ofFloat(0,1);
        alphaVa.addUpdateListener(valueAnimator->{
            llContent.setAlpha((Float) valueAnimator.getAnimatedValue());
            btnCancel.setAlpha((Float) valueAnimator.getAnimatedValue());
        });


        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) llSearch.getLayoutParams();
        ValueAnimator heightVa = ValueAnimator.ofInt(dip2px(this,120),dip2px(this,65));
        heightVa.addUpdateListener(valueAnimator->{
            linearParams.height = (int) valueAnimator.getAnimatedValue();
            llSearch.setLayoutParams(linearParams);
        });

        translateVa.setDuration(500);
        translateVa.start();

        alphaVa.setDuration(500);
        alphaVa.start();

        heightVa.setDuration(500);
        heightVa.start();

    }

    private void performExitAnimation() {
        float originY = getIntent().getIntExtra("y",0);

        int rl[] = new int[2];
        int icon[] = new int[2];
        rlSearch.getLocationOnScreen(rl);
        iconSearch.getLocationOnScreen(icon);

        rlSearch.setY(originY-rlSearch.getHeight());
        float top = getResources().getDisplayMetrics().density * 15;
        float margin = getResources().getDisplayMetrics().density * 5;

        ValueAnimator translateVa = ValueAnimator.ofFloat(top,originY-rlSearch.getHeight());
        translateVa.addUpdateListener(valueAnimator->{
            rlSearch.setY((Float) valueAnimator.getAnimatedValue());
            iconSearch.setX((Float) valueAnimator.getAnimatedValue());
            txtSearch.setX((Float) valueAnimator.getAnimatedValue() + iconSearch.getWidth());
            btnCancel.setY((Float) valueAnimator.getAnimatedValue()+margin);
        });



        ValueAnimator alphaVa = ValueAnimator.ofFloat(1,0);
        alphaVa.addUpdateListener(valueAnimator->{
            llContent.setAlpha((Float) valueAnimator.getAnimatedValue());
            btnCancel.setAlpha((Float) valueAnimator.getAnimatedValue());
            llSearch.setAlpha((Float) valueAnimator.getAnimatedValue());
        });

        alphaVa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) llSearch.getLayoutParams();
        ValueAnimator heightVa = ValueAnimator.ofInt(dip2px(this,65),dip2px(this,120));
        heightVa.addUpdateListener(valueAnimator->{
            linearParams.height = (int) valueAnimator.getAnimatedValue();
            llSearch.setLayoutParams(linearParams);
        });

        translateVa.setDuration(500);
        translateVa.start();

        alphaVa.setDuration(800);
        alphaVa.start();

        heightVa.setDuration(800);
        heightVa.start();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK ) performExitAnimation();
        return false;

    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
