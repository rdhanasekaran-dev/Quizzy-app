package com.sekar.quizzy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sekar.quizzy.model.Points;
import com.sekar.quizzy.tabs.tab2Fragment;
import com.sekar.quizzy.tabs.tab3Fragment;
import com.sekar.quizzy.tabs.tab4Fragment;
import com.sekar.quizzy.tabs.tab5Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {
    ImageView img;
    Dialog dialog;
    Dialog mydialog;
    ImageView profile;
    DatabaseReference  reference,reference2;
    SharedPreferences.Editor editor;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    private TabLayout tablayout;
    String m;
    List<Points> pointsList;
    String deviceID;
    public static String purpose1;
    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        img=findViewById(R.id.img);
        profile=findViewById(R.id.profile);
        tablayout=findViewById(R.id.tabLayout);
        deviceID= Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        pointsList=new ArrayList<>();
            reference2 = FirebaseDatabase.getInstance().getReference().child("Devices").child(deviceID);


        mPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());


        tablayout.setupWithViewPager(mPager,true);


        final float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());

        Animation animate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        img.setAnimation(animate);


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

    public void navActivity(View view){
        if(view.getId()==R.id.profile){
            if(isConnected(this)) {
                Intent i = new Intent(getApplicationContext(),EntryLoadActivity.class);
                startActivity(i);
            }else {
                isConnected(this);
            }
        }else if(view.getId()==R.id.privacyPolicy){
            Intent i=new Intent(getApplicationContext(),privacyActivity2.class);
            startActivity(i);
        }else if(view.getId()==R.id.report){
            ImageView txtclose;
            mydialog=new Dialog(Main2Activity.this);
            mydialog.setContentView(R.layout.custom_popup_comments);
            Button b1=mydialog.findViewById(R.id.send);

            mydialog.getWindow().getAttributes().windowAnimations=R.style.Animation;
            txtclose=mydialog.findViewById(R.id.txtclose);
            txtclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mydialog.dismiss();
                }
            });
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        EditText ed=mydialog.findViewById(R.id.comments);
                        String comment=ed.getText().toString();
                        String[] TO = {"dogoodteamofdevelopers@gmail.com"};
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Quizzy customer reports");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, comment);
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));

                    }catch (ActivityNotFoundException ex){

                    };
                }
            });
            mydialog.show();
        }else if(view.getId()==R.id.comment){
            ImageView txtclose;
            mydialog=new Dialog(Main2Activity.this);
            mydialog.setContentView(R.layout.custom_popup_comments_email);
            Button b1=mydialog.findViewById(R.id.send);

            mydialog.getWindow().getAttributes().windowAnimations=R.style.Animation;
            txtclose=mydialog.findViewById(R.id.txtclose);
            txtclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mydialog.dismiss();
                }
            });
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        EditText ed=mydialog.findViewById(R.id.comments);
                        String comment=ed.getText().toString();
                        String[] TO = {"dogoodteamofdevelopers@gmail.com"};
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Quizzy customer comments");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, comment);
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));

                    }catch (ActivityNotFoundException ex){

                    };
                }
            });
            mydialog.show();
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

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {


        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(final int position) {

            switch (position){
                case 0:
                    return new tab2Fragment();
                case 1:
                    return new tab3Fragment();
                case 2:
                    return new tab4Fragment();
                case 3:
                    return new tab5Fragment();
                default:
                    return null;}


        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
