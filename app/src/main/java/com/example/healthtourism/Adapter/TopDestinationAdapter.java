package com.example.healthtourism.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.healthtourism.Common.CommonWisata;
import com.example.healthtourism.Model.WisataModel;
import com.example.healthtourism.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TopDestinationAdapter extends RecyclerView.Adapter<TopDestinationAdapter.MyViewHolder> {
    Context context;
    List<WisataModel>wisataDestinasiList;

    public TopDestinationAdapter(Context context, List<WisataModel> wisataDestinasiList) {
        this.context = context;
        this.wisataDestinasiList = wisataDestinasiList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_top_destination,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(wisataDestinasiList.get(position).getGambar()).into(holder.img_destinasi);
        holder.nama_destinasi.setText(new StringBuilder(wisataDestinasiList.get(position).getNama()));
    }

    @Override
    public int getItemCount() {
        return wisataDestinasiList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Unbinder unbinder;
        @BindView(R.id.imgDestinasi)
        ImageView img_destinasi;
        @BindView(R.id.tvNamaDestinasi)
        TextView nama_destinasi;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(wisataDestinasiList.size() == 1){
            return CommonWisata.DEFAULT_COLUMN_COUNT;
        }else {
            if(wisataDestinasiList.size() % 2 == 0 ){
                return CommonWisata.DEFAULT_COLUMN_COUNT;
            }else {
                return (position > 1 && position == wisataDestinasiList.size()-1) ? CommonWisata.FULL_WIDTH_COLUMN:CommonWisata.DEFAULT_COLUMN_COUNT;
            }
        }
    }
}
