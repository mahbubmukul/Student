package com.coderstrust.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

public class SignUpStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_sign_up_start);

        RelativeLayout signup = (RelativeLayout) findViewById(R.id.sign_up);
        RelativeLayout signin = (RelativeLayout) findViewById(R.id.sign_in);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                expand(bb);

                startActivity(new Intent(getApplicationContext(), SignupActivity.class));

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                expand(login);

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            }
        });
    }

}
