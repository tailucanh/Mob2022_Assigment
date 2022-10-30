package vn.edu.poly.ph26495_mob2022_ass.ADAPTER;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import vn.edu.poly.ph26495_mob2022_ass.FRAGMENT.ChiFragment;
import vn.edu.poly.ph26495_mob2022_ass.FRAGMENT.GioithieuFragment;
import vn.edu.poly.ph26495_mob2022_ass.FRAGMENT.ThongKeFragment;
import vn.edu.poly.ph26495_mob2022_ass.FRAGMENT.ThuFragment;
import vn.edu.poly.ph26495_mob2022_ass.R;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    int images[] = {
            R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4
    };
    int headings[] = {
            R.string.heading_one, R.string.heading_two, R.string.heading_three, R.string.heading_fourth
    };
    int contents[] = {
            R.string.desc_one, R.string.desc_two, R.string.desc_three, R.string.desc_fourth
    };


    public ViewPagerAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

       View view = LayoutInflater.from(context).inflate(R.layout.slide_onboarding_content,container,false);

       ImageView imgTitle = view.findViewById(R.id.title_img_onboarding);
       TextView title = view.findViewById(R.id.text_title_onboarding);
        TextView content = view.findViewById(R.id.text_content_onboarding);

        imgTitle.setImageResource(images[position]);
        title.setText(headings[position]);
        content.setText(contents[position]);

        container.addView(view);
       return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((LinearLayout) object);
    }
}















