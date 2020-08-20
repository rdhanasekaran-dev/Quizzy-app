package com.sekar.quizzy.puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.sekar.quizzy.Main2Activity;
import com.sekar.quizzy.R;

public class PuzzleSelectActivity extends AppCompatActivity {
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_select);

        MobileAds.initialize(this,"ca-app-pub-2780742672110526/6821820498");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    public void selectLevels(View view){
        if(view.getId()==R.id.connectme){
            startActivity(new Intent(getApplicationContext(), ConstellationActivity.class));
        }
        else if(view.getId()==R.id.wordpuzzle){
            Intent j=new Intent(getApplicationContext(),AnagramSelectActivity.class);
            j.putExtra("type","wordpuzzle");
            startActivity(j);
        }
        else if(view.getId()==R.id.mathfun){
            Intent k=new Intent(getApplicationContext(),MathSelectActivity.class);
            k.putExtra("type","mathpuzzle");
            startActivity(k);
        }
        else if(view.getId()==R.id.listmem){
            Intent j=new Intent(getApplicationContext(),ListMemActivity.class);
            startActivity(j);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), Main2Activity.class));
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
