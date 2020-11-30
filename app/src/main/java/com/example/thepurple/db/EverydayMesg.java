package com.example.thepurple.db;

import org.litepal.crud.LitePalSupport;

public class EverydayMesg extends LitePalSupport {
    private String msg;
    private String account;
    public void setMsg(String your_words){this.msg = your_words;}
    public String getMsg(){return msg;}
    public void setAccount(String your_account){this.account = your_account;}
    public String getAccount(){return account;}


}
