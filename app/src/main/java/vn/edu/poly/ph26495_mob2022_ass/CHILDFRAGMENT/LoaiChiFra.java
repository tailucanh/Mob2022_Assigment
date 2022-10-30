package vn.edu.poly.ph26495_mob2022_ass.CHILDFRAGMENT;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.KhoanChiAdapter;
import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.LoaiChiAdapter;
import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.LoaiThuAdapter;
import vn.edu.poly.ph26495_mob2022_ass.DAO.KhoanChiDAO;
import vn.edu.poly.ph26495_mob2022_ass.DAO.LoaiChiDAO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiChiDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.LoaiThuDTO;
import vn.edu.poly.ph26495_mob2022_ass.R;

public class LoaiChiFra extends Fragment {

    RecyclerView list_loai_chi;
    FloatingActionButton btn_loai_chi;
    SearchView searchView;
    TextView tv_title;
    ImageView img_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loai_chi, container, false);
        btn_loai_chi = view.findViewById(R.id.button_add_loaiChi);
        list_loai_chi = view.findViewById(R.id.list_loaiChi);
        searchView = view.findViewById(R.id.ed_search_loai_chi);
        tv_title  = view.findViewById(R.id.title_loai_chi);
        img_search = view.findViewById(R.id.ic_search_loaichi);
        tv_title.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.GONE);
        showHideWhenScroll();
        LoaiChiDAO loaiChiDAO = new LoaiChiDAO(getContext());
        ArrayList<LoaiChiDTO> listLoaiChi = loaiChiDAO.selectAll();
        LoaiChiAdapter loaiChiAdapter = new LoaiChiAdapter(loaiChiDAO.selectAll(), loaiChiDAO);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideTextView(tv_title);
                showSearch(searchView);
                showSearch(listLoaiChi,searchView,loaiChiAdapter);
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

        btn_loai_chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiChiAdapter.addLoaiChi(getContext());
            }
        });

        list_loai_chi.setAdapter(loaiChiAdapter);

        return  view;
    }

    void showSearch(ArrayList<LoaiChiDTO> listLoaiChi, SearchView searchView, LoaiChiAdapter adapter ){
        showSearch(searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<LoaiChiDTO> list = new ArrayList<>();
                for(LoaiChiDTO loaiChiDTO : listLoaiChi){
                    if(loaiChiDTO.getTen_LoaiChi().toLowerCase().contains(newText.toLowerCase())){
                        list.add(loaiChiDTO);
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
        list_loai_chi.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //dy > 0: scroll up; dy < 0: scroll down
                if (dy > 0) btn_loai_chi.hide();
                else btn_loai_chi.show();
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}