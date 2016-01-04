package com.topster.topster.firstlab_cinema;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoadingScreenFragment extends Fragment {

    public Button myButton;

    public LoadingScreenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loading_screen, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();

        this.myButton = (Button) getView().findViewById(R.id.mainButton);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TESTE LAB CINEMA", "Cliquei no botão");

            }
        });

    }
}


