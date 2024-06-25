package com.btl.btl_android.Activity;


import static com.btl.btl_android.Activity.DangNhap.idtkonl;
import static com.btl.btl_android.ui.XuLyMenuItem.Fragment.SlideshowFragment.MaSua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.btl.btl_android.DAO.MonAnDAO;
import com.btl.btl_android.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SuaMonAn extends AppCompatActivity {
    private int REQUEST_CODE_FOLDER = 456;
    private Context context;
    private MonAnDAO dbmonan;
    private EditText edSuaTen;
    private CheckBox edCheckKieng;
    private CheckBox edCheckChay;
    private CheckBox edCheckMan;
    private CheckBox edCheckVat;
    private TextView txtSuaCongThuc;
    private ImageView imgSua_hinh;
    private byte[] hinh  = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_mon_an);
        context =this;
        dbmonan = new MonAnDAO(this);

        edSuaTen = (EditText) findViewById(R.id.txt_sua_tenmon) ;
        edCheckKieng = (CheckBox) findViewById(R.id.checkBox_suaKieng) ;
        edCheckChay = (CheckBox) findViewById(R.id.checkBox_suaChay) ;
        edCheckMan = (CheckBox) findViewById(R.id.checkBox_suaMan) ;
        edCheckVat = (CheckBox) findViewById(R.id.checkBox_suaVat) ;
        txtSuaCongThuc = (TextView) findViewById(R.id.txt_sua_CongThuc);
        imgSua_hinh = (ImageView) findViewById(R.id.imagehinhmon) ;
        Cursor cs = dbmonan.GETALL();

        cs.moveToFirst();
        while (0 == 0){
            if(cs.getString(0).equals(MaSua)){
                edSuaTen.setText(cs.getString(1));
                String loai = cs.getString(2);
                if(loai.contains("CHAY"))
                    edCheckChay.setChecked(true);
                if(loai.contains("MAN"))
                    edCheckMan.setChecked(true);
                if(loai.contains("KIENG"))
                    edCheckKieng.setChecked(true);
                if(loai.contains("VAT"))
                    edCheckVat.setChecked(true);

                txtSuaCongThuc.setText(cs.getString(4));

                if(cs.getBlob(5) != null){
                    hinh = cs.getBlob(5);

                    Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
                    imgSua_hinh.setImageBitmap(bitmap);
                }

                break;
            }
            cs.moveToNext();
        }


    }


    public void ChonAnh(View view) {
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
                ImageView imghinh = (ImageView) findViewById(R.id.imagehinhmon);
                imghinh.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requesCode, resultCode, data);
    }

    public void btnSua_Luu(View view) {
        edSuaTen = (EditText) findViewById(R.id.txt_sua_tenmon) ;
        edCheckKieng = (CheckBox) findViewById(R.id.checkBox_suaKieng) ;
        edCheckChay = (CheckBox) findViewById(R.id.checkBox_suaChay) ;
        edCheckMan = (CheckBox) findViewById(R.id.checkBox_suaMan) ;
        edCheckVat = (CheckBox) findViewById(R.id.checkBox_suaVat) ;
        txtSuaCongThuc = (TextView) findViewById(R.id.txt_sua_CongThuc);
        imgSua_hinh = (ImageView) findViewById(R.id.imagehinhmon) ;
        // set loai
        String loai = "";
        if(edCheckKieng.isChecked() == true)
            loai += "KIENG/";
        if(edCheckChay.isChecked() == true)
            loai += "CHAY/";
        if(edCheckMan.isChecked() == true)
            loai += "MAN/";
        if(edCheckVat.isChecked() == true)
            loai += "VAT/";
        // set ve byte
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imgSua_hinh.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        hinh = byteArrayOutputStream.toByteArray();

        dbmonan.UpdateToMon(MaSua,edSuaTen.getText().toString(), loai, idtkonl, txtSuaCongThuc.getText().toString(), hinh );
        //reload();
        reload();
        Toast.makeText(this, "Đã lưu!", Toast.LENGTH_LONG).show();
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

}
