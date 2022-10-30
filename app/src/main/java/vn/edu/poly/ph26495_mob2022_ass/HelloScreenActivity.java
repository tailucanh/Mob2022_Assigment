package vn.edu.poly.ph26495_mob2022_ass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class HelloScreenActivity extends AppCompatActivity {

    GifImageView astrounaut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_screen);

        anhXa();
        animationActivity();

        final  Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(),OnboardingScreen.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }




    public void animationActivity(){
        anhXa();


        astrounaut.setAlpha(0f);
        astrounaut.setTranslationY(-200);
        astrounaut.animate().alpha(1f).translationYBy(200).setDuration(2000);


    }

    public void anhXa(){
        astrounaut = findViewById(R.id.gif_astrounaut);

    }


}