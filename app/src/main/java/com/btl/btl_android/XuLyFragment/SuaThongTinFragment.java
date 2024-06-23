package com.btl.btl_android.XuLyFragment;

import static com.btl.btl_android.Activity.DangNhap.idtkonl;
import static com.btl.btl_android.Activity.TrangChu.dbTaiKhoan;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.btl.btl_android.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SuaThongTinFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuaThongTinFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Context context;
    //private TaiKhoanDAO dbTaiKhoan;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String idtaikhoanne;
    public SuaThongTinFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuaThongTinFragment newInstance(String param1, String param2) {
        SuaThongTinFragment fragment = new SuaThongTinFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_suathongtin, container, false);
        Cursor cs =  dbTaiKhoan.GetTaiKhoan(idtkonl);
        View inf = inflater.inflate(R.layout.fragment_sua_thong_tin, container, false);
        EditText hoten = (EditText) inf.findViewById(R.id.txtHoTenEdit);
        EditText ngaysinh = (EditText) inf.findViewById(R.id.txtNgaySinhedit);
        EditText diachi = (EditText) inf.findViewById(R.id.txtDiaChiEdit);
        EditText gmail = (EditText) inf.findViewById(R.id.txtGmailEdit);
        while (cs.moveToNext()) {
            hoten.setText(cs.getString(2));
            ngaysinh.setText(cs.getString(3));
            diachi.setText(cs.getString(4));
            gmail.setText(cs.getString(5));
            if(cs.getBlob(6) != null){
                byte[] hinh = cs.getBlob(6);

                Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
                ImageView avtr = (ImageView) inf.findViewById(R.id.imgAvatar);
                avtr.setImageBitmap(bitmap);
            }
        }
        return inf;
    }


}