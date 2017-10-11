package com.pcjr.pcjr_oa.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by mario on 2017/7/26.
 */

public class ClassifySection extends SectionEntity<Classify>{

    public ClassifySection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public ClassifySection(Classify workItem) {
        super(workItem);
    }

}
