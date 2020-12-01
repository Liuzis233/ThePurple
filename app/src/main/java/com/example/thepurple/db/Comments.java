package com.example.thepurple.db;

import org.litepal.crud.LitePalSupport;

import java.text.DateFormat;
import java.util.Date;

public class Comments extends LitePalSupport {
    private long msg_id;//评论消息的id
    private String account;//发布评论的用户
    private String comment;//评论内容
    private Date submit_comment_time;//发布时间

    public Comments(String account,long msg_id,String comment){
        this.account = account;
        this.msg_id = msg_id;
        this.comment = comment;
        this.submit_comment_time = new Date();
    }
    public String getComment(){
        return this.comment;
    }
    private String getAccount(){
        return this.account;
    }
    public long getMsg_id(){
        return this.msg_id;
    }

    public String getSubmitComment(){
        String strdate = null;
        strdate = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT).format(submit_comment_time);
        //格式化时间
        return strdate;
    }
}
