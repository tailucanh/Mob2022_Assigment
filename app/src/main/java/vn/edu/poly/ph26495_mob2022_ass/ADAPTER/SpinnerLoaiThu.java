package vn.edu.poly.ph26495_mob2022_ass.ADAPTER;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiThuDTO;
import vn.edu.poly.ph26495_mob2022_ass.R;

public class SpinnerLoaiThu extends BaseAdapter {
    ArrayList<LoaiThuDTO> listLoaiThu;

    public SpinnerLoaiThu(ArrayList<LoaiThuDTO> listLoaiThu) {
        this.listLoaiThu = listLoaiThu;
    }

    @Override
    public int getCount() {
        return listLoaiThu.size();
    }

    @Override
    public Object getItem(int position) {
        LoaiThuDTO loaiThuDTO = listLoaiThu.get(position);
        return loaiThuDTO;
    }

    @Override
    public long getItemId(int position) {
        LoaiThuDTO loaiThuDTO = listLoaiThu.get(position);
        return loaiThuDTO.getId_loaiThu();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemSpinnerLoaithu;
        if (convertView == null){
            itemSpinnerLoaithu = View.inflate(parent.getContext(), R.layout.layout_item_spinner_loai_thu_chi,null);

        }else {
            itemSpinnerLoaithu = convertView;
        }
        final  LoaiThuDTO loaiThuDTO = listLoaiThu.get(position);
        TextView tv_id_loai_thu = itemSpinnerLoaithu.findViewById(R.id.id_spinner_loai_thu_chi);
        TextView tv_name_loai_thu = itemSpinnerLoaithu.findViewById(R.id.spinner_ten_loai_thu_chi);
        tv_id_loai_thu.setText(loaiThuDTO.getId_loaiThu()+" . ");
        tv_name_loai_thu.setText(loaiThuDTO.getTen_Loaithu());
        return itemSpinnerLoaithu;
    }
}
