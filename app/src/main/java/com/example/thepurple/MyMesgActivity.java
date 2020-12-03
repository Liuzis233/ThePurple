package com.example.thepurple;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thepurple.db.AccountMesg;
import com.example.thepurple.db.Comments;

import org.litepal.LitePal;

import java.util.Date;
import java.util.List;

public class MyMesgActivity extends AppCompatActivity {

    private TextView treehole_msg;
    private ImageView treehole_image;
    private ImageView deleteit;
    private RecyclerView recyclerview;
    private List<Comments> comments_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_mesg);
        treehole_msg = (TextView) findViewById(R.id.my_treehole_msg);
        treehole_image = (ImageView) findViewById(R.id.my_treehole_image);
        deleteit = (ImageView) findViewById(R.id.deleteit);
        deleteit.setImageResource(R.mipmap.deleteit);
        Intent intent = getIntent();
        final AccountMesg accountMesg = (AccountMesg) intent.getSerializableExtra("accountMesg");
        treehole_msg.setText(accountMesg.getMsg());
        treehole_image.setImageResource(accountMesg.getImageId());
        init_commentslist(accountMesg.getAccount(),accountMesg.gettime());
        //实现瀑布布局
        recyclerview = (RecyclerView) findViewById(R.id.my_comments_recycler);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        Comments_Adapter adapter = new Comments_Adapter(comments_list);
        recyclerview.setAdapter(adapter);

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
                        LitePal.deleteAll(AccountMesg.class, "str_submit_time =%s"+accountMesg.getSubmit_time()
                                +" and account = "+accountMesg.getAccount());
                        //根据提交时间和账户找出树洞消息并删除
                        LitePal.deleteAll(Comments.class, "submit_mesg_time = "+accountMesg.getSubmit_time()
                                +" and account = "+accountMesg.getAccount());
                        //根据提交时间和账户找出树洞评论并删除
                        Toast.makeText(MyMesgActivity.this,"成功删除",Toast.LENGTH_SHORT).show();
                        intent1.putExtra("account",accountMesg.getAccount());
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


    public void init_commentslist(String account, Date submit_time){//初始化评论列表
        comments_list = LitePal.where("account = ? and submit_mesg_time = ?",account,""+submit_time).find(Comments.class);
        //根据评论对应消息的账号和消息发布时间选出所有评论
    }
}
