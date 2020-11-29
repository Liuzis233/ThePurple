package com.example.thepurple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.example.thepurple.db.AccountMesg;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<AccountMesg> accountMesgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.getDatabase();
        init_msgList();
        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        Ground_Adapter adapter = new Ground_Adapter(accountMesgList);
        recyclerview.setAdapter(adapter);
    }

    private void init_msgList(){
        accountMesgList = LitePal.where("if_private = ?",
                "false").find(AccountMesg.class);//选出所有允许公开的消息
    }
}
