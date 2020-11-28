package com.example.thepurple;

import org.litepal.crud.LitePalSupport;

import java.text.DateFormat;
import java.util.Date;
import java.lang.String;

public class AccountMesg extends LitePalSupport {
    //树洞消息存储表格
    private String account;//用户账号
    private boolean if_private;//是否设置为所有人可见，private为1,public为0,发布两天后自动改为1
    private Date submit_time;//发布时间
    //private Date update_time;//更新时间
    private String style;//发布消息分区（default，study，life，work）
    //default类型会发布到首页，其余类型会发布在各自分区以及首页


    public AccountMesg(String admin){//创建一条树洞
        this.account = admin;
        this.style = "default";
    }
    public boolean getIf_private(){return if_private;}
    public void setIf_private(boolean change_privacy){
        this.if_private = change_privacy;
    }
    public String getAccount(){return account;}
    public void setPrivacyAuto(){//实现发布树洞两天以后显示水平自动降为private

    }
    public void setSubmit_time(){//设置提交时间
        this.submit_time = new Date();
    }
    public String getSubmit_time(){
        String strdate = null;
        strdate = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT).format(submit_time);
        //格式化时间
        return strdate;
    }
    public void setStyle(String my_style){this.style = my_style;}
    public String getStyle(){return style;}
}
