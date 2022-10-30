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

import vn.edu.poly.ph26495_mob2022_ass.DAO.LoaiChiDAO;
import vn.edu.poly.ph26495_mob2022_ass.DAO.LoaiThuDAO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiChiDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiThuDTO;
import vn.edu.poly.ph26495_mob2022_ass.R;

public class LoaiThuAdapter extends RecyclerView.Adapter<LoaiThuAdapter.MyViewHolderLoaiThu> {
    ArrayList<LoaiThuDTO> listThu;
    LoaiThuDAO loaiThuDAO ;
    ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public LoaiThuAdapter(ArrayList<LoaiThuDTO> listThu, LoaiThuDAO loaiThuDAO) {
        this.listThu = listThu;
        this.loaiThuDAO = loaiThuDAO;
    }

    public void setFilter(ArrayList<LoaiThuDTO> filterList){
        this.listThu = filterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LoaiThuAdapter.MyViewHolderLoaiThu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_one_item_loai_thu_chi,null);
        return new LoaiThuAdapter.MyViewHolderLoaiThu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiThuAdapter.MyViewHolderLoaiThu holder, int position) {
        final  int index = position;
        LoaiThuDTO loaiThuDTO = listThu.get(position);

        holder.ten_loai_thu.setText(loaiThuDTO.getTen_Loaithu());
        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(loaiThuDTO.getId_loaiThu()));
        holder.delete_loai_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLoaiThu(v.getContext(), loaiThuDTO,index);
            }
        });
        holder.update_loai_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLoaiThu(v.getContext(), loaiThuDTO,index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listThu.size();
    }

    public class MyViewHolderLoaiThu extends RecyclerView.ViewHolder{
        TextView ten_loai_thu;
        ImageView delete_loai_thu, update_loai_thu;
        SwipeRevealLayout swipeRevealLayout;

        public MyViewHolderLoaiThu(@NonNull View view) {
            super(view);
            swipeRevealLayout = view.findViewById(R.id.swiper_layout);


            ten_loai_thu = view.findViewById(R.id.ten_loai_thu_chi);
            delete_loai_thu = view.findViewById(R.id.img_delete_loai_thuchi);
            update_loai_thu = view.findViewById(R.id.umg_update_loai_thuchi);
        }
    }


    public  void addLoaiThu(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_loai_thu);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //Code xử lí
        TextInputLayout ed_ten_loai_thu = dialog.findViewById(R.id.ed_ten_loai_thu);
        Button btn_save = dialog.findViewById(R.id.btn_save_loaiThu);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_ten_loai_thu.getEditText().getText().toString().equals("")){
                    ed_ten_loai_thu.setError("Hãy nhập tên loại thu !");
                }else{
                    ed_ten_loai_thu.setError("");
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setIcon(R.drawable.ic_save);
                    builder.setTitle("Confirm !!!");
                    builder.setMessage("Xác nhận lưu thông tin !");
                    builder.setCancelable(false);

                    LoaiThuDTO loaiThuDTO = new LoaiThuDTO();
                    String ten_loai_thu = ed_ten_loai_thu.getEditText().getText().toString();
                    loaiThuDTO.setTen_Loaithu(ten_loai_thu);

                    long res = loaiThuDAO.insertLoaiThu(loaiThuDTO);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog1, int which) {
                            if(res > 0){
                                if(ten_loai_thu.equals("")){
                                    Toast.makeText(context, "Hãy nhập tên loại thu!", Toast.LENGTH_SHORT).show();
                                }else{
                                    listThu.clear();
                                    listThu.addAll(loaiThuDAO.selectAll());
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Đã thêm mới! ", Toast.LENGTH_SHORT).show();
                                    dialog1.cancel();
                                    dialog.dismiss();
                                }
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

    public void updateLoaiThu(Context context, LoaiThuDTO loaiThuDTO, int index){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_change_loai_thu);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_change_3);
        builder.setTitle("Confirm !!!");
        builder.setMessage("Xác nhận sửa đổi thông tin!");
        builder.setCancelable(true);

        EditText ten_loai_thu_update = dialog.findViewById(R.id.ed_loaiThu_change);
        ten_loai_thu_update.setText(loaiThuDTO.getTen_Loaithu());

        Button btn_update = dialog.findViewById(R.id.btn_update_loaiThu);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiThuDTO.setTen_Loaithu(ten_loai_thu_update.getText().toString());
                int res = loaiThuDAO.updateLoaiThu(loaiThuDTO);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        if(res > 0){
                            listThu.set(index,loaiThuDTO);
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

    public void deleteLoaiThu(Context context,LoaiThuDTO loaiThuDTO,int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xóa giỏ hàng?");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setMessage("Có chắc chắn xóa : " + loaiThuDTO.getTen_Loaithu());
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int kq = loaiThuDAO.deleteLoaiThu(loaiThuDTO);
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




}
