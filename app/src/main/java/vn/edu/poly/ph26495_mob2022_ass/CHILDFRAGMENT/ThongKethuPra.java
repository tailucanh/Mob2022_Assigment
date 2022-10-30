package vn.edu.poly.ph26495_mob2022_ass.CHILDFRAGMENT;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.droidsonroids.gif.GifImageView;
import vn.edu.poly.ph26495_mob2022_ass.DAO.KhoanThuDAO;
import vn.edu.poly.ph26495_mob2022_ass.R;


public class ThongKethuPra extends Fragment {
    private int selectedYear;
    private int selectedMonth;
    private int selectedDayOfMonth;
    ImageView date_start,date_finish,img_thong_ke;
    TextView tv_date_start, tv_date_finish,tv_title,tv_subtitle1,tv_subtitle2,tv_soLuong,tv_tongTien,tv_VN_dong;
    GifImageView img_thong_ke_true, img_thong_ke_false;
    LinearLayout layout_content;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_thong_ke_thu, container, false);
        anhXa(view);

        img_thong_ke.setVisibility(View.VISIBLE);
        img_thong_ke_true.setVisibility(View.GONE);
        img_thong_ke_false.setVisibility(View.GONE);
        hideContent();
        showLayoutContent();

        date_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate(tv_date_start,getContext());
            }
        });
        date_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate(tv_date_finish,getContext());
            }
        });
        final Handler handler = new Handler();
        img_thong_ke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_date_start.getText().toString().equals("")|| tv_date_finish.getText().toString().equals("")){
                    img_thong_ke_false.setVisibility(View.VISIBLE);
                    img_thong_ke.setVisibility(View.GONE);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            img_thong_ke.setVisibility(View.VISIBLE);
                        }
                    },2200);
                    img_thong_ke_true.setVisibility(View.GONE);
                    hideContent();
                    Toast.makeText(getContext(), "Hãy chọn ngày!", Toast.LENGTH_SHORT).show();
                }else {
                    img_thong_ke_true.setVisibility(View.VISIBLE);
                    img_thong_ke.setVisibility(View.GONE);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            img_thong_ke.setVisibility(View.VISIBLE);
                        }
                    },2000);
                    img_thong_ke_false.setVisibility(View.GONE);

                    showContent();
                    hideLayoutContent();

                    String ngayBatDau = tv_date_start.getText().toString();
                    String ngayKetThuc = tv_date_finish.getText().toString();

                    KhoanThuDAO khoanThuDAO = new KhoanThuDAO(getContext());
                    int res = khoanThuDAO.selectCountKhoanThu(ngayBatDau,ngayKetThuc);
                    double res2 = khoanThuDAO.selectSumKhoanThu(ngayBatDau,ngayKetThuc);
                    Locale localeEN = new Locale("en", "EN");
                    NumberFormat en = NumberFormat.getInstance(localeEN);

                    Double sum = res2;
                    String strSum = en.format(sum);

                    if(res > 0){
                        tv_soLuong.setText(String.valueOf(res));
                        Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                    }else{

                    }
                    if(res2 > 0){
                        tv_tongTien.setText(strSum);
                    }else {
                        Toast.makeText(getContext(), "Không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
    void anhXa(View view){
        date_start = view.findViewById(R.id.date_start_tk_thu);
        date_finish = view.findViewById(R.id.date_finish_tk_thu);
        tv_date_start = view.findViewById(R.id.tv_date_start_tk_thu);
        tv_date_finish = view.findViewById(R.id.tv_date_finish_tk_thu);

        img_thong_ke = view.findViewById(R.id.img_thong_ke);
        img_thong_ke_true = view.findViewById(R.id.img_thong_ke_true);
        img_thong_ke_false = view.findViewById(R.id.img_thong_ke_false);

        tv_title = view.findViewById(R.id.tv_title);
        tv_subtitle1 = view.findViewById(R.id.tv_subtitle_1);
        tv_subtitle2 = view.findViewById(R.id.tv_subtitle_2);
        tv_soLuong = view.findViewById(R.id.tv_soLuong_thu);
        tv_tongTien = view.findViewById(R.id.tv_tongTien_thu);
        tv_VN_dong = view.findViewById(R.id.tv_VN_dong);

        layout_content = view.findViewById(R.id.layout_content_tk);
    }

    void animationContent(TextView tv){
        tv.setAlpha(0f);
        tv.setTranslationZ(-150);
        tv.animate().alpha(1f).translationZBy(150).setDuration(2000);
    }

    void hideContent(){
        tv_title.setVisibility(View.GONE);
        tv_subtitle1.setVisibility(View.GONE);
        tv_subtitle2.setVisibility(View.GONE);
        tv_soLuong.setVisibility(View.GONE);
        tv_tongTien.setVisibility(View.GONE);
        tv_VN_dong.setVisibility(View.GONE);
    }

    void showLayoutContent(){
        layout_content.setVisibility(View.VISIBLE);
        layout_content.setAlpha(0f);
        layout_content.setTranslationZ(-150);
        layout_content.animate().alpha(1f).translationZBy(150).setDuration(2000);
    }

    void hideLayoutContent(){
        layout_content.setVisibility(View.GONE);
        layout_content.setAlpha(0f);
        layout_content.setTranslationZ(150);
        layout_content.animate().alpha(1f).translationZBy(-150).setDuration(2000);
    }

    void showContent(){
        tv_title.setVisibility(View.VISIBLE);
        animationContent(tv_title);
        tv_subtitle1.setVisibility(View.VISIBLE);
        animationContent(tv_subtitle1);
        tv_subtitle2.setVisibility(View.VISIBLE);
        animationContent(tv_subtitle2);
        tv_soLuong.setVisibility(View.VISIBLE);
        animationContent(tv_soLuong);
        tv_tongTien.setVisibility(View.VISIBLE);
        animationContent(tv_tongTien);
        tv_VN_dong.setVisibility(View.VISIBLE);
        animationContent(tv_VN_dong);

    }


    public void SelectDate(TextView textView, Context context){
        final Calendar calendar = Calendar.getInstance();
        this.selectedYear = calendar.get(Calendar.YEAR);
        this.selectedMonth = calendar.get(Calendar.MONTH);
        this.selectedDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textView.setText(year + " - "+(month+1)+" - "+dayOfMonth);
                selectedYear = year;
                selectedMonth = month;
                selectedDayOfMonth = dayOfMonth;
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,dateSetListener,selectedYear,
                selectedMonth,selectedDayOfMonth);
        datePickerDialog.show();
    }
}