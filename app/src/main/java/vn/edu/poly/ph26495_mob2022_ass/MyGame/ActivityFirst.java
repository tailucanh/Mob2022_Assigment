package vn.edu.poly.ph26495_mob2022_ass.MyGame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
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

public class ActivityFirst extends AppCompatActivity {

    Chronometer chronometerCountDown;
    public static  int COUNT = 60;

    Button kiemtra,dauhang;
    RadioGroup groupDapAn;
    ImageView imgCaudo,imgA,imgB,imgC,imgD;
    GifImageView goiY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

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
                suggestions(ActivityFirst.this," \" Quy luật: Tại mỗi hàng, chấm tròn sẽ đứng yên, ô vuông sẽ tịnh tiến theo chiều kim đồng hồ, mỗi lần đi một góc phần tư. \"");
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
                        submitFalse();
                        break;
                    case R.id.rdo_dapanB:
                        submitFalse();
                        break;
                    case R.id.rdo_dapanC:
                        submitFalse();
                        break;
                    case R.id.rdo_dapanD:
                        submitTrue();
                        break;
                    default:
                        Toast.makeText(ActivityFirst.this, "Hãy chọn 1 đáp án !", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });

        dauhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ActivityFirst.this);
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
        groupDapAn = findViewById(R.id.rdg_caudo1);
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
        imgCaudo.setTranslationY(100);
        imgCaudo.animate().alpha(1f).translationYBy(-100).setStartDelay(50).setDuration(1700);

        imgA.setAlpha(0f);
        imgA.setTranslationY(110);
        imgA.animate().alpha(1f).translationYBy(-110).setStartDelay(50).setDuration(1700);

        imgB.setAlpha(0f);
        imgB.setTranslationY(110);
        imgB.animate().alpha(1f).translationYBy(-110).setStartDelay(50).setDuration(1700);
        imgC.setAlpha(0f);
        imgC.setTranslationY(110);
        imgC.animate().alpha(1f).translationYBy(-110).setStartDelay(50).setDuration(1700);
        imgD.setAlpha(0f);
        imgD.setTranslationY(110);
        imgD.animate().alpha(1f).translationYBy(-110).setStartDelay(50).setDuration(1700);

        dauhang.setAlpha(0f);
        dauhang.setTranslationY(110);
        dauhang.animate().alpha(1f).translationYBy(-110).setStartDelay(50).setDuration(1700);
        kiemtra.setAlpha(0f);
        kiemtra.setTranslationY(110);
        kiemtra.animate().alpha(1f).translationYBy(-110).setStartDelay(50).setDuration(1700);
    }



    public void submitTrue(){
         final  Dialog dialog = new Dialog(ActivityFirst.this);
         dialog.setContentView(R.layout.dialog_kiemtratrue);
         dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
         Button tieptuc = dialog.findViewById(R.id.bnt_tieptuc);
         tieptuc.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getBaseContext(),ActivitySecond.class);
                 Toast.makeText(ActivityFirst.this, "Câu số 2...", Toast.LENGTH_LONG).show();
                 startActivity(intent);
                 dialog.dismiss();
             }
         });
         dialog.show();
    }

    public void submitFalse(){
        final  Dialog dialog = new Dialog(ActivityFirst.this);
        dialog.setContentView(R.layout.dialog_kiemtrafalse);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button lamlai = dialog.findViewById(R.id.btn_lamlai);
        lamlai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void  suggestions(Context context, String content){
        final  AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.idea);
        String title = "<font color='yellow'>Your suggestion...</font>";
        builder.setTitle(Html.fromHtml(title));
        builder.setCancelable(true);
        builder.setMessage(content);

        builder.show();

    }



    public void chrCountDown(){
        if(COUNT == 10){
            Toast.makeText(ActivityFirst.this, "10s cuối cùng ! Nhanh tay nào ", Toast.LENGTH_SHORT).show();
        }

        if(COUNT == 0){
            dialogTimeOut(ActivityFirst.this,chronometerCountDown);
            Toast.makeText(ActivityFirst.this, "Đã hết thời gian! ", Toast.LENGTH_LONG).show();
            chronometerCountDown.stop();
        }
        chronometerCountDown.setText(COUNT +"");
        COUNT--;
    }


    public static void dialogTimeOut(Context context,  Chronometer chronometerCountDown){
        final  Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_sad);
        dialog.setCancelable(false);


        AlertDialog.Builder  builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_baseline_star_24);
        String title = "<font color='yellow' >Good luck...</font>";
        builder.setTitle(Html.fromHtml(title));
        builder.setCancelable(false);
        String title2 = "<font color='red'><big> <u> Go...</u> </big></font>";

        Button lamlai = dialog.findViewById(R.id.bnt_lamlai);
        lamlai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setNegativeButton(Html.fromHtml(title2), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.dismiss();

                AlertDialog sh = builder.create();
                sh.show();
                if(COUNT <= 0){
                    COUNT = 60;
                }
                chronometerCountDown.setText(COUNT +"");
                COUNT--;
                chronometerCountDown.start();
            }
        });

        dialog.show();

    }


}