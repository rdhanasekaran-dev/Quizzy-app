package com.sekar.quizzy.quizz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sekar.quizzy.LoadActivity;
import com.sekar.quizzy.Main2Activity;
import com.sekar.quizzy.R;
import com.sekar.quizzy.model.Riddles;
import com.sekar.quizzy.riddles.RiddleActivity;

import java.util.ArrayList;
import java.util.List;

public class RandomQuizActivity extends AppCompatActivity {
    TextView questionTextView;
    RadioButton option1View,option2View,option3View,option4View;
    DatabaseReference reference,reference2;
    List<Riddles> riddlesList;
    Riddles riddles;
    String answerNr="";
    RadioGroup radioGroup;
    int startLimit=0;
    TextView totalCoinsHolderView,coinsViewHolder,countViewHolder;
    int endLimit;
    int c=0;
    long k=1;
    long noOfCoins,coinsgot;
    Dialog result;
    long total=0;
    public static String changed;
    String deviceID;
    public static String points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_quiz);
        questionTextView=findViewById(R.id.question);
        option1View=findViewById(R.id.radio_button1);
        option2View=findViewById(R.id.radio_button2);
        option3View=findViewById(R.id.radio_button3);
        option4View=findViewById(R.id.radio_button4);
        totalCoinsHolderView=findViewById(R.id.totalcoinsHolder);
        totalCoinsHolderView.setText(Main2Activity.purpose1);
        coinsViewHolder=findViewById(R.id.coins_view);
        coinsViewHolder.setText(String.valueOf(c));
        countViewHolder=findViewById(R.id.count_view);
        result=new Dialog(RandomQuizActivity.this);
        result.setContentView(R.layout.result_dialogue);
        coinsgot=0;
        points="0";
        noOfCoins=0;
        startLimit= 0;
        reference= FirebaseDatabase.getInstance().getReference().child("Randomquiz");
        deviceID= Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        reference2=FirebaseDatabase.getInstance().getReference().child("Devices").child(deviceID);
        riddlesList=new ArrayList<>();
        radioGroup=findViewById(R.id.radio_group);

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                points=dataSnapshot.child("points").getValue().toString();
                noOfCoins=Integer.parseInt(points);
                totalCoinsHolderView.setText(String.valueOf(noOfCoins));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                riddlesList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    riddlesList.add(snapshot.getValue(Riddles.class));
                next();

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

        result.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                String balance=String.valueOf(total);
                reference2.child("points").setValue(balance);
                Intent i=new Intent(getApplicationContext(),ChooseActivity.class);
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

    public void next(){
        if(isConnected(this)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (startLimit < 25) {
                        option1View.setVisibility(View.VISIBLE);
                        option2View.setVisibility(View.VISIBLE);
                        option3View.setVisibility(View.VISIBLE);
                        option4View.setVisibility(View.VISIBLE);
                        countViewHolder.setText(k + "/25");
                        option1View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                        option2View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                        option3View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                        option4View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_white_back));
                        for (int i = 0; i < radioGroup.getChildCount(); i++) {
                            radioGroup.getChildAt(i).setEnabled(true);
                        }

                        riddles = riddlesList.get(startLimit);
                        questionTextView.setText(riddles.getQuestion());
                        option1View.setText(riddles.getOption1());
                        option2View.setText(riddles.getOption2());
                        option3View.setText(riddles.getOption3());
                        option4View.setText(riddles.getOption4());
                        showSollution();
                        startLimit++;
                        k++;
                    } else {
                        coinsgot = Integer.parseInt(coinsViewHolder.getText().toString());
                        total = coinsgot + Integer.parseInt(totalCoinsHolderView.getText().toString());
                        TextView coinsReceived, totalCoins;
                        result.getWindow().getAttributes().windowAnimations = R.style.Animation;
                        coinsReceived = result.findViewById(R.id.coinsReceivedView);
                        totalCoins = result.findViewById(R.id.totalCoinsView);
                        coinsReceived.setText(String.valueOf(c));
                        totalCoins.setText(String.valueOf(total));
                        result.show();
                    }
                }
            }, 1000);

        }else{
            isConnected(this);
        }
    }

    public void showSollution(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    radioGroup.getChildAt(i).setEnabled(false);
                }
                int i=radioGroup.getCheckedRadioButtonId();
                RadioButton rbselected=findViewById(radioGroup.getCheckedRadioButtonId());
                int answered=radioGroup.indexOfChild(rbselected)+1;
                if(i==option1View.getId()){
                    if(answered==Integer.parseInt(riddles.getAnswerNr())) {
                        c+=50;
                        option1View.setChecked(false);
                        coinsViewHolder.setText(String.valueOf(c));
                        option1View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                        next();
                    }
                    else {
                        option1View.setChecked(false);
                        option1View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_round_back));
                        showCrtAnswer();
                    }
                }else if(i==option2View.getId()){
                    if(answered==Integer.parseInt(riddles.getAnswerNr())) {
                        c+=50;
                        option2View.setChecked(false);
                        coinsViewHolder.setText(String.valueOf(c));
                        option2View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                        next();
                    }
                    else {
                        option2View.setChecked(false);
                        option2View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_round_back));
                        showCrtAnswer();
                    }
                }else if(i==option3View.getId()){
                    if(answered==Integer.parseInt(riddles.getAnswerNr())) {
                        c+=50;
                        option3View.setChecked(false);
                        coinsViewHolder.setText(String.valueOf(c));
                        option3View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                        next();
                    }
                    else {
                        option3View.setChecked(false);
                        option3View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_round_back));
                        showCrtAnswer();
                    }
                }else if(i==option4View.getId()){
                    if(answered==Integer.parseInt(riddles.getAnswerNr())) {
                        c+=50;
                        option4View.setChecked(false);
                        coinsViewHolder.setText(String.valueOf(c));
                        option4View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                        next();
                    }
                    else {
                        option4View.setChecked(false);
                        option4View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_round_back));
                        showCrtAnswer();
                    }
                }
            }
        });
    }

    public void showCrtAnswer(){
        int crtAnswer=Integer.parseInt(riddles.getAnswerNr());
        switch (crtAnswer){
            case 1:option1View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
                break;
            case 2:option2View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
                break;
            case 3:option3View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
                break;
            case 4:option4View.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
                break;
        }
        next();
    }

    public void skipWord(View view){
        if(isConnected(this)) {
            if (view.getId() == R.id.skip) {
                showCrtAnswer();
                noOfCoins -= 100;
                totalCoinsHolderView.setText(String.valueOf(noOfCoins));
            }
        }else{
            isConnected(this);
        }
    }

    public void showHint(View view){
        if(isConnected(this)) {
            int answer = Integer.parseInt(riddles.getAnswerNr());
            noOfCoins -= 30;
            totalCoinsHolderView.setText(String.valueOf(noOfCoins));
            if (view.getId() == R.id.hint) {
                if (1 != answer) {
                    option1View.setVisibility(View.GONE);
                } else {
                    option2View.setVisibility(View.GONE);
                }
                if (3 != answer) {
                    option3View.setVisibility(View.GONE);
                } else {
                    option4View.setVisibility(View.GONE);
                }
            }
        }else{
            isConnected(this);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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