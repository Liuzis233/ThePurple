package com.example.thepurple;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.thepurple.db.AccountMesg;

import java.util.List;

public class Ground_Adapter extends RecyclerView.Adapter<Ground_Adapter.ViewHolder> {
    private List<AccountMesg> MsgList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView account_image;
        TextView account_msg;
        View mesg_view;

        public ViewHolder(View view){
            super(view);
            mesg_view = view;
            account_image = (ImageView) view.findViewById(R.id.account_image);
            account_msg = (TextView) view.findViewById(R.id.mesg);
        }
    }
    public Ground_Adapter(List<AccountMesg> msgs){
        MsgList = msgs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_recycler,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        //点击整个树洞消息范围都会显示
        holder.mesg_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                AccountMesg accountmesg = MsgList.get(position);
                //给树洞消息显示界面传递消息对象
                Intent intent = new Intent(v.getContext(), MesgActivity.class);
                intent.putExtra("account_mesg", accountmesg);
                v.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        AccountMesg accountmsg = MsgList.get(position);
        holder.account_image.setImageResource(accountmsg.getImageId());
        holder.account_msg.setText(accountmsg.getMsg());
    }

    @Override
    public int getItemCount(){
        return MsgList.size();
    }
}
