package com.demo.e_commerce;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.window.SplashScreen;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Locale;

public class UserSession {
    // Shared Preferences reference
    SharedPreferences pref;

    // Editor reference for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared preferences mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    public static final String PREFER_NAME = "Chara_User";

    // All Shared Preferences Keys
    public static final String IS_USER_LOGIN = "IsUserLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAMEID = "id";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "whatsapp";

    // Mobile Number (make variable public to access from outside)
    public static final String KEY_MOBILE = "mobile";

    // Email address (make variable public to access from outside)
    public static final String KEY_ADMIN_WHATSAPP = "whatsapp_admin";

    // Mobile Number (make variable public to access from outside)
    public static final String KEY_ADMIN_MOBILE = "mobile_admin";

    // Image (make variable public to access from outside)
    public static final String KEY_H_NO = "hno";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_CITY = "city";
    public static final String KEY_STATE = "state";
    public static final String KEY_COUNTRY = "country";
    public static final String Refererence_Code = "refer_code";


    // Constructor
    public UserSession(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static String getSharedPreferences(Context context, String name) {
        SharedPreferences settings = context
                .getSharedPreferences(PREFER_NAME, 0);
        return settings.getString(name, "");
    }


    //Create login session
    public void createUserLoginSession(String uMobile) {
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        // Storing name in preferences
        editor.putString(KEY_MOBILE, uMobile);

        // Storing email in preferences
        //editor.putString(KEY_PASSWORD,  uPassword);

        // commit changes
        editor.commit();
    }

    public boolean checkLogin(){
        // Check login status
        if(!this.isUserLoggedIn()){

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, MainActivity.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            i.setAction(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Staring Login Activity
            _context.startActivity(i);

            return true;
        }
        return false;
    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user name
        user.put(KEY_NAMEID, pref.getString(KEY_NAMEID, null));

        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // user mobile number
        user.put(KEY_MOBILE, pref.getString(KEY_MOBILE, null));

        user.put(KEY_ADDRESS, pref.getString(KEY_ADDRESS, null));
        // user Image
        user.put(KEY_H_NO, pref.getString(KEY_H_NO, null));
        user.put(KEY_CITY, pref.getString(KEY_CITY, null));
        user.put(KEY_ADMIN_MOBILE, pref.getString(KEY_ADMIN_MOBILE, null));
        user.put(KEY_ADMIN_WHATSAPP, pref.getString(KEY_ADMIN_WHATSAPP, null));
        user.put(KEY_STATE, pref.getString(KEY_STATE, null));
        user.put(KEY_COUNTRY, pref.getString(KEY_COUNTRY, null));
        user.put(Refererence_Code, pref.getString(Refererence_Code, null));

        // return user
        return user;
    }
    /**
     * Clear session details
     * */
    @RequiresApi(api = Build.VERSION_CODES.S)
    public void logoutUser(){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to MainActivity
        Intent i = new Intent(_context, SplashScreen.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }


    public static void setLocale(Context context, String localeName) {
        Locale myLocale = new Locale(localeName);
        Locale.setDefault(myLocale);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Log.e("Utility Status", "Locale updated!");
    }

    // Check for login
    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}