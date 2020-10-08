package com.example.healthtourism.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.healthtourism.Interface.ItemClickListener;
import com.example.healthtourism.R;

public class RecommendedPlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txtMenuName, tvketerangan;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public RecommendedPlaceViewHolder(View itemView){
        super(itemView);

        txtMenuName = (TextView)itemView.findViewById(R.id.tvWisataRecommended);
        imageView = (ImageView)itemView.findViewById(R.id.imgWisataRecommended);

        itemView.setOnClickListener(this);
    }

    public void setOnClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view){
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
