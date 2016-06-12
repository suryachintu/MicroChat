package com.example.android.microchat;

/**
 * Created by surya on 12-06-2016.
 */
public class User {
    String email,messages;

    public User(String email, String messages) {
        this.email = email;
        this.messages = messages;
    }

    public String getEmail() {
        return email;
    }

    public String getMessages() {
        return messages;
    }
}
