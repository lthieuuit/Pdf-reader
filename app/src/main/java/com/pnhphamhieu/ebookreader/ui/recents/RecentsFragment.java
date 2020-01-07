package com.pnhphamhieu.ebookreader.ui.recents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pnhphamhieu.ebookreader.R;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;



public class RecentsFragment extends Fragment {

    private RecentsViewModel recentsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recentsViewModel =
                ViewModelProviders.of(this).get(RecentsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recents, container, false);

        recentsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

}