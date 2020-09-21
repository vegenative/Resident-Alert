package com.example.residentalert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private Button login_btn;
    private EditText email_et, password_et;
    private TextView signUp_tv,resetPassword_tv;
    private LinearLayout forgotPasword_ll;


    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//editText
        email_et = (EditText) findViewById(R.id.email_et);
        password_et = (EditText) findViewById(R.id.password_et);
//Button
        login_btn = (Button) findViewById(R.id.login_btn);
//TextView
        signUp_tv = (TextView) findViewById(R.id.noAccount_tv);
        resetPassword_tv = (TextView) findViewById(R.id.resetPassword_tv);
//LinearLayout
        forgotPasword_ll = (LinearLayout) findViewById(R.id.forgotPassword_ll);

//Firebase
        fAuth = FirebaseAuth.getInstance();

////Onclick/////
//login btn
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_et.getText().toString().trim();
                String password = password_et.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    email_et.setError("Pole nie może być puste ");
                    email_et.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    email_et.setError("Pole musi być adresem e-mail");
                    email_et.requestFocus();
                }
                else if(TextUtils.isEmpty(password)){
                    password_et.setError("Pole nie może być puste");
                    email_et.requestFocus();
                }
                else if(password.length()<6){
                    password_et.setError("Hasło musi posiadać co najmniej 6 znaków");
                    email_et.requestFocus();
                    forgotPasword_ll.setVisibility(View.VISIBLE);
                }
                else if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(Login.this, "Uzupełnij pola", Toast.LENGTH_SHORT).show();
                }
                else {
                    LoginUser(email,password);
                }
            }
        });

//SignUp
        signUp_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUp.class));
                finish();
            }
        });
//reset password
        resetPassword_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

    }

////OnStart////
    @Override
    protected void onStart() {
        super.onStart();
        forgotPasword_ll.setVisibility(View.GONE);

        //if user is already login
        FirebaseUser currentUser = fAuth.getCurrentUser();

    }

////methods///
//Login
    private void LoginUser(String email, String password) {
        fAuth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(Login.this,"Witaj !",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, Menu.class));
                        finish();
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this,"Nie ma takiego konta. \n Zresetuj hasło lub stwórz nowe konto",
                        Toast.LENGTH_LONG).show();
                forgotPasword_ll.setVisibility(View.VISIBLE);
            }
        });
    }
//Reset password
private void resetPassword(){
    String email = email_et.getText().toString().trim();
    if (TextUtils.isEmpty(email)){
        email_et.setError("Pole nie może być puste");
    }
    // mail body doesn't fit
    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        email_et.setError("Pole musi być adresem E-mail");
    }
    else{
        fAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Login.this, "Message sent", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}


}