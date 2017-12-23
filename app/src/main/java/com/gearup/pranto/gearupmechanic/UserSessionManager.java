package com.gearup.pranto.gearupmechanic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.*;

import java.util.HashMap;

/**
 * Created by pranto on 12/22/17.
 */

public class UserSessionManager {

    SharedPreferences pref;
    Editor editor;
    Context context;
    int PRIVATE_MODE = 0;
    private static final String PREFER_NAME = "grearuppref";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_SERVICE = "service";
    public static final String KEY_RATING = "rating";
    public static final String PASS = "pass";

    UserSessionManager (Context context)
    {
        this.context = context;
        pref = context.getSharedPreferences(PREFER_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserSession(String name, String phone, String pass, String email, String service, String rating)
    {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_PHONE, phone);
        editor.putString(PASS, pass);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_SERVICE, service);
        editor.putString(KEY_RATING, rating);
        editor.commit();
    }

    public boolean cheackLogIn()
    {
        if(!this.isUserLoggedIn())
        {
            Intent i = new Intent(context, LogInActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            return  true;
        }
        return false;
    }

    public void logOut()
    {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LogInActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public HashMap<String, String> getUserDetails()
    {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));
        user.put(PASS, pref.getString(PASS, null));
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_SERVICE, pref.getString(KEY_SERVICE, null));
        user.put(KEY_RATING, pref.getString(KEY_RATING, null));
        return user;
    }

    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

}
