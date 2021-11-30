package com.example.tlnt;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_CONTACT = "contactInfo";
    public static final String KEY_TITLE = "title";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_KEY = "createdAt";



    public String getDescription(){
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description){
        put(KEY_DESCRIPTION, description);
    }

    public String getTitle(){
        return getString(KEY_TITLE);
    }

    public void setTitle(String title){
        put(KEY_TITLE, title);
    }

    public String getContact(){
        return getString(KEY_CONTACT);
    }

    public void setContact(String contact){
        put(KEY_CONTACT, contact);
    }


    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user){
        put(KEY_USER, user);

    }

    public static String getKeyCreatedKey() {
        return KEY_CREATED_KEY;
    }
}
