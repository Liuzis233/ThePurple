package com.example.thepurple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thepurple.db.Account;

import org.litepal.LitePal;
import java.util.List;

public class LoginActivity extends Activity {

    private EditText accountEdit;
    private EditText passwdEdit;
    private Button login;
    private TextView register;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEdit = (EditText) findViewById(R.id.account);
        passwdEdit = (EditText) findViewById(R.id.password);
        register = (TextView) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String passwd = passwdEdit.getText().toString();
                //将账号密码与数据库中账号密码匹配
                List<Account> accounts =  LitePal.where("account = ? and passwd = ?",
                        account,passwd).find(Account.class);
                if(accounts.size() != 0){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"account or password is invalid",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                //跳转到注册界面
                startActivity(intent);
            }
        });
    }

}
