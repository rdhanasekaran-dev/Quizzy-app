package com.sekar.quizzy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sekar.quizzy.model.Users;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ProfileViewActivity extends AppCompatActivity {

    TextView totalCoinsView,usernameView,countryView;
    LinearLayout dh;
    List<RelativeLayout> relativeLayouts;
    DatabaseReference reference;
    ImageView profile;
    TextView username1,username2,username3,username4,username5,coin1,coins2,coins3,coins4,coins5;
    List<Users> usersList1,usersList2;
    List<Integer> integersList;
    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        username1=findViewById(R.id.username1);
        username2=findViewById(R.id.username2);
        username3=findViewById(R.id.username3);
        coin1=findViewById(R.id.totalCoins1);
        coins2=findViewById(R.id.totalCoins2);
        coins3=findViewById(R.id.totalCoins3);

        reference = FirebaseDatabase.getInstance().getReference().child("Devices");
        profile=findViewById(R.id.img);
        totalCoinsView=findViewById(R.id.totalCoins);
        usersList1=new ArrayList<>();
        dh=new LinearLayout(this);
        integersList=new ArrayList<>();
        usernameView=findViewById(R.id.username);
        countryView=findViewById(R.id.totalCoins);
        relativeLayouts=new ArrayList<>();

        usernameView.setText(EntryLoadActivity.username);
        countryView.setText(EntryLoadActivity.country);
        totalCoinsView.setText(EntryLoadActivity.points);
        limit();
    }

    public void limit(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                   usersList1.add(postSnapshot.getValue(Users.class));
                  lim();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void lim(){
        integersList.clear();
        for(int i=0;i<usersList1.size();i++) {
            String word=usersList1.get(i).getPoints();
            integersList.add(Integer.parseInt(word));
        }
        Collections.sort(integersList);
        for(int i=0;i<usersList1.size();i++){
            if(String.valueOf(integersList.get(integersList.size()-1)).equals(usersList1.get(i).getPoints())){
                username1.setText(usersList1.get(i).getUsername());
                coin1.setText(usersList1.get(i).getPoints());
            }else if(String.valueOf(integersList.get(integersList.size()-2)).equals(usersList1.get(i).getPoints())){
                username2.setText(usersList1.get(i).getUsername());
                coins2.setText(usersList1.get(i).getPoints());
            }else if(String.valueOf(integersList.get(integersList.size()-3)).equals(usersList1.get(i).getPoints())) {
                username3.setText(usersList1.get(i).getUsername());
                coins3.setText(usersList1.get(i).getPoints());
            }
        }
    }

    public void back(View view){
        if(view.getId()==R.id.back){
            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),Main2Activity.class));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
