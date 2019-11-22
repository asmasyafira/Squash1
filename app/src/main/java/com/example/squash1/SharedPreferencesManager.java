package com.example.squash1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.squash1.activities.SignInActivity;

import java.util.HashMap;

public class SharedPreferencesManager {
    public static final String SP_USER = "spUser";
    public static final String SP_NAME = "nameUser";
    public static final String SP_SIGNED = "signed";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;

    Context context;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SP_USER, Context.MODE_PRIVATE);
        spEditor = sharedPreferences.edit();
    }

    public void saveSpString(String keySp, String valueSp) {
        spEditor.putString(keySp, valueSp);
        spEditor.commit();
    }

    public void saveSpBoolean(String keySp, Boolean valueSp) {
        spEditor.putBoolean(keySp, valueSp);
        spEditor.commit();
    }

    public String getSpName() {
        return sharedPreferences.getString(SP_NAME, "");
    }

    public Boolean getSpSigned() {
        return sharedPreferences.getBoolean(SP_SIGNED, false);
    }
    //logout
//    public void logout() {
//        spEditor.clear();
//        spEditor.commit();
//
//        Intent i = new Intent(context, SignInActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        context.startActivity(i);
//    }
//
//    public HashMap<String,String> getUserData(){
//        HashMap<String, String> user = new HashMap<>();
//        user.put(SP_NAME, sharedPreferences.getString(SP_NAME,null));
//
//        return user;
//    }
}
