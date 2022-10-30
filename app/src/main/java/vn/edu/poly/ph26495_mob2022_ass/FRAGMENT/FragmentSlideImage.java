package vn.edu.poly.ph26495_mob2022_ass.FRAGMENT;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;
import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.PhotoAdapter;
import vn.edu.poly.ph26495_mob2022_ass.DTO.Photos;
import vn.edu.poly.ph26495_mob2022_ass.R;

public class FragmentSlideImage extends Fragment {

    ViewPager2 viewPager2;
    CircleIndicator3 indicator3;
    ArrayList<Photos> listPhotos;

    private  Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentItems = viewPager2.getCurrentItem();
            if(currentItems == listPhotos.size() - 1){
                viewPager2.setCurrentItem(0);
            }else {
                viewPager2.setCurrentItem(currentItems + 1);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_slide_image,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager2 = view.findViewById(R.id.viewpage_slide);
        indicator3 = view.findViewById(R.id.indicator_slide);

        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(40));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);

                page.setScaleY(0.85f + r* 0.15f);
            }
        });
        viewPager2.setPageTransformer(transformer);

        listPhotos = getListPhotos();
        PhotoAdapter photoAdapter = new PhotoAdapter(listPhotos);
        viewPager2.setAdapter(photoAdapter);
        indicator3.setViewPager(viewPager2);


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,2500);
            }
        });
    }

    public ArrayList<Photos> getListPhotos (){
        ArrayList<Photos> listPhotos = new ArrayList<>();
        listPhotos.add(new Photos(R.drawable.slide_img_1));
        listPhotos.add(new Photos(R.drawable.slide_img_2));
        listPhotos.add(new Photos(R.drawable.slide_img_3));
        listPhotos.add(new Photos(R.drawable.slide_img_4));

        return listPhotos;
    }
    //Giữ vi trí banner khi sang tab khác
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable,2500);
    }

}
