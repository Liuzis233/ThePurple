package com.example.thepurple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thepurple.db.AccountMesg;
import com.example.thepurple.db.Comments;

import org.litepal.LitePal;

import java.util.List;

public class MesgActivity extends AppCompatActivity {

    private TextView treehole_msg;
    private ImageView treehole_image;
    private Button send_comment;
    private ImageView likeit;
    private boolean if_like = false;
    private EditText comment;
    private RecyclerView recyclerview;
    private List<Comments> my_comments_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesg);
        treehole_msg = (TextView) findViewById(R.id.treehole_msg);
        treehole_image = (ImageView) findViewById(R.id.treehole_image);
        comment = (EditText) findViewById(R.id.comment);
        send_comment = (Button) findViewById(R.id.send_comment);
        likeit = (ImageView) findViewById(R.id.likeit);
        likeit.setImageResource(R.mipmap.like);//默认是不点赞状态
        Intent intent = getIntent();
        final AccountMesg accountMesg = (AccountMesg) intent.getSerializableExtra("account_mesg");
        final long mesg_id = accountMesg.getId();
        init_commentslist(mesg_id);
        treehole_msg.setText(accountMesg.getMsg());
        treehole_image.setImageResource(accountMesg.getImageId());
        //实现瀑布布局
        recyclerview = (RecyclerView) findViewById(R.id.comments_recycler);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        Comments_Adapter adapter = new Comments_Adapter(my_comments_list);
        recyclerview.setAdapter(adapter);

        send_comment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){//评论功能
                if(comment.getText().toString().length()!=0){
                    Comments your_comment = new Comments(accountMesg.getAccount(),mesg_id,comment.getText().toString());
                    your_comment.save();
                    Toast.makeText(MesgActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
        likeit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!if_like){//如果此时没有点赞，则设置为点赞图片
                    likeit.setImageResource(R.mipmap.like_1);
                    if_like = true;
                }else{//如果此时是点赞状态，则设置为不点赞图片
                    likeit.setImageResource(R.mipmap.like);
                    if_like = false;
                }
            }
        });
    }
    public void init_commentslist(long mesg_id){//初始化评论列表
        my_comments_list = LitePal.where("msg_id = ?","mesg_id").find(Comments.class);
        //选出所有评论
    }
}
