package com.pnhphamhieu.ebookreader.ui.openfile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OpenfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OpenfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Chọn file sách .epub, .pdf muốn đọc");
    }

    public LiveData<String> getText() {
        return mText;
    }
}