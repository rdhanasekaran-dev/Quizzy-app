package com.sekar.quizzy.riddles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.sekar.quizzy.R;

public class RiddleLevelActivity extends AppCompatActivity {
    private AdView mAdView;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22,b23,b24,b25,b26;
    String category;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle_level);

        category=getIntent().getExtras().getString("type");

        MobileAds.initialize(this,"ca-app-pub-2780742672110526/6821820498");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        b1=findViewById(R.id.b1);b2=findViewById(R.id.b2);b3=findViewById(R.id.b3);b4=findViewById(R.id.b4);b5=findViewById(R.id.b5);
        b6=findViewById(R.id.b6);b7=findViewById(R.id.b7);b8=findViewById(R.id.b8);b9=findViewById(R.id.b9);b10=findViewById(R.id.b10);
        b11=findViewById(R.id.b11);b12=findViewById(R.id.b12);b13=findViewById(R.id.b13);b14=findViewById(R.id.b14);b15=findViewById(R.id.b15);
        b16=findViewById(R.id.b16);b17=findViewById(R.id.b17);b18=findViewById(R.id.b18);b19=findViewById(R.id.b19);b20=findViewById(R.id.b20);
        b21=findViewById(R.id.b21);b22=findViewById(R.id.b22);b23=findViewById(R.id.b23);b24=findViewById(R.id.b24);b24=findViewById(R.id.b24);
        b25=findViewById(R.id.b25);b26=findViewById(R.id.b26);

        b11.setVisibility(View.GONE);  b12.setVisibility(View.GONE);  b13.setVisibility(View.GONE);  b14.setVisibility(View.GONE);  b15.setVisibility(View.GONE);
        b16.setVisibility(View.GONE);  b17.setVisibility(View.GONE);  b18.setVisibility(View.GONE);  b19.setVisibility(View.GONE);  b20.setVisibility(View.GONE);
        b21.setVisibility(View.GONE); b22.setVisibility(View.GONE); b23.setVisibility(View.GONE); b24.setVisibility(View.GONE); b25.setVisibility(View.GONE);
        b26.setVisibility(View.GONE);


        sharedPreferences = getSharedPreferences("myPreferencesAct", MODE_PRIVATE);
        editor = sharedPreferences.edit();


        if (category.equals("riddle")) {
            if (RiddleActivity.changed != null) {
                editor.putString("riddlevel",RiddleActivity.changed);
                editor.apply();
            }
            if (sharedPreferences.getString("riddlevel", "go").equals("go")) {

            } else {
                if (sharedPreferences.getString("riddlevel", null).equals("0")) {
                    b1.setAlpha(0.9f);
                    b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b2.setAlpha(1);
                    b3.setAlpha(0.9f);
                    b4.setAlpha(0.8f);
                    b5.setAlpha(0.7f);
                    b6.setAlpha(0.6f);
                    b7.setAlpha(0.5f);
                    b8.setAlpha(0.4f);
                    b9.setAlpha(0.3f);
                    b10.setAlpha(0.2f);
                }else if (sharedPreferences.getString("riddlevel", null).equals("20")) {
                    b1.setAlpha(0.9f);
                    b2.setAlpha(0.9f);
                    b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b3.setAlpha(1);
                    b4.setAlpha(0.9f);
                    b5.setAlpha(0.8f);
                    b6.setAlpha(0.7f);
                    b7.setAlpha(0.6f);
                    b8.setAlpha(0.5f);
                    b9.setAlpha(0.4f);
                    b10.setAlpha(0.3f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("40")) {
                    b1.setAlpha(0.9f);
                    b2.setAlpha(0.9f);
                    b3.setAlpha(0.9f);
                    b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));

                    b4.setAlpha(1);
                    b5.setAlpha(0.9f);
                    b6.setAlpha(0.8f);
                    b7.setAlpha(0.7f);
                    b8.setAlpha(0.6f);
                    b9.setAlpha(0.5f);
                    b10.setAlpha(0.4f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("60")) {
                    b1.setAlpha(0.9f);
                    b2.setAlpha(0.9f);
                    b3.setAlpha(0.9f);
                    b4.setAlpha(0.9f);
                    b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));

                    b5.setAlpha(1);
                    b6.setAlpha(0.9f);
                    b7.setAlpha(0.8f);
                    b8.setAlpha(0.7f);
                    b9.setAlpha(0.6f);
                    b10.setAlpha(0.5f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("80")) {
                    b1.setAlpha(0.9f);
                    b2.setAlpha(0.9f);
                    b3.setAlpha(0.9f);
                    b4.setAlpha(0.9f);
                    b5.setAlpha(0.9f);
                    b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));

                    b6.setAlpha(1);
                    b7.setAlpha(0.9f);
                    b8.setAlpha(0.8f);
                    b9.setAlpha(0.7f);
                    b10.setAlpha(0.6f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("100")) {
                    b1.setAlpha(0.9f);
                    b2.setAlpha(0.9f);
                    b3.setAlpha(0.9f);
                    b4.setAlpha(0.9f);
                    b5.setAlpha(0.9f);
                    b6.setAlpha(0.9f);
                    b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));

                    b7.setAlpha(1);
                    b8.setAlpha(0.9f);
                    b9.setAlpha(0.8f);
                    b10.setAlpha(0.7f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("120")) {
                    b1.setAlpha(0.9f);
                    b2.setAlpha(0.9f);
                    b3.setAlpha(0.9f);
                    b4.setAlpha(0.9f);
                    b5.setAlpha(0.9f);
                    b6.setAlpha(0.9f);
                    b7.setAlpha(0.9f);
                    b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b7.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));

                    b8.setAlpha(1);
                    b9.setAlpha(0.9f);
                    b10.setAlpha(0.8f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("140")) {
                    b1.setAlpha(0.9f);
                    b2.setAlpha(0.9f);
                    b3.setAlpha(0.9f);
                    b4.setAlpha(0.9f);
                    b5.setAlpha(0.9f);
                    b6.setAlpha(0.9f);
                    b7.setAlpha(0.9f);
                    b8.setAlpha(0.9f);
                    b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b7.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b8.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b9.setAlpha(1);
                    b10.setAlpha(0.9f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("160")) {
                    b1.setAlpha(0.9f);
                    b2.setAlpha(0.9f);
                    b3.setAlpha(0.9f);
                    b4.setAlpha(0.9f);
                    b5.setAlpha(0.9f);
                    b6.setAlpha(0.9f);
                    b7.setAlpha(0.9f);
                    b8.setAlpha(0.9f);
                    b9.setAlpha(0.9f);
                    b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b7.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b8.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b9.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b10.setAlpha(1);

                } else if (sharedPreferences.getString("riddlevel", null).equals("180")) {
                    b1.setAlpha(0.9f);
                    b2.setAlpha(0.9f);
                    b3.setAlpha(0.9f);
                    b4.setAlpha(0.9f);
                    b5.setAlpha(0.9f);
                    b6.setAlpha(0.9f);
                    b7.setAlpha(0.9f);
                    b8.setAlpha(0.9f);
                    b9.setAlpha(0.9f);
                    b10.setAlpha(0.9f);
                    b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b7.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b8.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b9.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b10.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    visible20();
                    b11.setAlpha(1);
                    b12.setAlpha(0.9f);
                    b13.setAlpha(0.8f);
                    b14.setAlpha(0.7f);
                    b15.setAlpha(0.6f);
                    b16.setAlpha(0.5f);
                    b17.setAlpha(0.4f);
                    b18.setAlpha(0.3f);
                    b19.setAlpha(0.2f);
                    b20.setAlpha(0.1f);

                }else  if (sharedPreferences.getString("riddlevel", null).equals("200")) {
                    level10completed(); visible20();
                    b11.setAlpha(0.9f);
                    b11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b12.setAlpha(1);
                    b13.setAlpha(0.9f);
                    b14.setAlpha(0.8f);
                    b15.setAlpha(0.7f);
                    b16.setAlpha(0.6f);
                    b17.setAlpha(0.5f);
                    b18.setAlpha(0.4f);
                    b19.setAlpha(0.3f);
                    b20.setAlpha(0.2f);
                }else if (sharedPreferences.getString("riddlevel", null).equals("220")) {
                    level10completed();visible20();
                    b11.setAlpha(0.9f);
                    b12.setAlpha(0.9f);
                    b11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b13.setAlpha(1);
                    b14.setAlpha(0.9f);
                    b15.setAlpha(0.8f);
                    b16.setAlpha(0.7f);
                    b17.setAlpha(0.6f);
                    b18.setAlpha(0.5f);
                    b19.setAlpha(0.4f);
                    b20.setAlpha(0.3f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("240")) {
                    level10completed();visible20();
                    b11.setAlpha(0.9f);
                    b12.setAlpha(0.9f);
                    b13.setAlpha(0.9f);
                    b11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b14.setAlpha(1);
                    b15.setAlpha(0.9f);
                    b16.setAlpha(0.8f);
                    b17.setAlpha(0.7f);
                    b18.setAlpha(0.6f);
                    b19.setAlpha(0.5f);
                    b20.setAlpha(0.4f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("260")) {
                    level10completed();visible20();
                    b11.setAlpha(0.9f);
                    b12.setAlpha(0.9f);
                    b13.setAlpha(0.9f);
                    b14.setAlpha(0.9f);
                    b11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b15.setAlpha(1);
                    b16.setAlpha(0.9f);
                    b17.setAlpha(0.8f);
                    b18.setAlpha(0.7f);
                    b19.setAlpha(0.6f);
                    b20.setAlpha(0.5f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("280")) {
                    level10completed();visible20();
                    b11.setAlpha(0.9f);
                    b12.setAlpha(0.9f);
                    b13.setAlpha(0.9f);
                    b14.setAlpha(0.9f);
                    b15.setAlpha(0.9f);
                    b11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b15.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));

                    b16.setAlpha(1);
                    b17.setAlpha(0.9f);
                    b18.setAlpha(0.8f);
                    b19.setAlpha(0.7f);
                    b20.setAlpha(0.6f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("300")) {
                    level10completed();visible20();
                    b11.setAlpha(0.9f);
                    b12.setAlpha(0.9f);
                    b13.setAlpha(0.9f);
                    b14.setAlpha(0.9f);
                    b15.setAlpha(0.9f);
                    b16.setAlpha(0.9f);
                    b11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b15.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b16.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b17.setAlpha(1);
                    b18.setAlpha(0.9f);
                    b19.setAlpha(0.8f);
                    b20.setAlpha(0.7f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("320")) {
                    level10completed();visible20();
                    b11.setAlpha(0.9f);
                    b12.setAlpha(0.9f);
                    b13.setAlpha(0.9f);
                    b14.setAlpha(0.9f);
                    b15.setAlpha(0.9f);
                    b16.setAlpha(0.9f);
                    b17.setAlpha(0.9f);
                    b11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b15.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b16.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b17.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b18.setAlpha(1);
                    b19.setAlpha(0.9f);
                    b20.setAlpha(0.8f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("340")) {
                    level10completed();visible20();
                    b11.setAlpha(0.9f);
                    b12.setAlpha(0.9f);
                    b13.setAlpha(0.9f);
                    b14.setAlpha(0.9f);
                    b15.setAlpha(0.9f);
                    b16.setAlpha(0.9f);
                    b17.setAlpha(0.9f);
                    b18.setAlpha(0.9f);
                    b11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b15.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b16.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b17.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b18.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b19.setAlpha(1);
                    b20.setAlpha(0.9f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("360")) {
                    level10completed();visible20();
                    b11.setAlpha(0.9f);
                    b12.setAlpha(0.9f);
                    b13.setAlpha(0.9f);
                    b14.setAlpha(0.9f);
                    b15.setAlpha(0.9f);
                    b16.setAlpha(0.9f);
                    b17.setAlpha(0.9f);
                    b18.setAlpha(0.9f);
                    b19.setAlpha(0.9f);
                    b11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b15.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b16.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b17.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b18.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b19.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b20.setAlpha(1);

                } else if (sharedPreferences.getString("riddlevel", null).equals("380")) {
                    level10completed();visible20();visible25();
                    b11.setAlpha(0.9f);
                    b12.setAlpha(0.9f);
                    b13.setAlpha(0.9f);
                    b14.setAlpha(0.9f);
                    b15.setAlpha(0.9f);
                    b16.setAlpha(0.9f);
                    b17.setAlpha(0.9f);
                    b18.setAlpha(0.9f);
                    b19.setAlpha(0.9f);
                    b20.setAlpha(0.9f);
                    b11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b15.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b16.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b17.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b18.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b19.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b20.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b21.setAlpha(1);
                    b22.setAlpha(0.9f);
                    b23.setAlpha(0.8f);
                    b24.setAlpha(0.7f);
                    b25.setAlpha(0.6f);

                }else  if (sharedPreferences.getString("riddlevel", null).equals("400")) {
                    level10completed();
                    level20completed();visible20();visible25();
                    b21.setAlpha(0.9f);
                    b21.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b22.setAlpha(1);
                    b23.setAlpha(0.9f);
                    b24.setAlpha(0.8f);
                    b25.setAlpha(0.7f);
                }else if (sharedPreferences.getString("riddlevel", null).equals("420")) {
                    level10completed();
                    level20completed();visible20();visible25();
                    b21.setAlpha(0.9f);
                    b22.setAlpha(0.9f);
                    b21.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b22.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b23.setAlpha(1);
                    b24.setAlpha(0.9f);
                    b25.setAlpha(0.8f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("440")) {
                    level10completed();
                    level20completed();visible20();visible25();
                    b21.setAlpha(0.9f);
                    b22.setAlpha(0.9f);
                    b23.setAlpha(0.9f);
                    b21.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b22.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b23.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b24.setAlpha(1);
                    b25.setAlpha(0.9f);

                } else if (sharedPreferences.getString("riddlevel", null).equals("460")) {
                    level10completed();
                    level20completed();visible20();visible25();
                    b21.setAlpha(0.9f);
                    b22.setAlpha(0.9f);
                    b23.setAlpha(0.9f);
                    b24.setAlpha(0.9f);
                    b21.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b22.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b23.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b24.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b25.setAlpha(1);

                } else if (sharedPreferences.getString("riddlevel", null).equals("480")) {
                    level10completed();
                    level20completed();visible20();visible25();
                    b21.setAlpha(0.9f);
                    b22.setAlpha(0.9f);
                    b23.setAlpha(0.9f);
                    b24.setAlpha(0.9f);
                    b25.setAlpha(0.9f);
                    b21.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b22.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b23.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b24.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b25.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));

                }
                else if (sharedPreferences.getString("riddlevel", null).equals("500")) {
                    level10completed();
                    level20completed();visible20();visible25();
                    b21.setAlpha(0.9f);
                    b22.setAlpha(0.9f);
                    b23.setAlpha(0.9f);
                    b24.setAlpha(0.9f);
                    b25.setAlpha(0.9f);
                    b21.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b22.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b23.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b24.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b25.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                    b26.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                    b26.setVisibility(View.VISIBLE);
                    b26.setAlpha(1);
                }
            }
        }
    }

    public void visible20(){
        b11.setVisibility(View.VISIBLE); b12.setVisibility(View.VISIBLE); b13.setVisibility(View.VISIBLE); b14.setVisibility(View.VISIBLE); b15.setVisibility(View.VISIBLE);
        b16.setVisibility(View.VISIBLE); b17.setVisibility(View.VISIBLE); b18.setVisibility(View.VISIBLE); b19.setVisibility(View.VISIBLE); b20.setVisibility(View.VISIBLE);
    }
    public void visible25(){
        b21.setVisibility(View.VISIBLE); b22.setVisibility(View.VISIBLE); b23.setVisibility(View.VISIBLE); b24.setVisibility(View.VISIBLE); b25.setVisibility(View.VISIBLE);
    }

    public void level10completed(){
        b1.setAlpha(0.9f);
        b2.setAlpha(0.9f);
        b3.setAlpha(0.9f);
        b4.setAlpha(0.9f);
        b5.setAlpha(0.9f);
        b6.setAlpha(0.9f);
        b7.setAlpha(0.9f);
        b8.setAlpha(0.9f);
        b9.setAlpha(0.9f);
        b10.setAlpha(0.9f);
        b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b7.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b8.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b9.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b10.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
    }
    public void level20completed(){
        b11.setAlpha(0.9f);
        b12.setAlpha(0.9f);
        b13.setAlpha(0.9f);
        b14.setAlpha(0.9f);
        b15.setAlpha(0.9f);
        b16.setAlpha(0.9f);
        b17.setAlpha(0.9f);
        b18.setAlpha(0.9f);
        b19.setAlpha(0.9f);
        b20.setAlpha(0.9f);
        b11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b15.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b16.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b17.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b18.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b19.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
        b20.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
    }

    public void goToActivity(View view) {
        if (category.equals("riddle")) {
            Intent i = new Intent(getApplicationContext(), RiddleActivity.class);
            if (view.getId() == R.id.b1 && b1.getAlpha() == 1) {
                i.putExtra("start", 0); i.putExtra("end", 20);
                startActivity(i);
            } else  if (view.getId() == R.id.b2 && b2.getAlpha() == 1) {
                i.putExtra("start", 20); i.putExtra("end", 40);
                startActivity(i);
            }else  if (view.getId() == R.id.b3 && b3.getAlpha() == 1) {
                i.putExtra("start", 40); i.putExtra("end", 60);
                startActivity(i);
            }else  if (view.getId() == R.id.b4 && b4.getAlpha() == 1) {
                i.putExtra("start", 60); i.putExtra("end", 80);
                startActivity(i);
            }else  if (view.getId() == R.id.b5 && b5.getAlpha() == 1) {
                i.putExtra("start", 80); i.putExtra("end", 100);
                startActivity(i);
            }else if (view.getId() == R.id.b6 && b6.getAlpha() == 1) {
                i.putExtra("start", 100); i.putExtra("end", 120);
                startActivity(i);
            } else  if (view.getId() == R.id.b7 && b7.getAlpha() == 1) {
                i.putExtra("start", 120); i.putExtra("end", 140);
                startActivity(i);
            }else  if (view.getId() == R.id.b8 && b8.getAlpha() == 1) {
                i.putExtra("start", 140); i.putExtra("end", 160);
                startActivity(i);
            }else  if (view.getId() == R.id.b9 && b9.getAlpha() == 1) {
                i.putExtra("start", 160); i.putExtra("end", 180);
                startActivity(i);
            }else  if (view.getId() == R.id.b10 && b10.getAlpha() == 1) {
                i.putExtra("start", 180); i.putExtra("end", 200);
                startActivity(i);
            }else if (view.getId() == R.id.b11 && b11.getAlpha() == 1) {
                i.putExtra("start", 200); i.putExtra("end", 220);
                startActivity(i);
            } else  if (view.getId() == R.id.b12 && b12.getAlpha() == 1) {
                i.putExtra("start", 20); i.putExtra("end", 240);
                startActivity(i);
            }else  if (view.getId() == R.id.b13 && b13.getAlpha() == 1) {
                i.putExtra("start", 40); i.putExtra("end", 260);
                startActivity(i);
            }else  if (view.getId() == R.id.b14 && b14.getAlpha() == 1) {
                i.putExtra("start", 60); i.putExtra("end", 280);
                startActivity(i);
            }else  if (view.getId() == R.id.b15 && b15.getAlpha() == 1) {
                i.putExtra("start", 80); i.putExtra("end", 300);
                startActivity(i);
            }else if (view.getId() == R.id.b16 && b16.getAlpha() == 1) {
                i.putExtra("start", 300); i.putExtra("end", 320);
                startActivity(i);
            } else  if (view.getId() == R.id.b17 && b17.getAlpha() == 1) {
                i.putExtra("start", 320); i.putExtra("end", 340);
                startActivity(i);
            }else  if (view.getId() == R.id.b18 && b18.getAlpha() == 1) {
                i.putExtra("start", 340); i.putExtra("end", 360);
                startActivity(i);
            }else  if (view.getId() == R.id.b19 && b19.getAlpha() == 1) {
                i.putExtra("start", 360); i.putExtra("end", 380);
                startActivity(i);
            }else  if (view.getId() == R.id.b20 && b20.getAlpha() == 1) {
                i.putExtra("start", 380); i.putExtra("end", 400);
                startActivity(i);
            }else if (view.getId() == R.id.b21 && b21.getAlpha() == 1) {
                i.putExtra("start", 400); i.putExtra("end", 420);
                startActivity(i);
            } else  if (view.getId() == R.id.b22 && b22.getAlpha() == 1) {
                i.putExtra("start", 420); i.putExtra("end", 440);
                startActivity(i);
            }else  if (view.getId() == R.id.b23 && b23.getAlpha() == 1) {
                i.putExtra("start", 440); i.putExtra("end", 460);
                startActivity(i);
            }else  if (view.getId() == R.id.b24 && b24.getAlpha() == 1) {
                i.putExtra("start", 460); i.putExtra("end", 480);
                startActivity(i);
            }else  if (view.getId() == R.id.b25 && b25.getAlpha() == 1) {
                i.putExtra("start", 480); i.putExtra("end", 500);
                startActivity(i);
            }else  if (view.getId() == R.id.b26 && b26.getAlpha() == 1) {
                i.putExtra("start", 0); i.putExtra("end", 500);
                startActivity(i);
            }
        }
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