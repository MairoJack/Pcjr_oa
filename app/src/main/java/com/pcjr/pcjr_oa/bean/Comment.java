package com.pcjr.pcjr_oa.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.pcjr.pcjr_oa.ui.adapter.CommentAdapter;

import java.io.Serializable;

/**
 *  评论
 *  Created by Mario on 2017/8/30上午9:23
 */
public class Comment extends AbstractExpandableItem<Reply> implements MultiItemEntity, Serializable {

    private String name;
    private String content;
    private long date;
    private String avatar;

    public Comment(String name, String content, long date) {
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return CommentAdapter.TYPE_LEVEL_0;
    }
}
