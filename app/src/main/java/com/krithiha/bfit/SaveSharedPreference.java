package com.krithiha.bfit;

/**
 * @author sarath Quinoid
 * Business Solution Home page
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SaveSharedPreference {

    static final String FIRST_NAME = "firstname";
    static final String SUR_NAME = "surname";
    static final String ADDRESS = "address";
    static final String PHONE_NUMBER = "phone";
    static final String USERNAME="username";
    static final String PASSWORD="password";
    static final String CONFIRM_PASSWORD="confirmpassword";
    static final String REMIND="remind";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    public static String getFirstName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(FIRST_NAME, "");
    }
    public static void setFirstName(Context ctx, String registry) {
        Editor editor1 = getSharedPreferences(ctx).edit();
        editor1.putString(FIRST_NAME, registry);
        editor1.commit();
    }
    public static String getSurName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(SUR_NAME, "");
    }
    public static void setSurName(Context ctx, String registry) {
        Editor editor1 = getSharedPreferences(ctx).edit();
        editor1.putString(SUR_NAME, registry);
        editor1.commit();
    }
    public static String getAddress(Context ctx)
    {
        return getSharedPreferences(ctx).getString(ADDRESS, "");
    }
    public static void setAddress(Context ctx, String registry) {
        Editor editor1 = getSharedPreferences(ctx).edit();
        editor1.putString(ADDRESS, registry);
        editor1.commit();
    }
    public static String getPhoneNumber(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PHONE_NUMBER, "");
    }
    public static void setPhoneNumber(Context ctx, String registry) {
        Editor editor1 = getSharedPreferences(ctx).edit();
        editor1.putString(PHONE_NUMBER, registry);
        editor1.commit();
    }
    public static String getUsername(Context ctx)
    {
        return getSharedPreferences(ctx).getString(USERNAME, "");
    }
    public static void setUsername(Context ctx, String registry) {
        Editor editor1 = getSharedPreferences(ctx).edit();
        editor1.putString(USERNAME, registry);
        editor1.commit();
    }
    public static String getPassword(Context ctx) {
        return getSharedPreferences(ctx).getString(PASSWORD, "");
    }

    public static void setPassword(Context ctx, String registry) {
        Editor editor1 = getSharedPreferences(ctx).edit();
        editor1.putString(PASSWORD, registry);
        editor1.commit();
    }
    public static String getConfirmPassword(Context ctx)
    {
        return getSharedPreferences(ctx).getString(CONFIRM_PASSWORD, "");
    }
    public static void setConfirmPassword(Context ctx, String registry) {
        Editor editor1 = getSharedPreferences(ctx).edit();
        editor1.putString(CONFIRM_PASSWORD, registry);
        editor1.commit();
    }
    public static String getRemind(Context ctx)
    {
        return getSharedPreferences(ctx).getString(REMIND, "");
    }
    public static void setRemind(Context ctx, String registry) {
        Editor editor1 = getSharedPreferences(ctx).edit();
        editor1.putString(REMIND, registry);
        editor1.commit();
    }
//NOTIFICATIONNAME
}
