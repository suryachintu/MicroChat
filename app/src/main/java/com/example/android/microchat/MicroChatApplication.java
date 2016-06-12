package com.example.android.microchat;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by surya on 12-06-2016.
 */
public class MicroChatApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
