package vn.edu.poly.ph26495_mob2022_ass.FRAGMENT;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.AdapterTabLayoutChi;
import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.AdapterTabLayoutThongKe;
import vn.edu.poly.ph26495_mob2022_ass.AnimationViewPage2.ZoomOutPageTransformer;
import vn.edu.poly.ph26495_mob2022_ass.R;


public class ThongKeFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    AdapterTabLayoutThongKe adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_thong_ke, container, false);
        //Toolbar


        tabLayout = view.findViewById(R.id.tabLayout_tk);
        viewPager = view.findViewById(R.id.viewPage_tab);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        adapter = new AdapterTabLayoutThongKe(this);
        viewPager.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Thống kê thu"));
        tabLayout.addTab(tabLayout.newTab().setText("Thống kê chi"));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


        return  view;
    }
}