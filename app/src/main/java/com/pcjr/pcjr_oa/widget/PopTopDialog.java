package com.pcjr.pcjr_oa.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * 下拉弹出菜单
 * Created by mario on 2017/12/20.
 */
public class PopTopDialog {

    public enum TYPE {
        LIST, GRID
    }

    public static class Params {
        private List<ClassifySection> classifySectionList;
        private List<Classify> classifyList;
        private OnCloseListener listener;
        private int[] positions;

        private ImageView imageView;
    }

    public static class Builder {

        private Context context;
        private TYPE type;
        private Params p;
        private ObjectAnimator animator;
        private int[] temps;
        private int lastPosition = -1;
        private PopupWindow mPopTop;

        public Builder(Context context, TYPE type) {
            this.context = context;
            this.type = type;
            this.p = new Params();

        }

        public Builder setGridData(List<ClassifySection> classifySectionList) {
            p.classifySectionList = classifySectionList;
            return this;
        }

        public Builder setListData(List<Classify> classifyList) {
            p.classifyList = classifyList;
            return this;
        }

        public Builder setPositions(int[] positions) {
            p.positions = positions;
            return this;
        }

        public Builder setOnCloseListener(OnCloseListener listener) {
            p.listener = listener;
            return this;
        }

        public Builder setDropDownBtn(ImageView imageView) {
            p.imageView = imageView;
            this.animator = ObjectAnimator.ofFloat(p.imageView, "rotation", 0f, 180f);
            this.animator.setDuration(500);
            return this;
        }

        public Builder create() {

            initPopWindow();

            View classifyView = null;
            RecyclerView classifyRecyclerView;

            if (this.type == TYPE.LIST) {
                classifyView = LayoutInflater.from(this.context).inflate(R.layout.classify_list, null);
                classifyRecyclerView = classifyView.findViewById(R.id.recycler_view);
                classifyRecyclerView.setLayoutManager(new LinearLayoutManager(this.context));
                classifyRecyclerView.addItemDecoration(new RecycleViewDivider(this.context, 1));

                ClassifyListAdapter classifyListAdapter = new ClassifyListAdapter(p.classifyList);
                classifyRecyclerView.setAdapter(classifyListAdapter);

                classifyListAdapter.setOnItemClickListener((adapter, view, position) -> {
                    if (this.lastPosition != -1) {
                        Classify lastItem = p.classifyList.get(this.lastPosition);
                        lastItem.setSelected(false);
                        classifyListAdapter.notifyItemChanged(this.lastPosition, lastItem);
                    }
                    Classify selectedItem = p.classifyList.get(position);
                    selectedItem.setSelected(true);
                    classifyListAdapter.notifyItemChanged(position, selectedItem);
                    this.lastPosition = position;
                });

            } else if (this.type == TYPE.GRID) {
                classifyView = LayoutInflater.from(this.context).inflate(R.layout.classify_grid, null);
                classifyRecyclerView = classifyView.findViewById(R.id.recycler_view);
                classifyRecyclerView.setLayoutManager(new GridLayoutManager(this.context, 3));

                mPopTop.setContentView(classifyView);

                ClassifyGridAdapter classifyGridAdapter = new ClassifyGridAdapter(p.classifySectionList);
                classifyRecyclerView.setAdapter(classifyGridAdapter);

                Button btnOk = classifyView.findViewById(R.id.btn_ok);

                classifyGridAdapter.setOnItemClickListener((adapter, view, position) -> {
                    ClassifySection cs = (ClassifySection) adapter.getItem(position);
                    if (cs != null && !cs.isHeader) {
                        Classify classify = cs.t;
                        int lastPosition = p.positions[classify.getType()];
                        if (lastPosition != position) {
                            ClassifySection last = p.classifySectionList.get(lastPosition);
                            last.t.setSelected(false);
                            adapter.notifyItemChanged(lastPosition, last);

                            cs.t.setSelected(true);
                            p.positions[classify.getType()] = position;
                            adapter.notifyItemChanged(position, cs);
                        }
                    }
                });

                btnOk.setOnClickListener(v -> {
                    if (ViewUtil.isFastDoubleClick()) return;
                    mPopTop.dismiss();
                });

            }

            mPopTop.setContentView(classifyView);

            mPopTop.setOnDismissListener(() -> {
                p.imageView.setImageResource(R.mipmap.icon_up);
                animator.start();
                if (this.type == TYPE.LIST) {
                    String result = new String("");
                    if(this.lastPosition != -1){
                        result = p.classifyList.get(this.lastPosition).getName();
                    }
                    p.listener.onClose(result);

                } else if (this.type == TYPE.GRID) {
                    StringBuilder result = new StringBuilder("");
                    boolean isSame = true;
                    for (int i = 0; i < p.positions.length; i++) {
                        String name = p.classifySectionList.get(p.positions[i]).t.getName();
                        result.append(name).append(",");
                        if (temps[i] != p.positions[i]) {
                            isSame = false;
                        }
                    }
                    if(isSame){
                        p.listener.onClose("");
                    } else {
                        p.listener.onClose(result.toString());
                    }
                }

            });

            return this;
        }

        public void show() {
            if (this.type == TYPE.GRID) {
                temps = p.positions.clone();
            }
            p.imageView.setImageResource(R.mipmap.icon_down);
            animator.start();
            mPopTop.showAsDropDown(p.imageView);
        }

        private void initPopWindow() {
            mPopTop = new PopupWindow(this.context);
            mPopTop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            mPopTop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopTop.setFocusable(true);
            mPopTop.setTouchable(true);
            mPopTop.setOutsideTouchable(true);
            ColorDrawable dw = new ColorDrawable(0xb0000000);
            mPopTop.setBackgroundDrawable(dw);
            mPopTop.setAnimationStyle(R.style.popAnimation);
        }

    }

    public interface OnCloseListener {
        void onClose(String result);
    }

}
