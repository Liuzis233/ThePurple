package com.example.thepurple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import org.litepal.LitePal;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private String account;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.getDatabase();//获取数据库
        //获取用户账号信息
        Intent intent = getIntent();
        account = intent.getStringExtra("account");
        initWidght();//添加点击事件
        replaceFragment(new Main_fragment());
        //初始碎片为默认分区

    }
    public void initWidght(){
        findViewById(R.id.main_field).setOnClickListener(this);
        findViewById(R.id.life).setOnClickListener(this);
        findViewById(R.id.study).setOnClickListener(this);
        findViewById(R.id.work).setOnClickListener(this);
        findViewById(R.id.myworld).setOnClickListener(this);
        findViewById(R.id.everyday).setOnClickListener(this);
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_id,fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_field:
                // 当点击了首页时，跳转默认分区碎片布局
                replaceFragment(new Main_fragment());
                break;
            case R.id.life:
                // 当点击了生活时，跳转生活区的碎片布局
                replaceFragment(new Life_fragment());
                break;
            case R.id.study:
                // 当点击了学习时，跳转学习区的碎片布局
                replaceFragment(new Study_fragment());
                break;
            case R.id.work:
                // 当点击了工作时，跳转工作区的碎片布局
                replaceFragment(new Work_fragment());
                break;
            case R.id.myworld:
                //当点击底部我的时候
                Intent me_intent = new Intent(MainActivity.this, MyWorldActivity.class);
                me_intent.putExtra("account",account);
                startActivity(me_intent);
                break;
            case R.id.everyday:
                //当点击每日一句时
                Intent everday_intent = new Intent(MainActivity.this, EverydayImage.class);
                everday_intent.putExtra("account",account);
                startActivity(everday_intent);
                break;
            default:
                Toast.makeText(MainActivity.this,"点击无效",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
