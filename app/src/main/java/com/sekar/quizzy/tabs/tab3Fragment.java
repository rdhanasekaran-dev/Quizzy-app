package com.sekar.quizzy.tabs;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sekar.quizzy.R;
import com.sekar.quizzy.pictureQuiz.pic_select_Activity;

public class tab3Fragment extends Fragment {
    Button b2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab3, container, false);

        b2=view.findViewById(R.id.play);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), pic_select_Activity.class);
                startActivity(i);
            }
        });


        return view;
    }
}
