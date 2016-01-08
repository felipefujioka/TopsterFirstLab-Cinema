package com.topster.topster.firstlab_cinema;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.topster.topster.firstlab_cinema.databinding.FragmentLoadingScreenBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoadingScreenFragment extends Fragment {

    public Button myButton;
    public LoadingScreenViewModel viewModel;

    public LoadingScreenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentLoadingScreenBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_loading_screen, container, false);
        this.viewModel = new LoadingScreenViewModel();
        binding.setViewModel(this.viewModel);

        return binding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
    }
}


