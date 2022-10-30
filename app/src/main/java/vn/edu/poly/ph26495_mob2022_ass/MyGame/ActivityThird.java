package vn.edu.poly.ph26495_mob2022_ass.MyGame;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;
import vn.edu.poly.ph26495_mob2022_ass.R;

public class ActivityThird extends AppCompatActivity {
    Chronometer chronometerCountDown;

    Button kiemtra,dauhang;
    RadioGroup groupDapAn;
    ImageView imgCaudo,imgA,imgB,imgC,imgD;
    GifImageView goiY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

       anhXa();

        chronometerCountDown.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                chrCountDown();
            }
        });
        chronometerCountDown.start();


        goiY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityFirst.suggestions(ActivityThird.this," \" Quy luật: trong mỗi hàng, hai hình chồng lên nhau, triệt tiêu phần trùng nhau , chỉ giữ lại phần riêng biệt sẽ tạo thành hình mới. \"");
            }
        });



        //Animation
      animationActivity();


        kiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dapAn_Checked =  groupDapAn.getCheckedRadioButtonId();
                switch (dapAn_Checked){
                    case R.id.rdo_dapanA:
                        submitTrue();
                        break;
                    case R.id.rdo_dapanB:
                        submitFalse();
                        break;
                    case R.id.rdo_dapanC:
                        submitFalse();
                        break;
                    case R.id.rdo_dapanD:
                        submitFalse();
                        break;
                    default:
                        Toast.makeText(ActivityThird.this, "Hãy chọn 1 đáp án !", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        dauhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ActivityThird.this);
                dialog.setContentView(R.layout.dialog_dauhang);
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }


    void anhXa(){
        kiemtra = findViewById(R.id.btn_kiemtra);
        groupDapAn = findViewById(R.id.rdg_caudo3);
        dauhang = findViewById(R.id.btn_dauhang);
        imgCaudo = findViewById(R.id.img_caudo);
        imgA = findViewById(R.id.img_a);
        imgB= findViewById(R.id.img_b);
        imgC = findViewById(R.id.img_c);
        imgD= findViewById(R.id.img_d);
        goiY = findViewById(R.id.goi_y);
        chronometerCountDown= findViewById(R.id.simpleChronometer);
    }
    void animationActivity(){
        anhXa();
        imgCaudo.setAlpha(0f);
        imgCaudo.setTranslationX(100);
        imgCaudo.animate().alpha(1f).translationXBy(-100).setStartDelay(50).setDuration(1700);
        imgA.setAlpha(0f);
        imgA.setTranslationX(110);
        imgA.animate().alpha(1f).translationXBy(-110).setStartDelay(50).setDuration(1700);
        imgB.setAlpha(0f);
        imgB.setTranslationX(110);
        imgB.animate().alpha(1f).translationXBy(-110).setStartDelay(50).setDuration(1700);
        imgC.setAlpha(0f);
        imgC.setTranslationX(110);
        imgC.animate().alpha(1f).translationXBy(-110).setStartDelay(50).setDuration(1700);
        imgD.setAlpha(0f);
        imgD.setTranslationX(110);
        imgD.animate().alpha(1f).translationXBy(-110).setStartDelay(50).setDuration(1700);

        dauhang.setAlpha(0f);
        dauhang.setTranslationX(110);
        dauhang.animate().alpha(1f).translationXBy(-110).setStartDelay(50).setDuration(1700);
        kiemtra.setAlpha(0f);
        kiemtra.setTranslationX(110);
        kiemtra.animate().alpha(1f).translationXBy(-110).setStartDelay(50).setDuration(1700);

    }


    public void submitTrue(){
        final  Dialog dialog = new Dialog(ActivityThird.this);
        dialog.setContentView(R.layout.dialog_kiemtratrue);
        dialog.setCancelable(false);

        Button tieptuc = dialog.findViewById(R.id.bnt_tieptuc);
        tieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),ActivityFour.class);
                Toast.makeText(ActivityThird.this, "Câu số 4...", Toast.LENGTH_LONG).show();
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void submitFalse(){
        final  Dialog dialog = new Dialog(ActivityThird.this);
        dialog.setContentView(R.layout.dialog_kiemtrafalse);
        dialog.setCancelable(false);

        Button lamlai = dialog.findViewById(R.id.btn_lamlai);
        lamlai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void chrCountDown(){
        if(ActivityFirst.COUNT == 10){
            Toast.makeText(ActivityThird.this, "10s cuối cùng ! Nhanh tay nào ", Toast.LENGTH_SHORT).show();
        }
        if(ActivityFirst.COUNT == 0){
            ActivityFirst.dialogTimeOut(ActivityThird.this,chronometerCountDown);
            Toast.makeText(ActivityThird.this, "Đã hết thời gian! ", Toast.LENGTH_LONG).show();
            chronometerCountDown.stop();

        }
        chronometerCountDown.setText(ActivityFirst.COUNT +"");
        ActivityFirst.COUNT--;
    }
}