package com.coderstrust.student;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.coderstrust.student.DataStruct.Login;
import com.coderstrust.student.jsonparser.JSON;
import com.coderstrust.student.jsonparser.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Bind;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) RelativeLayout _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;

    ProgressDialog progressDialog;
    String email;
    String password;
    String data;
    String profile_data;

    public static final String MyPREFERENCES = "CT_Prefs" ;
    SharedPreferences sharedpreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");


        email = _emailText.getText().toString();
        password = _passwordText.getText().toString();

        new fetchLogin().execute();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
//        moveTaskToBack(true);

        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }


    public class fetchLogin extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            data = new JSON().parseLogin("http://test.coderstrust.com/api/students/authenticate.json", "email="+email+"&password="+password,LoginActivity.this);
            System.out.println("FUCK MMMKKK 1> "+ data);
            return null;
        }

        @Override
        protected void onPostExecute(Void results) {

            if(data.startsWith("success"))
            {
                System.out.println("FUCK MKKK 2> " + data);
//                _loginButton.setEnabled(true);
//                progressDialog.cancel();
//                String rrx =new JSONParser().requestGETHEAD("http://test.coderstrust.com/api/students/9064/personal","");
//
//                System.out.println("FUCK MKKK> "+ rrx);
//                new fetchProfile().execute();

                String [] da = data.split(" _ ");
                fetchProf(da[1],da[2]);


            }
            else if(data.startsWith("error")) {
                Toast.makeText(getBaseContext(), "Username or Password is invalid.", Toast.LENGTH_LONG).show();
                _loginButton.setEnabled(true);
                progressDialog.cancel();
            }
            else
            {
                Toast.makeText(getBaseContext(), "Connectivity Error! Check your internet connection.", Toast.LENGTH_LONG).show();
                _loginButton.setEnabled(true);
                progressDialog.cancel();
            }
        }

    }


    public void fetchProf(final String stId, final String token){

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                profile_data =new JSONParser().getContents("http://test.coderstrust.com/api/students/"+stId+"/personal",token);
                System.out.println("FUCK MKKKL> "+ profile_data);

                return null;
            }

            @Override
            protected void onPostExecute(Void results) {

                if(profile_data.startsWith("Internet"))
                {
                    Toast.makeText(getBaseContext(), "Cunnectivity Error! Check your internet connection.", Toast.LENGTH_LONG).show();
                    _loginButton.setEnabled(true);
                    progressDialog.cancel();
                }
                else{
                    System.out.println("FUCK MKKKL> " + profile_data);

                    try {
                        JSONObject jObj = new JSONObject(profile_data);
                        String id = "", address = "", age="", city="", date_of_birth="", contact_email="", gender="", marital_status="", mobile="",
                                first_name="", middle_name="", last_name="", country_name="", currency="", language_name="", trust_score="", avatar="", errorss="";

                        try {
                            errorss = jObj.getString("error");
                        } catch (Exception e) {
                        }

                        if(errorss.equals("")) {
                            try {
                                id = jObj.getString("id");
                            } catch (Exception e) {
                            }
                            try {
                                address = jObj.getString("address");
                            } catch (Exception e) {
                            }
                            try {
                                age = jObj.getString("age");
                            } catch (Exception e) {
                            }
                            try {
                                city = jObj.getString("city");
                            } catch (Exception e) {
                            }
                            try {
                                date_of_birth = jObj.getString("date_of_birth");
                            } catch (Exception e) {
                            }
                            try {
                                contact_email = jObj.getString("contact_email");
                            } catch (Exception e) {
                            }
                            try {
                                gender = jObj.getString("gender");
                            } catch (Exception e) {
                            }
                            try {
                                marital_status = jObj.getString("marital_status");
                            } catch (Exception e) {
                            }
                            try {
                                mobile = jObj.getString("mobile");
                            } catch (Exception e) {
                            }
                            try {
                                first_name = jObj.getString("first_name");
                            } catch (Exception e) {
                            }
                            try {
                                middle_name = jObj.getString("middle_name");
                            } catch (Exception e) {
                            }
                            try {
                                last_name = jObj.getString("last_name");
                            } catch (Exception e) {
                            }
                            try {
                                trust_score = jObj.getString("trust_score");
                            } catch (Exception e) {
                            }
                            try {
                                avatar = jObj.getString("avatar");
                            } catch (Exception e) {
                            }

                            try {
                                String country = jObj.getString("country");
                                JSONObject jObj1 = new JSONObject(country);

                                try {
                                    country_name = jObj1.getString("name");
                                } catch (Exception e) {
                                }
                                try {
                                    currency = jObj1.getString("currency");
                                } catch (Exception e) {
                                }
                                try {
                                    language_name = jObj1.getString("language_name");
                                } catch (Exception e) {
                                }
                            } catch (Exception e) {
                            }

                            SharedPreferences.Editor editor = sharedpreferences.edit();

                            editor.putString("Id", id);
                            editor.putString("Token", token);
                            editor.putString("Address",address);
                            editor.putString("Age", age);
                            editor.putString("City", city);
                            editor.putString("DOB", date_of_birth);
                            editor.putString("Email", contact_email);
                            editor.putString("Gender", gender);
                            editor.putString("Marital_Status", marital_status);
                            editor.putString("Mobile", mobile);
                            editor.putString("Name", first_name+" "+middle_name+" "+last_name);
                            editor.putString("Country", country_name);
                            editor.putString("Currency", currency);
                            editor.putString("Language", language_name);
                            editor.putString("Trust_Score",trust_score);
                            editor.putString("Profile_Pic", avatar);
                            editor.commit();

                            startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                            _loginButton.setEnabled(true);
                            progressDialog.cancel();
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),errorss, Toast.LENGTH_LONG).show();
                            _loginButton.setEnabled(true);
                            progressDialog.cancel();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.execute();
    }

    //public class fetchProfile extends AsyncTask<Void, Void, Void>

}
