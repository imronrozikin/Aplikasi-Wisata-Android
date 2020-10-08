package com.example.healthtourism.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthtourism.Model.CommentModel;
import com.example.healthtourism.R;
import com.example.healthtourism.Rating;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    Context context;
    List<CommentModel>commentModelList;

    public CommentAdapter(Context context, List<CommentModel> commentModelList) {
        this.context = context;
        this.commentModelList = commentModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_comment_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_comment_name.setText(commentModelList.get(position).getNama());
        holder.txt_comment.setText(commentModelList.get(position).getComment());
        holder.rating.setRating(commentModelList.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return commentModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Unbinder unbinder;

        @BindView(R.id.txt_comment_name)
        TextView txt_comment_name;
        @BindView(R.id.rating_bar)
        RatingBar rating;
        @BindView(R.id.txt_comment)
        TextView txt_comment;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            unbinder = ButterKnife.bind(this, itemView);
        }
    }
}
