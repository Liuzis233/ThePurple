package com.example.thepurple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thepurple.db.AccountMesg;

public class AddActivity extends AppCompatActivity{

    private EditText edit_text;
    private Button edit;
    private TextView private_style;
    private TextView public_style;
    private TextView default_style;
    private TextView life_style;
    private TextView study_style;
    private TextView work_style;
    private String style;
    private boolean if_private;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        style = "default";
        if_private = false;
        Intent intent = getIntent();//获取账号
        final String account = intent.getStringExtra("account");
        private_style = (TextView) findViewById(R.id.private_style);
        public_style = (TextView) findViewById(R.id.public_style);
        default_style = (TextView) findViewById(R.id.default_style);
        life_style = (TextView) findViewById(R.id.life_style);
        study_style = (TextView) findViewById(R.id.study_style);
        work_style = (TextView) findViewById(R.id.work_style);
        set_private_onClickListener();//监听是否选择公开
        set_style_onClickListener();//监听是否选择发布分区
        edit_text = (EditText) findViewById(R.id.edit_mesg);
        edit = (Button) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(edit_text.getText().toString().length()!=0 && if_private ){
                    //如果字符串不为空且为仅自己可见
                    AccountMesg accountmesg = new AccountMesg(account);
                    accountmesg.setMsg(edit_text.getText().toString());
                    accountmesg.setIf_private(true);
                    accountmesg.setSubmit_time();
                    accountmesg.save();
                    Toast.makeText(AddActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                    Intent submit_intent = new Intent(AddActivity.this,MyWorldActivity.class);
                    submit_intent.putExtra("account",account);
                    startActivity(submit_intent);
                    finish();
                }else if(edit_text.getText().toString().length() == 0){//如果树洞为空
                    Toast.makeText(AddActivity.this,"树洞为空",Toast.LENGTH_SHORT).show();
                }else{
                    //如果字符串不为空且公开发布
                    AccountMesg accountmesg = new AccountMesg(account);
                    accountmesg.setMsg(edit_text.getText().toString());
                    accountmesg.setIf_private(false);
                    accountmesg.setSubmit_time();
                    accountmesg.setStyle(style);
                    accountmesg.save();
                    Toast.makeText(AddActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                    Intent submit_intent = new Intent(AddActivity.this,MyWorldActivity.class);
                    submit_intent.putExtra("account",account);
                    startActivity(submit_intent);
                    finish();
                }

            }
        });

    }

    private void set_private_onClickListener(){
        private_style.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if_private = true;
                Toast.makeText(AddActivity.this,"树洞仅自己可见",Toast.LENGTH_SHORT).show();
            }
        });
        public_style.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if_private = false;
                Toast.makeText(AddActivity.this,"树洞所有人可见",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void set_style_onClickListener(){
        default_style.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                style = "default";
                Toast.makeText(AddActivity.this,"发布在默认分区",Toast.LENGTH_SHORT).show();
            }
        });
        life_style.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                style = "life";
                Toast.makeText(AddActivity.this,"发布在生活区",Toast.LENGTH_SHORT).show();
            }
        });
        study_style.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                style = "study";
                Toast.makeText(AddActivity.this,"发布在学习区",Toast.LENGTH_SHORT).show();
            }
        });
        work_style.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                style = "work";
                Toast.makeText(AddActivity.this,"发布在工作区",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
