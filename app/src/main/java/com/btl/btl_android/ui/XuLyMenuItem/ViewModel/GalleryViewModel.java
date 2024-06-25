package com.btl.btl_android.ui.XuLyMenuItem.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private String id ;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }
    public void SetID(String napid) {

        id = napid;
    }
    public String GetID() {

        return id;
    }
    public LiveData<String> getText() {
        return mText;
    }
}