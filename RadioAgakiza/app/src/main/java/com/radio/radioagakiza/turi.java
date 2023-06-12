package com.radio.radioagakiza;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class turi extends Fragment {


    public turi() {
        // Required empty public constructor
    }

    private Button youtube;
    private Button facebook;
    private Button twitter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_turi, container, false);
        //this code is used to redirect on youtube
        youtube=view.findViewById(R.id.youtube);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@agakizaadventtv8977"));
                startActivity(followIntent);
            }
        });

        //this code is used to redirect on facebook
        youtube=view.findViewById(R.id.facebook);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100064511151367&sk=photos_by"));
                startActivity(followIntent);
            }
        });

        //this code is used to redirect on twitter
        youtube=view.findViewById(R.id.twitter);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent followIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/radioagakiza?lang=en"));
                startActivity(followIntent);
            }
        });



        return view;

    }


}