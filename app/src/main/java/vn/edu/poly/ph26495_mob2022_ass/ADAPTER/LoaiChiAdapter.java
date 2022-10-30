package vn.edu.poly.ph26495_mob2022_ass.ADAPTER;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.poly.ph26495_mob2022_ass.DAO.KhoanChiDAO;
import vn.edu.poly.ph26495_mob2022_ass.DAO.LoaiChiDAO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.KhoanChiDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiChiDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiThuDTO;
import vn.edu.poly.ph26495_mob2022_ass.R;

public class LoaiChiAdapter extends RecyclerView.Adapter<LoaiChiAdapter.MyViewHolderLoaiChi> {
    ArrayList<LoaiChiDTO> listChi;
    LoaiChiDAO loaiChiDAO;
    ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public LoaiChiAdapter(ArrayList<LoaiChiDTO> listChi, LoaiChiDAO loaiChiDAO) {
        this.listChi = listChi;
        this.loaiChiDAO = loaiChiDAO;
    }
    public void setFilter(ArrayList<LoaiChiDTO> filterList){
        this.listChi = filterList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public LoaiChiAdapter.MyViewHolderLoaiChi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_one_item_loai_thu_chi,null);
        return new LoaiChiAdapter.MyViewHolderLoaiChi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiChiAdapter.MyViewHolderLoaiChi holder, int position) {
        final  int index = position;

        LoaiChiDTO loaiChiDTO = listChi.get(position);
        holder.ten_loai_chi.setText(loaiChiDTO.getTen_LoaiChi());
        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(loaiChiDTO.getId_loaiChi()));

        holder.delete_loai_chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLoaiChi(v.getContext(), loaiChiDTO,index);
            }
        });
        holder.update_loai_chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLoaiChi(v.getContext(), loaiChiDTO,index);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listChi.size();
    }
    public class MyViewHolderLoaiChi extends RecyclerView.ViewHolder{
        TextView ten_loai_chi;
        ImageView delete_loai_chi, update_loai_chi;
        SwipeRevealLayout swipeRevealLayout;

        public MyViewHolderLoaiChi(@NonNull View view) {
            super(view);
            swipeRevealLayout = view.findViewById(R.id.swiper_layout);

            ten_loai_chi = view.findViewById(R.id.ten_loai_thu_chi);
            delete_loai_chi = view.findViewById(R.id.img_delete_loai_thuchi);
            update_loai_chi = view.findViewById(R.id.umg_update_loai_thuchi);

        }
    }



    public  void addLoaiChi(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_loai_chi);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //Code xử lí
        TextInputLayout ed_ten_loai_chi = dialog.findViewById(R.id.ed_ten_loai_Chi);
        Button btn_save = dialog.findViewById(R.id.btn_save_loaiChi);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_ten_loai_chi.getEditText().getText().toString().equals("")){
                    ed_ten_loai_chi.setError("Hãy nhập tên loại chi");
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setIcon(R.drawable.ic_save);
                    builder.setTitle("Confirm !!!");
                    builder.setMessage("Xác nhận lưu thông tin !");
                    builder.setCancelable(false);

                    LoaiChiDTO loaiChiDTO = new LoaiChiDTO();
                    String ten_loai_chi = ed_ten_loai_chi.getEditText().getText().toString();
                    loaiChiDTO.setTen_LoaiChi(ten_loai_chi);

                    long res = loaiChiDAO.insertLoaiChi(loaiChiDTO);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog1, int which) {
                            if(res > 0){
                                listChi.clear();
                                listChi.addAll(loaiChiDAO.selectAll());
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

    public void updateLoaiChi(Context context, LoaiChiDTO loaiChiDTO, int index){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_change_loai_chi);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_change_3);
        builder.setTitle("Confirm !!!");
        builder.setMessage("Xác nhận sửa đổi thông tin!");
        builder.setCancelable(true);

        EditText ten_loai_chi_update = dialog.findViewById(R.id.ed_loaichi_change);
        ten_loai_chi_update.setText(loaiChiDTO.getTen_LoaiChi());

        Button btn_update = dialog.findViewById(R.id.btn_update_loaiChi);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiChiDTO.setTen_LoaiChi(ten_loai_chi_update.getText().toString());
                int res = loaiChiDAO.updateLoaiChi(loaiChiDTO);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        if(res > 0){
                            listChi.set(index,loaiChiDTO);
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

    public void deleteLoaiChi(Context context,LoaiChiDTO loaiChiDTO,int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xóa giỏ hàng?");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setMessage("Có chắc chắn xóa : " + loaiChiDTO.getTen_LoaiChi());
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int kq = loaiChiDAO.deleteLoaiChi(loaiChiDTO);
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

}