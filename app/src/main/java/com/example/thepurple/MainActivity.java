package com.example.thepurple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thepurple.db.AccountMesg;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<AccountMesg> defaultMesgList = new ArrayList<>();//默认区树洞集
    private List<AccountMesg> lifeMesgList = new ArrayList<>();//生活区树洞集
    private List<AccountMesg> studyMesgList = new ArrayList<>();//学习区树洞集
    private List<AccountMesg> workMesgList = new ArrayList<>();//工作区树洞集
    private TextView everday;
    private TextView me;
    private String account;

    private RecyclerView recyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.getDatabase();//获取数据库
        init_main_msgList();//初始化各树洞列表
        init_life_msgList();
        init_study_msgList();
        init_work_msgList();
        //实现瀑布布局，初始时在首页
        recyclerview = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        Ground_Adapter adapter = new Ground_Adapter(defaultMesgList);
        recyclerview.setAdapter(adapter);
        //获取用户账号信息
        Intent intent = getIntent();
        account = intent.getStringExtra("account");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_field:
                // 当点击了首页时
                Ground_Adapter main_adapter = new Ground_Adapter(defaultMesgList);
                recyclerview.setAdapter(main_adapter);
                break;
            case R.id.life:
                // 当点击了生活时
                Ground_Adapter life_adapter = new Ground_Adapter(lifeMesgList);
                recyclerview.setAdapter(life_adapter);
                break;
            case R.id.study:
                // 当点击了学习时
                Ground_Adapter study_adapter = new Ground_Adapter(studyMesgList);
                recyclerview.setAdapter(study_adapter);
                break;
            case R.id.work:
                // 当点击了工作时
                Ground_Adapter work_adapter = new Ground_Adapter(workMesgList);
                recyclerview.setAdapter(work_adapter);
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
            default:
                Toast.makeText(MainActivity.this,"点击无效",Toast.LENGTH_SHORT);
                break;
        }
    }


    private void init_main_msgList(){
        defaultMesgList = LitePal.where("if_private = ?",
                "false").find(AccountMesg.class);//选出所有允许公开的树洞
    }
    private void init_life_msgList(){
        lifeMesgList = LitePal.where("if_private = ? and style = ?",
                "false","life").find(AccountMesg.class);//选出所有允许公开的生活区树洞
    }
    private void init_study_msgList(){
        studyMesgList = LitePal.where("if_private = ? and style = ?",
                "false","study").find(AccountMesg.class);//选出所有允许公开的学习区树洞
    }
    private void init_work_msgList(){
        workMesgList = LitePal.where("if_private = ? and style = ?",
                "false","work").find(AccountMesg.class);//选出所有允许公开的工作区树洞
    }
}
