package com.pcjr.pcjr_oa.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by mario on 2017/7/26.
 */

public class CategorySection extends SectionEntity<CategoryTask>{

    public CategorySection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public CategorySection(CategoryTask workItem) {
        super(workItem);
    }
}
