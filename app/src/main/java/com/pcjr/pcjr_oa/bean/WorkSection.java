package com.pcjr.pcjr_oa.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by mario on 2017/7/26.
 */

public class WorkSection extends SectionEntity<WorkItem>{

    public WorkSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public WorkSection(WorkItem workItem) {
        super(workItem);
    }
}
