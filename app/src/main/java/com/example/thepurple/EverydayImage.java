package com.example.thepurple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thepurple.db.EverydayMesg;
import com.example.thepurple.util.HttpUtil;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EverydayImage extends AppCompatActivity {

    private ImageView bingPicImg;
    private EditText editText;
    private TextView msg;
    private Button send;
    private TextView main_ground;
    private TextView my_world;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LitePal.getDatabase();//获取数据库
        //将背景图与状态栏融合在一起
        if(Build.VERSION.SDK_INT >= 21){//系统版本号判断
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_everyday_image);
        //获取背景图，从缓存中加载，如果没有就调用loadBingPic去请求
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        bingPicImg = (ImageView) findViewById(R.id.bing_pic_img);
        String bingPic = prefs.getString("bing_pic", null);
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(bingPicImg);
        } else {
            loadBingPic();
        }
        msg = (TextView) findViewById(R.id.text_id);
        editText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        main_ground = (TextView) findViewById(R.id.myground);
        my_world = (TextView) findViewById(R.id.myworld);

        Intent intent = getIntent();
        final String account = intent.getStringExtra("account");//获取用户账号信息

        msg.setText(random_msg());//显示每日一句
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit = editText.getText().toString();
                if(edit.length()!=0&&edit.length()<=20){
                    //如果字符串不为空且字符串长度不超过20则存入数据库中
                    EverydayMesg new_msg = new EverydayMesg();
                    new_msg.setAccount(account);
                    new_msg.setMsg(edit);
                    new_msg.save();
                    Toast.makeText(EverydayImage.this,"投稿成功",Toast.LENGTH_SHORT).show();
                }else{//其他情况提示投稿失败
                    Toast.makeText(EverydayImage.this,"投稿失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //底部跳转
        main_ground.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent main_intent = new Intent(EverydayImage.this,MainActivity.class);
                main_intent.putExtra("account",account);
                startActivity(main_intent);
            }
        });
        my_world.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent my_intent = new Intent(EverydayImage.this,MyWorldActivity.class);
                my_intent.putExtra("account",account);
                startActivity(my_intent);
            }
        });
    }

    /**
     * 加载必应每日一图
     */
    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.
                        getDefaultSharedPreferences(EverydayImage.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(EverydayImage.this).load(bingPic).into(bingPicImg);
                    }
                });
            }

            @Override
            public void onFailure(Call call,IOException e){
                e.printStackTrace();
            }
        });
    }
    /**
    *从数据库中随机选择一句话作为每日一句
     */
    private String random_msg(){
        String day_mesg;
        Random rand = new Random();
        int msg_num = LitePal.count(EverydayMesg.class);//求数据库中标的数量
        if(msg_num == 0){
            day_mesg = "你真的真的真的真的真的真的真的很不错!";
        }else{
            int image_id = rand.nextInt(msg_num)+1;//随机取出一句话
            day_mesg = LitePal.find(EverydayMesg.class,image_id).getMsg();
        }
        return day_mesg;
    }

}