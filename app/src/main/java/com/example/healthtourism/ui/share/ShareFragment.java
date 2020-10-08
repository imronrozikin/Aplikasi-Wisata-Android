package com.example.healthtourism.ui.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.healthtourism.ListHealth;
import com.example.healthtourism.ListSport;
import com.example.healthtourism.ListWisata;
import com.example.healthtourism.R;
import com.google.android.material.navigation.NavigationView;

public class ShareFragment extends Fragment {

    private ImageView imageView, imgSport, imgKesehatan;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_share, container, false);
        DrawerLayout drawer = root.findViewById(R.id.drawer_layout);
        NavigationView navigationView = root.findViewById(R.id.nav_view);
        //tring provinsi = getActivity().getIntent().getStringExtra("provinsi");
        Bundle bundle =this.getArguments();
        String provinsi = bundle.getString("provinsi");
        imageView = (ImageView)root.findViewById(R.id.menu_image_wellness);
        imgSport =(ImageView)root.findViewById(R.id.menu_image_sport);
        imgKesehatan =(ImageView)root.findViewById(R.id.menu_image_kesehatan);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ListWisata.class);
                i.putExtra("provinsi",provinsi);
                startActivity(i);
            }
        });

        imgSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ListSport.class);
                i.putExtra("provinsi",provinsi);
                startActivity(i);
            }
        });
        imgKesehatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ListHealth.class);
                i.putExtra("provinsi",provinsi);
                startActivity(i);
            }
        });

        return root;
    }
}