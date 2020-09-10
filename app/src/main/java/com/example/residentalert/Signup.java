package com.example.residentalert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class Signup extends AppCompatActivity {

    private Button signUpBtn;
    private EditText id_et,name_et, surname_et, email_et, password_et, cnfPassword_et, tel_et, street_et, blockNumber_et, city_et, postalCode_et;
    private ScrollView scrollView;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUpBtn = (Button) findViewById(R.id.signup_button);

        id_et = (EditText) findViewById(R.id.id_et);
        name_et = (EditText) findViewById(R.id.name_et);
        surname_et = (EditText) findViewById(R.id.surname_et);
        email_et = (EditText) findViewById(R.id.email_et);
        password_et = (EditText) findViewById(R.id.password_et);
        cnfPassword_et = (EditText) findViewById(R.id.passwordConfirm_et);
        tel_et = (EditText) findViewById(R.id.phone_et);
        street_et = (EditText) findViewById(R.id.street_et);
        blockNumber_et = (EditText) findViewById(R.id.blockNumber_et);
        city_et = (EditText) findViewById(R.id.city_et);
        postalCode_et = (EditText) findViewById(R.id.postalCode_et);

        scrollView = (ScrollView) findViewById(R.id.scrollView);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);


    }

    @Override
    protected void onStart() {
        super.onStart();
        scrollView.setVisibility(View.INVISIBLE);


    }
}