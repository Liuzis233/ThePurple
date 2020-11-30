package com.example.thepurple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thepurple.db.AccountMesg;

public class MesgActivity extends AppCompatActivity {

    private Button send_comment;
    private ImageView likeit;
    private boolean if_like = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesg);
        send_comment = (Button) findViewById(R.id.send_comment);
        likeit = (ImageView) findViewById(R.id.likeit);
        likeit.setImageResource(R.mipmap.like);//默认是不点赞状态
        send_comment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){//评论功能
                Toast.makeText(MesgActivity.this,"发送成功",Toast.LENGTH_SHORT);
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
}
