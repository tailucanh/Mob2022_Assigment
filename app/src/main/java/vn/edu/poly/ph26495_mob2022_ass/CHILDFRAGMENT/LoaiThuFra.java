package vn.edu.poly.ph26495_mob2022_ass.CHILDFRAGMENT;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.LoaiChiAdapter;
import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.LoaiThuAdapter;
import vn.edu.poly.ph26495_mob2022_ass.DAO.LoaiChiDAO;
import vn.edu.poly.ph26495_mob2022_ass.DAO.LoaiThuDAO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiThuDTO;
import vn.edu.poly.ph26495_mob2022_ass.R;


public class LoaiThuFra extends Fragment {


    RecyclerView list_loai_thu;
    FloatingActionButton btn_loai_thu;
    SearchView searchView;
    TextView tv_title;
    ImageView img_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_loai_thu, container, false);

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_loai_thu = view.findViewById(R.id.button_add_loaiThu);
        list_loai_thu = view.findViewById(R.id.list_loaiThu);
        searchView = view.findViewById(R.id.ed_search_loai_thu);
        tv_title  = view.findViewById(R.id.title_loai_thu);
        img_search = view.findViewById(R.id.ic_search_loaithu);
        tv_title.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.GONE);
        showHideWhenScroll();
        LoaiThuDAO loaiThuDAO  = new LoaiThuDAO(getContext());
        ArrayList<LoaiThuDTO> listLoaithu = loaiThuDAO.selectAll();
        LoaiThuAdapter loaiThuAdapter  = new LoaiThuAdapter(loaiThuDAO.selectAll(), loaiThuDAO);
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideTextView(tv_title);
                showSearch(searchView);
                showSearch(listLoaithu,searchView,loaiThuAdapter);
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


        btn_loai_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiThuAdapter.addLoaiThu(getContext());
            }
        });
        list_loai_thu.setAdapter(loaiThuAdapter);


    }

    void showSearch( ArrayList<LoaiThuDTO> listLoaithu,SearchView searchView, LoaiThuAdapter adapter ){
        showSearch(searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<LoaiThuDTO> list = new ArrayList<>();
                for(LoaiThuDTO loaiThuDTO : listLoaithu){
                    if(loaiThuDTO.getTen_Loaithu().toLowerCase().contains(newText.toLowerCase())){
                        list.add(loaiThuDTO);
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



    private void showHideWhenScroll() {
        list_loai_thu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //dy > 0: scroll up; dy < 0: scroll down
                if (dy > 0) btn_loai_thu.hide();
                else btn_loai_thu.show();
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}