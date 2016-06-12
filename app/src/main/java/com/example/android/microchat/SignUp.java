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

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class SignUp extends AppCompatActivity {
    Firebase mRef;
    EditText mUsername,mPassword,mEmail;
    Button sign_up_button;
    TextView mLogin;
    ProgressBar sign_up_pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername = (EditText)findViewById(R.id.sign_up_username);
        mPassword = (EditText)findViewById(R.id.sign_up_password);
        mEmail = (EditText)findViewById(R.id.sign_up_email);
        sign_up_button = (Button)findViewById(R.id.sign_up_button);
        mLogin = (TextView)findViewById(R.id.sign_up_login_text);
        sign_up_pg = (ProgressBar)findViewById(R.id.sign_up_pg);
        mRef = new Firebase(Constants.FIREBASE_URL);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_page();
            }
        });

        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up_pg.setVisibility(View.VISIBLE);
                craeteUser();
            }
        });

    }

    private void craeteUser() {

        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        String email = mEmail.getText().toString();
        if (!username.equals("")&&!password.equals("")){

            mRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                @Override
                public void onSuccess(Map<String, Object> stringObjectMap) {
                    sign_up_pg.setVisibility(View.INVISIBLE);
                    login_page();
                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                    builder.setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Sign Up error")
                            .setMessage(firebaseError.getMessage())
                            .setPositiveButton("OK",null);

                    AlertDialog alert = builder.create();
                    alert.show();
                    sign_up_pg.setVisibility(View.INVISIBLE);

                }
            });

        }

    }

    private void login_page() {
        Intent intent = new Intent(SignUp.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
