package vn.edu.poly.ph26495_mob2022_ass.ADAPTER;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.google.android.material.textfield.TextInputLayout;

import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Calendar;

import vn.edu.poly.ph26495_mob2022_ass.DAO.KhoanChiDAO;
import vn.edu.poly.ph26495_mob2022_ass.DAO.KhoanThuDAO;
import vn.edu.poly.ph26495_mob2022_ass.DAO.LoaiThuDAO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.KhoanChiDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.KhoanThuDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiChiDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiThuDTO;
import vn.edu.poly.ph26495_mob2022_ass.R;

public class KhoanThuAdapter extends RecyclerView.Adapter<KhoanThuAdapter.MyViewHolderKhoanThu> {
    ArrayList<KhoanThuDTO> listThu;
    KhoanThuDAO khoanThuDAO;
    ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private int selectedYear;
    private int selectedMonth;
    private int selectedDayOfMonth;

    public KhoanThuAdapter(ArrayList<KhoanThuDTO> listThu, KhoanThuDAO khoanThuDAO) {
        this.listThu = listThu;
        this.khoanThuDAO = khoanThuDAO;
    }
    public void setFilter(ArrayList<KhoanThuDTO> filterList){
        this.listThu = filterList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public KhoanThuAdapter.MyViewHolderKhoanThu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_one_item_khoan_thu_chi,null);
        return new KhoanThuAdapter.MyViewHolderKhoanThu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanThuAdapter.MyViewHolderKhoanThu holder, int position) {
        final  int index = position;
        KhoanThuDTO khoanThuDTO = listThu.get(position);
        KhoanThuDTO show_loai_thu = khoanThuDAO.selectOne(khoanThuDTO.getId_khoanThu());

        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(khoanThuDTO.getId_khoanThu()));
        holder.ten_khoan_thu.setText(khoanThuDTO.getTen_khoanThu());
        holder.ngay_thu.setText(khoanThuDTO.getNgayThu());
        holder.loai_thu.setText(show_loai_thu.getTen_loaiThu());

        holder.xem_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeMoreInfo(v.getContext(), khoanThuDTO);
            }
        });
        holder.delete_khoan_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteKhoanThu(v.getContext(), khoanThuDTO,index);
            }
        });
        holder.update_khoan_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateKhoanThu(v.getContext(), khoanThuDTO,index);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listThu.size();
    }

    public class MyViewHolderKhoanThu extends RecyclerView.ViewHolder{
        TextView ten_khoan_thu,ngay_thu,loai_thu,xem_them;
        ImageView delete_khoan_thu, update_khoan_thu;
        SwipeRevealLayout swipeRevealLayout;
        public MyViewHolderKhoanThu(@NonNull View view) {
            super(view);

            swipeRevealLayout = view.findViewById(R.id.swiper_layout);

            ten_khoan_thu = view.findViewById(R.id.ten_khoan_thu_chi);
            ngay_thu = view.findViewById(R.id.ngay_thu_chi);
            loai_thu = view.findViewById(R.id.loai_thu_chi);
            xem_them = view.findViewById(R.id.mo_rong_thong_tin);

            delete_khoan_thu = view.findViewById(R.id.img_delete_khoan_thu_chi);
            update_khoan_thu = view.findViewById(R.id.img_update_khoan_thu_chi);
        }
    }


    public  void addKhoanThu(Context context){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_khoan_thu);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //Code xử lí
        ImageView dateThu = dialog.findViewById(R.id.datetime_thu);

        TextInputLayout ed_ten_khoan_thu = dialog.findViewById(R.id.ed_ten_khoan_thu);
        TextInputLayout ed_ten_nguoi_thu = dialog.findViewById(R.id.ed_ten_nguoi_thu);
        TextInputLayout ed_dateThu = dialog.findViewById(R.id.ed_ngay_thu);
        TextInputLayout ed_so_tien = dialog.findViewById(R.id.ed_so_tien_thu);
        TextInputLayout ed_ghi_chu_thu = dialog.findViewById(R.id.ed_ghi_chu_thu);
        dateThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate(ed_dateThu.getEditText(), context);
            }
        });

        final  Spinner spinner = dialog.findViewById(R.id.spinner_loai_thu);
        LoaiThuDAO loaiThuDAO = new LoaiThuDAO(context);
        SpinnerLoaiThu spinnerLoaiThu = new SpinnerLoaiThu(loaiThuDAO.selectAll());
        spinner.setAdapter(spinnerLoaiThu);

        Button btn_save = dialog.findViewById(R.id.btn_save_khoanThu);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_ten_khoan_thu.getEditText().getText().toString().equals("")){
                    ed_ten_khoan_thu.setError("Hãy nhập tên khoản thu !");
                }else if(ed_ten_nguoi_thu.getEditText().getText().toString().equals("")) {
                    ed_ten_khoan_thu.setError("");
                    ed_ten_nguoi_thu.setError("Hãy nhập tên người thu !");
                }else if(ed_dateThu.getEditText().getText().toString().equals("")){
                    ed_ten_nguoi_thu.setError("");
                    ed_dateThu.setError("Hãy nhập ngày thu !");
                }else if(ed_so_tien.getEditText().getText().toString().equals("")){
                    ed_dateThu.setError("");
                    ed_so_tien.setError("Hãy nhập số tiền thu !");
                }else{
                    ed_so_tien.setError("");
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setIcon(R.drawable.ic_save);
                    builder.setTitle("Confirm !!!");
                    builder.setMessage("Xác nhận lưu thông tin !");
                    builder.setCancelable(false);

                    KhoanThuDTO khoanThuDTO = new KhoanThuDTO();
                    LoaiThuDTO loaiThuDTO = (LoaiThuDTO) spinner.getSelectedItem();
                    khoanThuDTO.setId_loaiThu(loaiThuDTO.getId_loaiThu());
                    khoanThuDTO.setTen_khoanThu( ed_ten_khoan_thu.getEditText().getText().toString());
                    khoanThuDTO.setTen_nguoiThu( ed_ten_nguoi_thu.getEditText().getText().toString());
                    khoanThuDTO.setNgayThu(ed_dateThu.getEditText().getText().toString());
                    khoanThuDTO.setSoTien(Integer.parseInt(ed_so_tien.getEditText().getText().toString()));
                    khoanThuDTO.setGhiChu(ed_ghi_chu_thu.getEditText().getText().toString());

                    long res = khoanThuDAO.insertKhoanThu(khoanThuDTO);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog1, int which) {
                            if(res > 0){
                                listThu.clear();
                                listThu.addAll(khoanThuDAO.selectAll());
                                notifyDataSetChanged();
                                Toast.makeText(context, "Đã thêm mới! ", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                            }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog1, int which) {
                            Toast.makeText(context,"Đã hủy !",Toast.LENGTH_SHORT).show();
                            dialog1.cancel();
                            dialog.dismiss();
                        }
                    });

                    AlertDialog sh = builder.create();
                    sh.show();
                }
            }

        });

        dialog.show();
    }


    public void updateKhoanThu(Context context, KhoanThuDTO khoanThuDTO, int index){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_change_khoan_thu);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_change_3);
        builder.setTitle("Confirm !!!");
        builder.setMessage("Xác nhận sửa đổi thông tin!");
        builder.setCancelable(false);

        ImageView date_update = dialog.findViewById(R.id.datetime_thu_update);
        EditText ten_khoan_thu_update = dialog.findViewById(R.id.ed_ten_khoan_thu_update);
        EditText ed_ten_nguoi_thu_update = dialog.findViewById(R.id.ed_ten_nguoi_thu_update);
        EditText ed_dateThu_update = dialog.findViewById(R.id.ed_ngay_thu_update);
        EditText ed_so_tien_update = dialog.findViewById(R.id.ed_so_tien_thu_update);
        EditText ed_ghi_chu_thu_update = dialog.findViewById(R.id.ed_ghi_chu_thu_update);
        date_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate(ed_dateThu_update, context);
            }
        });
        ten_khoan_thu_update.setText(khoanThuDTO.getTen_khoanThu());
        ed_ten_nguoi_thu_update.setText(khoanThuDTO.getTen_nguoiThu());
        ed_dateThu_update.setText(khoanThuDTO.getNgayThu());
        ed_so_tien_update.setText(khoanThuDTO.getSoTien() +"");
        ed_ghi_chu_thu_update.setText(khoanThuDTO.getGhiChu());

        final  Spinner spinner = dialog.findViewById(R.id.spinner_loai_thu_update);
        LoaiThuDAO loaiThuDAO = new LoaiThuDAO(context);
        ArrayList<LoaiThuDTO> listLoaiThu = loaiThuDAO.selectAll();
        SpinnerLoaiThu adapter = new SpinnerLoaiThu(listLoaiThu);
        spinner.setAdapter(adapter);

        for(int i = 0; i < listLoaiThu.size(); i++){
            LoaiThuDTO temp = listLoaiThu.get(i);
            if(temp.getId_loaiThu() == khoanThuDTO.getId_loaiThu()){
                spinner.setSelection(i);
                spinner.setSelected(true);
            }
        }


        Button btn_update = dialog.findViewById(R.id.btn_change_khoanThu);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                khoanThuDTO.setTen_khoanThu( ten_khoan_thu_update.getText().toString());
                khoanThuDTO.setTen_nguoiThu( ed_ten_nguoi_thu_update.getText().toString());
                khoanThuDTO.setNgayThu(ed_dateThu_update.getText().toString());
                khoanThuDTO.setSoTien(Integer.parseInt(ed_so_tien_update.getText().toString()));
                khoanThuDTO.setGhiChu(ed_ghi_chu_thu_update.getText().toString());

                LoaiThuDTO loaiThuDTO = (LoaiThuDTO) spinner.getSelectedItem();
                khoanThuDTO.setId_loaiThu(loaiThuDTO.getId_loaiThu());

                int res = khoanThuDAO.updateKhoanThu(khoanThuDTO);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        if(res > 0){
                            listThu.set(index,khoanThuDTO);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Đã sửa thông tin !", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(context, "Không sửa được thông tin !" + res, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        Toast.makeText(context,"Đã hủy !",Toast.LENGTH_SHORT).show();
                        dialog1.cancel();
                        dialog.dismiss();
                    }
                });
                AlertDialog sh = builder.create();
                sh.show();
            }
        });
        dialog.show();
    }



    public void deleteKhoanThu(Context context,KhoanThuDTO khoanThuDTO,int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xóa giỏ hàng?");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setMessage("Có chắc chắn xóa : " + khoanThuDTO.getTen_khoanThu());
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int kq = khoanThuDAO.deleteKhoanThu(khoanThuDTO);
                if(kq > 0)
                {
                    listThu.remove(index);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã xóa! ", Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }else
                    Toast.makeText(context, "Không xóa được! " + kq, Toast.LENGTH_SHORT).show();

                dialogInterface.dismiss();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Đã hủy", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public void SelectDate(EditText editText, Context context){
        final  Calendar calendar = Calendar.getInstance();
        this.selectedYear = calendar.get(Calendar.YEAR);
        this.selectedMonth = calendar.get(Calendar.MONTH);
        this.selectedDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editText.setText(year + " - "+(month+1)+" - "+dayOfMonth);
                editText.setFocusable(false);
                selectedYear = year;
                selectedMonth = month;
                selectedDayOfMonth = dayOfMonth;
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,dateSetListener,selectedYear,
                selectedMonth,selectedDayOfMonth);
        datePickerDialog.show();
    }

    public void seeMoreInfo(Context context,KhoanThuDTO khoanThuDTO){
        final  Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_xem_them_thong_tin);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView title_name = dialog.findViewById(R.id.title_nguoi_thu_chi);
        TextView title_so_tien = dialog.findViewById(R.id.title_so_tien_thu_chi);
        TextView title_ghi_chu = dialog.findViewById(R.id.title_ghi_chu_thu_chi);
        title_name.setText("Tên người thu :");
        title_so_tien.setText("Số tiền thu : ");
        title_ghi_chu.setText("Ghi chú : ");

        TextView name = dialog.findViewById(R.id.ten_nguoi_thu_chi);
        TextView so_tien = dialog.findViewById(R.id.so_tien_thu_chi);
        TextView ghi_chu = dialog.findViewById(R.id.ghi_chu_thu_chi);
        name.setText(khoanThuDTO.getTen_nguoiThu());
        so_tien.setText(khoanThuDTO.getSoTien()+"");
        ghi_chu.setText(khoanThuDTO.getGhiChu());

        dialog.show();
    }
}
