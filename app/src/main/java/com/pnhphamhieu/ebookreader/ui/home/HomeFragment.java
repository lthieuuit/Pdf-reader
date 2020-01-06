package com.pnhphamhieu.ebookreader.ui.home;

import android.media.midi.MidiOutputPort;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnhphamhieu.ebookreader.Files;
import com.pnhphamhieu.ebookreader.FilesAdapter;
import com.pnhphamhieu.ebookreader.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static String KEY_Month = "month";
    private static String KEY_Year = "year";

    public static HomeFragment newInstance() {
        HomeFragment fr = new HomeFragment();

        Bundle args = new Bundle();
//        args.putInt(KEY_Month, month);
//        args.putInt(KEY_Year, year);
        fr.setArguments(args);
        return fr;
    }
    private FilesAdapter filesAdapter;
    private List<Files> filesList = new ArrayList<>();
//    private  int Month = 1;
//    private  int Year = 2019;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Month = getArguments().getInt(KEY_Month);
//        Year = getArguments().getInt(KEY_Year);

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView rvFiles = view.findViewById(R.id.rv_File);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        filesAdapter = new FilesAdapter(getContext(), filesList);
        rvFiles.setLayoutManager(layoutManager);
        rvFiles.setAdapter(filesAdapter);
        loadData();
        return view;
    }

    public void loadData() {
        //sample
            Files files1 = new Files(1, "Conan", Files.GROUP_NEW);
            Files files2 = new Files(2, "AOT", Files.GROUP_FAVORITE);
            Files files3 = new Files(3, "Doraemon", Files.GROUP_NEW);
            Files files4 = new Files(4, "OnePiece", Files.GROUP_FAVORITE);
            filesList.add(files1);
            filesList.add(files2);
            filesList.add(files3);
            filesList.add(files4);
        filesAdapter.notifyDataSetChanged();
    }
}