package com.example.thepurple;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.thepurple.db.AccountMesg;

import java.util.List;

public class Mesg_Adapter extends RecyclerView.Adapter<Mesg_Adapter.ViewHolder> {
    private List<AccountMesg> MsgList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View mesg_view;
        TextView my_msg;//树洞内容
        TextView submit_time;//发布时间
        TextView msg_style;//发布类型
        TextView msg_private;//是否仅自己可见

        public ViewHolder(View view){
            super(view);
            mesg_view = view;
            my_msg = (TextView) view.findViewById(R.id.my_mesg);
            submit_time = (TextView) view.findViewById(R.id.submit_time);
            msg_style = (TextView) view.findViewById(R.id.msg_style);
            msg_private = (TextView) view.findViewById(R.id.msg_private);
        }
    }
    public Mesg_Adapter(List<AccountMesg> msgs){
        MsgList = msgs;
    }
    @Override
    public Mesg_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mesg_recycler,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        //点击整个树洞消息范围都会显示
        holder.mesg_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                AccountMesg accountmesg = MsgList.get(position);
                //给树洞消息显示界面传递消息对象
                Intent intent = new Intent(v.getContext(), MyMesgActivity.class);
                intent.putExtra("accountMesg",accountmesg);
                v.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(Mesg_Adapter.ViewHolder holder, int position){
        AccountMesg accountmsg = MsgList.get(position);
        holder.my_msg.setText(accountmsg.getMsg());
        holder.submit_time.setText(accountmsg.getSubmit_time());
        holder.msg_style.setText(accountmsg.getStyle());
        boolean if_private = accountmsg.getIf_private();
        if(if_private){
            holder.msg_private.setText("仅自己可见");
        }else{
            holder.msg_private.setText("所有人可见");
        }
    }

    @Override
    public int getItemCount(){
        return MsgList.size();
    }
}
