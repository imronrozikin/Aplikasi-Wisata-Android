package com.example.healthtourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;
import androidx.multidex.MultiDex;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color; 
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthtourism.Common.Common;
import com.example.healthtourism.Common.CommonWisata;
import com.example.healthtourism.Interface.ItemClickListener;
import com.example.healthtourism.Model.CommentModel;
import com.example.healthtourism.Model.WisataModel;
import com.example.healthtourism.ViewHolder.CommentViewHolder;
import com.example.healthtourism.ViewHolder.WisataViewHolder;
import com.example.healthtourism.decoration.LayoutMarginDecoration;
import com.example.healthtourism.utils.Tools;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DetailWisata extends AppCompatActivity {
    TextView nama, deskripsi, fasilitas, jamBuka, provinsi, harga, btnMaps, btnKomentar;
    FloatingActionButton btn_star;
    EditText edt_comment;
    String koordinat = "";
    Button showComment;
    RecyclerView recycler_comment;
    ImageView gambar, ivKomentar, getKomentar;
    String wisataId = "";
    private RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference wisata, comment_ref;
    GoogleMap googleMaps;
    FirebaseRecyclerAdapter<WisataModel, WisataViewHolder> adapter;
    FirebaseRecyclerAdapter<CommentModel, CommentViewHolder> adapter_comment;
    WisataModel currentWisata;
    CommentModel commentModel;
    Dialog dialog;
    String id;
    RatingBar ratingBar, rating;
    private MutableLiveData<CommentModel> cModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String kategori = getIntent().getStringExtra("kategori");
        database =FirebaseDatabase.getInstance();
        wisata = database.getReference(kategori);

        getKomentar= (ImageView)findViewById(R.id.getKomentar);
        btnKomentar = (TextView)findViewById(R.id.tvKomentar);
        ivKomentar = (ImageView)findViewById(R.id.ivKomnetar);
        btn_star = (FloatingActionButton)findViewById(R.id.btn_star);
        nama = (TextView)findViewById(R.id.tvNama);
        rating = (RatingBar)findViewById(R.id.rating);
        jamBuka = (TextView)findViewById(R.id.tvJamBuka);
        fasilitas = (TextView)findViewById(R.id.tvFasilitas);
        harga = (TextView)findViewById(R.id.tvHarga);
        gambar = (ImageView)findViewById(R.id.menu_image);
        deskripsi = (TextView)findViewById(R.id.tvDeskripsi);
        btnMaps = (TextView)findViewById(R.id.tvMaps);
        dialog = new Dialog(this);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference comment = database.getReference(kategori);

        if (getIntent() != null)
            wisataId=getIntent().getStringExtra("detailWisata");
        if (!wisataId.isEmpty())
            getDetailWisata(wisataId);

        comment_ref = FirebaseDatabase.getInstance().getReference("Wellnes").child(wisataId).child("Comment");

        btnKomentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailWisata.this);
                builder.setTitle("Komentar");
                builder.setCancelable(true);


                View viewComment = getLayoutInflater().inflate(R.layout.fargment_comment, null);
                recycler_comment = (RecyclerView)viewComment.findViewById(R.id.recycler_comment);
                recycler_comment.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getApplication());
                recycler_comment.setLayoutManager(layoutManager);

                builder.setView(viewComment);

                builder.setNegativeButton("Kembali", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });

                adapter_comment = new FirebaseRecyclerAdapter<CommentModel, CommentViewHolder>(CommentModel.class,R.layout.layout_comment_item,CommentViewHolder.class, comment_ref) {
                    @Override
                    protected void populateViewHolder(CommentViewHolder viewHolder, final CommentModel model, int position) {
                        viewHolder.txtMenuName.setText(model.getNama());
                        viewHolder.txtComment.setText(model.getComment());
                        viewHolder.rating.setRating(model.getRating());
                    }
                };

                recycler_comment.setAdapter(adapter_comment);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        getKomentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailWisata.this);
                builder.setTitle("Komentar");
                builder.setCancelable(true);


                View viewComment = getLayoutInflater().inflate(R.layout.fargment_comment, null);
                recycler_comment = (RecyclerView)viewComment.findViewById(R.id.recycler_comment);
                recycler_comment.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getApplication());
                recycler_comment.setLayoutManager(layoutManager);

                builder.setView(viewComment);

                builder.setNegativeButton("Kembali", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });

                adapter_comment = new FirebaseRecyclerAdapter<CommentModel, CommentViewHolder>(CommentModel.class,R.layout.layout_comment_item,CommentViewHolder.class, comment_ref) {
                    @Override
                    protected void populateViewHolder(CommentViewHolder viewHolder, final CommentModel model, int position) {
                        viewHolder.txtMenuName.setText(model.getNama());
                        viewHolder.txtComment.setText(model.getComment());
                        viewHolder.rating.setRating(model.getRating());
                    }
                };

                recycler_comment.setAdapter(adapter_comment);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        ivKomentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailWisata.this);
                builder.setTitle("Komentar");
                builder.setCancelable(true);


                View viewComment = getLayoutInflater().inflate(R.layout.fargment_comment, null);
                recycler_comment = (RecyclerView)viewComment.findViewById(R.id.recycler_comment);
                recycler_comment.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getApplication());
                recycler_comment.setLayoutManager(layoutManager);

                builder.setView(viewComment);

                builder.setNegativeButton("Kembali", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });

                adapter_comment = new FirebaseRecyclerAdapter<CommentModel, CommentViewHolder>(CommentModel.class,R.layout.layout_comment_item,CommentViewHolder.class, comment_ref) {
                    @Override
                    protected void populateViewHolder(CommentViewHolder viewHolder, final CommentModel model, int position) {
                        viewHolder.txtMenuName.setText(model.getNama());
                        viewHolder.txtComment.setText(model.getComment());
                        viewHolder.rating.setRating(model.getRating());
                    }
                };

                recycler_comment.setAdapter(adapter_comment);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btn_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailWisata.this);
                builder.setTitle("Rating Wisata");
                builder.setCancelable(true);

                View view1 = getLayoutInflater().inflate(R.layout.activity_rating, null);
                ratingBar = (RatingBar)view1.findViewById(R.id.rating_bar);
                edt_comment = (EditText)view1.findViewById(R.id.edt_comment);

                builder.setView(view1);

                builder.setNegativeButton("CANCEL", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });

                builder.setPositiveButton("OK", (dialogInterface, i) -> {
                    final ProgressDialog mDialog = new ProgressDialog(DetailWisata.this);
                    mDialog.setMessage("Please Wait..");
                    mDialog.show();

                    comment.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            mDialog.dismiss();
                            commentModel = new CommentModel();
                            commentModel.setNama(Common.currentUser.getNama());
                            commentModel.setComment(edt_comment.getText().toString());
                            commentModel.setRating(ratingBar.getRating());
                            randomText();
                            comment.child(wisataId).child("Comment").push().setValue(commentModel);
                            addRatingToDetail(commentModel.getRating());
                            Toast.makeText(DetailWisata.this, "Komentar Ditambahkan", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }

    private void getDetailWisata(String wisataId) {
        wisata.child(wisataId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentWisata = dataSnapshot.getValue(WisataModel.class);
                Picasso.get().load(currentWisata.getGambar())
                        .into(gambar);
                nama.setText(currentWisata.getNama());
                fasilitas.setText(currentWisata.getFasilitas());
                jamBuka.setText(currentWisata.getJamBuka());
                deskripsi.setText(currentWisata.getDeskripsi());
                harga.setText(currentWisata.getHarga());
                koordinat = currentWisata.getKoordinat();
                if (currentWisata.getRatingValue() != null)
                    rating.setRating(currentWisata.getRatingValue());

                btnMaps.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent map = new Intent(DetailWisata.this, MapsActivity.class);
                        map.putExtra("koordinat", koordinat);
                        map.putExtra("nama", nama.getText().toString());
                        startActivity(map);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addRatingToDetail(float rating){
        final FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        final DatabaseReference editWisata = database1.getReference("Wellnes").child(wisataId);
        editWisata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (currentWisata.getRatingValue() == null) {
                    currentWisata.setRatingValue(0f);
                }
                if (currentWisata.getRatingCount() == null) {
                    currentWisata.setRatingCount(0l);
                }
                if (currentWisata.getRatingSum() == null){
                    currentWisata.setRatingSum(0f);
                }

                float ratingSum = currentWisata.getRatingSum() + rating;
                long ratingCount = currentWisata.getRatingCount()+1;

                float result = ratingSum / ratingCount;

                Map<String,Object> updateData = new HashMap<>();
                updateData.put("ratingValue", result);
                updateData.put("ratingSum", ratingSum);
                updateData.put("ratingCount", ratingCount);

                editWisata.updateChildren(updateData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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


    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static class RandomString{
        private char[] chars = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890qwertyuiopasdfghjklzxcvbnm".toCharArray();
        private StringBuilder stringBuilder = new StringBuilder();
        private Random random = new Random();
        private String output;
        public String getRandom(){
            for (int length = 0; length < 20; length++){
                Character character = chars[random.nextInt(chars.length)];
                stringBuilder.append(character);
            }
         output=stringBuilder.toString();
            stringBuilder.delete(0,10);
            return output;
        }
    }

    public void randomText(){
        RandomString randomString =new RandomString();
        id = randomString.getRandom();
    }
}
