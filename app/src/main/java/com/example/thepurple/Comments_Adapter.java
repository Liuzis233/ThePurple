package com.example.thepurple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.thepurple.db.Comments;

import java.util.List;

public class Comments_Adapter extends RecyclerView.Adapter<Comments_Adapter.ViewHolder> {
    private List<Comments> Comments_list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View comments_view;
        TextView my_comments;//评论内容
        TextView comments_submit_time;//发布时间
        public ViewHolder(View view) {
            super(view);
            comments_view = view;
            my_comments = (TextView) view.findViewById(R.id.my_comments);
            comments_submit_time = (TextView) view.findViewById(R.id.comments_submit_time);
        }
    }
    public Comments_Adapter(List<Comments> comments_list){
        Comments_list = comments_list;
    }
    @Override
    public Comments_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_recycler,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(Comments_Adapter.ViewHolder holder, int position){
        Comments msg_comment = Comments_list.get(position);
        holder.my_comments.setText(msg_comment.getComment());
        holder.comments_submit_time.setText(msg_comment.getSubmitComment());
    }
    @Override
    public int getItemCount(){return Comments_list.size();}
}
