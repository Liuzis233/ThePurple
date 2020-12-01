package com.example.thepurple.db;

import org.litepal.crud.LitePalSupport;

public class Account extends LitePalSupport {

    private String account;
    private String passwd;
    private boolean if_invalid;//是否被举报

    public Account(){if_invalid = false;}//初始设置未被举报
    public String getAccount(){
        return this.account;
    }
    public void setAccount(String myaccount){
        this.account = myaccount;
    }
    public String getPasswd(){
        return this.passwd;
    }
    public void setPasswd(String mypasswd){
        this.passwd = mypasswd;
    }
    public void setIf_invalid(){this.if_invalid = true;}
    public boolean getIf_invalid(){return if_invalid;}
}
