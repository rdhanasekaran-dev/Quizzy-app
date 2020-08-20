package com.sekar.quizzy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class EntryLoadActivity extends AppCompatActivity {
    DatabaseReference reference,reference2;
    String deviceID;
    public static Bitmap bitmaped;
    public static String username;
    public static String country;
    public static String points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_load);
        deviceID= Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        reference = FirebaseDatabase.getInstance().getReference().child("Devices").child(deviceID);

        addProfile();
    }

    public void addProfile(){

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username = dataSnapshot.child("username").getValue().toString();
                country = dataSnapshot.child("country").getValue().toString();
                points = dataSnapshot.child("points").getValue().toString();
                    Intent i = new Intent(getApplicationContext(), ProfileViewActivity.class);
                    finish();
                    startActivity(i);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}