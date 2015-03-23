package com.boushtech.quickorder.libraries;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.boushtech.quickorder.activities.LoginActivity;


public class SessionManagement {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "QuickOrder";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_NOMBRES = "nombres";

    public static final String KEY_APATERNO = "a_paterno";

    public static final String KEY_AMATERNO = "a_materno";

    public static final String KEY_NOMBRE_COMPLETO = "nombre_completo";

    public static final String KEY_SEXO = "sexo";

    public static final String KEY_ROL = "rol";

    public static final String KEY_AUTH_TOKEN = "auth_token";

    public static final String KEY_AUTH_TOKEN2 = "auth_token2";

    public static final String KEY_IMAGEN = "imagen";


    // Constructor
    public SessionManagement(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void createLoginSession(String auth_token, String nombres, String apaterno, String amaterno, String nombre_completo, String sexo, String imagen) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_AUTH_TOKEN, auth_token);

        editor.putString(KEY_NOMBRES, nombres);

        editor.putString(KEY_APATERNO, apaterno);

        editor.putString(KEY_AMATERNO, amaterno);

        editor.putString(KEY_NOMBRE_COMPLETO, nombre_completo);

        editor.putString(KEY_SEXO, sexo);

        editor.putString(KEY_IMAGEN, imagen);
        // commit changes
        editor.commit();
    }


    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }


    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        // Staring Login Activity
        _context.startActivity(i);
    }


    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }


    public void setAuth_Token2(String auth_token2) {
        // Storing login value as TRUE

        editor.putString(KEY_AUTH_TOKEN2, auth_token2);

        // commit changes
        editor.commit();
    }

    public String getKeyAuthToken2() {
        return pref.getString(KEY_AUTH_TOKEN2, null);
    }

    public String getKeyAuthToken() {
        return pref.getString(KEY_AUTH_TOKEN, null);
    }


}