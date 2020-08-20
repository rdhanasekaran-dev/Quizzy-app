package com.sekar.quizzy.puzzle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sekar.quizzy.LoadActivity;
import com.sekar.quizzy.R;
import com.sekar.quizzy.model.Maths;

import java.util.ArrayList;
import java.util.List;

public class MathActivity extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    TextView answerTextView,questionTextView,totalCoinsHolderView,coinsView,countView,levelViewHolder;
    DatabaseReference reference,reference2;
    List<Maths> mathsQuesList;
    ImageView correct,wrong;
    Maths maths;
    int start=0;
    int c=0;
    int i=1;
    Dialog result;
    int startLimit,endLimit;
    long total;
    long noOfCoins;
    String deviceID;
    public static String points;
    public static String changed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
        reference= FirebaseDatabase.getInstance().getReference().child("Maths");
        totalCoinsHolderView=findViewById(R.id.totalcoinsHolder);
        coinsView=findViewById(R.id.coins_view);
        b1=findViewById(R.id.b1);   b2=findViewById(R.id.b2);   b3=findViewById(R.id.b3);   b4=findViewById(R.id.b4);   b5=findViewById(R.id.b5);
        b6=findViewById(R.id.b6);   b7=findViewById(R.id.b7);   b8=findViewById(R.id.b8);   b9=findViewById(R.id.b9);   b10=findViewById(R.id.b10);
        answerTextView=findViewById(R.id.answerEnterView);
        questionTextView=findViewById(R.id.questionView);
        correct=findViewById(R.id.correct);
        reference2=FirebaseDatabase.getInstance().getReference().child("Points");
        countView=findViewById(R.id.count_view);
        wrong=findViewById(R.id.wrong);
        mathsQuesList=new ArrayList<>();
        levelViewHolder=findViewById(R.id.level_view);
        startLimit=getIntent().getExtras().getInt("start");
        endLimit=getIntent().getExtras().getInt("end");
        changed=String.valueOf(startLimit);
        result=new Dialog(MathActivity.this);
        result.setContentView(R.layout.result_dialogue);
        points="0";
        noOfCoins=0;
        isConnected(this);
        deviceID= Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        reference2=FirebaseDatabase.getInstance().getReference().child("Devices").child(deviceID);

        if(startLimit==0)
            levelViewHolder.setText(R.string.level1);
        else if(startLimit==5)
            levelViewHolder.setText(R.string.level2);
        else if(startLimit==10)
            levelViewHolder.setText(R.string.level3);
        else if(startLimit==15)
            levelViewHolder.setText(R.string.level4);
        else if(startLimit==20)
            levelViewHolder.setText(R.string.level5);
        else if(startLimit==25)
            levelViewHolder.setText(R.string.level6);
        else if(startLimit==30)
            levelViewHolder.setText(R.string.level7);
        else if(startLimit==35)
            levelViewHolder.setText(R.string.level8);
        else if(startLimit==40)
            levelViewHolder.setText(R.string.level9);
        else if(startLimit==45)
            levelViewHolder.setText(R.string.level10);
        else if(startLimit==50)
            levelViewHolder.setText(R.string.level11);
        else if(startLimit==55)
            levelViewHolder.setText(R.string.level12);
        else if(startLimit==60)
            levelViewHolder.setText(R.string.level13);
        else if(startLimit==65)
            levelViewHolder.setText(R.string.level14);
        else if(startLimit==70)
            levelViewHolder.setText(R.string.level15);
        else if(startLimit==75)
            levelViewHolder.setText(R.string.level16);
        else if(startLimit==80)
            levelViewHolder.setText(R.string.level17);
        else if(startLimit==85)
            levelViewHolder.setText(R.string.level18);
        else if(startLimit==90)
            levelViewHolder.setText(R.string.level19);
        else if(startLimit==95)
            levelViewHolder.setText(R.string.level20);
        else if(startLimit==100)
            levelViewHolder.setText(R.string.level21);
        else if(startLimit==105)
            levelViewHolder.setText(R.string.level22);
        else if(startLimit==110)
            levelViewHolder.setText(R.string.level23);
        else if(startLimit==120)
            levelViewHolder.setText(R.string.level24);
        else if(startLimit==125)
            levelViewHolder.setText(R.string.level25);
        else if(startLimit==130)
            levelViewHolder.setText("");



        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mathsQuesList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    mathsQuesList.add(snapshot.getValue(Maths.class));
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
        result.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {


                String balance=String.valueOf(total);
                reference2.child("points").setValue(balance);
                Intent i=new Intent(getApplicationContext(), LoadActivity.class);
                i.putExtra("type","mathResult");
                finish();
                startActivity(i);


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

    public void next() {
        if (startLimit < endLimit) {
            countView.setText(""+i+"/20");
            maths=mathsQuesList.get(startLimit);
            correct.setVisibility(View.GONE);
            wrong.setVisibility(View.GONE);
            questionTextView.setText(maths.getQuestion());
            startLimit++;
            i++;
        } else{
            total=c+Integer.parseInt(totalCoinsHolderView.getText().toString());
            TextView coinsReceived,totalCoins;
            result.getWindow().getAttributes().windowAnimations=R.style.Animation;
            coinsReceived=result.findViewById(R.id.coinsReceivedView);
            totalCoins=result.findViewById(R.id.totalCoinsView);
            coinsReceived.setText(String.valueOf(c));
            totalCoins.setText(String.valueOf(total));
            result.show();
        }
    }


    public void type(View view){
        String word=answerTextView.getText().toString();
        if(view.getId()==R.id.b1){
            answerTextView.setText(word+b1.getText().toString());
        }else if(view.getId()==R.id.b2){
            answerTextView.setText(word+b2.getText().toString());
        }else if(view.getId()==R.id.b3){
            answerTextView.setText(word+b3.getText().toString());
        }else if(view.getId()==R.id.b4){
            answerTextView.setText(word+b4.getText().toString());
        }else if(view.getId()==R.id.b5){
            answerTextView.setText(word+b5.getText().toString());
        }else if(view.getId()==R.id.b6){
            answerTextView.setText(word+b6.getText().toString());
        }else if(view.getId()==R.id.b7){
            answerTextView.setText(word+b7.getText().toString());
        }else if(view.getId()==R.id.b8){
            answerTextView.setText(word+b8.getText().toString());
        }else if(view.getId()==R.id.b9){
            answerTextView.setText(word+b9.getText().toString());
        }else if(view.getId()==R.id.b10){
            answerTextView.setText(word+b10.getText().toString());
        }
    }
    public void erase(View view){
        if(view.getId()==R.id.erase)
            answerTextView.setText("");
    }
    public void checkAnswer(View view){
        if(view.getId()==R.id.enter){
            if(answerTextView.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"Please enter Answer",Toast.LENGTH_LONG).show();
            }else if(maths.getAnswer().equals(answerTextView.getText().toString())) {
                c+=50;
                coinsView.setText(String.valueOf(c));
                correct.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next();
                        answerTextView.setText("");
                    }
                },1000);
            }
            else {
                wrong.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        wrong.setVisibility(View.GONE);
                        answerTextView.setText("");
                    }
                },1000);
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        changed=null;
        finish();
        startActivity(new Intent(getApplicationContext(), PuzzleSelectActivity.class));
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