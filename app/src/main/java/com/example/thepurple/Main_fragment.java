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
        return defaultMesgList;
    }
}