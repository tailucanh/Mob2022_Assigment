package vn.edu.poly.ph26495_mob2022_ass.CHILDFRAGMENT;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.KhoanChiAdapter;
import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.KhoanThuAdapter;
import vn.edu.poly.ph26495_mob2022_ass.DAO.KhoanChiDAO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.KhoanChiDTO;
import vn.edu.poly.ph26495_mob2022_ass.DTO.KhoanThuDTO;
import vn.edu.poly.ph26495_mob2022_ass.R;


public class KhoanChiFra extends Fragment {
    RecyclerView list_khoan_chi;
    FloatingActionButton btn_khoan_chi;
    SearchView searchView;
    TextView tv_title;
    ImageView img_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_khoan_chi, container, false);
         btn_khoan_chi = view.findViewById(R.id.button_add_khoanChi);
         list_khoan_chi = view.findViewById(R.id.list_khoanChi);

        searchView = view.findViewById(R.id.ed_search_khoan_chi);
        tv_title  = view.findViewById(R.id.title_khoan_chi);
        img_search = view.findViewById(R.id.ic_search_khoanchi);
        tv_title.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.GONE);

        showHideWhenScroll();
        KhoanChiDAO khoanChiDAO = new KhoanChiDAO(getContext());
        ArrayList<KhoanChiDTO> listKhoanChi = khoanChiDAO.selectAll();
        KhoanChiAdapter khoanChiAdapter = new KhoanChiAdapter(khoanChiDAO.selectAll(), khoanChiDAO);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideTextView(tv_title);
                showSearch(searchView);
                showSearch(listKhoanChi,searchView,khoanChiAdapter);
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

        btn_khoan_chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoanChiAdapter.addKhoanChi(getContext());
            }
        });
        list_khoan_chi.setAdapter(khoanChiAdapter);

        return view;
    }

    void showSearch(ArrayList<KhoanChiDTO> listKhoanChi, SearchView searchView, KhoanChiAdapter adapter ){
        showSearch(searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<KhoanChiDTO> list = new ArrayList<>();
                for(KhoanChiDTO khoanChiDTO : listKhoanChi){
                    if(khoanChiDTO.getTen_khoanChi().toLowerCase().contains(newText.toLowerCase())){
                        list.add(khoanChiDTO);
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
        list_khoan_chi.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //dy > 0: scroll up; dy < 0: scroll down
                if (dy > 0) btn_khoan_chi.hide();
                else btn_khoan_chi.show();
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}