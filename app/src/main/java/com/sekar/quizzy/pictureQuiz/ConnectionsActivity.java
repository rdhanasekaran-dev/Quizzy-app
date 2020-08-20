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
import android.text.method.DialerKeyListener;
import android.view.View;
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
import com.sekar.quizzy.Main2Activity;
import com.sekar.quizzy.R;
import com.sekar.quizzy.model.Connections;
import com.sekar.quizzy.puzzle.PuzzleSelectActivity;
import com.sekar.quizzy.riddles.RiddleActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.List;

public class ConnectionsActivity extends AppCompatActivity {
    TextView answerShowEd,totalCoinsHolderView,CoinsGotView,countView;
    DatabaseReference reference,reference2;
    List<Connections> connectionsList;
    Connections connections;
    ImageView img1,img2;
    int startLimt;
    int endLimit;
    Dialog result;
    int c;
    long total;
    long noOfCoins;
    String deviceID;
    public static String points;
    int k=1;
    public static String changed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connections);
        answerShowEd=findViewById(R.id.ed);
        isConnected(this);
        connectionsList=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference().child("Connections");
        deviceID= Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        reference2=FirebaseDatabase.getInstance().getReference().child("Devices").child(deviceID);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        result=new Dialog(ConnectionsActivity.this);
        result.setContentView(R.layout.result_dialogue);
        startLimt=0;endLimit=0;noOfCoins=0;
        CoinsGotView=findViewById(R.id.coins_view);
        countView=findViewById(R.id.count_view);
        totalCoinsHolderView=findViewById(R.id.totalcoinsHolder);
        startLimt=getIntent().getExtras().getInt("start");
        endLimit=getIntent().getExtras().getInt("end");
        changed=String.valueOf(startLimt);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                connectionsList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    connectionsList.add(snapshot.getValue(Connections.class));
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
                i.putExtra("type","ConnectionsResult");
                finish();
                startActivity(i);

            }
        });
    }

    public void addImage() {
        if (isConnected(this)) {
            if (startLimt < endLimit) {
                countView.setText("" + k + "/5");
                connections = connectionsList.get(startLimt);
                Picasso.get().load(connections.getImg1()).into(img1);
                Picasso.get().load(connections.getImg2()).into(img2);
                startLimt++;
                k++;
            } else {

                total = c + Integer.parseInt(totalCoinsHolderView.getText().toString());
                TextView coinsReceived, totalCoins;
                result.getWindow().getAttributes().windowAnimations = R.style.Animation;
                coinsReceived = result.findViewById(R.id.coinsReceivedView);
                totalCoins = result.findViewById(R.id.totalCoinsView);
                coinsReceived.setText(String.valueOf(c));
                totalCoins.setText(String.valueOf(total));
                result.show();

            }
        } else {
            isConnected(this);
       }
    }

    public boolean isConnected(Context context){
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


    public void enter(View view){
        if(view.getId()==R.id.a){
            answerShowEd.setText(answerShowEd.getText().toString()+"q");
        }else if(view.getId()==R.id.b){
            answerShowEd.setText(answerShowEd.getText().toString()+"w");
        }else if(view.getId()==R.id.c){
            answerShowEd.setText(answerShowEd.getText().toString()+"e");
        }else if(view.getId()==R.id.d){
            answerShowEd.setText(answerShowEd.getText().toString()+"r");
        }else if(view.getId()==R.id.e){
            answerShowEd.setText(answerShowEd.getText().toString()+"t");
        }else if(view.getId()==R.id.f){
            answerShowEd.setText(answerShowEd.getText().toString()+"y");
        }else if(view.getId()==R.id.g){
            answerShowEd.setText(answerShowEd.getText().toString()+"u");
        }else if(view.getId()==R.id.h){
            answerShowEd.setText(answerShowEd.getText().toString()+"i");
        }else if(view.getId()==R.id.i){
            answerShowEd.setText(answerShowEd.getText().toString()+"o");
        }else if(view.getId()==R.id.j){
            answerShowEd.setText(answerShowEd.getText().toString()+"p");
        }else if(view.getId()==R.id.k){
            answerShowEd.setText(answerShowEd.getText().toString()+"a");
        }else if(view.getId()==R.id.l){
            answerShowEd.setText(answerShowEd.getText().toString()+"s");
        }else if(view.getId()==R.id.m){
            answerShowEd.setText(answerShowEd.getText().toString()+"d");
        }else if(view.getId()==R.id.n){
            answerShowEd.setText(answerShowEd.getText().toString()+"f");
        }else if(view.getId()==R.id.o){
            answerShowEd.setText(answerShowEd.getText().toString()+"g");
        }else if(view.getId()==R.id.p){
            answerShowEd.setText(answerShowEd.getText().toString()+"h");
        }else if(view.getId()==R.id.q){
            answerShowEd.setText(answerShowEd.getText().toString()+"j");
        }else if(view.getId()==R.id.r){
            answerShowEd.setText(answerShowEd.getText().toString()+"k");
        }else if(view.getId()==R.id.s){
            answerShowEd.setText(answerShowEd.getText().toString()+"l");
        }else if(view.getId()==R.id.t){
            answerShowEd.setText(answerShowEd.getText().toString()+"z");
        }else if(view.getId()==R.id.u){
            answerShowEd.setText(answerShowEd.getText().toString()+"x");
        }else if(view.getId()==R.id.v){
            answerShowEd.setText(answerShowEd.getText().toString()+"c");
        }else if(view.getId()==R.id.w){
            answerShowEd.setText(answerShowEd.getText().toString()+"v");
        }else if(view.getId()==R.id.x){
            answerShowEd.setText(answerShowEd.getText().toString()+"b");
        }else if(view.getId()==R.id.y){
            answerShowEd.setText(answerShowEd.getText().toString()+"n");
        }else if(view.getId()==R.id.z){
            answerShowEd.setText(answerShowEd.getText().toString()+"m");
        }else if(view.getId()==R.id.delete){
            if(answerShowEd.getText().equals("")){
                Toast.makeText(getApplicationContext(),"Enter answer",Toast.LENGTH_SHORT).show();
            }else {
                String word = answerShowEd.getText().toString();
                answerShowEd.setText(word.substring(0, ((word.length()) - 1)));
            }
        }
    }

    public void checkAnswer(View view){
        if(answerShowEd.getText().toString().equals(connections.getAnswer())){
            c+=50;
            CoinsGotView.setText(String.valueOf(c));
            answerShowEd.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.green_round_back));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    answerShowEd.setText("");
                    answerShowEd.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                    addImage();
                }
            },500);
        }else{
            answerShowEd.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_round_back));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    answerShowEd.setText("");
                    answerShowEd.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.round_white_back));
                }
            },500);
        }
    }

    public void skipWord(View view){
        if(isConnected(this)) {
            noOfCoins = Integer.parseInt(totalCoinsHolderView.getText().toString());
            noOfCoins -= 100;
            totalCoinsHolderView.setText(String.valueOf(noOfCoins));
            addImage();
        }else{
            isConnected(this);
        }
    }

    public void showHint(View view){
        if(isConnected(this)) {
            answerShowEd.setText("");
            String word = connections.getAnswer();
            int length = word.length();
            answerShowEd.setText(word.substring(0, (length / 2)));
        }else {
            isConnected(this);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        changed=null;
        finish();
        startActivity(new Intent(getApplicationContext(),pic_select_Activity.class));
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