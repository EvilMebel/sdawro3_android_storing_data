package com.sdacademy.zientara.rafal.awesomeapp.models;

import android.content.Context;

import com.sdacademy.zientara.rafal.awesomeapp.R;

/**
 * Created by Evil on 23.07.2017.
 */

public class Email {
    private String title;
    private String message;
    private String sender;

    public Email(Context context) {
        title = "Dummy title";
        message = context.getString(R.string.email_dummy_message);
        sender = "Boss";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
