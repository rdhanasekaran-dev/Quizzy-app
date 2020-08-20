package com.sekar.quizzy.tabs;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sekar.quizzy.R;
import com.sekar.quizzy.riddles.RiddleLevelActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class tab5Fragment extends Fragment {

    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab5, container, false);

        button=view.findViewById(R.id.play);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity=new Intent(getActivity(), RiddleLevelActivity.class);
                activity.putExtra("type","riddle");
                startActivity(activity);
            }
        });

        return view;
    }
}
