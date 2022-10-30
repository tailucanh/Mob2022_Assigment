package vn.edu.poly.ph26495_mob2022_ass.DATABASE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

    public static String DB_NAME = "QuanLiThuChi";
    public static int DB_VERSION = 1;

    public  MyDatabase(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String db_loaiThu = "CREATE TABLE  tb_loaiThu (id_loaiThu INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,ten_Loaithu TEXT NOT NULL );";
        db.execSQL(db_loaiThu);


        String db_khoanThu = "CREATE TABLE  tb_khoanThu (id_khoanThu INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,ten_khoanThu TEXT NOT NULL,ten_nguoiThu TEXT NOT NULL , ngayThu TEXT NOT NULL,soTien INTEGER NOT NULL,ghiChu TEXT  ,id_loaiThu INTEGER NOT NULL );";
        db.execSQL(db_khoanThu);

        String db_loaiChi = "CREATE TABLE  tb_loaiChi (id_loaiChi INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,ten_loaiChi TEXT NOT NULL );";
        db.execSQL(db_loaiChi);


        String db_khoanChi = "CREATE TABLE  tb_khoanChi (id_khoanChi INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,ten_khoanChi TEXT NOT NULL,ten_nguoiChi TEXT NOT NULL , ngayChi TEXT NOT NULL,soTien INTEGER NOT NULL,ghiChu TEXT  ,id_loaiChi INTEGER NOT NULL );";
        db.execSQL(db_khoanChi);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
