package vn.edu.poly.ph26495_mob2022_ass.MyGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.poly.ph26495_mob2022_ass.R;

public class GameActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        Button btnStart = findViewById(R.id.btn_start);
        TextView title = findViewById(R.id.tv_title);
        ImageView img = findViewById(R.id.image_title);
        // Animation
        btnStart.setAlpha(0f);
        btnStart.setTranslationY(50);
        btnStart.animate().alpha(1f).translationYBy(-50).setStartDelay(300).setDuration(2500);
        title.setAlpha(0f);
        title.setTranslationY(50);
        title.animate().alpha(1f).translationYBy(-50).setStartDelay(200).setDuration(2700);
        img.animate().rotation(360).setDuration(3500);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),ActivityFirst.class);
                Toast.makeText(GameActivityMain.this, "Chào mừng đến với TEST IQ", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


    }


}