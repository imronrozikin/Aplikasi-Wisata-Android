package com.example.healthtourism.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.healthtourism.Interface.ItemClickListener;
import com.example.healthtourism.R;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    public TextView txtMenuName,txtComment;
    public RatingBar rating;


    public CommentViewHolder(View itemView){
        super(itemView);

        rating = (RatingBar)itemView.findViewById(R.id.rating_bar);
        txtMenuName = (TextView)itemView.findViewById(R.id.txt_comment_name);
        txtComment = (TextView)itemView.findViewById(R.id.txt_comment);

    }


}
