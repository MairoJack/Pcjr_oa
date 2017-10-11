package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 *  平台公告
 *  Created by Mario on 2017/7/26下午3:43
 */
public class PlatformNotice implements Serializable{
    @Expose
    private String id;
    @Expose
    private String title;
    @Expose
    private String author;
    @Expose
    private long send_date;
    @Expose
    private int read_status;
    @Expose
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getSend_date() {
        return send_date;
    }

    public void setSend_date(long send_date) {
        this.send_date = send_date;
    }

    public int getRead_status() {
        return read_status;
    }

    public void setRead_status(int read_status) {
        this.read_status = read_status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
