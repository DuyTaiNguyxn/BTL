package com.btl.btl_android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.NonNull;

public class SQLite extends SQLiteOpenHelper {
    public SQLite(Context context){
        super(context,"food.db",null,1);
    }
    public SQLite(){
        super(null,"food.db",null,1);
    }
    public void QuerryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    public void UpdateToTaiKhoan(String id,String pass, String ten, String ngaysinh, String diachi, String Gmail, byte[] hinh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM TaiKhoan WHERE TenTK = '"+id+"'";
        database.execSQL(sql);

        sql = "INSERT INTO TaiKhoan VALUES(?,?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, id);
        statement.bindString(2, pass);
        statement.bindString(3, ten);
        statement.bindString(4, ngaysinh);
        statement.bindString(5, diachi);
        statement.bindString(6, Gmail);
        statement.bindBlob(7, hinh);

        statement.executeInsert();

    }


    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        String sql = "CREATE TABLE TaiKhoan (TenTK Text PRIMARY KEY, MatKhau Text, HoTen Text, NgaySinh Text, DiaChi Text, Gmail Text, Avatar BLOB )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS TaiKhoan");
    }

}
