package com.example.squash1;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    public static final String SP_USER = "spUser";
    public static final String SP_NAME = "nameUser";
    public static final String SP_SIGNED = "signed";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;

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
}
