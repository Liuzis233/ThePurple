package com.example.thepurple;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    private TextView default_style;
    private String style;
    private boolean if_private;
    private String[] items = new String[]{"默认","生活", "学习", "工作"};
    private int mWhich = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        style = "default";
        if_private = false;
        Intent intent = getIntent();//获取账号
        final String account = intent.getStringExtra("account");
        private_style = (TextView) findViewById(R.id.public_private);
        default_style = (TextView) findViewById(R.id.change_styles);
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
                    switch (mWhich){//判断分区
                        case 0:
                            style = "default";
                            break;
                        case 1:
                            style = "life";
                            break;
                        case 2:
                            style = "study";
                            break;
                        case 3:
                            style = "work";
                            break;
                        default:
                            break;
                    }
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(AddActivity.this);
                dialog.setTitle("修改权限");
                dialog.setMessage("设置树洞为仅自己可见还是所有人可见（匿名）");
                dialog.setCancelable(false);//点返回键无法返回
                dialog.setPositiveButton("仅自己可见", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if_private = true;
                        private_style.setText("私密");
                        Toast.makeText(AddActivity.this,"树洞仅自己可见",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("所有人可见（匿名）", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if_private = false;
                        private_style.setText("公开");
                        Toast.makeText(AddActivity.this,"树洞所有人可见",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });
    }

    private void set_style_onClickListener(){

        default_style.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder dialog = new AlertDialog.Builder(AddActivity.this);
                dialog.setTitle("设置分区");
                dialog.setCancelable(false);//点返回键无法返回
                dialog.setSingleChoiceItems(items, mWhich, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        mWhich = which;
                    }
                });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        default_style.setText(items[mWhich]);
                        Toast.makeText(AddActivity.this,"发布在"+items[mWhich]+"分区",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        default_style.setText("默认");
                        mWhich = 0;
                        Toast.makeText(AddActivity.this,"发布在"+items[mWhich]+"分区",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });

    }
}
