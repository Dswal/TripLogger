package com.bignerdranch.android.criminalintent.database;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dswal on 19/10/2016.
 */

// Usefull information and code taken from the following link
// http://stackoverflow.com/questions/23351904/getting-cannot-resolve-method-error-when-trying-to-implement-getsharedpreferen
public class KeyValueDB {
    private SharedPreferences sharedPreferences;
    private static String PREF_NAME = "prefs";

    public KeyValueDB() {
        // Blank
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getName(Context context) {
        return getPrefs(context).getString("name_key", "David");
    }

    public static void setName(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("name_key", input);
        editor.commit();
    }

    public static String getId(Context context) {
        return getPrefs(context).getString("id_key", "123456789");
    }

    public static void setId(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("id_key", input);
        editor.commit();
    }

    public static String getEmail(Context context) {
        return getPrefs(context).getString("email_key", "meow@catmail.com");
    }

    public static void setEmail(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("email_key", input);
        editor.commit();
    }

    public static String getGender(Context context) {
        return getPrefs(context).getString("gender_key", "Male");
    }

    public static void setGender(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("gender_key", input);
        editor.commit();
    }

    public static String getComment(Context context) {
        return getPrefs(context).getString("comment_key", "I am a comment, so respect me.");
    }

    public static void setComment(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("comment_key", input);
        editor.commit();
    }
}