package com.example.android.microchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class Login extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mLogin_Button;
    ProgressBar mLogin_pg;
    TextView mLogin_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = (EditText)findViewById(R.id.login_email);
        mPassword = (EditText)findViewById(R.id.login_password);
        mLogin_Button = (Button)findViewById(R.id.login_button);
        mLogin_sign_up = (TextView)findViewById(R.id.login_sign_up_text);
        mLogin_pg = (ProgressBar)findViewById(R.id.login_pg);
        mLogin_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SignUp.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        mLogin_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLogin_pg.setVisibility(View.VISIBLE);
                    validateUser();
            }
        });

    }

    private void validateUser() {

        Constants.mRef.authWithPassword(mEmail.getText().toString(), mPassword.getText().toString(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                mLogin_pg.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(Login.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Login Error")
                        .setMessage(firebaseError.getMessage())
                        .setPositiveButton("OK",null);
                AlertDialog alert = builder.create();
                builder.show();
                mLogin_pg.setVisibility(View.INVISIBLE);
            }
        });

    }
}
