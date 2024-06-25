package com.btl.btl_android.Activity;

import static com.btl.btl_android.Activity.DangNhap.idtkonl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.btl.btl_android.DAO.MonAnDAO;
import com.btl.btl_android.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

public class DangBaiViet extends AppCompatActivity {
    private int REQUEST_CODE_FOLDER = 456;
    private Context context;
    private MonAnDAO dbMonAn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_bai_viet);
        context = this;
        dbMonAn = new MonAnDAO(this);


    }

    public void MoFolderHinh(View view) {
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
                ImageButton imghinh = (ImageButton) findViewById(R.id.addhinh);
                imghinh.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requesCode, resultCode, data);
    }

    public void DangCapNhat(View view) {
        Toast.makeText(this, "Chức năng đang được cập nhật!",Toast.LENGTH_LONG).show();
    }

    public void btnDangbai(View view) {
        CheckBox chekKien = (CheckBox) findViewById(R.id.checkBoxKien)    ;
        CheckBox chekChay = (CheckBox) findViewById(R.id.checkBoxChay)    ;
        CheckBox chekMan = (CheckBox) findViewById(R.id.checkBoxMan)    ;
        CheckBox chekVat = (CheckBox) findViewById(R.id.checkBoxAnVat)    ;
        TextView txtTen = (TextView) findViewById(R.id.editTextTextPersonName2);
        TextView txtCongThuc = (TextView) findViewById(R.id.editTextTextMultiLine3);

        // set ve byte
        ImageView imgavatarne = (ImageView) findViewById(R.id.addhinh);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imgavatarne.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] hinhanh = byteArrayOutputStream.toByteArray();
        String loai = "";
        if(chekChay.isChecked())
            loai += "CHAY/";
        if(chekMan.isChecked())
            loai += "MAN/";
        if(chekKien.isChecked())
            loai += "KIENG/";
        if (chekVat.isChecked())
            loai += "VAT";
        Date date = new Date();
        String mm =  idtkonl + "" + date;
        dbMonAn.UpdateToMon(mm,  txtTen.getText().toString(),loai.toString(), idtkonl, txtCongThuc.getText().toString(), hinhanh);
        Toast.makeText(this, "Đã đăng", Toast.LENGTH_LONG).show();
    }
}