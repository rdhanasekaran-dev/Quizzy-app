package com.sekar.quizzy;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

import okhttp3.internal.tls.OkHostnameVerifier;

public class StartScreenActivity extends AppCompatActivity {

    EditText Username,country;
    DatabaseReference reference1;
    HashMap<String,String> usernameHashmap,hashmap;
    String imguri;
    String deviceID;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        Username=findViewById(R.id.username);
        country=findViewById(R.id.country);
        deviceID= Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        usernameHashmap=new HashMap<>();
        hashmap=new HashMap<>();
        imguri="";
        reference1=FirebaseDatabase.getInstance().getReference().child("Devices");


        sharedPreferences = getSharedPreferences("myPreferencesAct", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if(sharedPreferences.getString("account", "go").equals("created")){
            goToActivity();
        }

        isConnected(getApplicationContext());
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

    public void upload(View view){
        if(view.getId()==R.id.start){
          if((!Username.getText().toString().equals(""))&&(!country.getText().toString().equals(""))){
                reference1=reference1.child(deviceID);
                hashmap.put("country",country.getText().toString());
                hashmap.put("deviceid",deviceID);
                hashmap.put("username",Username.getText().toString());
                hashmap.put("points","1000");
                hashmap.put("imageuri","default");
                reference1.setValue(hashmap);
                editor.putString("account","created");
                editor.apply();
                goToActivity();
            }

        }
    }

    public void goToActivity(){
       if(isConnected(this)) {
           Intent i = new Intent(getApplicationContext(), Main2Activity.class);
           finish();
           startActivity(i);
       }else {
           isConnected(this);
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
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}