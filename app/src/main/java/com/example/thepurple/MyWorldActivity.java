package com.example.thepurple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thepurple.db.AccountMesg;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyWorldActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private List<AccountMesg> myMesgList = new ArrayList<>();//我的树洞集
    private String account;//账号信息
    private ImageView myimage;
    private int random_id;
    private ImageView submit;
    private TextView main_ground;//底部跳转
    private TextView everyday_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_world);
        Intent intent = getIntent();
        account = intent.getStringExtra("account");//获取用户账号信息
        random_id = getRandomId();//随机生成一个头像
        myimage = (ImageView) findViewById(R.id.my_image);
        myimage.setImageResource(random_id);
        submit = (ImageView) findViewById(R.id.submit);
        submit.setImageResource(R.mipmap.like_1);
        main_ground = (TextView) findViewById(R.id.myground);
        everyday_image = (TextView) findViewById(R.id.everyday);

        init_mymsgList();//初始化树洞列表
        //实现瀑布布局
        recyclerview = (RecyclerView) findViewById(R.id.mesg_recycler);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        Mesg_Adapter adapter = new Mesg_Adapter(myMesgList);
        recyclerview.setAdapter(adapter);

        //发树洞
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent submit_intent = new Intent(MyWorldActivity.this,AddActivity.class);
                submit_intent.putExtra("account",account);
            }
        });

        //底部跳转
        main_ground.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent main_intent = new Intent(MyWorldActivity.this,MainActivity.class);
                main_intent.putExtra("account",account);
            }
        });
        everyday_image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent every_intent = new Intent(MyWorldActivity.this,EverydayImage.class);
                every_intent.putExtra("account",account);
            }
        });

    }

    private void init_mymsgList(){//初始化树洞列表
        myMesgList = LitePal.where("account = ?",
                account).find(AccountMesg.class);//选出所有本用户的树洞
    }

    public int getRandomId(){//每条消息随机生成头像
        Random rand = new Random();
        int image_id = rand.nextInt(16);
        switch (image_id){
            case 0:
                image_id = R.mipmap.image_b1;
                break;
            case 1:
                image_id = R.mipmap.image_b2;
                break;
            case 2:
                image_id = R.mipmap.image_b3;
                break;
            case 3:
                image_id = R.mipmap.image_b4;
                break;
            case 4:
                image_id = R.mipmap.image_b5;
                break;
            case 5:
                image_id = R.mipmap.image_b6;
                break;
            case 6:
                image_id = R.mipmap.image_b7;
                break;
            case 7:
                image_id = R.mipmap.image_g1;
                break;
            case 8:
                image_id = R.mipmap.image_g2;
                break;
            case 9:
                image_id = R.mipmap.image_g3;
                break;
            case 10:
                image_id = R.mipmap.image_g4;
                break;
            case 11:
                image_id = R.mipmap.image_g5;
                break;
            case 12:
                image_id = R.mipmap.image_g6;
                break;
            case 13:
                image_id = R.mipmap.image_g7;
                break;
            case 14:
                image_id = R.mipmap.image_g8;
                break;
            default:
                image_id = R.mipmap.image_g9;
                break;
        }
        return image_id;
    }
}
