package com.example.thepurple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thepurple.db.AccountMesg;

public class AddActivity extends AppCompatActivity {

    private EditText edit_text;
    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Intent intent = getIntent();//获取账号
        final String account = intent.getStringExtra("account");
        edit_text = (EditText) findViewById(R.id.edit_mesg);
        edit = (Button) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(edit_text.toString().length()!=0){
                    //如果字符串不为空
                    AccountMesg accountmesg = new AccountMesg(account);
                    accountmesg.setMsg(edit_text.toString());
                    accountmesg.save();
                }
                Intent submit_intent = new Intent(AddActivity.this,MyWorldActivity.class);
                submit_intent.putExtra("account",account);
            }
        });

    }
}
