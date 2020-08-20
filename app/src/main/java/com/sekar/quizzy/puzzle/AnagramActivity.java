package com.sekar.quizzy.puzzle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
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
import com.sekar.quizzy.R;
import com.sekar.quizzy.model.Anagrams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AnagramActivity extends AppCompatActivity {
    RelativeLayout ans,word;
    Button hint,skip;
    TextView an1,an2,an3,an4,an5,an6,word1,word2,word3,word4,word5,word6;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences1;
    char letterArray[];
    List<String> charList=new ArrayList<>();
    int i=0;
    public static int completed=0;
    String givenWord;
    List<String> wordslist=new ArrayList<>();
    int wordsTotal;
    public static String changed;
    int currentword=0;
    TextView coins,wordcount;
    int l=1;
    int c=0;
    Dialog result;
    long total;
    public static String change;
    DatabaseReference reference,reference2;
    List<Anagrams> anagramsList;
    ProgressDialog pd;
    TextView totalCoinsViewHolder;
    long noOfCoins=1000;
    int hintLimit=0;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor editor1;
    List<String> setWordsList;
    public static String points;
    Set<String> retreive;
    TextView levelView;
    String resultPurpose;
    String resultPurpose1;
    public static String coi;
    private AdView mAdView;
    long coinsgot;


    boolean notClicked1=true,notClicked2=true,notClicked3=true,notClicked4=true,notClicked5=true,notClicked6=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anagram2);
        sharedPreferences=getSharedPreferences("mypreferences", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        sharedPreferences1=getSharedPreferences("mypreferencesview", Context.MODE_PRIVATE);
        editor1=sharedPreferences.edit();
        setWordsList=new ArrayList<>();
        MobileAds.initialize(this,"ca-app-pub-2780742672110526/6821820498");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        currentword=getIntent().getExtras().getInt("start");
        wordsTotal=getIntent().getExtras().getInt("end");
        resultPurpose=String.valueOf(currentword);
        changed=String.valueOf(currentword);
        isConnected(this);
        ans=findViewById(R.id.ans_view);
        word=findViewById(R.id.word_view);
        an1=findViewById(R.id.ansone);
        an2=findViewById(R.id.anstwo);
        an3=findViewById(R.id.ansthree);
        an4=findViewById(R.id.ansfour);
        an5=findViewById(R.id.ansfive);
        an6=findViewById(R.id.anssix);
        word1=findViewById(R.id.wordone);
        word2=findViewById(R.id.wordtwo);
        word3=findViewById(R.id.wordthree);
        word4=findViewById(R.id.wordfour);
        word5=findViewById(R.id.wordfive);
        word6=findViewById(R.id.wordsix);
        coins=findViewById(R.id.coins_view);
        wordcount=findViewById(R.id.count_view);
        hint=findViewById(R.id.hint);
        String deviceID;
        points="0";
        skip=findViewById(R.id.skip);
        totalCoinsViewHolder=findViewById(R.id.totalcoinsHolder);
        result=new Dialog(AnagramActivity.this);
        result.setContentView(R.layout.result_dialogue);
        anagramsList=new ArrayList<>();
        pd=new ProgressDialog(this);
        pd.setTitle("Retrieving data");
        pd.setMessage("Please wait...");
        deviceID= Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        reference2=FirebaseDatabase.getInstance().getReference().child("Devices").child(deviceID);
        reference= FirebaseDatabase.getInstance().getReference().child("Anagrams");

            dataRetrieve();

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                points=dataSnapshot.child("points").getValue().toString();
                noOfCoins=Long.parseLong(points);
                totalCoinsViewHolder.setText(String.valueOf(noOfCoins));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        result.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                String balance=String.valueOf(total);
                reference2.child("points").setValue(balance);
                Intent i=new Intent(getApplicationContext(), LoadActivity.class);
                i.putExtra("type","wordResult");
                finish();
                startActivity(i);

            }
        });

    }

    public void dataRetrieve(){
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                anagramsList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    anagramsList.add(snapshot.getValue(Anagrams.class));
                addWord();
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

    public void addWord(){
        if(isConnected(this)) {
            if (currentword < wordsTotal) {
                hintLimit = 0;
                an1.setText("");
                an2.setText("");
                an3.setText("");
                an4.setText("");
                an5.setText("");
                an6.setText("");
                an1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                an2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                an3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                an4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                an5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                an6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                word1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                word2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                word3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                word4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                word5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                word6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                an1.setVisibility(View.GONE);
                an2.setVisibility(View.GONE);
                an3.setVisibility(View.GONE);
                an4.setVisibility(View.GONE);
                an5.setVisibility(View.GONE);
                an6.setVisibility(View.GONE);
                word1.setVisibility(View.GONE);
                word2.setVisibility(View.GONE);
                word3.setVisibility(View.GONE);
                word4.setVisibility(View.GONE);
                word5.setVisibility(View.GONE);
                word6.setVisibility(View.GONE);
                word1.setText("");
                word2.setText("");
                word3.setText("");
                word4.setText("");
                word5.setText("");
                word6.setText("");
                wordcount.setText(l + "/20");
                show(anagramsList.get(currentword).getWord());
                givenWord = anagramsList.get(currentword).getWord();
                currentword++;
                l++;
            } else {
                change = resultPurpose;
                coi = totalCoinsViewHolder.getText().toString();
                total = c + Integer.parseInt(totalCoinsViewHolder.getText().toString());
                TextView coinsReceived, totalCoins;
                result.getWindow().getAttributes().windowAnimations = R.style.Animation;
                coinsReceived = result.findViewById(R.id.coinsReceivedView);
                totalCoins = result.findViewById(R.id.totalCoinsView);
                coinsReceived.setText(String.valueOf(c));
                totalCoins.setText(String.valueOf(total));
                result.show();
            }
        }else {
            isConnected(this);
        }
    }

    public void show(String word){

        letterArray=word.toCharArray();
        for(char letter:letterArray)
            charList.add(String.valueOf(letter));

        Collections.shuffle(charList);

        for(int o=0;o<charList.size();o++){
                switch (o) {
                    case 0:
                        an1.setVisibility(View.VISIBLE);
                        word1.setVisibility(View.VISIBLE);
                        word1.setText(charList.get(o));
                        break;
                    case 1:
                        an2.setVisibility(View.VISIBLE);
                        word2.setVisibility(View.VISIBLE);
                        word2.setText(charList.get(o));
                        break;
                    case 2:
                        an3.setVisibility(View.VISIBLE);
                        word3.setVisibility(View.VISIBLE);
                        word3.setText(charList.get(o));
                        break;
                    case 3:
                        an4.setVisibility(View.VISIBLE);
                        word4.setVisibility(View.VISIBLE);
                        word4.setText(charList.get(o));
                        break;
                    case 4:
                        an5.setVisibility(View.VISIBLE);
                        word5.setVisibility(View.VISIBLE);
                        word5.setText(charList.get(o));
                        break;
                    case 5:
                        an6.setVisibility(View.VISIBLE);
                        word6.setVisibility(View.VISIBLE);
                        word6.setText(charList.get(o));
            }

        }
        charList.clear();
    }


    public void enterLetter(View view){

            if (view.getId() == R.id.wordone && notClicked1) {
                notClicked1 = false;
                word1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.yello_round_back));
                if (an1.getText().equals("")) {
                    an1.setText(word1.getText());
                    if (an2.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an2.getText().equals("")) {
                    an2.setText(word1.getText());
                    if (an3.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an3.getText().equals("")) {
                    an3.setText(word1.getText());
                    if (an4.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an4.getText().equals("")) {
                    an4.setText(word1.getText());
                    if (an5.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an5.getText().equals("")) {
                    an5.setText(word1.getText());
                    if (an6.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an6.getText().equals("")) {
                    an6.setText(word1.getText());
                    checkAnswer();
                }
            }

            if (view.getId() == R.id.wordtwo && notClicked2) {
                notClicked2=false;
                word2.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.yello_round_back));
                if (an1.getText().equals("")) {
                    an1.setText(word2.getText());
                    if (an2.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an2.getText().equals("")) {
                    an2.setText(word2.getText());
                    if (an3.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an3.getText().equals("")) {
                    an3.setText(word2.getText());
                    if (an4.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an4.getText().equals("")) {
                    an4.setText(word2.getText());
                    if (an5.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an5.getText().equals("")) {
                    an5.setText(word2.getText());
                    if (an6.getVisibility() == View.GONE)
                        checkAnswer();
                }else if (an6.getText().equals("")) {
                    an6.setText(word2.getText());
                    checkAnswer();
                }
            }
            if (view.getId() == R.id.wordthree && notClicked3) {
                word3.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.yello_round_back));
                notClicked3=false;
                if (an1.getText().equals("")) {
                    an1.setText(word3.getText());
                    if (an2.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an2.getText().equals("")) {
                    an2.setText(word3.getText());
                    if (an3.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an3.getText().equals("")) {
                    an3.setText(word3.getText());
                    if (an4.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an4.getText().equals("")) {
                    an4.setText(word3.getText());
                    if (an5.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an5.getText().equals("")) {
                    an5.setText(word3.getText());
                    if (an6.getVisibility() == View.GONE)
                        checkAnswer();
                }else if (an6.getText().equals("")) {
                    an6.setText(word3.getText());
                    checkAnswer();
                }
            }

            if (view.getId() == R.id.wordfour && notClicked4) {
                word4.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.yello_round_back));
                notClicked4=false;
                if (an1.getText().equals("")) {
                    an1.setText(word4.getText());
                    if (an2.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an2.getText().equals("")) {
                    an2.setText(word4.getText());
                    if (an3.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an3.getText().equals("")) {
                    an3.setText(word4.getText());
                    if (an4.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an4.getText().equals("")) {
                    an4.setText(word4.getText());
                    if (an5.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an5.getText().equals("")) {
                    an5.setText(word4.getText());
                    if (an6.getVisibility() == View.GONE)
                        checkAnswer();
                }else if (an6.getText().equals("")) {
                    an6.setText(word4.getText());
                    checkAnswer();
                }
            }
            if (view.getId() == R.id.wordfive && notClicked5) {
                word5.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.yello_round_back));
                notClicked5=false;
                if (an1.getText().equals("")) {
                    an1.setText(word5.getText());
                    if (an2.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an2.getText().equals("")) {
                    an2.setText(word5.getText());
                    if (an3.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an3.getText().equals("")) {
                    an3.setText(word5.getText());
                    if (an4.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an4.getText().equals("")) {
                    an4.setText(word5.getText());
                    if (an5.getVisibility() == View.GONE)
                        checkAnswer();
                } else if (an5.getText().equals("")) {
                    an5.setText(word5.getText());
                    if (an6.getVisibility() == View.GONE)
                        checkAnswer();
                }else if (an6.getText().equals("")) {
                    an6.setText(word5.getText());
                    checkAnswer();
                }
        }
        if (view.getId() == R.id.wordsix && notClicked6) {
            word6.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.yello_round_back));
            notClicked6=false;
            if (an1.getText().equals("")) {
                an1.setText(word6.getText());
                if (an2.getVisibility() == View.GONE)
                    checkAnswer();
            } else if (an2.getText().equals("")) {
                an2.setText(word6.getText());
                if (an3.getVisibility() == View.GONE)
                    checkAnswer();
            } else if (an3.getText().equals("")) {
                an3.setText(word6.getText());
                if (an4.getVisibility() == View.GONE)
                    checkAnswer();
            } else if (an4.getText().equals("")) {
                an4.setText(word6.getText());
                if (an5.getVisibility() == View.GONE)
                    checkAnswer();
            } else if (an5.getText().equals("")) {
                an5.setText(word6.getText());
                if (an6.getVisibility() == View.GONE)
                    checkAnswer();
            }else if (an6.getText().equals("")) {
                an6.setText(word6.getText());
                checkAnswer();
            }
        }
    }

    public void checkAnswer(){
        notClicked1=true;notClicked2=true;notClicked3=true;notClicked4=true;notClicked5=true;notClicked6=true;
        String answerWrite=(an1.getText().toString()+an2.getText()+an3.getText()+an4.getText()+an5.getText()+an6.getText()).trim();
        if(answerWrite.equals(givenWord)){
            c+=50;
            coins.setText(String.valueOf(c));
            an1.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.green_round_back));
            an2.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.green_round_back));
            an3.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.green_round_back));
            an4.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.green_round_back));
            an5.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.green_round_back));
            an6.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.green_round_back));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    addWord();
                }
            },1000);

        }else {
            an1.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.red_round_back));
            an2.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.red_round_back));
            an3.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.red_round_back));
            an4.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.red_round_back));
            an5.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.red_round_back));
            an6.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.red_round_back));

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    word1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                    word2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                    word3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                    word4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                    word5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                    word6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                    an1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                    an2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                    an3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                    an4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                    an5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                    an6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                    an1.setText("");
                    an2.setText("");
                    an3.setText("");
                    an4.setText("");
                    an5.setText("");
                    an6.setText("");
                }
            },1000);
        }
    }

    public void showHint(View view){

       if(view.getId()==R.id.hint) {
           if (hintLimit == 0) {
               edit(view);
               noOfCoins -= 10;
               hintLimit = 1;
               totalCoinsViewHolder.setText("" + noOfCoins);
               an1.setText(givenWord.substring(0, 1));
               compareHint(givenWord.substring(0, 1));
           } else {
            edit(view);
            noOfCoins-=20;
               totalCoinsViewHolder.setText("" + noOfCoins);
               an1.setText(givenWord.substring(0,1));
               compareHint(givenWord.substring(0,1));
               an3.setText(givenWord.substring(2,3));
               compareHint(givenWord.substring(2,3));
           }
       }
    }

    public void compareHint(String a){
        if(a.equals(word1.getText()))
            word1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));
        else if(a.equals(word2.getText()))
            word2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));
        else if(a.equals(word3.getText()))
            word3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));
        else if(a.equals(word4.getText()))
            word4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));
        else if(a.equals(word5.getText()))
            word5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));
        else if(a.equals(word6.getText()))
            word6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));
    }
    public void skipWord(View view){

        if(isConnected(this)) {
            noOfCoins -= 100;
            totalCoinsViewHolder.setText("" + noOfCoins);
            addWord();
        }else {
            isConnected(this);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        changed=null;
        finish();
        startActivity(new Intent(getApplicationContext(), PuzzleSelectActivity.class));
    }
    public void edit(View view){
        an1.setText("");an2.setText("");an3.setText("");an4.setText("");an5.setText("");an6.setText("");
        notClicked1=true;notClicked2=true;notClicked3=true;notClicked4=true;notClicked5=true;notClicked6=true;
        word1.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.round_white_back));
        word2.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.round_white_back));
        word3.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.round_white_back));
        word4.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.round_white_back));
        word5.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.round_white_back));
        word6.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.round_white_back));
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
    }}
