package com.example.healthtourism.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.healthtourism.Kategori;
import com.example.healthtourism.Main2Activity;
import com.example.healthtourism.R;
import com.example.healthtourism.ui.share.ShareFragment;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private HomeViewModel homeViewModel;
    private Button btnSearch;
    private SliderLayout sliderLayout;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        sliderLayout = (SliderLayout)root.findViewById(R.id.img_slider);

        final Spinner spinner = (Spinner)root.findViewById(R.id.provinsi);
        List<String> categories = new ArrayList<String>();
        categories.add("Semua");
        categories.add("Aceh");
        categories.add("Sumatera Utara");
        categories.add("Riau");
        categories.add("Jambi");
        categories.add("Bengkulu");
        categories.add("Sumatera Selatan");
        categories.add("Lampung");
        categories.add("Bangka Belitung");
        categories.add("DKI Jakarta");
        categories.add("Jawa Barat");
        categories.add("Jawa Tengah");
        categories.add("D.I. Yogyakarat");
        categories.add("Jawa Timur");
        categories.add("Banten");
        categories.add("Bali");
        categories.add("NTB");
        categories.add("NTT");
        categories.add("Kalimantan Barat");
        categories.add("Kalimantan Tengah");
        categories.add("Kalimantan Selatan");
        categories.add("Kalimantan Timur");
        categories.add("Kalimantan Utara");
        categories.add("Sulawesi Utara");
        categories.add("Sulawesi Tengah");
        categories.add("Selawesi Selatan");
        categories.add("Sulawesi Tenggara");
        categories.add("Gorontalo");
        categories.add("Sulawesi Barat");
        categories.add("Maluku");
        categories.add("Maluku Utara");
        categories.add("Papua");
        categories.add("Papua Barat");
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(1);

        btnSearch = (Button) root.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle =new Bundle();
                bundle.putString("provinsi",String.valueOf(spinner.getSelectedItem()));

                ShareFragment shareFragment = new ShareFragment();
                shareFragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.nav_host_fragment,shareFragment);
                fragmentTransaction.replace(R.id.nav_host_fragment,shareFragment);
                fragmentTransaction.commit();
            }
        });
        setSliderViews();
        return root;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();


    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    private void setSliderViews() {
        for (int i = 0; i <=2 ; i++){
            DefaultSliderView sliderView = new DefaultSliderView(getContext());
            switch (i){
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/health-tourism-a71f1.appspot.com/o/gambar2.jpg?alt=media&token=e3da15f1-1eed-4bdb-ac7b-b9dce529f9c5");
                    sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                    sliderView.setDescription("Spa Ubud Bali");
                    break;
                case  1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/health-tourism-a71f1.appspot.com/o/gambar4.jpg?alt=media&token=0fca34a3-073e-4a32-8a9f-ef5051a99aff");
                    sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                    sliderView.setDescription("Panjat Tebing");
                    break;
                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/health-tourism-a71f1.appspot.com/o/gambar6.jpg?alt=media&token=8e24e990-3faf-4239-9e79-1e377f33f933");
                    sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                    sliderView.setDescription("Museum Duver");
                    break;
            }

            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {

                }
            });
            sliderLayout.addSliderView(sliderView);
        }
    }
}