package com.btl.btl_android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.btl.btl_android.Model.MonAn;


public class MonAnAdapter extends BaseAdapter {

    private MonAn[] item;
    private Activity activity;

    public MonAnAdapter(Activity activity, MonAn[] lmonan) {
        this.activity =  activity;
        this.item = lmonan;
    }

    @Override
    public int getCount() {
        return item.length;
    }

    @Override
    public Object getItem(int i) {
        return item[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Gọi layoutInflater ra để bắt đầu ánh xạ view và data.
        LayoutInflater inflater = activity.getLayoutInflater();

        // Đổ dữ liệu vào biến View, view này chính là những gì nằm trong item_name.xml
        view = inflater.inflate(R.layout.item_mon_an, null);

        // Đặt chữ cho từng view trong danh sách.


        TextView tvName = (TextView) view.findViewById(R.id.text_name);
        TextView tvND = (TextView) view.findViewById(R.id.idnguoidang);
        //TextView tvDG = (TextView) view.findViewById(R.id.danhgia);
        ImageView anh = (ImageView) view.findViewById(R.id.image_hero);
        if(item[i].getHinhAnh() != null){
            byte[] hinh = item[i].getHinhAnh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
            anh.setImageBitmap(bitmap);
        }

        tvName.setText(item[i].getTenMon());
        tvND.setText( item[i].getNguoiDang());

        return view;
    }
}
