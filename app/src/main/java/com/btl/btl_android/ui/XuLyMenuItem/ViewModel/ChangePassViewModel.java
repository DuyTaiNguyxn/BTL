package com.btl.btl_android.ui.XuLyMenuItem.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChangePassViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ChangePassViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("doi pass");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
