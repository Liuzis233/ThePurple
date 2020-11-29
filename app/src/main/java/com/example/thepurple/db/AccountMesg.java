package com.example.thepurple.db;

import com.example.thepurple.R;

import org.litepal.crud.LitePalSupport;

import java.text.DateFormat;
import java.util.Date;
import java.lang.String;
import java.util.Random;

public class AccountMesg extends LitePalSupport {
    //树洞消息存储表格
    private String account;//用户账号
    private boolean if_private;
    //是否设置为所有人可见，private为true,public为false,发布两天后自动改为true
    private Date submit_time;//发布时间
    //private Date update_time;//更新时间
    private String style;//发布消息分区（default，study，life，work）
    //default类型会发布到首页，其余类型会发布在各自分区以及首页
    private String msg;
    private int imageId;


    public AccountMesg(String admin){//创建一条树洞
        this.account = admin;
        this.style = "default";
        this.imageId = getRandom_image();
    }
    public int getRandom_image(){//每条消息随机生成头像
        Random rand = new Random();
        int image_id = rand.nextInt(10);
        switch (image_id){
            case 0:
                image_id = R.mipmap.account_1;
                break;
            case 1:
                image_id = R.mipmap.account;
                break;
            case 2:
                image_id = R.mipmap.passwd_1;
                break;
            case 3:
                image_id = R.mipmap.passwd_1;
                break;
            case 4:
                image_id = R.mipmap.passwd_1;
                break;
            case 5:
                image_id = R.mipmap.passwd_1;
                break;
            case 6:
                image_id = R.mipmap.passwd_1;
                break;
            case 7:
                image_id = R.mipmap.passwd_1;
                break;
            case 8:
                image_id = R.mipmap.passwd_1;
                break;
            case 9:
                image_id = R.mipmap.passwd_1;
                break;
            default:
                image_id = R.mipmap.passwd_1;
                break;
        }
        return image_id;
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
    public void setMsg(String account_msg){this.msg = account_msg;}
    public String getMsg(){return msg;}
    public int getImageId(){return imageId;}
}
