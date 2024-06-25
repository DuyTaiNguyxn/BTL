package com.btl.btl_android.ui.XuLyMenuItem.Fragment;

import static com.btl.btl_android.Activity.DangNhap.idtkonl;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.btl.btl_android.DAO.TaiKhoanDAO;
import com.btl.btl_android.R;
import com.btl.btl_android.databinding.FragmentGalleryBinding;


public class GalleryFragment extends Fragment {
    private Context context ;
    private TaiKhoanDAO dbTaiKhoan;
    private FragmentGalleryBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context  = this.getParentFragment().getContext();
        dbTaiKhoan = new TaiKhoanDAO(context);
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Cursor cs =  dbTaiKhoan.GetTaiKhoan(idtkonl);
        View inf = inflater.inflate(R.layout.fragment_gallery, container, false);
        TextView tv = (TextView) inf.findViewById(R.id.luutruid);
        TextView hoten = (TextView) inf.findViewById(R.id.txtHoten);
        TextView tentk = (TextView) inf.findViewById(R.id.txtTentk);
        TextView ngays = (TextView) inf.findViewById(R.id.txtNS);
        TextView diac = (TextView) inf.findViewById(R.id.txtDC);
        TextView mail = (TextView) inf.findViewById(R.id.txtMail2);
         while (cs.moveToNext()) {
            tv.setText(cs.getString(2));
            tentk.setText(cs.getString(0));
            hoten.setText(cs.getString(2));
            ngays.setText(cs.getString(3));
            diac.setText(cs.getString(4));
            mail.setText(cs.getString(5));
            if(cs.getBlob(6) != null){
                byte[] hinh = cs.getBlob(6);

                Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
                ImageView avtr = (ImageView) inf.findViewById(R.id.imageView6);
                avtr.setImageBitmap(bitmap);
            }
        }

        return inf;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}