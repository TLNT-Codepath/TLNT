package com.example.tlnt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    public static final String TAG = "SignupActivity";
    private EditText etSignupUsername;
    private EditText etSignupPassword;
    private Button btnSignupSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etSignupUsername = findViewById(R.id.etSignupUsername);
        etSignupPassword = findViewById(R.id.etSignupPassword);
        btnSignupSignup = findViewById(R.id.btnSignupSignup);

        btnSignupSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

    }

    private void signup() {
        ParseUser user = new ParseUser();
        user.setUsername(etSignupUsername.getText().toString());
        user.setPassword(etSignupPassword.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error while signing up", e);
                    return;
                }
                goMainActivity();
            }
        });

    }

    private void goMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}