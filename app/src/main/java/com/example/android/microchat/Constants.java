package com.example.android.microchat;

import com.firebase.client.Firebase;

/**
 * Created by surya on 12-06-2016.
 */
public class Constants {

    protected static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;

    private static final String MESSAGES = "messages";
    protected static final String FIREBASE_URL_MESSAGES = BuildConfig.UNIQUE_FIREBASE_ROOT_URL + MESSAGES;

    protected static final Firebase mRef = new Firebase(FIREBASE_URL);

}
