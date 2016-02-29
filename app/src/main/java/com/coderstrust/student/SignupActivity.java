package com.coderstrust.student;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coderstrust.student.jsonparser.JSON;
import com.coderstrust.student.jsonparser.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Bind;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @Bind(R.id.input_fname) EditText _fnameText;
    @Bind(R.id.input_lname) EditText _lnameText;
    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.input_mobile) EditText _mobileText;
    @Bind(R.id.btn_signup) RelativeLayout _signupButton;


    String fname;
    String lname;
    String email;
    String password;
    String cpassword;
    String mobile;

    ProgressDialog progressDialog;

    String data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });


    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");


        fname = _fnameText.getText().toString();
        lname = _lnameText.getText().toString();
        email = _emailText.getText().toString();
        password = _passwordText.getText().toString();
        cpassword = _passwordText.getText().toString();
        mobile = _mobileText.getText().toString();

        new fetchSignup().execute();

        // TODO: Implement your own signup logic here.

//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onSignupSuccess or onSignupFailed
//                        // depending on success
//                        onSignupSuccess();
//                        // onSignupFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Toast.makeText(getBaseContext(), "Signup Success! Check your mail.", Toast.LENGTH_LONG).show();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String fname = _fnameText.getText().toString();
        String lname = _lnameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String mobile = _mobileText.getText().toString();

        if (fname.isEmpty() || fname.length() < 3) {
            _fnameText.setError("at least 3 characters");
            valid = false;
        } else {
            _fnameText.setError(null);
        }

        if (lname.isEmpty() || lname.length() < 3) {
            _lnameText.setError("at least 3 characters");
            valid = false;
        } else {
            _lnameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() <8 ) {
            _passwordText.setError("at least 8 characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length() < 10 || mobile.length() > 15) {
            _mobileText.setError("Invalid Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        return valid;
    }


    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
//        moveTaskToBack(true);

        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }


    public class fetchSignup extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array


            data = new JSON().parseSignup("http://test.coderstrust.com/api/students.json", "student[email]=" + email + "&student[password]=" + password + "&student[password_confirmation]=" + password
                    + "&student[first_name]=" + fname + "&student[last_name]=" + lname + "&student[mobile]=" + mobile, SignupActivity.this);


            System.out.println("FUCK MMMKKK 1> "+ data);
            return null;
        }

        @Override
        protected void onPostExecute(Void results) {

            if(data.startsWith("success"))
            {
                System.out.println("FUCK MKKK 2> " + data);
                Toast.makeText(getBaseContext(), "Registration Success! Please check you mail and confirm.", Toast.LENGTH_LONG).show();
                progressDialog.cancel();
            }
            else if(data.startsWith("error")) {
                Toast.makeText(getBaseContext(), "Email already exist!.", Toast.LENGTH_LONG).show();
                progressDialog.cancel();
            }
            else
            {
                Toast.makeText(getBaseContext(), "Connectivity Error! Check your internet connection.", Toast.LENGTH_LONG).show();
                progressDialog.cancel();
            }
        }

    }


}