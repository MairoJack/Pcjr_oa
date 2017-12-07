package com.pcjr.pcjr_oa.constant;

/**
 * Created by mario on 2017/9/1.
 */

public class Constant {

    //手势密码点的状态
    public static final int POINT_STATE_NORMAL = 0;     // 正常状态
    public static final int POINT_STATE_SELECTED = 1;   // 按下状态
    public static final int POINT_STATE_WRONG = 2;      // 错误状态

    public final static int REQUEST_UNION_ITEM = 101;          //关联审批
    public final static int REQUEST_UNION_PRODUCT = 102;       //关联产品
    public final static int REQUEST_UNION_SCHEDULE = 103;      //关联日程
    public final static int REQUEST_UNION_DOCUMENT = 104;      //关联文档
    public final static int REQUEST_UNION_CONTRACT = 105;      //关联合同
    public final static int REQUEST_UNION_CUSTOMER = 106;      //关联客户
    public final static int REQUEST_UNION_CONTACT = 107;       //关联联系人
    public final static int REQUEST_UNION_APPROVE = 108;       //关联审批
    public final static int REQUEST_UNION_DATA = 109;          //关联数据
    public final static int REQUEST_UNION_FORM = 110;          //关联表单
    public final static int REQUEST_UNION_SHARE = 111;         //关联分享
    public final static int REQUEST_UNION_ACTIVITY= 112;       //关联活动

    public final static int REQUEST_REPEAT_RESULT = 201;            //设置重复
    public final static int REQUEST_REMIND_SETTING = 202;           //设置提醒
    public final static int REQUEST_PARTICIPANTS = 203;             //设置参与人
    public final static int REQUEST_SHAREER = 204;                  //设置共享人
    public final static int REQUEST_OPENNESS = 205;                 //设置公开程度
    public final static int REQUEST_TAG = 206;                      //设置标签
    public final static int REQUEST_ATTACHMENT = 207;               //设置附件
    public final static int REQUEST_LEAVE_TYPE = 208;               //设置请假类型
    public final static int REQUEST_MANAGER = 209;                  //设置负责人
    public final static int REQUEST_URGENCY = 210;                  //设置紧急程度
    public final static int REQUEST_PARENT_TASK = 211;              //设置上级任务
    public final static int REQUEST_SUB_TASK = 212;                 //设置子任务
    public final static int REQUEST_CUSTOMER_TYPE = 213;            //设置客户类型
    public final static int REQUEST_CUSTOMER_CREDIT_LEVEL = 214;    //设置客户信用等级
    public final static int REQUEST_SEX = 215;                      //设置性别
    public final static int REQUEST_MARITAL_STATUS = 216;           //设置婚姻状况
    public final static int REQUEST_ROLE = 217;                     //设置角色关系
    public final static int REQUEST_INTIMACY = 218;                 //设置亲密度
    public final static int REQUEST_COUNT_DATE = 219;               //设置统计起止日期

    public final static String[] SELECT_LEAVE_TYPE = new String[]{"事假","病假","年假","调休","婚假","产假","其他"};
    public final static String[] SELECT_OPENNESS = new String[]{"上级可见(默认)","仅自己可见","公开"};
    public final static String[] SELECT_CUSTOMER_TYPE = new String[]{"客户","借款人","平台出面人","商户","供应商","代理商"};
    public final static String[] SELECT_CUSTOMER_CREDIT_LEVEL = new String[]{"A级","B级","C级"};
    public final static String[] SELECT_SEX = new String[]{"男","女"};
    public final static String[] SELECT_MARITAL_STATUS = new String[]{"已婚","未婚","保密"};
    public final static String[] SELECT_ROLE = new String[]{"普通人","决策人","分项决策人","商务决策"};
    public final static String[] SELECT_INTIMACY = new String[]{"初相识","一般关系","朋友关系"};

    public final static String KEY = "key";
    public final static String ACCESS_TOKEN = "access_token";
    public final static String REFRESH_TOKEN = "refresh_token";
}
