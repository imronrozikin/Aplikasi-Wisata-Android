package com.example.healthtourism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.healthtourism.Model.KategoriModel;
import com.example.healthtourism.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Kategori extends AppCompatActivity {
    private ImageView imageView, imgSport, imgKesehatan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Kategori");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() == null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String provinsi = intent.getStringExtra("provinsi");
        imageView = (ImageView)findViewById(R.id.menu_image_wellness);
        imgSport =(ImageView)findViewById(R.id.menu_image_sport);
        imgKesehatan =(ImageView)findViewById(R.id.menu_image_kesehatan);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ListWisata.class);
                i.putExtra("provinsi",provinsi);
                startActivity(i);
            }
        });

        imgSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ListSport.class);
                i.putExtra("provinsi",provinsi);
                startActivity(i);
            }
        });
        imgKesehatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ListHealth.class);
                i.putExtra("provinsi",provinsi);
                startActivity(i);
            }
        });
    }

    @Override
    public  boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

}
