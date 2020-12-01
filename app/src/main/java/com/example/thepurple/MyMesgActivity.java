package com.example.thepurple;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.thepurple.db.AccountMesg;
import com.example.thepurple.db.Comments;

import org.litepal.LitePal;

import java.util.List;

public class MyMesgActivity extends AppCompatActivity {

    private ImageView deleteit;
    private RecyclerView recyclerview;
    private List<Comments> comments_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_mesg);
        deleteit = (ImageView) findViewById(R.id.deleteit);
        deleteit.setImageResource(R.mipmap.deleteit);
        Intent intent = getIntent();
        final AccountMesg accountMesg = (AccountMesg) intent.getSerializableExtra("account_mesg");
        final long mesg_id = accountMesg.getId();
        init_commentslist();
        //实现瀑布布局

        deleteit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){//弹出是否确认删除的对话框，点返回键不可返回
                AlertDialog.Builder dialog = new AlertDialog.Builder(MyMesgActivity.this);
                dialog.setTitle("注意！");
                dialog.setMessage("是否确定删除此树洞？");
                dialog.setCancelable(false);//点返回键无法返回
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1 = new Intent(MyMesgActivity.this,MyWorldActivity.class);
                        intent1.putExtra("account",accountMesg.getAccount());
                        accountMesg.delete();//删除数据，销毁活动
                        startActivity(intent1);
                        finish();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
            }
        });

    }


    public void init_commentslist(){//初始化评论列表
        comments_list = LitePal.where("msg_id = ?","mesg_id").find(Comments.class);
        //选出所有评论
    }
}