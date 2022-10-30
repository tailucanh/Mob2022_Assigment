package vn.edu.poly.ph26495_mob2022_ass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.edu.poly.ph26495_mob2022_ass.ADAPTER.ViewPagerAdapter;

public class OnboardingScreen extends AppCompatActivity {

    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;
    TextView tv_back, tv_next,tv_skip;
    TextView dots[];
    ImageView ellipse1,ellipse2;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen);
        anhXa();
        Intent intent = new Intent(getBaseContext(),LoginActivity.class);
        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getItems(0) > 0){
                    mSlideViewPager.setCurrentItem(getItems(-1),true);
                }
            }
        });

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getItems(0) < 3){
                    mSlideViewPager.setCurrentItem(getItems(1),true);
                }else {
                    startActivity(intent);
                    finish();
                }
            }
        });


        viewPagerAdapter = new ViewPagerAdapter(this);
        mSlideViewPager.setAdapter(viewPagerAdapter);
        setUpIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListner);

        animationActivity();

    }



    public void setUpIndicator(int position){
        dots = new TextView[4];
        mDotLayout.removeAllViews();
        for(int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);
        }

        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));

    }

    ViewPager.OnPageChangeListener  viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpIndicator(position);
            if(position > 0 ){
                tv_back.setVisibility(View.VISIBLE);
            }else{
                tv_back.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private int getItems(int i ){
        return  mSlideViewPager.getCurrentItem() + i;
    }


    public void anhXa(){
        ellipse1 = findViewById(R.id.ellipse1);
        ellipse2 = findViewById(R.id.ellipse2);

        tv_back = findViewById(R.id.tv_back);
        tv_next = findViewById(R.id.tv_next);
        tv_skip = findViewById(R.id.tv_skip);

        mSlideViewPager = findViewById(R.id.slide_on_boarding);
        mDotLayout = findViewById(R.id.indicator_layout);
    };

    public void animationActivity() {
        anhXa();
        ellipse1.setAlpha(0f);
        ellipse1.setTranslationZ(-150);
        ellipse1.animate().alpha(1f).translationZBy(150).setDuration(2000);

        ellipse2.setAlpha(0f);
        ellipse2.setTranslationZ(-150);
        ellipse2.animate().alpha(1f).translationZBy(150).setDuration(2000);

        tv_back.setAlpha(0f);
        tv_back.setTranslationZ(-150);
        tv_back.animate().alpha(1f).translationZBy(150).setDuration(2000);

        tv_skip.setAlpha(0f);
        tv_skip.setTranslationZ(-150);
        tv_skip.animate().alpha(1f).translationZBy(150).setDuration(2000);

        tv_next.setAlpha(0f);
        tv_next.setTranslationZ(-150);
        tv_next.animate().alpha(1f).translationZBy(150).setDuration(1200);

        mSlideViewPager.setAlpha(0f);
        mSlideViewPager.setTranslationZ(-150);
        mSlideViewPager.animate().alpha(1f).translationZBy(150).setDuration(1200);

        mDotLayout.setAlpha(0f);
        mDotLayout.setTranslationZ(-150);
        mDotLayout.animate().alpha(1f).translationZBy(150).setDuration(1200);


    }

}