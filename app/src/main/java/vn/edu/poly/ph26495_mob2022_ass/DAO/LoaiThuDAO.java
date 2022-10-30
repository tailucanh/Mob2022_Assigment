package vn.edu.poly.ph26495_mob2022_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import vn.edu.poly.ph26495_mob2022_ass.DATABASE.MyDatabase;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiThuDTO;

public class LoaiThuDAO {
    SQLiteDatabase sqLiteDatabase;
    MyDatabase myDatabase;

    public LoaiThuDAO(Context context){
        myDatabase =new MyDatabase(context);
        sqLiteDatabase = myDatabase.getWritableDatabase();
    }
    public void close(){
        myDatabase.close();
    }


    public long insertLoaiThu(LoaiThuDTO loaiThuDTO){

        ContentValues contentValues = new ContentValues();
        contentValues.put(LoaiThuDTO.COL_TEN_LOAI_THU, loaiThuDTO.getTen_Loaithu());
        long res = sqLiteDatabase.insert(LoaiThuDTO.TB_NAME, null, contentValues);
        return  res;
    }

    public int deleteLoaiThu(LoaiThuDTO loaiThuDTO){
        int res = sqLiteDatabase.delete(LoaiThuDTO.TB_NAME,"id_loaiThu = ?", new String[]{loaiThuDTO.getId_loaiThu() +""});
        return  res;
    }

    //Hàm update
    public int updateLoaiThu(LoaiThuDTO loaiThuDTO){

        ContentValues values= new ContentValues();
        values.put( LoaiThuDTO.COL_TEN_LOAI_THU,loaiThuDTO.getTen_Loaithu() );

        int res = sqLiteDatabase.update( LoaiThuDTO.TB_NAME, values,"id_loaiThu = ?", new String[] { loaiThuDTO.getId_loaiThu() +"" } );
        return  res;
    }


    public ArrayList<LoaiThuDTO> selectAll(){
        ArrayList<LoaiThuDTO> lists = new ArrayList<>();

        String sql_Select = "SELECT * FROM tb_loaiThu";


        Cursor cursor = sqLiteDatabase.rawQuery(sql_Select,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                LoaiThuDTO loaiThuDTO = new LoaiThuDTO();
                loaiThuDTO.setId_loaiThu(cursor.getInt(0));
                loaiThuDTO.setTen_Loaithu(cursor.getString(1));
                lists.add(loaiThuDTO);

                cursor.moveToNext(); ///Phải cho vào while
            }
        }

        return lists;
    }

}
