package com.example.data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    private FirebaseDatabase fbDB;
    private DatabaseReference databaseReference;

    private TextView suhu;
    private TextView temp;
    private TextView dev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        suhu = findViewById(R.id.suhu);
        temp = findViewById(R.id.temp);
        dev = findViewById(R.id.dev);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        fbDB = FirebaseDatabase.getInstance();
        databaseReference = fbDB.getReference("/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                suhu.setText(dataSnapshot.child("SUHU").getValue().toString());
                temp.setText(dataSnapshot.child("KELEMBABAN").getValue().toString());
                dev.setText(dataSnapshot.child("DEVICE/NODE").getValue().toString());
                                  


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}