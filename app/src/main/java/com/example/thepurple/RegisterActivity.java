package com.example.thepurple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thepurple.db.Account;
import com.example.thepurple.db.EverydayMesg;

import org.litepal.LitePal;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText accountEdit;
    private EditText passwdEdit;
    private EditText confirm_passwd;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EverydayMesg mesg1 = new EverydayMesg();
        mesg1.setAccount("admin");
        mesg1.setMsg("你真的很不错!");
        mesg1.save();
        EverydayMesg mesg2 = new EverydayMesg();
        mesg2.setAccount("admin");
        mesg2.setMsg("你真的真的很不错!");
        mesg2.save();
        EverydayMesg mesg3 = new EverydayMesg();
        mesg3.setAccount("admin");
        mesg3.setMsg("你真的真的真的很不错!");
        mesg3.save();

        accountEdit = (EditText) findViewById(R.id.register_account);//账户
        passwdEdit = (EditText) findViewById(R.id.register_password);//密码
        confirm_passwd = (EditText) findViewById(R.id.confirm_password); //确认密码
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String passwd = passwdEdit.getText().toString();
                String confirmpasswd = confirm_passwd.getText().toString();
                //查找数据库中是否已存在相同账号
                List<Account> accounts =  LitePal.where("account = ?",
                        account).find(Account.class);
                if(accounts.size() != 0){//账号已存在
                    Toast.makeText(RegisterActivity.this,"账号已存在 ",
                            Toast.LENGTH_SHORT).show();
                }else if(! if_validPasswd(passwd)){//账号通过，密码不安全
                    Toast.makeText(RegisterActivity.this,"密码长度至少8位，包含大写，小写字母，数字和特殊字符中至少两类",
                            Toast.LENGTH_SHORT).show();
                }else if(passwd.equals(confirmpasswd)){//两次密码输入相同，账号未重复
                    //将账号信息存入数据库
                    Account new_account = new Account();
                    new_account.setAccount(account);
                    new_account.setPasswd(passwd);
                    new_account.save();
                    //跳转到主界面
                    Toast.makeText(RegisterActivity.this,"Welcome to the sea!",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();//销毁当前活动
                }else{//账号未重复，但是两次密码输入不一致
                    Toast.makeText(RegisterActivity.this,"两次密码输入不一致",
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    public boolean if_validPasswd(String mypasswd){//返回密码是否安全
        //密码长度至少8位，包含大写，小写字母，数字和特殊字符中至少两个
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
        if(count>1&&mypasswd.length()>=7) {
            return true;
        }else return false;
    }
}
