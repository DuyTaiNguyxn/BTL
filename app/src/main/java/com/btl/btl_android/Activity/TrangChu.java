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

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.btl.btl_android.DAO.MonAnDAO;
import com.btl.btl_android.DAO.TaiKhoanDAO;
import com.btl.btl_android.R;
import com.btl.btl_android.XuLyFragment.SuaThongTinFragment;
import com.btl.btl_android.databinding.ActivityTrangchuBinding;
import com.btl.btl_android.ui.XuLyMenuItem.Fragment.HomeFragment;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TrangChu extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private int REQUEST_CODE_FOLDER = 456;
    private ActivityTrangchuBinding binding;
    private Context context;
    public static TaiKhoanDAO  dbTaiKhoan;

    public static MonAnDAO dbMonAn;
    public static Context trangchucontext;
    public static Activity aty;
    public static String TimMonAn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aty = this;
        context = this;
        trangchucontext = this;
        dbTaiKhoan = new TaiKhoanDAO(this);
        dbMonAn = new MonAnDAO(this);

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

    public  void Mofolder(View view){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_FOLDER);

        Toast.makeText(this, "abc", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onActivityResult(int requesCode, int resultCode, Intent data) {

        if (requesCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri  = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ImageView imghinh = (ImageView) findViewById(R.id.imgAvatar);
                imghinh.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requesCode, resultCode, data);
    }


    public void btnmotrangdangbai(View view) {
        Toast.makeText(this, "mo trang dang ", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this , DangBaiViet.class));

    }

    public void LoadKien(View view) {
        TextView txt = (TextView) findViewById(R.id.txtTentk);
        TimMonAn = "KIENG";
        Fragment fragment =  new HomeFragment();
        replaceFragment(fragment);
    }
    public void LoadMan(View view) {
        TextView txt = (TextView) findViewById(R.id.txtTentk);
        TimMonAn = "MAN";
        Fragment fragment =  new HomeFragment();
        replaceFragment(fragment);
    }
    public void LoadChay(View view) {
        TextView txt = (TextView) findViewById(R.id.txtTentk);
        TimMonAn = "CHAY";
        Fragment fragment =  new HomeFragment();
        replaceFragment(fragment);
    }
    public void LoadVat(View view) {
        TextView txt = (TextView) findViewById(R.id.txtTentk);
        TimMonAn = "VAT";
        Fragment fragment =  new HomeFragment();
        replaceFragment(fragment);
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
  
    public void ChuyenDenTimKiem(View view) {
        Toast.makeText(this, "Sẵn sàng để tìm kiếm ", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this , TimKiem.class));
    }
  
    public void ChuyenDenDangXuat(View view) {
        Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, DangNhap.class));
    }
}