package com.example.thepurple.db;

import org.litepal.crud.LitePalSupport;

import java.text.DateFormat;
import java.util.Date;

public class Comments extends LitePalSupport {
    private String account;//发布评论的用户
    private String comment;//评论内容
    private Date submit_comment_time;//发布时间
    private String submit_mesg_time;//对应消息的发布时间
    public Comments(String account,String mesg_time,String comment){
        this.account = account;
        this.comment = comment;
        this.submit_comment_time = new Date();
        this.submit_mesg_time = mesg_time;
    }
    public String getComment(){
        return this.comment;
    }
    private String getAccount(){
        return this.account;
    }

    public String getSubmitComment(){
        String strdate = null;
        strdate = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT).format(submit_comment_time);
        //格式化时间
        return strdate;
    }
}
