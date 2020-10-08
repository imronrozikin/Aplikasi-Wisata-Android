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

public class Register extends AppCompatActivity {
    Button btn_register;
    TextView email, username, password, batal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_register = (Button)findViewById(R.id.btn_register);
        email = (TextView) findViewById(R.id.email);
        username = (TextView)findViewById(R.id.username);
        password = (TextView)findViewById(R.id.password);
        batal = (TextView)findViewById(R.id.batal);

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent batal = new Intent(getApplicationContext(), Login.class);
                startActivity(batal);
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tabel_user = database.getReference("User");

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(Register.this);
                mDialog.setMessage("Please Waiting..");
                mDialog.show();

                tabel_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(username.getText().toString()).exists()) {
                            mDialog.dismiss();
                            Toast.makeText(Register.this, "Username sudah terdaftar", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mDialog.dismiss();
                           User user = new User(username.getText().toString(), email.getText().toString(), password.getText().toString());
                           tabel_user.child(username.getText().toString()).setValue(user);
                            Toast.makeText(Register.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                            Intent intent_register = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent_register);
                            finish();
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
