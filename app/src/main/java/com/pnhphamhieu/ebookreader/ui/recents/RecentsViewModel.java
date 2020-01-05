package com.pnhphamhieu.ebookreader.ui.recents;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecentsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RecentsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Sách đọc gần đây");
    }

    public LiveData<String> getText() {
        return mText;
    }
}