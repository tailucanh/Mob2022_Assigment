package vn.edu.poly.ph26495_mob2022_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import vn.edu.poly.ph26495_mob2022_ass.DATABASE.MyDatabase;
import vn.edu.poly.ph26495_mob2022_ass.DTO.KhoanThuDTO;


public class KhoanThuDAO {
    SQLiteDatabase sqLiteDatabase;
    MyDatabase myDatabase;

    public KhoanThuDAO(Context context){
        myDatabase =new MyDatabase(context);
        sqLiteDatabase = myDatabase.getWritableDatabase();
    }
    public void close(){
        myDatabase.close();
    }

    public long insertKhoanThu(KhoanThuDTO khoanThuDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KhoanThuDTO.COL_TEN_KHOAN_THU, khoanThuDTO.getTen_khoanThu());
        contentValues.put(KhoanThuDTO.COL_NGAY_THU,khoanThuDTO.getNgayThu());
        contentValues.put(KhoanThuDTO.COL_GHI_CHU,khoanThuDTO.getGhiChu());
        contentValues.put(KhoanThuDTO.COL_TEN_NGUOI_THU,khoanThuDTO.getTen_nguoiThu());
        contentValues.put(KhoanThuDTO.COL_SO_TIEN,khoanThuDTO.getSoTien());
        contentValues.put(KhoanThuDTO.COL_ID_LOAI_THU,khoanThuDTO.getId_loaiThu());


        long res = sqLiteDatabase.insert(KhoanThuDTO.TB_NAME, null, contentValues);

        return  res;
    }

    public int updateKhoanThu(KhoanThuDTO khoanThuDTO){
        ContentValues contentValues= new ContentValues();
        contentValues.put(KhoanThuDTO.COL_TEN_KHOAN_THU, khoanThuDTO.getTen_khoanThu());
        contentValues.put(KhoanThuDTO.COL_NGAY_THU,khoanThuDTO.getNgayThu());
        contentValues.put(KhoanThuDTO.COL_GHI_CHU,khoanThuDTO.getGhiChu());
        contentValues.put(KhoanThuDTO.COL_TEN_NGUOI_THU,khoanThuDTO.getTen_nguoiThu());
        contentValues.put(KhoanThuDTO.COL_SO_TIEN,khoanThuDTO.getSoTien());
        contentValues.put(KhoanThuDTO.COL_ID_LOAI_THU,khoanThuDTO.getId_loaiThu());

        int res = sqLiteDatabase.update( KhoanThuDTO.TB_NAME, contentValues,"id_khoanThu = ?", new String[] { khoanThuDTO.getId_khoanThu() +"" } );
        return  res;
    }

    public int deleteKhoanThu(KhoanThuDTO khoanThuDTO){
        int res =sqLiteDatabase.delete(KhoanThuDTO.TB_NAME,"id_khoanThu = ?", new String[]{khoanThuDTO.getId_khoanThu() +""});

        return  res;
    }

    public ArrayList<KhoanThuDTO> selectAll(){
        ArrayList<KhoanThuDTO> lists = new ArrayList<>();

        String sql_Select = "SELECT * FROM tb_khoanThu";
        Cursor cursor = sqLiteDatabase.rawQuery(sql_Select,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                KhoanThuDTO khoanThuDTO = new KhoanThuDTO();
                khoanThuDTO.setId_khoanThu(cursor.getInt(0));
                khoanThuDTO.setTen_khoanThu(cursor.getString(1));
                khoanThuDTO.setTen_nguoiThu(cursor.getString(2));
                khoanThuDTO.setNgayThu(cursor.getString(3));
                khoanThuDTO.setSoTien(cursor.getInt(4));
                khoanThuDTO.setGhiChu(cursor.getString(5));
                khoanThuDTO.setId_loaiThu(cursor.getInt(6));

                lists.add(khoanThuDTO);

                cursor.moveToNext(); //Phải cho vào while
            }
        }
        return lists;
    }


    public  KhoanThuDTO selectOne(int id){
        KhoanThuDTO khoanThuDTO = new KhoanThuDTO();
        String[] dk = new String[]{id + ""};

        String str_sql = " SELECT id_khoanThu, ten_khoanThu, ten_nguoiThu ,ngayThu, soTien,ghiChu ,tb_khoanThu.id_loaiThu, tb_loaiThu.ten_Loaithu" +
                " FROM tb_khoanThu INNER JOIN tb_loaiThu " +
                "ON tb_khoanThu.id_loaiThu = tb_loaiThu.id_loaiThu" +
                " WHERE id_khoanThu = ?";

        Cursor cursor = sqLiteDatabase.rawQuery(str_sql,dk);
        if(cursor.moveToFirst()){
            khoanThuDTO.setId_khoanThu(cursor.getInt(0));
            khoanThuDTO.setTen_khoanThu(cursor.getString(1));
            khoanThuDTO.setTen_nguoiThu(cursor.getString(2));
            khoanThuDTO.setNgayThu(cursor.getString(3));
            khoanThuDTO.setSoTien(cursor.getInt(4));
            khoanThuDTO.setGhiChu(cursor.getString(5));
            khoanThuDTO.setId_loaiThu(cursor.getInt(6));
            khoanThuDTO.setTen_loaiThu(cursor.getString(7));
        }

        return  khoanThuDTO;
    }
    public int selectCountKhoanThu(String ngayBatDau , String ngayKetThuc){
        int count = 0;
        String select_sql = "SELECT COUNT (*)  FROM tb_khoanThu WHERE ngayThu >='"+ngayBatDau+"' AND ngayThu  <= '"+ ngayKetThuc+"'";
        Log.d("TAG", "selectCountKhoanThu: "+select_sql);
        Cursor cursorThu = sqLiteDatabase.rawQuery(select_sql,null);
        if (cursorThu.getCount() != 0){
            cursorThu.moveToFirst();
            count = cursorThu.getInt(0);
        }
        return count;
    }


    public long selectSumKhoanThu(String ngayBatDau , String ngayKetThuc){
        long sum = 0;
        String select_sql = "SELECT SUM (soTien) as"+ "TongTien"+" FROM tb_khoanThu WHERE ngayThu >= '"+ngayBatDau+"' AND ngayThu <= '"+ ngayKetThuc+"'";
        Cursor cursorThu = sqLiteDatabase.rawQuery(select_sql,null);
        if (cursorThu.getCount() != 0){
            cursorThu.moveToFirst();
            sum = cursorThu.getInt(0);
        }
        return  sum;
    }


}
