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
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.btl.btl_android.DAO.TaiKhoanDAO;
import com.btl.btl_android.R;
import com.btl.btl_android.databinding.ActivityTrangchuBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TrangChu extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private int REQUEST_CODE_FOLDER = 456;
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
        String idtk = intent.getStringExtra("idtk");
        Cursor cs = dbTaiKhoan.GetTaiKhoan(idtk);
        byte[] hinh  = null;
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
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_doipass, R.id.nav_search, R.id.nav_Dangxuat)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_trangchu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        TextView etten = binding.txtTen;
        TextView etgmai = binding.txtGmail;
        while (cs.moveToNext()) {
            String ten = cs.getString(2);
            String gmail = cs.getString(5);
            if(cs.getBlob(6) != null){
                hinh = cs.getBlob(6);
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
                ImageView avtr = binding.imgAvt;
                avtr.setImageBitmap(bitmap);
            }
            etten.setText(ten);
            etgmai.setText(gmail);
            break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_trangchu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    public void ChuyenDenDangXuat(View view) {
        Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, DangNhap.class));
    }


}