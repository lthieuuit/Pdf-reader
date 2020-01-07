package com.pnhphamhieu.ebookreader.ui.openfile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pnhphamhieu.ebookreader.R;

import java.lang.String;

public class OpenfileFragment extends Fragment {

    private OpenfileViewModel openfileViewModel;
    Button bt;
    //TextView tw_path;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        openfileViewModel =
                ViewModelProviders.of(this).get(OpenfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_openfile, container, false);
        //final TextView textView = root.findViewById(R.id.text_openfile);
        openfileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        return root;
    }


}