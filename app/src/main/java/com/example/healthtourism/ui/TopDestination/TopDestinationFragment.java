package com.example.healthtourism.ui.TopDestination;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthtourism.Common.CommonWisata;
import com.example.healthtourism.Common.SpaceItemDecoration;
import com.example.healthtourism.DetailWisata;
import com.example.healthtourism.Interface.ItemClickListener;
import com.example.healthtourism.ListWisata;
import com.example.healthtourism.Model.CommentModel;
import com.example.healthtourism.Model.WisataModel;
import com.example.healthtourism.R;
import com.example.healthtourism.ViewHolder.CommentViewHolder;
import com.example.healthtourism.ViewHolder.TopDestinationViewHolder;
import com.example.healthtourism.ViewHolder.WisataViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TopDestinationFragment extends Fragment {
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<WisataModel, TopDestinationViewHolder> adapter;
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
        topDestinationViewModel =
                ViewModelProviders.of(this).get(TopDestinationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_top_destination, container, false);

        database = FirebaseDatabase.getInstance();
        wisata = database.getReference("Wellnes");

        Query query = wisata.orderByChild("status").equalTo("top_destination");
        recyclerView = (RecyclerView)root.findViewById(R.id.rvTopDestionation);

        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_item_from_left);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FirebaseRecyclerAdapter<WisataModel, TopDestinationViewHolder>(WisataModel.class,R.layout.list_top_destination,TopDestinationViewHolder.class, query) {
            @Override
            protected void populateViewHolder(TopDestinationViewHolder viewHolder, final WisataModel model, int position) {
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