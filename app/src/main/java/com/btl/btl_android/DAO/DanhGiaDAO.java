package com.btl.btl_android.DAO;

import android.content.Context;
import android.database.Cursor;

import com.btl.btl_android.SQLite;
public class DanhGiaDAO {
    private Context context;
    private SQLite dbNauAn;

    public DanhGiaDAO(Context context, SQLite dbNauAn) {
        this.context = context;
        this.dbNauAn = dbNauAn;
    }
    public DanhGiaDAO(Context context){
        this.context = context;
        dbNauAn = new SQLite(context);
    }
    public DanhGiaDAO() {
    }

    public int Insert (String idmon, String idnguoi, String danhgia){
        try {
            String sql = "INSERT INTO DanhGia VALUES ('"+idmon+"','"+idnguoi+"','"+danhgia  +"')";
            dbNauAn.QuerryData(sql);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    public Cursor GETSL(String idmon, String idnguoi){
        Cursor cursor = null;
        try{
            String sql = "SELECT * FROM DanhGia WHERE MaMon = '"+ idmon +"' AND IDNguoiDanhGia = '"+ idnguoi +"'";
            cursor = dbNauAn.GetData(sql);
            return  cursor;
        }catch (Exception e){
            return cursor;
        }
    }
    public Cursor GETSLfull(String idmon){
        Cursor cursor = null;
        try{
            String sql = "SELECT * FROM DanhGia WHERE MaMon = '"+ idmon +"'";
            cursor = dbNauAn.GetData(sql);
            return  cursor;
        }catch (Exception e){
            return cursor;
        }
    }

    public int Delete(String idmon , String idnguoi){
        try{
            String sql = "DELETE FROM DanhGia WHERE MaMon = '"+idmon+"' AND IDNguoiDanhGia = '"+idnguoi+"'";
            dbNauAn.QuerryData(sql);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
}
