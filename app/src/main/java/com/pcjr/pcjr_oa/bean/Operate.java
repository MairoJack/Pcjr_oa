package com.pcjr.pcjr_oa.bean;


import com.pcjr.pcjr_oa.R;

/**
 * Created by mario on 2017/12/29.
 */

public class  Operate {
    public static int getLightIcon(String name){
        int icon = 0;
        switch (name){
            case "反馈":
                icon =  R.mipmap.icon_feedback_48;
                break;
            case "驳回":
                icon =  R.mipmap.icon_reject_light_48;
                break;
            case "同意":
                icon =  R.mipmap.icon_agree_light_48;
                break;
            case "返回":
                icon =  R.mipmap.icon_return_light_48;
                break;
            case "删除":
                icon =  R.mipmap.icon_delete_48;
                break;
            case "查询记录":
                icon =  R.mipmap.icon_query_record_light_48;
                break;
            case "操作日志":
                icon =  R.mipmap.icon_log_light_48;
                break;
            case "流程":
                icon =  R.mipmap.icon_flow_light_48;
                break;
        }
        return icon;
    }
    public static int getDarkIcon(String name){
        int icon = 0;
        switch (name){
            case "删除":
                icon =  R.mipmap.icon_delete;
                break;
            case "查询记录":
                icon =  R.mipmap.icon_query_record_dark_48;
                break;
            case "操作日志":
                icon =  R.mipmap.icon_log_dark_48;
                break;
            case "流程":
                icon =  R.mipmap.icon_flow_dark_48;
                break;
        }
        return icon;
    }
}
