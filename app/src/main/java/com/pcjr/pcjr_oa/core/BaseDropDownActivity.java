package com.pcjr.pcjr_oa.core;

import android.animation.ObjectAnimator;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.ClassifySection;
import com.pcjr.pcjr_oa.ui.adapter.ClassifyGridAdapter;
import com.pcjr.pcjr_oa.ui.adapter.ClassifyListAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基础顶部下拉菜单
 */
public abstract class BaseDropDownActivity extends BaseToolbarActivity {

    @BindView(R.id.btn_down) ImageView btnDown;
    private Button btnOk;

    private ClassifyGridAdapter classifyGridAdapter;
    private ClassifyListAdapter classifyListAdapter;
    protected PopupWindow mPopTop;
    private ObjectAnimator animator;
    private RecyclerView classifyRecyclerView;
    protected List<ClassifySection> classifySectionList;
    protected List<Classify> classifyList;

    private View classifyView;

    protected int[] positions;
    private int[] temps;
    private int lastPosition = -1;



    protected void initGridPop(){

        classifyView = getLayoutInflater().inflate(R.layout.classify_grid, null);
        classifyRecyclerView = ButterKnife.findById(classifyView, R.id.recycler_view);
        classifyRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        btnOk = ButterKnife.findById(classifyView,R.id.btn_ok);
        initPop();

    }

    protected void initGridPopListener() {

        btnDown.setOnClickListener(v -> {
            if(ViewUtil.isFastDoubleClick()) return;
            btnDown.setImageResource(R.mipmap.icon_down);
            animator.start();
            mPopTop.showAsDropDown(mToolbar);
            backgroundAlpha(0.7f);

        });

        classifyGridAdapter.setOnItemClickListener((adapter, view, position) -> {
            ClassifySection cs = (ClassifySection) adapter.getItem(position);
            if(!cs.isHeader){
                Classify classify = cs.t;
                int lastPosition = positions[classify.getType()];
                if(lastPosition != position) {
                    ClassifySection last = classifySectionList.get(lastPosition);
                    last.t.setSelected(false);
                    adapter.notifyItemChanged(lastPosition, last);

                    cs.t.setSelected(true);
                    positions[classify.getType()] = position;
                    adapter.notifyItemChanged(position, cs);
                }
            }

        });

        btnOk.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            mPopTop.dismiss();
        });
    }

    protected void initListPopListener() {

        btnDown.setOnClickListener(v -> {
            if(ViewUtil.isFastDoubleClick()) return;
            btnDown.setImageResource(R.mipmap.icon_down);
            animator.start();
            mPopTop.showAsDropDown(mToolbar);
            backgroundAlpha(0.7f);

        });

        classifyListAdapter.setOnItemClickListener((adapter, view, position) -> {
            if(lastPosition != -1) {
                Classify lastItem = classifyList.get(lastPosition);
                lastItem.setSelected(false);
                classifyListAdapter.notifyItemChanged(lastPosition,lastItem);
            }
            Classify selectedItem = classifyList.get(position);
            selectedItem.setSelected(true);
            classifyListAdapter.notifyItemChanged(position,selectedItem);
            lastPosition = position;
        });

    }

    protected void initGridPopData(){
        classifyGridAdapter = new ClassifyGridAdapter(classifySectionList);
        classifyRecyclerView.setAdapter(classifyGridAdapter);
        temps = positions.clone();
        initGridPopListener();
    }

    protected void initListPop(){

        classifyView = getLayoutInflater().inflate(R.layout.classify_list, null);
        classifyRecyclerView = ButterKnife.findById(classifyView, R.id.recycler_view);
        classifyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        classifyRecyclerView.addItemDecoration(new RecycleViewDivider(this,1));

        initPop();
    }

    protected void initListPopData(){
        classifyListAdapter = new ClassifyListAdapter(classifyList);
        classifyRecyclerView.setAdapter(classifyListAdapter);
        initListPopListener();
    }

    private void backgroundAlpha(float f) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = f;
        if (f == 1) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        getWindow().setAttributes(lp);
    }

    protected String closeGridPop(){
        btnDown.setImageResource(R.mipmap.icon_up);
        animator.start();
        backgroundAlpha(1f);

        String result = "";
        boolean isSame = true;
        for (int i = 0 ;i < positions.length ; i++){
            String name = classifySectionList.get(positions[i]).t.getName();
            result += name +",";
            if(temps[i] != positions[i]){
                isSame = false;
            }
        }
        if(isSame){
            return null;
        }else{
            return result;
        }
//        if(temps[0] == positions[0] && temps[1] == positions[1]){
//            return null;
//        }
//         else {
//            ClassifySection cs1 = classifySectionList.get(positions[0]);
//            ClassifySection cs2 = classifySectionList.get(positions[1]);
//            return  cs1.t.getName() + "," + cs2.t.getName();
//        }
    }

    protected String closeListPop(){
        btnDown.setImageResource(R.mipmap.icon_up);
        animator.start();
        backgroundAlpha(1f);
        if(lastPosition == -1){
            return null;
        }
        Classify classify = classifyList.get(lastPosition);
        return classify.getName();
    }

    private void initPop(){

        animator = ObjectAnimator.ofFloat(btnDown, "rotation", 0f, 180f);
        animator.setDuration(500);

        mPopTop = new PopupWindow(this);
        mPopTop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopTop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopTop.setFocusable(true);
        mPopTop.setTouchable(true);
        mPopTop.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        mPopTop.setBackgroundDrawable(dw);
        mPopTop.setAnimationStyle(R.style.popAnimation);
        mPopTop.setContentView(classifyView);
    }
}
