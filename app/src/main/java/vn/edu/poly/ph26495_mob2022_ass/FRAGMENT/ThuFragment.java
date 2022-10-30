package vn.edu.poly.ph26495_mob2022_ass.FRAGMENT;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.AdapterTabLayoutThu;
import vn.edu.poly.ph26495_mob2022_ass.AnimationViewPage2.ZoomOutPageTransformer;
import vn.edu.poly.ph26495_mob2022_ass.R;


public class ThuFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    AdapterTabLayoutThu adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_thu, container, false);
        
         tabLayout = view.findViewById(R.id.tabLayout_Thu);
         viewPager = view.findViewById(R.id.viewPage_tab);
         viewPager.setPageTransformer(new ZoomOutPageTransformer());
         adapter = new AdapterTabLayoutThu(this);
         viewPager.setAdapter(adapter);
        animationContent(viewPager);
         tabLayout.addTab(tabLayout.newTab().setText("Khoản thu"));
        tabLayout.addTab(tabLayout.newTab().setText("Loại thu"));


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
    void animationContent(ViewPager2 viewPager){
        viewPager.setAlpha(0f);
        viewPager.setTranslationZ(-50);
        viewPager.animate().alpha(1f).translationZBy(50).setDuration(1500);
    }

}