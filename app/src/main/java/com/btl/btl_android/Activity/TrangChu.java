package com.btl.btl_android.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.btl.btl_android.DAO.TaiKhoanDAO;
import com.btl.btl_android.R;
import com.btl.btl_android.databinding.ActivityTrangchuBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TrangChu extends AppCompatActivity {

    private ActivityTrangchuBinding binding;
    private Context context;
    public static TaiKhoanDAO  dbTaiKhoan;
    public static Context trangchucontext;
    public static Activity aty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aty = this;
        context = this;
        trangchucontext = this;
        dbTaiKhoan = new TaiKhoanDAO(this);
        Intent intent = this.getIntent();
        binding = ActivityTrangchuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarTrangchu.toolbar);
        binding.appBarTrangchu.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}