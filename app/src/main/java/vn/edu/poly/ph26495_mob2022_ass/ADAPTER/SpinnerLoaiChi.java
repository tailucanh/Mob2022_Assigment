package vn.edu.poly.ph26495_mob2022_ass.ADAPTER;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiChiDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiThuDTO;
import vn.edu.poly.ph26495_mob2022_ass.R;

public class SpinnerLoaiChi extends BaseAdapter {
    ArrayList<LoaiChiDTO> listLoaiChi;

    public SpinnerLoaiChi(ArrayList<LoaiChiDTO> listLoaiChi) {
        this.listLoaiChi = listLoaiChi;
    }

    @Override
    public int getCount() {
        return listLoaiChi.size();
    }

    @Override
    public Object getItem(int position) {
        LoaiChiDTO loaiChiDTO = listLoaiChi.get(position);
        return loaiChiDTO;
    }

    @Override
    public long getItemId(int position) {
        LoaiChiDTO loaiChiDTO = listLoaiChi.get(position);
        return loaiChiDTO.getId_loaiChi();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemSpinnerLoaiChi;
        if (convertView == null){
            itemSpinnerLoaiChi = View.inflate(parent.getContext(), R.layout.layout_item_spinner_loai_thu_chi,null);

        }else {
            itemSpinnerLoaiChi = convertView;
        }
        final  LoaiChiDTO loaiChiDTO = listLoaiChi.get(position);
        TextView tv_id_loai_chi = itemSpinnerLoaiChi.findViewById(R.id.id_spinner_loai_thu_chi);
        TextView tv_name_loai_chi = itemSpinnerLoaiChi.findViewById(R.id.spinner_ten_loai_thu_chi);
        tv_id_loai_chi.setText(loaiChiDTO.getId_loaiChi() +"");
        tv_name_loai_chi.setText(loaiChiDTO.getTen_LoaiChi());

        return itemSpinnerLoaiChi;
    }
}