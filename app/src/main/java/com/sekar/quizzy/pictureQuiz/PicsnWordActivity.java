package com.sekar.quizzy.pictureQuiz;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sekar.quizzy.LoadActivity;
import com.sekar.quizzy.Main2Activity;
import com.sekar.quizzy.MainActivity;
import com.sekar.quizzy.R;
import com.sekar.quizzy.model.Connections;
import com.sekar.quizzy.model.Picsnword;
import com.sekar.quizzy.puzzle.PuzzleSelectActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PicsnWordActivity extends AppCompatActivity {
    ImageView img1,img2,img3,img4;
    TextView totalCoinsView,coinsView,countView;
    int k=1;
    DatabaseReference reference,reference2;
    List<Picsnword> picsnwordList;
    Picsnword picsnword;
    int startLimt,endLimit;
    Button b1,b2,b3,b4,b5,b6;
    List<String> answerCharList;
    String typedAnswer;
    boolean notClicked1=true,notClicked2=true,notClicked3=true,notClicked4=true,notClicked5=true,notClicked6=true;
    int c=0;
    int noOfCoins;
    long total;
    Dialog result;
    String answer;
    String deviceID;
    public static String points;
    public static String changed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picsn_word);
        picsnwordList=new ArrayList<>();
        startLimt=0;
        endLimit=10;
        total=0;
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);
        b5=findViewById(R.id.b5);
        b6=findViewById(R.id.b6);
        noOfCoins=0;
        isConnected(this);
        result=new Dialog(PicsnWordActivity.this);
        result.setContentView(R.layout.result_dialogue);
        countView=findViewById(R.id.count_view);
        coinsView=findViewById(R.id.coins_view);
        totalCoinsView=findViewById(R.id.totalcoinsHolder);
        startLimt=getIntent().getExtras().getInt("start");
        endLimit=getIntent().getExtras().getInt("end");
        changed=String.valueOf(startLimt);
        answerCharList=new ArrayList<>();
        img1=findViewById(R.id.img1); img2=findViewById(R.id.img2); img3=findViewById(R.id.img3); img4=findViewById(R.id.img4);
        reference= FirebaseDatabase.getInstance().getReference().child("Picsnwords");
        deviceID= Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        reference2=FirebaseDatabase.getInstance().getReference().child("Devices").child(deviceID);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                picsnwordList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    picsnwordList.add(snapshot.getValue(Picsnword.class));
                addImage();
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
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                points=dataSnapshot.child("points").getValue().toString();
                noOfCoins=Integer.parseInt(points);
                totalCoinsView.setText(String.valueOf(noOfCoins));
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
                i.putExtra("type","PicsnwordResult");
                finish();
                startActivity(i);

            }
        });

    }

    public void addImage() {
        if (startLimt < endLimit) {
            typedAnswer="";
            notClicked1=true;notClicked2=true;notClicked3=true;notClicked4=true;notClicked5=true;notClicked6=true;
            countView.setText(k+"/5");
            picsnword = picsnwordList.get(startLimt);
            Picasso.get().load(picsnword.getImg1()).into(img1);
            Picasso.get().load(picsnword.getImg2()).into(img2);
            Picasso.get().load(picsnword.getImg3()).into(img3);
            Picasso.get().load(picsnword.getImg4()).into(img4);
            addButtons();
            startLimt++;
            k++;
        } else {

            total=c+Integer.parseInt(totalCoinsView.getText().toString());
            TextView coinsReceived,totalCoins;
            result.getWindow().getAttributes().windowAnimations=R.style.Animation;
            coinsReceived=result.findViewById(R.id.coinsReceivedView);
            totalCoins=result.findViewById(R.id.totalCoinsView);
            coinsReceived.setText(String.valueOf(c));
            totalCoins.setText(String.valueOf(total));
            result.show();
        }
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


    public void addButtons(){
        answerCharList.clear();
        answer="";
        answer=picsnword.getAnswer();
        char[] wordsArray=answer.toCharArray();
        for(int i=0;i<wordsArray.length;i++){
            answerCharList.add(String.valueOf( wordsArray[i]));
        }
        b1.setVisibility(View.GONE);  b3.setVisibility(View.GONE);  b5.setVisibility(View.GONE);
        b2.setVisibility(View.GONE);  b4.setVisibility(View.GONE);  b6.setVisibility(View.GONE);

        addText();
    }
    public void addText(){
        Collections.shuffle(answerCharList);
        for(int i=0;i<answerCharList.size();i++) {
            switch (i){
                case 0:
                    b1.setVisibility(View.VISIBLE);
                    b1.setText(answerCharList.get(i));
                break;
                case 1:
                    b2.setVisibility(View.VISIBLE);
                    b2.setText(answerCharList.get(i));
                    break;
                case 2:
                    b3.setVisibility(View.VISIBLE);
                    b3.setText(answerCharList.get(i));
                    break;
                case 3:
                    b4.setVisibility(View.VISIBLE);
                    b4.setText(answerCharList.get(i));
                    break;
                case 4:
                    b5.setVisibility(View.VISIBLE);
                    b5.setText(answerCharList.get(i));
                    break;
                case 5:
                    b6.setVisibility(View.VISIBLE);
                    b6.setText(answerCharList.get(i));
                break;
            }

        }
    }
    public void type(View view){
        if(view.getId()==R.id.b1){
            if(notClicked1==true){
                notClicked1=false;
            typedAnswer=typedAnswer+b1.getText().toString();
            b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));
            if(typedAnswer.length()==picsnword.getAnswer().length()) {
                check();
            }
            }
        }else if(view.getId()==R.id.b2){
            if(notClicked2=true){
                notClicked2=false;
            typedAnswer=typedAnswer+b2.getText().toString();
            b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));
            if(typedAnswer.length()==picsnword.getAnswer().length()) {
                check();
            }
            }
        }else if(view.getId()==R.id.b3){
            if(notClicked3=true){
                notClicked3=false;
            typedAnswer=typedAnswer+b3.getText().toString();
            b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));
            if(typedAnswer.length()==picsnword.getAnswer().length()) {
                check();
            }
            }
        }else if(view.getId()==R.id.b4){
            if(notClicked4=true){
                notClicked4=false;
            typedAnswer=typedAnswer+b4.getText().toString();
            b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));
            if(typedAnswer.length()==picsnword.getAnswer().length()) {
                check();
            }
            }
        }else if(view.getId()==R.id.b5){
            if(notClicked5=true){
                notClicked5=false;
            typedAnswer=typedAnswer+b5.getText().toString();
            b5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));
            if(typedAnswer.length()==picsnword.getAnswer().length()) {
                check();
            }
            }
        }else if(view.getId()==R.id.b6){
            if(notClicked6=true){
                notClicked6=false;
            typedAnswer=typedAnswer+b6.getText().toString();
            b6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.yello_round_back));
            if(typedAnswer.length()==picsnword.getAnswer().length()) {
                check();
            }
            }
        }

    }
    public void check(){
        if(isConnected(this)) {
            if (typedAnswer.equals(picsnword.getAnswer())) {
                c += 50;
                coinsView.setText(String.valueOf(c));
                b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                b5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                b6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_round_back));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fbback));
                        b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fbback));
                        b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fbback));
                        b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fbback));
                        b5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fbback));
                        b6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fbback));
                        addImage();
                    }
                }, 500);
            } else {
                b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_round_back));
                b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_round_back));
                b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_round_back));
                b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_round_back));
                b5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_round_back));
                b6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_round_back));
                b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fbback));
                b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fbback));
                b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fbback));
                b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fbback));
                b5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fbback));
                b6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fbback));
            }
        }else {
            isConnected(this);
        }

    }

    public void reset(View view){
        if(view.getId()==R.id.edit){
            typedAnswer="";
            notClicked1=true;notClicked2=true;notClicked3=true;notClicked4=true;notClicked5=true;notClicked6=true;
            b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fbback));
            b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fbback));
            b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fbback));
            b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fbback));
            b5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fbback));
            b6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fbback));
        }
    }

    public void skipWord(View view){
        if(isConnected(this)){
        if(view.getId()==R.id.skip){
            notClicked1=true;notClicked2=true;notClicked3=true;notClicked4=true;notClicked5=true;notClicked6=true;
            noOfCoins=Integer.parseInt(totalCoinsView.getText().toString());
            noOfCoins-=100;
            totalCoinsView.setText(String.valueOf(noOfCoins));
            b1.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fbback));
            b2.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fbback));
            b3.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fbback));
            b4.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fbback));
            b5.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fbback));
            b6.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fbback));
            totalCoinsView.setText(String.valueOf(noOfCoins));
            addImage();
        }
        }else {
            isConnected(this);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        changed=null;
        finish();
        startActivity(new Intent(getApplicationContext(), pic_select_Activity.class));
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