package com.btl.btl_android.XuLyMenuItem.Fragment;
import static com.yentrinh.food.Activity.DangNhap.idtkonl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.yentrinh.food.R;
import com.yentrinh.food.databinding.FragmentChangePassBinding;

public class ChangePassFragment extends Fragment {

    private FragmentChangePassBinding binding;
    private String id;
    private View vv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = idtkonl;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_change_pass, container, false);

        vv = inf;
        return inf;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}