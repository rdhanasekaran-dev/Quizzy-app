package com.sekar.quizzy.puzzle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.sekar.quizzy.Main2Activity;
import com.sekar.quizzy.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ConstellationActivity extends AppCompatActivity {
    LinearLayout ll1,ll2,ll3,ll4,ll5,ll6,ll7,ll8,ll9,ll10,ll11,ll12,ll13,ll14,ll15,ll16;
    boolean notClicked1=false,notClicked2=false,notClicked3=false,notClicked4=false,notClicked5=false;
    boolean notClicked6=false,notClicked7=false,notClicked8=false,notClicked9=false,notClicked10=false,notClicked11=false,notClicked12=false;
    boolean notClicked13=false,notClicked14=false,notClicked15=false,notClicked16=false;
    int i=0;
    Random r=new Random();
    List<Integer> integers;
    Dialog mydialog;
    Set<Integer> set;
    List<String> questionList,answerList;
    Button b1;
    TextView right,wrong;
    int rightInt=0,wrongInt=0;
    int current=0;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constellation);

        MobileAds.initialize(this,"ca-app-pub-2780742672110526/6821820498");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ll1=findViewById(R.id.ll1);
        ll2=findViewById(R.id.ll2);
        ll3=findViewById(R.id.ll3);
        ll4=findViewById(R.id.ll4);
        ll5=findViewById(R.id.ll5);
        ll6=findViewById(R.id.ll6);
        ll7=findViewById(R.id.ll7);
        ll8=findViewById(R.id.ll8);
        ll9=findViewById(R.id.ll9);
        ll10=findViewById(R.id.ll10);
        ll11=findViewById(R.id.ll11);
        ll12=findViewById(R.id.ll12);
        ll13=findViewById(R.id.ll13);
        ll14=findViewById(R.id.ll14);
        ll15=findViewById(R.id.ll15);
        ll16=findViewById(R.id.ll16);
        b1=findViewById(R.id.start);
        right=findViewById(R.id.right);
        wrong=findViewById(R.id.wrong);
        integers=new ArrayList<>();
        mydialog=new Dialog(ConstellationActivity.this);
        mydialog.setContentView(R.layout.custom_result_popup);
        questionList=new ArrayList<>();
        answerList=new ArrayList<>();


        mydialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Intent i=new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(i);
            }
        });

    }

    public void align(){
        if(current<10) {
            i = 0;
            integers.clear();
            for (int i = 0; i < 11; i++) {
                integers.add(r.nextInt(11));
            }
            set = new LinkedHashSet<>(integers);
            integers.clear();
            integers.addAll(set);

            for (int i : integers) {
                switch (i) {
                    case 0:
                        ll1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll1");
                        break;
                    case 1:
                        ll2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll2");
                        break;
                    case 2:
                        ll3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll3");
                        break;
                    case 3:
                        ll4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll4");
                        break;
                    case 4:
                        ll5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll5");
                        break;
                    case 5:
                        ll6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll6");
                        break;
                    case 6:
                        ll7.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll7");
                        break;
                    case 7:
                        ll8.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll8");
                        break;
                    case 8:
                        ll9.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll9");
                        break;
                    case 9:
                        ll10.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll10");
                        break;
                    case 10:
                        ll11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll11");
                        break;
                    case 11:
                        ll12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll12");
                        break;
                    case 12:
                        ll13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll3");
                        break;
                    case 13:
                        ll14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll14");
                        break;
                    case 14:
                        ll15.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll15");
                        break;
                    case 15:
                        ll16.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.yello_round_back));
                        questionList.add("ll16");
                        break;
                }
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    goBack();
                }
            }, 200);

            current++;
        }else {
            TextView correct,wrongv;
            Button b1=mydialog.findViewById(R.id.send);
            correct=mydialog.findViewById(R.id.correctView);
            wrongv=mydialog.findViewById(R.id.wrongView);
            correct.setText(right.getText().toString());
            wrongv.setText(wrong.getText().toString());

            mydialog.getWindow().getAttributes().windowAnimations=R.style.Animation;
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  Intent i=new Intent(getApplicationContext(),Main2Activity.class);
                  startActivity(i);
                }
            });
            mydialog.show();

        }
    }

    public void goBack(){
        ll1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll7.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll8.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll9.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll10.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll15.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll16.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        notClicked1=true; notClicked2=true; notClicked3=true; notClicked4=true; notClicked5=true; notClicked6=true;
        notClicked7=true; notClicked8=true; notClicked9=true; notClicked10=true; notClicked11=true; notClicked12=true;
        notClicked13=true; notClicked14=true; notClicked15=true; notClicked16=true;
    }

    public void normal(){
        questionList.clear();
        answerList.clear();
        ll1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll7.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll8.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll9.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll10.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll15.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        ll16.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                align();
            }
        },1000);
    }

    public void click(View view){
        if(view.getId()==R.id.ll1){
            if(notClicked1==true){
                ll1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));
                answerList.add("ll1");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }else  if(view.getId()==R.id.ll2){
            if(notClicked2==true){
                ll2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll2");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }

        }else if(view.getId()==R.id.ll3){
            if(notClicked3==true){
                ll3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll3");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }else if(view.getId()==R.id.ll4){
            if(notClicked4==true){
                ll4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll4");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }else if(view.getId()==R.id.ll5){
            if(notClicked5==true){
                ll5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll5");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }else if(view.getId()==R.id.ll6){
            if(notClicked6==true){
                ll6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll6");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }else if(view.getId()==R.id.ll7){
            if(notClicked7==true){
                ll7.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll7");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }else if(view.getId()==R.id.ll8){
            if(notClicked8==true){
                ll8.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll8");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }else if(view.getId()==R.id.ll9){
            if(notClicked9==true){
                ll9.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll9");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }else if(view.getId()==R.id.ll10){
            if(notClicked10==true){
                ll10.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll10");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }else if(view.getId()==R.id.ll11){
            if(notClicked11==true){
                ll11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll11");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }else if(view.getId()==R.id.ll12){
            if(notClicked12==true){
                ll12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll12");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }else if(view.getId()==R.id.ll13){
            if(notClicked13==true){
                ll13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll13");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }else if(view.getId()==R.id.ll14){
            if(notClicked14==true){
                ll14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll14");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }else if(view.getId()==R.id.ll15){
            if(notClicked15==true){
                ll15.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll15");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }else if(view.getId()==R.id.ll16){
            if(notClicked16==true){
                ll16.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));   answerList.add("ll16");
                i++;
                if(i==integers.size()){
                    check(questionList,answerList);
                }
            }
        }
    }

    public void check(List<String> stringList1,List<String> stringList2){
        Collections.sort(stringList1);
        Collections.sort(stringList2);
        if(stringList1.equals(stringList2)) {
            green();
        }
        else {
            red();
        }

    }

    public void green(){
        rightInt++;
        right.setText(String.valueOf(rightInt));
        notClicked1=false;  notClicked2=false;  notClicked3=false;  notClicked4=false;  notClicked5=false;  notClicked6=false;
        notClicked7=false;  notClicked8=false;  notClicked9=false;  notClicked10=false;  notClicked11=false;  notClicked12=false;
        ll1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll7.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll8.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll9.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll10.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll15.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
        ll16.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                normal();
            }
        },1000);
    }

    public void red(){
        wrongInt++;
        wrong.setText(String.valueOf(wrongInt));
        notClicked1=false;  notClicked2=false;  notClicked3=false;  notClicked4=false;  notClicked5=false;  notClicked6=false;
        notClicked7=false;  notClicked8=false;  notClicked9=false;  notClicked10=false;  notClicked11=false;  notClicked12=false;
        ll1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll7.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll8.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll9.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll10.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll15.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        ll16.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                normal();
            }
        },1000);
    }

    public void start(View view){
        if(view.getId()==R.id.start){
            if(b1.getText().toString().equals("start")) {
                current=0;
                rightInt=0;
                wrongInt=0;
                align();
                b1.setText("stop");
            }else{
                rightInt=0;
                current=0;
                wrongInt=0;
                b1.setText("start");
                ll1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll7.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll8.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll9.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll10.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll11.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll12.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll13.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll14.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll15.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                ll16.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
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