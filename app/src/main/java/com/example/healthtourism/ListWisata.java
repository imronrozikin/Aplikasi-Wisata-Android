package com.example.healthtourism;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.healthtourism.Interface.ItemClickListener;
import com.example.healthtourism.Model.KategoriModel;
import com.example.healthtourism.Model.WisataModel;
import com.example.healthtourism.ViewHolder.MenuViewHolder;
import com.example.healthtourism.ViewHolder.WisataViewHolder;
import com.example.healthtourism.decoration.LayoutMarginDecoration;
import com.example.healthtourism.utils.Tools;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.smarteist.autoimageslider.SliderLayout;
import com.squareup.picasso.Picasso;

public class ListWisata extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference wisata;
    Query query;
    String provinsi;
    RecyclerView recyclerView;
    LayoutMarginDecoration gridMargin;
    RecyclerView.LayoutManager layoutManager;
    LayoutAnimationController layoutAnimationController;
    FirebaseRecyclerAdapter<WisataModel, WisataViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wisata);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Daftar Wisata");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = (RecyclerView)findViewById(R.id.rvwisata);
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_item_from_left);
        recyclerView.setHasFixedSize(true);
        String kategori = "Wellnes";
        database = FirebaseDatabase.getInstance();
        wisata = database.getReference("Wellnes");

        Intent intent = getIntent();
        provinsi = intent.getStringExtra("provinsi");

        if ("Semua".equals(provinsi)) {
            query = wisata;
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(gridLayoutManager);
            gridMargin = new LayoutMarginDecoration(2, Tools.dp2px(this, 4));
            recyclerView.addItemDecoration(gridMargin);
            recyclerView.setHasFixedSize(true);

            adapter = new FirebaseRecyclerAdapter<WisataModel, WisataViewHolder>(WisataModel.class,R.layout.item_list_wisata,WisataViewHolder.class,query) {
                @Override
                protected void populateViewHolder(WisataViewHolder viewHolder, final WisataModel model, int position) {
                    viewHolder.txtMenuName.setText(model.getNama());
                    viewHolder.txtProvinsi.setText(model.getProvinsi());
                    Picasso.get().load(model.getGambar())
                            .into(viewHolder.imageView);
                    final WisataModel clickItem = model;
                    viewHolder.setOnClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongclick) {
                            Intent intent = new Intent(ListWisata.this, DetailWisata.class);
                            intent.putExtra("detailWisata", adapter.getRef(position).getKey());
                            intent.putExtra("kategori",kategori);
//                          intent.putExtra("koordinat", model.getKoordinat());
                            startActivity(intent);
                        }
                    });
                }

            };
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutAnimation(layoutAnimationController);
        }else {
            query = wisata.orderByChild("provinsi").equalTo(provinsi);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            adapter = new FirebaseRecyclerAdapter<WisataModel, WisataViewHolder>(WisataModel.class,R.layout.list_wisata_2,WisataViewHolder.class,query) {
                @Override
                protected void populateViewHolder(WisataViewHolder viewHolder, final WisataModel model, int position) {
                    viewHolder.txtMenuName.setText(model.getNama());
                    viewHolder.txtProvinsi.setText(model.getProvinsi());
                    Picasso.get().load(model.getGambar())
                            .into(viewHolder.imageView);
                    final WisataModel clickItem = model;
                    viewHolder.setOnClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongclick) {
                            Intent intent = new Intent(ListWisata.this, DetailWisata.class);
                            intent.putExtra("detailWisata", adapter.getRef(position).getKey());
//                          intent.putExtra("koordinat", model.getKoordinat());
                            startActivity(intent);
                        }
                    });
                }

            };
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutAnimation(layoutAnimationController);
        }


    }

    private  void firebaseSearch(String searchText){

        Query firebaseSearchQuery = wisata.orderByChild("nama").startAt(searchText).endAt(searchText+"\uf8ff");

        FirebaseRecyclerAdapter<WisataModel, WisataViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<WisataModel, WisataViewHolder>(
                    WisataModel.class, R.layout.item_list_wisata,WisataViewHolder.class,firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(WisataViewHolder wisataViewHolder, final WisataModel wisataModel, int postition) {
                wisataViewHolder.txtMenuName.setText(wisataModel.getNama());
                Picasso.get().load(wisataModel.getGambar())
                        .into(wisataViewHolder.imageView);
                final WisataModel clickItem = wisataModel;
                wisataViewHolder.setOnClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongclick) {
                        Intent intent = new Intent(ListWisata.this, DetailWisata.class);
                        intent.putExtra("detailWisata", adapter.getRef(position).getKey());
//                          intent.putExtra("koordinat", model.getKoordinat());
                        startActivity(intent);
                    }
                });
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
        recyclerView.setLayoutAnimation(layoutAnimationController);
    }

//        public void loadMenu(){
//
//
//    }

    @Override
    public  boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if ("Semua".equals(provinsi)) {
            getMenuInflater().inflate(R.menu.main2, menu);
            MenuItem item = menu.findItem(R.id.search);
            SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    firebaseSearch(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    firebaseSearch(s);
                    return false;
                }
            });
        }
        return true;
    }

}
