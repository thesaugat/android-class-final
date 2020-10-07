package com.thesaugat.androidclass.activity;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.thesaugat.androidclass.R;
import com.thesaugat.androidclass.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainFrameContainer, loginFragment).commit();

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = emailField.getText().toString();
//                String password = passwordField.getText().toString();
//                openHome();
//
//                if (email.isEmpty() && password.isEmpty()) {
//                    Toast.makeText(LoginActivity.this, "Please Provide Email and Password", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    if (email.equals("hello@gmail.com") && password.equals("password123")) {
//                        Toast.makeText(LoginActivity.this, "Login SUccessfull!!!!", Toast.LENGTH_SHORT).show();
//                       openHome();
//
//
//                    } else {
//                        Toast.makeText(LoginActivity.this, "Invalid Credentials!!!", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                }
    }

//    void openHome(){
//        Intent splashIntent = new Intent(LoginActivity.this, HomeActivity.class);
//        startActivity(splashIntent);
//        finish();
//    }
}
