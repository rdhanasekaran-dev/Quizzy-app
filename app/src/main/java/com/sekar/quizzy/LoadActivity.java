package com.sekar.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.sekar.quizzy.pictureQuiz.ConnectionsSelectActivity;
import com.sekar.quizzy.pictureQuiz.PicsnWordSelectActivity;
import com.sekar.quizzy.puzzle.AnagramSelectActivity;
import com.sekar.quizzy.puzzle.MathSelectActivity;
import com.sekar.quizzy.riddles.RiddleLevelActivity;

public class LoadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        String word=getIntent().getExtras().getString("type");
        if(word.equals("ConnectionsResult")){
            Intent i=new Intent(getApplicationContext(), ConnectionsSelectActivity.class);
            i.putExtra("type","connections");
            startActivity(i);
        }
        if(word.equals("wordResult")){
            Intent i=new Intent(getApplicationContext(), AnagramSelectActivity.class);
            i.putExtra("type","wordpuzzle");
            startActivity(i);
        }

        if(word.equals("PicsnwordResult")){
            Intent i=new Intent(getApplicationContext(), PicsnWordSelectActivity.class);
            i.putExtra("type","picsnword");
            startActivity(i);
        }
        if(word.equals("riddleResult")){
            Intent i=new Intent(getApplicationContext(), RiddleLevelActivity.class);
            i.putExtra("type","riddle");
            startActivity(i);
        }
        if(word.equals("mathResult")){
            Intent i=new Intent(getApplicationContext(), MathSelectActivity.class);
            i.putExtra("type","mathpuzzle");
            startActivity(i);
        }

    }
}