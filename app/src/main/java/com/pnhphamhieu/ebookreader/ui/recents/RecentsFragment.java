package com.pnhphamhieu.ebookreader.ui.recents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pnhphamhieu.ebookreader.R;

public class RecentsFragment extends Fragment {

    private RecentsViewModel recentsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recentsViewModel =
                ViewModelProviders.of(this).get(RecentsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recents, container, false);
        final TextView textView = root.findViewById(R.id.text_recents);
        recentsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}