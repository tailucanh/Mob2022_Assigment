package vn.edu.poly.ph26495_mob2022_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.ph26495_mob2022_ass.DATABASE.MyDatabase;
import vn.edu.poly.ph26495_mob2022_ass.DTO.KhoanChiDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.KhoanThuDTO;

public class KhoanChiDAO {
    SQLiteDatabase sqLiteDatabase;
    MyDatabase myDatabase;

    public KhoanChiDAO(Context context){
        myDatabase =new MyDatabase(context);
        sqLiteDatabase = myDatabase.getWritableDatabase();
    }
    public void close(){
        myDatabase.close();
    }

    public long insertKhoanChi(KhoanChiDTO khoanChiDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KhoanChiDTO.COL_TEN_KHOAN_CHI, khoanChiDTO.getTen_khoanChi());
        contentValues.put(KhoanChiDTO.COL_NGAY_CHI,khoanChiDTO.getNgayChi());
        contentValues.put(KhoanChiDTO.COL_GHI_CHU,khoanChiDTO.getGhiChu());
        contentValues.put(KhoanChiDTO.COL_TEN_NGUOI_CHI,khoanChiDTO.getTen_nguoiChi());
        contentValues.put(KhoanChiDTO.COL_SO_TIEN,khoanChiDTO.getSoTien());
        contentValues.put(KhoanChiDTO.COL_ID_LOAI_CHI,khoanChiDTO.getId_loaiChi());


        long res = sqLiteDatabase.insert(KhoanChiDTO.TB_NAME, null, contentValues);

        return  res;
    }

    public int updateKhoanChi(KhoanChiDTO khoanChiDTO){
        ContentValues contentValues= new ContentValues();
        contentValues.put(KhoanChiDTO.COL_TEN_KHOAN_CHI, khoanChiDTO.getTen_khoanChi());
        contentValues.put(KhoanChiDTO.COL_NGAY_CHI,khoanChiDTO.getNgayChi());
        contentValues.put(KhoanChiDTO.COL_GHI_CHU,khoanChiDTO.getGhiChu());
        contentValues.put(KhoanChiDTO.COL_TEN_NGUOI_CHI,khoanChiDTO.getTen_nguoiChi());
        contentValues.put(KhoanChiDTO.COL_SO_TIEN,khoanChiDTO.getSoTien());
        contentValues.put(KhoanChiDTO.COL_ID_LOAI_CHI,khoanChiDTO.getId_loaiChi());
        int res = sqLiteDatabase.update( KhoanChiDTO.TB_NAME, contentValues,"id_khoanChi = ?", new String[] { khoanChiDTO.getId_khoanChi() +"" } );
        return  res;
    }

    public int deleteKhoanChi(KhoanChiDTO khoanChiDTO){
        int res =sqLiteDatabase.delete(khoanChiDTO.TB_NAME,"id_khoanChi = ?", new String[]{khoanChiDTO.getId_khoanChi() +""});

        return  res;
    }

    public ArrayList<KhoanChiDTO> selectAll(){
        ArrayList<KhoanChiDTO> lists = new ArrayList<>();

        String sql_Select = "SELECT * FROM tb_khoanChi";
        Cursor cursor = sqLiteDatabase.rawQuery(sql_Select,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                KhoanChiDTO khoanChiDTO = new KhoanChiDTO();
                khoanChiDTO.setId_khoanChi(cursor.getInt(0));
                khoanChiDTO.setTen_khoanChi(cursor.getString(1));
                khoanChiDTO.setTen_nguoiChi(cursor.getString(2));
                khoanChiDTO.setNgayChi(cursor.getString(3));
                khoanChiDTO.setSoTien(cursor.getInt(4));
                khoanChiDTO.setGhiChu(cursor.getString(5));
                khoanChiDTO.setId_loaiChi(cursor.getInt(6));

                lists.add(khoanChiDTO);

                cursor.moveToNext(); ///Phải cho vào while
            }
        }
        return lists;
    }

    public KhoanChiDTO selectOne(int id){
        KhoanChiDTO khoanChiDTO= new KhoanChiDTO();
        String[] dk = new String[]{id + ""};

        String str_sql = " SELECT id_khoanChi, ten_khoanChi, ten_nguoiChi ,ngayChi, soTien ,ghiChu,tb_khoanChi.id_loaiChi, tb_loaiChi.ten_loaiChi" +
                " FROM tb_khoanChi INNER JOIN tb_loaiChi " +
                "ON tb_khoanChi.id_loaiChi = tb_loaiChi.id_loaiChi" +
                " WHERE id_khoanChi = ?";

        Cursor cursor = sqLiteDatabase.rawQuery(str_sql,dk);
        if(cursor.moveToFirst()){
            khoanChiDTO.setId_khoanChi(cursor.getInt(0));
            khoanChiDTO.setTen_khoanChi(cursor.getString(1));
            khoanChiDTO.setTen_nguoiChi(cursor.getString(2));
            khoanChiDTO.setNgayChi(cursor.getString(3));
            khoanChiDTO.setSoTien(cursor.getInt(4));
            khoanChiDTO.setGhiChu(cursor.getString(5));
            khoanChiDTO.setId_loaiChi(cursor.getInt(6));
            khoanChiDTO.setTen_loaiChi(cursor.getString(7));
        }

        return  khoanChiDTO;
    }

    public int selectCountKhoanChi(String ngayBatDau , String ngayKetThuc){
        int count = 0;
        String select_sql = "SELECT COUNT (*) FROM tb_khoanChi WHERE ngayChi >= '"+ngayBatDau+"' AND ngayChi <= '"+ ngayKetThuc+"'";
        Cursor cursorChi = sqLiteDatabase.rawQuery(select_sql,null);
        if (cursorChi.getCount() != 0){
            cursorChi.moveToFirst();
            count = cursorChi.getInt(0);
        }
        return  count;
    }
    public long selectSumKhoanChi(String ngayBatDau , String ngayKetThuc){
        long sum = 0;
        String select_sql = "SELECT SUM (soTien) as"+ "TongTien"+" FROM tb_khoanChi WHERE ngayChi >= '"+ngayBatDau+"' AND ngayChi <= '"+ ngayKetThuc+"'";
        Cursor cursorChi = sqLiteDatabase.rawQuery(select_sql,null);
        if (cursorChi.getCount() != 0){
            cursorChi.moveToFirst();
            sum = cursorChi.getInt(0);
        }
        return  sum;
    }



}
