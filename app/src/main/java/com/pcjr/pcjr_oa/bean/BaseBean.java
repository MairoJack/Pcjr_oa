package com.pcjr.pcjr_oa.bean;

import java.io.Serializable;
import lombok.Data;

/**
 *  基础返回消息
 *  Created by Mario on 2017/9/28上午10:42
 */
@Data
public class BaseBean<T> implements Serializable {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 消息码
     */
    private int code;
    /**
     * 数据
     */
    private T data;
    /**
     * 分页
     */
    private Pager pager;
}
