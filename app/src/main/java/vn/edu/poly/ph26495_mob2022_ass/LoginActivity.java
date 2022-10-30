package vn.edu.poly.ph26495_mob2022_ass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

import pl.droidsonroids.gif.GifImageView;

public class LoginActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    SharedPreferences preferences;
    TextView tv_register,title1,content1,content2,content3;
    Button btn_login,btnOr ;
    EditText ed_user,ed_pass;
    ImageView img_title;
    View line,layoutLogin,v_background;
    ImageButton google_btn,facebook_btn ,github_btn;
    TextInputLayout layout_name,layout_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhXa();
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogRegister();
            }
        });

        preferences = getSharedPreferences("form_login", Context.MODE_PRIVATE);

        String checkUser = preferences.getString("user", "");
        String checkPass = preferences.getString("pass", "");
        if(checkPass.length() > 0){
            layout_name.getEditText().setText(checkUser);
            layout_pass.getEditText().setText(checkPass);
        }



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
                String user = layout_name.getEditText().getText().toString();
                String pass = layout_pass.getEditText().getText().toString();
                if(user.equals("")){
                    layout_name.setError("Hãy nhập tên !");
                }else if(pass.equals("")){
                    layout_pass.setError("Hãy nhập password !");
                }else if(!user.matches(checkUser)){
                    layout_name.setError("Sai tài khoản !");
                }else if(!pass.matches(checkPass)){
                    layout_pass.setError("Sai password !");
                }else{
                    layout_name.setError("");
                    layout_pass.setError("");
                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        //Animation
        animationActivity();



        //----------------------------Liên kết google----------------------
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            navigateToSecondActivity();
        }

        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        facebook_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogWarning(getBaseContext(),"https://www.facebook.com/");
            }
        });
        github_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogWarning(getBaseContext(),"https://github.com/");
            }
        });
        //-------------------------------------------------------



    }

    //-------------------- liên kết google-------------------------------
    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }
    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Đã hủy!", Toast.LENGTH_SHORT).show();
            }

        }
    }
    //-------------------------------------------

    public void dialogRegister(){
        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.layout_register);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView tv_title = dialog.findViewById(R.id.tv_title);
        TextView tv_content = dialog.findViewById(R.id.tv_content);
        GifImageView img_line = dialog.findViewById(R.id.img_lineAnimate);
        EditText ed_surName = dialog.findViewById(R.id.ed_surname);
        EditText ed_firstName = dialog.findViewById(R.id.ed_firstName);
        EditText ed_phoneEmail = dialog.findViewById(R.id.ed_phone_email);
        EditText ed_user = dialog.findViewById(R.id.ed_userName);
        EditText ed_pass = dialog.findViewById(R.id.ed_pass);

        Button btn_register = dialog.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                String surName = ed_surName.getText().toString();
                String firstName = ed_firstName.getText().toString();
                String phoneEmail = ed_phoneEmail.getText().toString();
                String user = ed_user.getText().toString();
                String pass = ed_pass.getText().toString();

                if(surName.equals("")){
                    Toast.makeText(LoginActivity.this, "Hãy nhập họ tên !", Toast.LENGTH_SHORT).show();
                }else if(firstName.equals("")){
                    Toast.makeText(LoginActivity.this, "Hãy nhập họ tên !", Toast.LENGTH_SHORT).show();
                }
                else if(phoneEmail.equals("")){
                    Toast.makeText(LoginActivity.this, "Hãy nhập số điện thoại hoặc email!", Toast.LENGTH_SHORT).show();

                }
                else if(user.equals("")){
                    Toast.makeText(LoginActivity.this, "Hãy nhập tên tài khoản!", Toast.LENGTH_SHORT).show();

                }
                else if(pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Hãy nhập password !", Toast.LENGTH_SHORT).show();

                }else {
                    editor.putString("user",user);
                    editor.putString("pass",pass);
                    editor.commit();
                    Toast.makeText(LoginActivity.this, "Đăng kí thành công. Hãy đăng nhập lại tài khoản!", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }

            }
        });

        //Animation
        ed_surName.setAlpha(0f);
        ed_surName.setTranslationX(-150);
        ed_surName.animate().alpha(1f).translationXBy(150).setDuration(2000);

        ed_firstName.setAlpha(0f);
        ed_firstName.setTranslationX(150);
        ed_firstName.animate().alpha(1f).translationXBy(-150).setDuration(2000);

        ed_phoneEmail.setAlpha(0f);
        ed_phoneEmail.setTranslationX(-150);
        ed_phoneEmail.animate().alpha(1f).translationXBy(150).setDuration(2000);

        ed_user.setAlpha(0f);
        ed_user.setTranslationX(150);
        ed_user.animate().alpha(1f).translationXBy(-150).setDuration(2000);

        ed_pass.setAlpha(0f);
        ed_pass.setTranslationX(-150);
        ed_pass.animate().alpha(1f).translationXBy(150).setDuration(2000);

        tv_title.setAlpha(0f);
        tv_title.setTranslationY(-100);
        tv_title.animate().alpha(1f).translationYBy(100).setDuration(2000);

        tv_content.setAlpha(0f);
        tv_content.setTranslationY(-100);
        tv_content.animate().alpha(1f).translationYBy(100).setDuration(2000);

        img_line.setAlpha(0f);
        img_line.setTranslationY(-100);
        img_line.animate().alpha(1f).translationYBy(100).setDuration(2000);

        btn_register.setAlpha(0f);
        btn_register.setTranslationY(100);
        btn_register.animate().alpha(1f).translationYBy(-100).setDuration(2000);

        dialog.show();
    }


    public void animationActivity(){
        anhXa();
        img_title.setAlpha(0f);
        img_title.setTranslationY(-100);
        img_title.animate().alpha(1f).translationYBy(100).setStartDelay(50).setDuration(2000);

        content2.setAlpha(0f);
        content2.setTranslationY(-100);
        content2.animate().alpha(1f).translationYBy(100).setStartDelay(50).setDuration(2000);


        tv_register.setAlpha(0f);
        tv_register.setTranslationX(-100);
        tv_register.animate().alpha(1f).translationXBy(100).setDuration(2500);

        content3.setAlpha(0f);
        content3.setTranslationX(100);
        content3.animate().alpha(1f).translationXBy(-100).setDuration(2500);

        layoutLogin.setAlpha(0f);
        layoutLogin.setTranslationZ(-100);
        layoutLogin.animate().alpha(1f).translationZBy(100).setStartDelay(25).setDuration(2500);

        v_background.setAlpha(0f);
        v_background.setTranslationZ(-200);
        v_background.animate().alpha(1f).translationZBy(200).setDuration(2500);

        title1.setAlpha(0f);
        title1.setTranslationZ(-90);
        title1.animate().alpha(1f).translationZBy(90).setStartDelay(25).setDuration(2000);

        content1.setAlpha(0f);
        content1.setTranslationZ(-90);
        content1.animate().alpha(1f).translationZBy(90).setStartDelay(25).setDuration(2000);

        facebook_btn.setAlpha(0f);
        facebook_btn.setTranslationZ(-90);
        facebook_btn.animate().alpha(1f).translationZBy(90).setStartDelay(25).setDuration(2000);

        github_btn.setAlpha(0f);
        github_btn.setTranslationZ(-90);
        github_btn.animate().alpha(1f).translationZBy(90).setStartDelay(25).setDuration(2000);

        google_btn.setAlpha(0f);
        google_btn.setTranslationZ(-90);
        google_btn.animate().alpha(1f).translationZBy(90).setStartDelay(25).setDuration(2000);

        line.setAlpha(0f);
        line.setTranslationZ(-90);
        line.animate().alpha(1f).translationZBy(90).setStartDelay(25).setDuration(2000);

        btnOr.setAlpha(0f);
        btnOr.setTranslationZ(-90);
        btnOr.animate().alpha(1f).translationZBy(90).setStartDelay(25).setDuration(2000);


    }
    void ShowDialogWarning(final Context context,String url){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("Warning !!!");
        builder.setMessage("Bạn sắp chuyển hướng đến 1 trang web mới. Hãy xác nhận !");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clickText(context,url);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"Đã hủy !",Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        AlertDialog sh = builder.create();
        sh.show();
    }
    void clickText(Context context, String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        Toast.makeText(context,"Điều hướng thành công !",Toast.LENGTH_SHORT).show();
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }


    public void anhXa(){
        tv_register = findViewById(R.id.tv_register);
        btn_login = findViewById(R.id.btn_login);
        ed_user = findViewById(R.id.ed_User);
        ed_pass = findViewById(R.id.ed_pass);
        layout_name = findViewById(R.id.input_layout_user_name);
        layout_pass = findViewById(R.id.input_layout_pass);
        title1 = findViewById(R.id.tv_title);
        content1 = findViewById(R.id.tv_content);
        img_title = findViewById(R.id.img_title);
        content2 = findViewById(R.id.tv_conten2);
        content3 = findViewById(R.id.tv_content3);
        btnOr = findViewById(R.id.btn_or);
        line = findViewById(R.id.v_line);
        layoutLogin = findViewById(R.id.layout_login);
        v_background = findViewById(R.id.v_background);
        google_btn = findViewById(R.id.google_btn);
        facebook_btn = findViewById(R.id.facebook_btn);
        github_btn = findViewById(R.id.github_btn);
    }


}