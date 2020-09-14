package com.algamil.mywaystore.data.local;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPreferencesManger {

    public static SharedPreferences sharedPreferences = null;
    public static final String USER_DATA = "USER_DATA";
    public static final String REMEMBER_ME = "REMEMBER_ME";
    public static final String REMEMBER_ME2 = "REMEMBER_ME2";
    public static final String USER_PASSWORD = "USER_PASSWORD";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_EMAIL = "USER_EMAIL";
    public static final String USER_EMAIL_SENDED = "USER_EMAIL_SENDED";
    public static final String USER_IMAGE = "USER_IMAGE";
    public static final String USER_PHONE = "USER_PHONE";
    public static final String USER_LOCATION = "USER_LOCATION";
    public static final String CUSTOMER_ID = "CUSTOMER_ID";
    public static final String CATEGORY  = "CATEGORY";
    public static final String OPERATION  = "OPERATION";
    public static final String OPERATION_WALK  = "OPERATION_WALK";
    public static final String PRODUCT_NAME  = "PRODUCT_NAME";
    public static final String PRODUCT_DETAILS  = "PRODUCT_DETAILS";
    public static final String PRODUCT_COST  = "PRODUCT_COST";
    public static final String PRODUCT_QUANTITY  = "PRODUCT_QUANTITY";
    public static final String PRODUCT_IMAGE  = "PRODUCT_IMAGE";
    public static final String CODE  = "CODE";
    public static final String MY_PHONE  = "MY_PHONE";
    public static final String LOCATION  = "LOCATION";
    public static final String NAME  = "NAME";
    public static final String MAKE_EDIT  = "MAKE_EDIT";
    public static final String LOG_OUT  = "LOG_OUT";
    public static final String MYOERATION  = "MYOERATION";



    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "Tester", activity.MODE_PRIVATE);
        }
    }

    public static void saveData(Activity activity, String data_Key, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.apply();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void saveData(Activity activity, String data_Key, boolean data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void saveData(Activity activity, String data_Key, int data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void saveData(Activity activity, String data_Key, Object data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String StringData = gson.toJson(data_Value);
            editor.putString(data_Key, StringData);
            editor.commit();
        }
    }

    public static String loadData(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        return sharedPreferences.getString(data_Key, null);
    }

    public static boolean loadBoolean(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        return sharedPreferences.getBoolean(data_Key, false);
    }

    public static void clean(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }

}
