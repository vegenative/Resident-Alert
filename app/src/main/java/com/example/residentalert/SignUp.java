package com.example.residentalert;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class SignUp extends AppCompatActivity {

    private Button signUpBtn, checkBtn;
    private EditText id_et,name_et, surname_et, email_et, password_et, cnfPassword_et, tel_et, street_et, blockNumber_et, city_et, postalCode_et;
    private ScrollView scrollView;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //button
        signUpBtn = (Button) findViewById(R.id.signup_button);
        checkBtn = (Button) findViewById(R.id.login_btn);
        //edit text
        id_et = (EditText) findViewById(R.id.email_et);
        name_et = (EditText) findViewById(R.id.name_et);
        surname_et = (EditText) findViewById(R.id.surname_et);
        email_et = (EditText) findViewById(R.id.email_et);
        password_et = (EditText) findViewById(R.id.password_et);
        tel_et = (EditText) findViewById(R.id.phone_et);
        street_et = (EditText) findViewById(R.id.street_et);
        blockNumber_et = (EditText) findViewById(R.id.blockNumber_et);
        city_et = (EditText) findViewById(R.id.city_et);
        postalCode_et = (EditText) findViewById(R.id.postalCode_et);
        //scroll view
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        //layout
        linearLayout = (LinearLayout) findViewById(R.id.lign_ll);


        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_text = id_et.getText().toString().trim();
                String password_text = password_et.getText().toString().trim();
                String cnfPassword_text = cnfPassword_et.getText().toString().trim();
                checkBtn.setElevation(8);

                if (TextUtils.isEmpty(id_text)){
                    id_et.setError("Pole nie może być puste");
                    return;
                }
                if (TextUtils.isEmpty(password_text)){
                    password_et.setError("Pole nie może być puste");
                    return;
                }
                else if(password_text.length() < 6 ){
                    password_et.setError("Hasło musi posiadać co najmniej 6 znaków");
                    return;
                }
                else if(cnfPassword_text.equals(password_text)){
                    linearLayout.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }
                else{
                    cnfPassword_et.setError("Hasła muszą być identyczne");
                }
            }
        });




    }

}