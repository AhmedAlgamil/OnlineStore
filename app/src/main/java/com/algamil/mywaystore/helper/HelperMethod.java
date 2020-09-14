package com.algamil.mywaystore.helper;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.algamil.mywaystore.R;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class HelperMethod {


    private static ProgressDialog checkDialog;

    public static void replaceFragment( FragmentManager getChildFragmentManager, int id, Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void showToast( Activity activity, String message)
    {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public static void showCustomToast( Activity activity, View view)
    {
        Toast toast = new Toast(activity);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showSnackBar(View container,String message)
    {
        Snackbar.make(container,message,Snackbar.LENGTH_LONG).show();
    }

    public static void showSnackBar(View container,String message,String action,View.OnClickListener makeaction)
    {
        Snackbar.make(container,message,Snackbar.LENGTH_LONG).setAction(action, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }

    public static void replaceFragmentWithData( FragmentManager getChildFragmentManager, int id, Fragment fragment, Bundle b)
    {
        FragmentTransaction transaction = getChildFragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        transaction.replace( R.id.fragment_container_view_tag, new Fragment());
        fragment.setArguments(b);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static String getData(Bundle b ,String key)
    {
        String data = null;
        if(b != null)
        {
            b.getString(key);
        }
        return data;
    }

    public static void showCalender( Context context, String title, final TextInputEditText edtit_text_date, final DateTxt data1) {
        DatePickerDialog mDatePicker = new DatePickerDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet( DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                DecimalFormatSymbols symbols = new DecimalFormatSymbols( Locale.US);
                DecimalFormat mFormat = new DecimalFormat("00", symbols);
                String data = selectedYear + "-" + mFormat.format(Double.valueOf((selectedMonth + 1))) + "-" + mFormat.format(Double.valueOf(selectedDay));
                data1.setDate_txt(data);
                data1.setDay(mFormat.format(Double.valueOf(selectedDay)));
                data1.setMonth(mFormat.format(Double.valueOf(selectedMonth + 1)));
                data1.setYear(String.valueOf(selectedYear));
                edtit_text_date.setText(data);
            }
        }, Integer.parseInt(data1.getYear()), Integer.parseInt(data1.getMonth()) - 1, Integer.parseInt(data1.getDay()));
        mDatePicker.setTitle(title);
        mDatePicker.show();
    }

    public static void onLoadImageFromUrl( ImageView imageView, String URl, Context context) {
        Glide.with(context).load(URl).into(imageView);
    }

    public static void showProgressDialog(Activity activity, String title) {
        try {

            checkDialog = new ProgressDialog(activity);
            checkDialog.setMessage(title);
            checkDialog.setIndeterminate(false);
            checkDialog.setCancelable(false);
            checkDialog.show();

        } catch (Exception e) {

        }
    }

    public static void dismissProgressDialog() {
        try {
            checkDialog.dismiss();

        } catch (Exception e) {

        }
    }

    public static void disappearKeypad(Activity activity, View v) {
        try {
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {

        }
    }

    @RequiresApi (api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void changeLang(Context context, String lang) {
        Resources res = context.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(lang)); // API 17+ only.
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
    }

    public static void htmlReader( TextView textView, String s) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText( Html.fromHtml(s, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(Html.fromHtml(s));
        }
    }

    public static void showSimpleDialog(Activity activity
            , String title
            , String message
            , DialogInterface.OnClickListener listenerOk
            , String yes
            , String no
            , DialogInterface.OnClickListener cancel
            , Drawable dr)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(yes, listenerOk)
                .setNegativeButton(no, cancel)
                .setIcon(dr)
                .show();
    }
//
//    public static void makeListViewDialog(Activity activity, String title, StringHighArray stringHighArray,int max)
//    {
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        stringHighArray = new StringHighArray(max);
//        builder.setTitle("Choose any item");
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity,
//                android.R.layout.simple_list_item_multiple_choice, stringHighArray.getStringArray());
//        builder.setAdapter(dataAdapter, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //Toast.makeText(activity, , Toast.LENGTH_SHORT).show();
//            }
//        });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }

    public static void onPermission(Activity activity) {
        String[] perms = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE};

        ActivityCompat.requestPermissions(activity,
                perms,
                100);
    }

    public static String getStringFromXML(Activity activity,int res)
    {
        return activity.getResources().getString(res);
    }

    public static Drawable getDrawableFromXML(Activity activity, int res)
    {
        return activity.getResources().getDrawable(res);
    }


    public static int getImageRecourceFromXML(int res)
    {
        return res;
    }

    public static XmlResourceParser getAnimationFromXML( Activity activity, int res)
    {
        return activity.getResources().getAnimation(res);
    }

    public static int getColorFromXML(Activity activity,int res)
    {
        return activity.getResources().getColor(res);
    }

    public static boolean getBooleanFromXML(Activity activity,int res)
    {
        return activity.getResources().getBoolean(res);
    }

    public static int[] getIntgerArray(Activity activity,int res)
    {
        return activity.getResources().getIntArray(res);
    }

    public static AssetManager getaAssets( Activity activity)
    {
        return activity.getResources().getAssets();
    }

    public static ColorStateList getcolorStateListFromXML( Activity activity, int res)
    {
        return activity.getResources().getColorStateList(res);
    }
    public static float getDimentionFromXML(Activity activity,int res)
    {
        return activity.getResources().getDimension(res);
    }

    public static String[] getStringArrayFromXML(Activity activity,int res)
    {
        return activity.getResources().getStringArray(res);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Typeface getFont( Activity activity, int res)
    {
        return activity.getResources().getFont(res);
    }

    public static boolean checkPhone(String phone)
    {
        boolean cancel = false;
        if(phone.length() != 11)
        {
            cancel = true;
        }
        else {
            cancel = false;
        }
        return cancel;
    }

    public static boolean checkPassword(String password)
    {
        boolean cancel = false;
        if(password.length() <= 5)
        {
            cancel = true;
        }
        else {
            cancel = false;
        }
        return cancel;
    }

    public static void showError( TextInputLayout tx, String Error)
    {
        tx.setError(Error);
    }

    public static int changeErrorColor(Activity activity,int resColor)
    {
        return ContextCompat.getColor(activity, resColor);
    }

    public static void showError(TextInputEditText tx,String Error,Drawable drawable)
    {
        tx.setError(Error,drawable);
    }

    public static boolean checkEmpty(TextInputEditText textInputEditText)
    {
        boolean cancel = false;
        if(textInputEditText.getText().toString().isEmpty())
        {
            cancel = true;
        }
        else {
            cancel = false;
        }
        return cancel;
    }

    public static void makeAnimation(Context context, View view,int resAnimation)
    {
        Animation anim;
        // load the animation
        anim = AnimationUtils.loadAnimation(context, resAnimation);
        // start the animation
        view.startAnimation(anim);
    }

    public static void createMenu( Activity activity, Menu menu, int resMenuse)
    {
        MenuInflater menuInflater = activity.getMenuInflater();
        menuInflater.inflate(resMenuse,menu);
    }

    public static void showTime(Context con,String title,TextInputEditText edit_text_date,boolean is24Hours)
    {
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes  = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(con, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet( TimePicker view, int hourOfDay, int minute) {
                edit_text_date.setText(hourOfDay + " : " + minute);
            }
        },hours,minutes,is24Hours);
        timePickerDialog.setTitle(title);
        timePickerDialog.show();
    }

    public static void makeTransetionX(View view,long duration,int x)
    {
        view.animate().setDuration(duration).translationX(x).start();
    }

    public static void makeTransetionY(View view,long duration,int y)
    {
        view.animate().setDuration(duration).translationX(y).start();
    }

    public static void makeRotation(View view,long duration,int rotation)
    {
        view.animate().setDuration(duration).rotationY(rotation).start();
    }

    public static void makeRotationY(View view,long duration,int rotationY)
    {
        view.animate().setDuration(duration).rotationY(rotationY).start();
    }

    public static void makeTransetionZ(View view,long duration,int z)
    {
        view.animate().setDuration(duration).translationX(z).start();
    }
    public static void makeRotationX(View view,long duration,int rotationX)
    {
        view.animate().setDuration(duration).rotationX(rotationX).start();
    }
    public static void makeFade(View view,long duration,float alpha)
    {
        view.animate().setDuration(duration).alpha(alpha).start();
    }

}
