package com.btl.btl_android.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.btl.btl_android.DAO.TaiKhoanDAO;
import com.btl.btl_android.Model.TaiKhoan;
import com.btl.btl_android.R;

public class DangKy extends AppCompatActivity {
    Button btnDK;
    EditText etHoten;
    EditText etTk;
    EditText etNsinh;
    EditText etMK;
    EditText etDiachi;
    EditText etMail;
    TaiKhoanDAO taiKhoanDAO;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        context = this;
        taiKhoanDAO =new TaiKhoanDAO(this);
        initView();
    }

    private void initView() {
        btnDK = (Button) findViewById(R.id.btnDK);
        etHoten = (EditText) findViewById(R.id.etHoTen);
        etTk =(EditText) findViewById(R.id.etTk);
        etNsinh = (EditText) findViewById(R.id.etNsinh);
        etMK = (EditText) findViewById(R.id.etMK);
        etDiachi = (EditText) findViewById(R.id.etDiachi);
        etMail = (EditText) findViewById(R.id.etMail);
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TaiKhoan t = new TaiKhoan();
                t.setTenTk(etTk.getText().toString());
                t.setHoTen(etHoten.getText().toString());
                t.setDiaChi(etDiachi.getText().toString());
                t.setNgaySinh(etNsinh.getText().toString());
                t.setMK(etMK.getText().toString());
                t.setMail(etMail.getText().toString());
                int kq = taiKhoanDAO.InsertTaiKhoan(t);
                if (kq == 1)
                {
                    Toast.makeText(DangKy.this,"Đăng ký thành công!",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DangKy.this, DangNhap.class);
                    i.putExtra("TenDn", t.getTenTk());
                    i.putExtra("pass", t.getMK());
                    context.startActivity(i);
                }
                else {
                    Toast.makeText(DangKy.this,"Lỗi rồi! Bạn kiểm tra lại thông tin đã nhập nhé!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}