package vn.edu.poly.ph26495_mob2022_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import vn.edu.poly.ph26495_mob2022_ass.DATABASE.MyDatabase;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiChiDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiThuDTO;

public class LoaiChiDAO {
    SQLiteDatabase sqLiteDatabase;
    MyDatabase myDatabase;

    public LoaiChiDAO(Context context){
        myDatabase =new MyDatabase(context);
        sqLiteDatabase = myDatabase.getWritableDatabase();
    }
    public void close(){
        myDatabase.close();
    }


    public long insertLoaiChi(LoaiChiDTO loaiChiDTO){

        ContentValues contentValues = new ContentValues();
        contentValues.put(LoaiChiDTO.COL_TEN_LOAI_CHI, loaiChiDTO.getTen_LoaiChi());
        long res = sqLiteDatabase.insert(LoaiChiDTO.TB_NAME, null, contentValues);
        return  res;
    }

    public int deleteLoaiChi(LoaiChiDTO loaiChiDTO){
        int res = sqLiteDatabase.delete(LoaiChiDTO.TB_NAME,"id_loaiChi = ?", new String[]{loaiChiDTO.getId_loaiChi() +""});
        return  res;
    }

    //Hàm update
    public int updateLoaiChi(LoaiChiDTO loaiChiDTO){

        ContentValues values= new ContentValues();
        values.put( LoaiChiDTO.COL_TEN_LOAI_CHI,loaiChiDTO.getTen_LoaiChi() );

        int res = sqLiteDatabase.update( LoaiChiDTO.TB_NAME, values,"id_loaiChi = ?", new String[] { loaiChiDTO.getId_loaiChi() +"" } );
        return  res;
    }


    public ArrayList<LoaiChiDTO> selectAll(){
        ArrayList<LoaiChiDTO> lists = new ArrayList<>();

        String sql_Select = "SELECT * FROM tb_loaiChi";


        Cursor cursor = sqLiteDatabase.rawQuery(sql_Select,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                LoaiChiDTO loaiChiDTO = new LoaiChiDTO();
                loaiChiDTO.setId_loaiChi(cursor.getInt(0));
                loaiChiDTO.setTen_LoaiChi(cursor.getString(1));
                lists.add(loaiChiDTO);

                cursor.moveToNext(); ///Phải cho vào while
            }
        }

        return lists;
    }

}
