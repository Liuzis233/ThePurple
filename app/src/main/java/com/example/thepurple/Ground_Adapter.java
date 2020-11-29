package com.example.thepurple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Ground_Adapter extends RecyclerView.Adapter<Ground_Adapter.ViewHolder> {
    private List<AccountMesg> MsgList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView account_image;
        TextView account_msg;

        public ViewHolder(View view){
            super(view);
            account_image = (ImageView) view.findViewById(R.id.account_image);
            account_msg = (TextView) view.findViewById(R.id.mesg);
        }
    }
    public Ground_Adapter(List<AccountMesg> msgs){
        MsgList = msgs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylcer_ground,
                parent,false);
        ViewHolder holder = new ViewHolder(view);
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
