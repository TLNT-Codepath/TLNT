package com.example.tlnt;


import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("yvCQtd7Ojitvk8dANrn2dci2kj5LipClKVki0v83")
                .clientKey("bJkyWmTGm4pvadulz3po844pYN6gTf3LA6dIV4h8")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
