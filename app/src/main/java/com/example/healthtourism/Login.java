package com.example.healthtourism;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthtourism.Common.Common;
import com.example.healthtourism.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private Button btnlogin;
    private TextView tvemail, btn_regiter;
    private TextView tvpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_regiter = (TextView)findViewById(R.id.btn_register);
        btnlogin = (Button)findViewById(R.id.btnlogin);
        tvemail = (TextView)findViewById(R.id.email);
        tvpwd = (TextView)findViewById(R.id.password);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tabel_user = database.getReference("User");

        btn_regiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(Login.this);
                mDialog.setMessage("Please Waiting..");
                mDialog.show();
                tabel_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(tvemail.getText().toString()).exists()) {
                            mDialog.dismiss();
                            User user = dataSnapshot.child(tvemail.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(tvpwd.getText().toString())) {
                                Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                Intent login = new Intent(getApplicationContext(), Main2Activity.class);
                                Common.currentUser = user;
                                startActivity(login);
                                finish();
                            } else {
                                Toast.makeText(Login.this, "Password salah!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            mDialog.dismiss();
                            Toast.makeText(Login.this, "User no exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
