package com.example.thepurple.db;

import org.litepal.crud.LitePalSupport;

public class Account extends LitePalSupport {

    private String account;
    private String passwd;

    public String getAccount(){
        return this.account;
    }
    public void setAccount(String myaccount){
        this.account = myaccount;
    }
    public String getPasswd(){
        return this.passwd;
    }
    public boolean setPasswd(String mypasswd){//返回是否设置成功
        //密码长度至少8位，包含大写，小写字母，数字和特殊字符中至少三个
        int count=0;
        if(mypasswd.length()-mypasswd.replaceAll("[A-Z]","").length()>0){
            count++;
        }
        if(mypasswd.length()-mypasswd.replaceAll("[a-z]","").length()>0){
            count++;
        }
        if(mypasswd.length()-mypasswd.replaceAll("[0-9]","").length()>0){
            count++;
        }
        if(mypasswd.replaceAll("[0-9,A-Z,a-z]","").length()>0){
            count++;
        }
        if(count>2&&mypasswd.length()>=7) {
            this.passwd = mypasswd;
            return true;
        }else return false;
    }
}
