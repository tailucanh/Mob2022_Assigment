package vn.edu.poly.ph26495_mob2022_ass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Property;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.navigation.NavigationView;


import vn.edu.poly.ph26495_mob2022_ass.FRAGMENT.ChiFragment;
import vn.edu.poly.ph26495_mob2022_ass.FRAGMENT.GioithieuFragment;
import vn.edu.poly.ph26495_mob2022_ass.FRAGMENT.ThongKeFragment;
import vn.edu.poly.ph26495_mob2022_ass.FRAGMENT.ThuFragment;
import vn.edu.poly.ph26495_mob2022_ass.MyGame.GameActivityMain;


public class MainActivity extends AppCompatActivity{

    SharedPreferences preferences;
    MeowBottomNavigation bottomNavigation;
    DrawerLayout drawerLayout;
    LinearLayout container_activity;
    ImageView img_setting,ic_toolbar;
    NavigationView nav;
    TextView title_toolbar,bottom_title;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        //Tool bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


         img_setting.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 drawerLayout.openDrawer(GravityCompat.START);
             }
         });


         ic_toolbar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 dialogInfoUser();
             }
         });


         //Navigation  view
        nav.setItemIconTintList(null);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.info_user:
                        dialogInfoUser();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.game:
                        Intent intent = new Intent(getBaseContext(), GameActivityMain.class);
                        startActivity(intent);
                }
                return true;
            }
        });

        //Bottom Navigation
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_input));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_pay));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_list));


        getSupportFragmentManager().beginTransaction().replace(R.id.container_activity,new GioithieuFragment()).commit();
        //set bottom nav on show listener

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

            }
        });
        bottomNavigation.setCount(1,"1");
        bottomNavigation.setCount(2,"999+");
        bottomNavigation.setCount(3,"999+");
        bottomNavigation.setCount(4,"999+");
        bottomNavigation.setCountBackgroundColor(android.R.color.transparent);


        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 1:
                        title_toolbar.setText("Thông tin của tôi");
                        ic_toolbar.setImageResource(R.drawable.icon_tool_bar);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_activity,new GioithieuFragment()).commit();
                        bottomNavigation.setCount(1,null);
                        bottomNavigation.setCount(2,"999+");
                        bottomNavigation.setCount(3,"999+");
                        bottomNavigation.setCount(4,"999+");
                        break;
                    case 2:
                        title_toolbar.setText("Quản lí thu nhập");
                        ic_toolbar.setImageResource(R.drawable.icon_tool_bar_1);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_activity,new ThuFragment()).commit();
                        bottomNavigation.setCount(2,null);
                        bottomNavigation.setCount(1,"1");
                        bottomNavigation.setCount(4,"999+");
                        bottomNavigation.setCount(3,"999+");
                        break;
                    case 3:
                        title_toolbar.setText("Quản lí chi tiêu");
                        ic_toolbar.setImageResource(R.drawable.icon_tool_bar_2);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_activity,new ChiFragment()).commit();
                        bottomNavigation.setCount(3,null);
                        bottomNavigation.setCount(1,"1");
                        bottomNavigation.setCount(4,"999+");
                        bottomNavigation.setCount(2,"999+");
                        break;
                    case 4:
                        title_toolbar.setText("Thống kê");
                        ic_toolbar.setImageResource(R.drawable.icon_tool_bar_3);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_activity,new ThongKeFragment()).commit();
                        bottomNavigation.setCount(4,null);
                        bottomNavigation.setCount(1,"1");
                        bottomNavigation.setCount(3,"999+");
                        bottomNavigation.setCount(2,"999+");

                        break;
                }
            }
        });

        bottomNavigation.show(1,true);
        //---------------------------------------------------------------------------
        // Animation text
        bottom_title = findViewById(R.id.bottom_title);
         oscillateDemo(bottom_title,this);
    }



    void anhXa(){
        toolbar = findViewById(R.id.toolbar_activity);
        drawerLayout = findViewById(R.id.drawer_layout);
        img_setting = findViewById(R.id.img_setting);
        ic_toolbar = findViewById(R.id.ic_toolbar);
        title_toolbar = findViewById(R.id.toolbar_title);
        nav = findViewById(R.id.navigation_View);
        container_activity = findViewById(R.id.container_activity);

        bottomNavigation = findViewById(R.id.bottom_nav);

    }

    public  void animateTextViewColors(TextView textView, Integer colorTo) {
        final Property<TextView, Integer> property = new Property<TextView, Integer>(int.class, "textColor") {
            @Override
            public Integer get(TextView object) {
                return object.getCurrentTextColor();
            }
            @Override
            public void set(TextView object, Integer value) {
                object.setTextColor(value);
            }
        };

        final ObjectAnimator animator = ObjectAnimator.ofInt(textView, property, colorTo);
        animator.setDuration(8533L);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setInterpolator(new DecelerateInterpolator(2));
        animator.start();
    }

    public void oscillateDemo(final TextView textView, Context context) {
        final int whiteColor = ContextCompat.getColor(context, R.color.color_animate);
        final int greenColor = ContextCompat.getColor(context, R.color.color_animate_2);

        final int counter = 100;

        Thread oscillateThread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < counter; i++) {

                    final int fadeToColor = (i % 2 == 0)
                            ? greenColor
                            : whiteColor;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            animateTextViewColors(textView, fadeToColor);
                        }
                    });

                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException iEx) {}
                }
            }
        };

        oscillateThread.start();
    }



    public void dialogInfoUser(){
        final  Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_info_user);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnSignOut = dialog.findViewById(R.id.signout);
        TextView userName = dialog.findViewById(R.id.username);
        TextView passWord = dialog.findViewById(R.id.pass);

        preferences = getSharedPreferences("form_login", Context.MODE_PRIVATE);
        String user = preferences.getString("user", "");
        String pass = preferences.getString("pass", "");
        userName.setText(user);
        passWord.setText(pass);


        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),LoginActivity.class);
                Toast.makeText(MainActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
        dialog.show();
    }


}