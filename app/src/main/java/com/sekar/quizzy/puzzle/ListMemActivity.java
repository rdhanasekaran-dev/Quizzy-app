package com.sekar.quizzy.puzzle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sekar.quizzy.LoadActivity;
import com.sekar.quizzy.Main2Activity;
import com.sekar.quizzy.R;
import com.sekar.quizzy.model.Lists;
import com.sekar.quizzy.riddles.RiddleActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ListMemActivity extends AppCompatActivity {
    RelativeLayout listLayout;
    LinearLayout counter;
    TextView counterView,tv11,tv12,tv13,tv14,tv15,tv21,tv22,tv23,tv24,tv25,answer,totalCoinsShow,Coinsview;
    Button start;
    DatabaseReference reference,reference2;
    List<Lists> listsList;
    List<String> extra,optionsList;
    String word1,word2;
    Lists list;
    Dialog result;
    int c=0;
    String deviceID;
    int coinsgot=0;
    List<String> wordsList;
    private AdView mAdView;
    public static String points;
    long total=0;
    ImageView img1,img2;
    boolean notClicked1=false,notClicked2=false,correct=false;
    int i;
    int k=0;
    long timeLeftInMillis=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mem);
        listLayout=findViewById(R.id.list);
        counter=findViewById(R.id.counter);
        start=findViewById(R.id.start);
        listsList=new ArrayList<>();
        Coinsview=findViewById(R.id.coins_view);
        totalCoinsShow=findViewById(R.id.totalcoinsHolder);
        answer=findViewById(R.id.answer);
        extra=new ArrayList<>();
        wordsList=new ArrayList<>();
        img1=findViewById(R.id.img1);
        result=new Dialog(ListMemActivity.this);
        result.setContentView(R.layout.result_dialogue);
        img2=findViewById(R.id.img2);
        optionsList=new ArrayList<>();
        counterView=findViewById(R.id.counterView);
        tv11=findViewById(R.id.tv11); tv12=findViewById(R.id.tv12); tv13=findViewById(R.id.tv13); tv14=findViewById(R.id.tv14); tv15=findViewById(R.id.tv15);
        tv21=findViewById(R.id.tv21); tv22=findViewById(R.id.tv22); tv23=findViewById(R.id.tv23); tv24=findViewById(R.id.tv24); tv25=findViewById(R.id.tv25);
        i=0;

        MobileAds.initialize(this,"ca-app-pub-2780742672110526/6821820498");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

            reference = FirebaseDatabase.getInstance().getReference().child("Lists");

        deviceID= Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        reference2=FirebaseDatabase.getInstance().getReference().child("Devices").child(deviceID);

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                points=dataSnapshot.child("points").getValue().toString();
                totalCoinsShow.setText(String.valueOf(Integer.parseInt(points)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listsList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    listsList.add(snapshot.getValue(Lists.class));

                Collections.shuffle(listsList);

                addList();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setVisibility(View.GONE);
                listLayout.setVisibility(View.VISIBLE);
                counter.setVisibility(View.VISIBLE);
                startCountDown();
            }
        });

        result.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {


                String balance=String.valueOf(total);
                reference2.child("points").setValue(balance);
                Intent i=new Intent(getApplicationContext(), PuzzleSelectActivity.class);
                finish();
                startActivity(i);

            }
        });
        isConnected(this);

    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiInfo != null && wifiInfo.isConnected()) || (mobileInfo != null && mobileInfo.isConnected())) {
            return true;
        } else {
            showDialog();
            return false;
        }
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Connect to wifi or quit")
                .setCancelable(false)
                .setPositiveButton("Connect to WIFI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void addList(){
        list=listsList.get(0);
        word1=list.getWord1();
        word2=list.getWord2();
        String[] wordsArray=word1.split(",",10);
        String[] extraArray=word2.split(",",5);

        wordsList= Arrays.asList(wordsArray);
        tv11.setText(Locale.getDefault().getDisplayLanguage());
        tv12.setText(wordsList.get(1));
        tv13.setText(wordsList.get(2));
        tv14.setText(wordsList.get(3));
        tv15.setText(wordsList.get(4));
        tv21.setText(wordsList.get(5));
        tv22.setText(wordsList.get(6));
        tv23.setText(wordsList.get(7));
        tv24.setText(wordsList.get(8));
        tv25.setText(wordsList.get(9));

        extra=Arrays.asList(extraArray);

        optionsList.addAll(wordsList);
        optionsList.addAll(extra);
        Collections.shuffle(optionsList);



    }

    public void showOption(){
        correct= Boolean.parseBoolean(null);
        answer.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        notClicked1=true;notClicked2=true;
        answer.setVisibility(View.VISIBLE);
           if(k<optionsList.size())
            answer.setText(optionsList.get(k));
           else {
               coinsgot=c;
               total=coinsgot+Integer.parseInt(totalCoinsShow.getText().toString());
               TextView coinsReceived,totalCoins;
               coinsReceived=result.findViewById(R.id.coinsReceivedView);
               result.getWindow().getAttributes().windowAnimations=R.style.Animation;
               totalCoins=result.findViewById(R.id.totalCoinsView);
               coinsReceived.setText(String.valueOf(c));
               totalCoins.setText(String.valueOf(total));
               result.show();
           }
        k++;
    }











    public void startCountDown(){
        CountDownTimer timer=new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis=millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                counter.setVisibility(View.GONE);
                listLayout.setVisibility(View.GONE);
                showOption();
            }
        }.start();
    }

    public void checkAnswer(View view){
        String answerShow=answer.getText().toString();
        if(view.getId()==R.id.img1){
            if(notClicked1==true){
                notClicked1=false;
                for(int j=0;j<wordsList.size();j++){
                    if(answerShow.equals(wordsList.get(j)))
                        correct=true;
                }
                if(correct==true) {
                    c+=50;
                    Coinsview.setText(String.valueOf(c));
                    answer.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                }
                    else {
                        c-=50;
                        Coinsview.setText(String.valueOf(c));
                    answer.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_round_back));
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showOption();
                    }
                },2000);
            }
        }else if(view.getId()==R.id.img2){
            if(notClicked2==true){
                notClicked2=false;
                for(int j=0;j<wordsList.size();j++){
                    if(answerShow.equals(wordsList.get(j)))
                        correct=true;
                }
                if(correct==true) {
                    c+=50;
                    Coinsview.setText(String.valueOf(c));
                    answer.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_round_back));
                }
                else {
                    c-=50;
                    Coinsview.setText(String.valueOf(c));
                    answer.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showOption();
                    }
                },1000);
            }
        }
    }


    private void updateCountDownText(){
        int seconds=(int)(timeLeftInMillis/1000)%60;

        String timeFormatted=String.format(Locale.getDefault(),"%1d",seconds);
        counterView.setText("You have "+timeFormatted+" seconds!");
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