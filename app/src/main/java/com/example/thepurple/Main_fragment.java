package com.example.thepurple;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.thepurple.db.AccountMesg;

import org.litepal.LitePal;

import java.util.List;

public class Main_fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.main_frag,container,false);
        //实现瀑布布局，初始时在首页
        RecyclerView recyclerview = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        Ground_Adapter adapter = new Ground_Adapter(getdefaultMesgList());
        recyclerview.setAdapter(adapter);
        return view;
    }
    private List<AccountMesg> getdefaultMesgList(){
        List<AccountMesg> defaultMesgList = LitePal.where("if_private = ?",
                "false").find(AccountMesg.class);//选出所有允许公开的树洞
        if(defaultMesgList.size()==0){
            //如果分区为空设置默认树洞列表
            for(int i=0;i<20;i++){
                AccountMesg mesg1 = new AccountMesg("admin1");//创建一个默认分区公开消息
                mesg1.setMsg("今天默认分区的天气很不错，你觉得呢？");
                mesg1.save();
                defaultMesgList.add(mesg1);
                AccountMesg mesg2 = new AccountMesg("admin2");//创建一个生活分区公开消息
                mesg2.setMsg("今天生活区的天气很不错，你觉得呢？");
                mesg2.setStyle("life");
                mesg2.save();
                defaultMesgList.add(mesg2);
                AccountMesg mesg3 = new AccountMesg("admin3");//创建一个学习分区公开消息
                mesg3.setMsg("今天学习区的天气很不错，你觉得呢？");
                mesg3.setStyle("study");
                mesg3.save();
                defaultMesgList.add(mesg3);
                AccountMesg mesg4 = new AccountMesg("admin4");//创建一个工作分区公开消息
                mesg4.setMsg("今天工作区的天气很不错，你觉得呢？");
                mesg4.setStyle("work");
                mesg4.save();
                defaultMesgList.add(mesg4);
            }
        }
        return defaultMesgList;
    }
}
