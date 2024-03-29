package com.example.tlnt;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("User")
public class User extends ParseObject {
    public static final String KEY_BIO = "bio";
    public static final String KEY_IS_TALENT = "isTalent";

    public String getBio(){ return getString(KEY_BIO);}

    public void setBio(String bio){ put(KEY_BIO, bio);}

    public Boolean getIsTalent(){ return getBoolean(KEY_IS_TALENT);}

    public void setIsTalent(Boolean isTalent){ put(KEY_IS_TALENT, isTalent);}

}
