package com.btl.btl_android.XuLyFragment;

import static com.btl.btl_android.Activity.DangNhap.idtkonl;
import static com.btl.btl_android.Activity.TrangChu.TimMonAn;
import static com.btl.btl_android.Activity.TrangChu.aty;
import static com.btl.btl_android.Activity.TrangChu.dbMonAn;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.btl.btl_android.Activity.ChiTietMonAn;
import com.btl.btl_android.Model.MonAn;
import com.btl.btl_android.MonAnAdapter;
import com.btl.btl_android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private ArrayList<MonAn> mHeros ;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private MonAnAdapter anAdapter;
    private MonAn[] mon;
    private Activity acti = aty;
    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inf = inflater.inflate(R.layout.fragment_home, container, false);

        ListView listView = (ListView) inf.findViewById(R.id.listmonan1);
        getmonantenloai();
        anAdapter = new MonAnAdapter(acti, mon);
        listView.setAdapter(anAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(inf.getContext(), ChiTietMonAn.class);
                MonAn mamon = mon[position];
                i.putExtra("mamonan", mamon.getMamon());
                i.putExtra("idne", idtkonl);
                //i.putExtra("", t.getMK());
                inf.getContext().startActivity(i);
            }
        });
        return inf;
    }


    private void getmonantenloai() {
        Cursor cs = dbMonAn.GETALL();
        cs.moveToFirst();
        int a = cs.getCount();
        mon = new MonAn[a];
        int i = 0;
        while (i < a){
            MonAn tem = new MonAn(cs.getString(0), cs.getString(1),cs.getString(2), cs.getString(3), cs.getString(4), cs.getBlob(5));
            mon[i] = tem;
            cs.moveToNext();
            i++;
        }
        MonAn[] ketqua = new MonAn[0];
        int sl = 0;
        if(TimMonAn != "")
        {
            for(int j = 0 ; j < a; j ++){
                if(mon[j].getLoai().contains(TimMonAn) == true)
                    sl ++;
            }
            ketqua = new MonAn[sl];
            int vtri = 0;
            for(int j = 0 ; j < a; j ++){
                if(mon[j].getLoai().contains(TimMonAn) == true) {
                    ketqua[vtri] = mon[j];
                    vtri ++;
                }
            }
        }
        if(ketqua.length != 0){
            mon = ketqua;
        }
    }
}