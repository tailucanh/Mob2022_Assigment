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

import java.util.ArrayList;
import java.util.Calendar;

import vn.edu.poly.ph26495_mob2022_ass.DAO.KhoanChiDAO;
import vn.edu.poly.ph26495_mob2022_ass.DAO.LoaiChiDAO;
import vn.edu.poly.ph26495_mob2022_ass.DAO.LoaiThuDAO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.KhoanChiDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.KhoanThuDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiChiDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiThuDTO;
import vn.edu.poly.ph26495_mob2022_ass.R;

public class KhoanChiAdapter extends RecyclerView.Adapter<KhoanChiAdapter.MyViewHolderKhoanChi> {
    ArrayList<KhoanChiDTO> listChi;
    KhoanChiDAO khoanChiDAO;
    ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private int selectedYear;
    private int selectedMonth;
    private int selectedDayOfMonth;


    public KhoanChiAdapter(ArrayList<KhoanChiDTO> listChi, KhoanChiDAO khoanChiDAO) {
        this.listChi = listChi;
        this.khoanChiDAO = khoanChiDAO;
    }
    public void setFilter(ArrayList<KhoanChiDTO> filterList){
        this.listChi = filterList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public KhoanChiAdapter.MyViewHolderKhoanChi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_one_item_khoan_thu_chi,null);
        return new KhoanChiAdapter.MyViewHolderKhoanChi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanChiAdapter.MyViewHolderKhoanChi holder, int position) {
        final  int index = position;
        KhoanChiDTO khoanChiDTO = listChi.get(position);
        KhoanChiDTO show_loai_chi = khoanChiDAO.selectOne(khoanChiDTO.getId_khoanChi());
        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(khoanChiDTO.getId_khoanChi()));
        holder.ten_khoan_chi.setText(khoanChiDTO.getTen_khoanChi());
        holder.ngay_chi.setText(khoanChiDTO.getNgayChi());
        holder.loai_chi.setText(show_loai_chi.getTen_loaiChi());
        holder.xem_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeMoreInfo(v.getContext(), khoanChiDTO);
            }
        });
        holder.delete_khoan_chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteKhoanChi(v.getContext(), khoanChiDTO,index);
            }
        });
        holder.update_khoan_chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateKhoanChi(v.getContext(), khoanChiDTO,index);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listChi.size();
    }

    public class MyViewHolderKhoanChi extends RecyclerView.ViewHolder{
        TextView ten_khoan_chi,ngay_chi,loai_chi,xem_them;
        ImageView delete_khoan_chi, update_khoan_chi;
        SwipeRevealLayout swipeRevealLayout;
        public MyViewHolderKhoanChi(@NonNull View view) {
            super(view);
            swipeRevealLayout = view.findViewById(R.id.swiper_layout);

            ten_khoan_chi = view.findViewById(R.id.ten_khoan_thu_chi);
            ngay_chi = view.findViewById(R.id.ngay_thu_chi);
            loai_chi = view.findViewById(R.id.loai_thu_chi);
            xem_them = view.findViewById(R.id.mo_rong_thong_tin);

            delete_khoan_chi = view.findViewById(R.id.img_delete_khoan_thu_chi);
            update_khoan_chi = view.findViewById(R.id.img_update_khoan_thu_chi);
        }
    }

    public  void addKhoanChi(Context context){
        final  Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_khoan_chi);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //Code xử lí
        ImageView dateChi = dialog.findViewById(R.id.datetime_chi);

        TextInputLayout ed_ten_khoan_chi= dialog.findViewById(R.id.ed_ten_khoan_chi);
        TextInputLayout ed_ten_nguoi_chi = dialog.findViewById(R.id.ed_ten_nguoi_chi);
        TextInputLayout ed_so_tien = dialog.findViewById(R.id.ed_so_tien_chi);
        TextInputLayout ed_ghi_chu_chi = dialog.findViewById(R.id.ed_ghi_chu_chi);
        TextInputLayout ed_dateChi = dialog.findViewById(R.id.ed_ngay_chi);

        dateChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate(ed_dateChi.getEditText(), context);
            }
        });

        final  Spinner spinner = dialog.findViewById(R.id.spinner_loai_chi);
        LoaiChiDAO loaiChiDAO = new LoaiChiDAO(context);
        SpinnerLoaiChi spinnerLoaiChi = new SpinnerLoaiChi(loaiChiDAO.selectAll());
        spinner.setAdapter(spinnerLoaiChi);
        Button btn_save = dialog.findViewById(R.id.btn_save_khoanChi);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_ten_khoan_chi.getEditText().getText().toString().equals("")){
                    ed_ten_khoan_chi.setError("Hãy nhập tên khoản chi !");
                }else if(ed_ten_nguoi_chi.getEditText().getText().toString().equals("")) {
                    ed_ten_khoan_chi.setError("");
                    ed_ten_nguoi_chi.setError("Hãy nhập tên người chi !");
                }else if(ed_dateChi.getEditText().getText().toString().equals("")){
                    ed_ten_nguoi_chi.setError("");
                    ed_dateChi.setError("Hãy nhập ngày chi !");
                }else if(ed_so_tien.getEditText().getText().toString().equals("")){
                    ed_dateChi.setError("");
                    ed_so_tien.setError("Hãy nhập số tiền chi !");
                }else{
                    ed_so_tien.setError("");
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setIcon(R.drawable.ic_save);
                    builder.setTitle("Confirm !!!");
                    builder.setMessage("Xác nhận lưu thông tin !");
                    builder.setCancelable(false);

                    KhoanChiDTO khoanChiDTO = new KhoanChiDTO();
                    LoaiChiDTO loaiChiDTO = (LoaiChiDTO) spinner.getSelectedItem();
                    khoanChiDTO.setId_loaiChi(loaiChiDTO.getId_loaiChi());

                    khoanChiDTO.setTen_khoanChi( ed_ten_khoan_chi.getEditText().getText().toString());
                    khoanChiDTO.setTen_nguoiChi( ed_ten_nguoi_chi.getEditText().getText().toString());
                    khoanChiDTO.setNgayChi(ed_dateChi.getEditText().getText().toString());
                    khoanChiDTO.setSoTien(Integer.parseInt(ed_so_tien.getEditText().getText().toString()));
                    khoanChiDTO.setGhiChu(ed_ghi_chu_chi.getEditText().getText().toString());

                    long res = khoanChiDAO.insertKhoanChi(khoanChiDTO);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog1, int which) {
                            if(res > 0){
                                listChi.clear();
                                listChi.addAll(khoanChiDAO.selectAll());
                                notifyDataSetChanged();
                                Toast.makeText(context, "Đã thêm mới! ", Toast.LENGTH_SHORT).show();
                                dialog1.dismiss();
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

    public void updateKhoanChi(Context context, KhoanChiDTO khoanChiDTO, int index){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_change_khoan_chi);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_change_3);
        builder.setTitle("Confirm !!!");
        builder.setMessage("Xác nhận sửa đổi thông tin!");
        builder.setCancelable(false);

        ImageView date_update = dialog.findViewById(R.id.datetime_chi_update);

        EditText ten_khoan_chi_update = dialog.findViewById(R.id.ed_ten_khoan_chi_update);
        EditText ed_ten_nguoi_chi_update = dialog.findViewById(R.id.ed_ten_nguoi_chi_update);
        EditText ed_dateChi_update = dialog.findViewById(R.id.ed_ngay_chi_update);
        EditText ed_so_tien_update = dialog.findViewById(R.id.ed_so_tien_chi_update);
        EditText ed_ghi_chu_chi_update = dialog.findViewById(R.id.ed_ghi_chu_chi_update);
        date_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate(ed_dateChi_update, context);
            }
        });
        ten_khoan_chi_update.setText(khoanChiDTO.getTen_khoanChi());
        ed_ten_nguoi_chi_update.setText(khoanChiDTO.getTen_nguoiChi());
        ed_dateChi_update.setText(khoanChiDTO.getNgayChi());
        ed_so_tien_update.setText(khoanChiDTO.getSoTien() +"");
        ed_ghi_chu_chi_update.setText(khoanChiDTO.getGhiChu());

        final Spinner spinner = dialog.findViewById(R.id.spinner_loai_chi_update);
        LoaiChiDAO loaiChiDAO = new LoaiChiDAO(context);
        ArrayList<LoaiChiDTO> listLoaiChi = loaiChiDAO.selectAll();
        SpinnerLoaiChi adapter = new SpinnerLoaiChi(listLoaiChi);
        spinner.setAdapter(adapter);

        for(int i = 0; i < listLoaiChi.size(); i++){
            LoaiChiDTO temp = listLoaiChi.get(i);
            if(temp.getId_loaiChi() == khoanChiDTO.getId_loaiChi()){
                spinner.setSelection(i);
                spinner.setSelected(true);
            }
        }


        Button btn_update = dialog.findViewById(R.id.btn_change_khoanChi);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                khoanChiDTO.setTen_khoanChi( ten_khoan_chi_update.getText().toString());
                khoanChiDTO.setTen_nguoiChi( ed_ten_nguoi_chi_update.getText().toString());
                khoanChiDTO.setNgayChi(ed_dateChi_update.getText().toString());
                khoanChiDTO.setSoTien(Integer.parseInt(ed_so_tien_update.getText().toString()));
                khoanChiDTO.setGhiChu(ed_ghi_chu_chi_update.getText().toString());

                LoaiChiDTO loaiChiDTO = (LoaiChiDTO) spinner.getSelectedItem();
                khoanChiDTO.setId_loaiChi(loaiChiDTO.getId_loaiChi());

                int res = khoanChiDAO.updateKhoanChi(khoanChiDTO);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        if(res > 0){
                            listChi.set(index,khoanChiDTO);
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



    public void deleteKhoanChi(Context context,KhoanChiDTO khoanChiDTO,int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xóa giỏ hàng?");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setMessage("Có chắc chắn xóa : " + khoanChiDTO.getTen_khoanChi());
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int kq = khoanChiDAO.deleteKhoanChi(khoanChiDTO);
                if(kq > 0)
                {
                    listChi.remove(index);
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
        final Calendar calendar = Calendar.getInstance();
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


    public void seeMoreInfo(Context context,KhoanChiDTO khoanChiDTO){
        final  Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_xem_them_thong_tin);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView title_name = dialog.findViewById(R.id.title_nguoi_thu_chi);
        TextView title_so_tien = dialog.findViewById(R.id.title_so_tien_thu_chi);
        TextView title_ghi_chu = dialog.findViewById(R.id.title_ghi_chu_thu_chi);
        title_name.setText("Tên người chi :");
        title_so_tien.setText("Số tiền chi : ");
        title_ghi_chu.setText("Ghi chú : ");

        TextView name = dialog.findViewById(R.id.ten_nguoi_thu_chi);
        TextView so_tien = dialog.findViewById(R.id.so_tien_thu_chi);
        TextView ghi_chu = dialog.findViewById(R.id.ghi_chu_thu_chi);
        name.setText(khoanChiDTO.getTen_nguoiChi());
        so_tien.setText(khoanChiDTO.getSoTien()+"");
        ghi_chu.setText(khoanChiDTO.getGhiChu());

        dialog.show();
    }

}
