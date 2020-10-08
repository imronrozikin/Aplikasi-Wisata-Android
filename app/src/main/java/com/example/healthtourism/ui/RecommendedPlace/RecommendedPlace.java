package com.example.healthtourism.ui.RecommendedPlace;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthtourism.DetailWisata;
import com.example.healthtourism.Interface.ItemClickListener;
import com.example.healthtourism.Model.WisataModel;
import com.example.healthtourism.R;
import com.example.healthtourism.ViewHolder.RecommendedPlaceViewHolder;
import com.example.healthtourism.ViewHolder.TopDestinationViewHolder;
import com.example.healthtourism.ui.TopDestination.TopDestinationViewModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.Unbinder;

public class RecommendedPlace extends Fragment {

    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<WisataModel, RecommendedPlaceViewHolder> adapter;
    private TopDestinationViewModel topDestinationViewModel;
    FirebaseDatabase database;
    DatabaseReference wisata, comment_ref;
    Unbinder unbinder;
    AlertDialog alertDialog;
    LayoutAnimationController layoutAnimationController;

    @BindView(R.id.rvTopDestionation)
    RecyclerView recyclerViewTop;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recommended_place, container, false);
        database = FirebaseDatabase.getInstance();
        wisata = database.getReference("Wellnes");

        Query query = wisata.orderByChild("status2").equalTo("recommendedPlace");
        recyclerView = (RecyclerView)root.findViewById(R.id.rvRecommendedPlace);

        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_item_from_left);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FirebaseRecyclerAdapter<WisataModel, RecommendedPlaceViewHolder>(WisataModel.class,R.layout.item_recommended_place,RecommendedPlaceViewHolder.class, query) {
            @Override
            protected void populateViewHolder(RecommendedPlaceViewHolder viewHolder, final WisataModel model, int position) {
                Picasso.get().load(model.getGambar())
                        .into(viewHolder.imageView);
                viewHolder.txtMenuName.setText(model.getNama());
                final WisataModel clickItem = model;
                viewHolder.setOnClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongclick) {
                        Intent intent = new Intent(getContext(), DetailWisata.class);
                        intent.putExtra("detailWisata", adapter.getRef(position).getKey());
//                          intent.putExtra("koordinat", model.getKoordinat());
                        startActivity(intent);
                    }
                });
            }
        };


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutAnimation(layoutAnimationController);

        return root;
    }
}