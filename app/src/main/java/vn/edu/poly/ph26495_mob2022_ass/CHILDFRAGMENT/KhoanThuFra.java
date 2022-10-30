package vn.edu.poly.ph26495_mob2022_ass.CHILDFRAGMENT;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.KhoanChiAdapter;
import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.KhoanThuAdapter;
import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.LoaiChiAdapter;
import vn.edu.poly.ph26495_mob2022_ass.DAO.KhoanChiDAO;
import vn.edu.poly.ph26495_mob2022_ass.DAO.KhoanThuDAO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.KhoanThuDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiChiDTO;
import vn.edu.poly.ph26495_mob2022_ass.R;


public class KhoanThuFra extends Fragment {
    RecyclerView list_khoan_thu;
    FloatingActionButton btn_khoan_thu;
    SearchView searchView;
    TextView tv_title;
    ImageView img_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_khoan_thu, container, false);
         btn_khoan_thu = view.findViewById(R.id.button_add_khoanThu);
         list_khoan_thu = view.findViewById(R.id.list_khoanThu);

        searchView = view.findViewById(R.id.ed_search_khoan_thu);
        tv_title  = view.findViewById(R.id.title_khoan_thu);
        img_search = view.findViewById(R.id.ic_search_khoanthu);
        tv_title.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.GONE);

        KhoanThuDAO khoanThuDAO = new KhoanThuDAO(getContext());
        ArrayList<KhoanThuDTO> listKhoanThu = khoanThuDAO.selectAll();
        KhoanThuAdapter khoanThuAdapter = new KhoanThuAdapter(khoanThuDAO.selectAll(), khoanThuDAO);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideTextView(tv_title);
                showSearch(searchView);
                showSearch(listKhoanThu,searchView,khoanThuAdapter);
            }
        });
        img_search.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showTextView(tv_title);
                hideSearch(searchView);
                return true;
            }
        });

        btn_khoan_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoanThuAdapter.addKhoanThu(getContext());
            }
        });

        list_khoan_thu.setAdapter(khoanThuAdapter);
        return  view;
    }


    void showSearch(ArrayList<KhoanThuDTO> listKhoanThu, SearchView searchView, KhoanThuAdapter adapter ){
        showSearch(searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<KhoanThuDTO> list = new ArrayList<>();
                for(KhoanThuDTO khoanThuDTO : listKhoanThu){
                    if(khoanThuDTO.getTen_khoanThu().toLowerCase().contains(newText.toLowerCase())){
                        list.add(khoanThuDTO);
                    }
                }

                adapter.setFilter(list);
                return true;
            }
        });
    }


    void showSearch(SearchView searchView) {
        searchView.setVisibility(View.VISIBLE);
        searchView.setAlpha(0f);
        searchView.setTranslationX(100);
        searchView.animate().alpha(1f).translationXBy(-100).setDuration(1500);
    }
    void hideSearch(SearchView searchView) {
        searchView.setVisibility(View.GONE);
        searchView.setAlpha(0f);
        searchView.setTranslationZ(100);
        searchView.animate().alpha(1f).translationZBy(-100).setDuration(1500);
    }

    void hideTextView(TextView tv) {
        tv.setVisibility(View.GONE);
        tv.setAlpha(0f);
        tv.setTranslationX(100);
        tv.animate().alpha(1f).translationXBy(-100).setDuration(1500);
    }
    void showTextView(TextView tv) {
        tv.setVisibility(View.VISIBLE);
        tv.setAlpha(0f);
        tv.setTranslationZ(100);
        tv.animate().alpha(1f).translationZBy(-100).setDuration(1500);
    }



}