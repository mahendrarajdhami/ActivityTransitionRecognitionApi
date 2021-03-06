package np.com.mahendrarajdhami.activityrecognitiontransitionapi.utility;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.webkit.URLUtil;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by DeltaTech on 1/18/2018.
 */

public class MFunction {
    //private   static ResponseListener mResponseListener;
    private static final String TAG = MFunction.class.getSimpleName();



    /*Date Related Helper Functions*/

    public static String getCurrentDateTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date());
    }

    public static String getCurrentTime(){
        return new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date());
    }



    /*pattern:"yyyy-MM-dd HH:mm:ss"*/
    public static String getCurrentDateTime(String pattern){
        return new SimpleDateFormat(pattern, Locale.ENGLISH).format(new Date());
    }

    public static String getCurrentDate(){
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());

    }

    public static String getCurrentYear(){
        return new SimpleDateFormat("yyyy", Locale.ENGLISH).format(new Date());
    }

    public static String getCurrentMonth(String pattern){
        return new SimpleDateFormat(pattern, Locale.ENGLISH).format(new Date());
    }

    public static Date getStrToDate(String strDate,String format) throws ParseException {
        return new SimpleDateFormat(format, Locale.ENGLISH).parse(strDate);
    }

    public static String getFormattedDate(String pattern, Date date) throws ParseException {
        return new SimpleDateFormat(pattern, Locale.ENGLISH).format(date);
    }

    public static String getFormattedDate(String pattern, String datetime) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        Date date = dateFormat.parse(datetime);
        return new SimpleDateFormat(pattern, Locale.ENGLISH).format(date);
    }

    public static String getFormattedDate(String outputPattern, String inputPattern, String input){
        DateFormat dateFormat = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
        Date date = null;
        try {
            date = dateFormat.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat(outputPattern, Locale.ENGLISH).format(date);
    }

    public static String getFormattedDateFromDate(String pattern, String datetime) throws ParseException {
        if(TextUtils.isEmpty(pattern) || TextUtils.isEmpty(datetime)) return "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = dateFormat.parse(datetime);
        return new SimpleDateFormat(pattern, Locale.ENGLISH).format(date);
    }


    public static String getDate(long milliSeconds) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(milliSeconds);
        return formatter.format(milliSeconds);
    }

    public static String getTime(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        return formatter.format(milliSeconds);
    }



    public static long getUnixTimestamp(){
        return System.currentTimeMillis();
    }

    public static long getUnixTimestamp(String datetime, String pattern){
        DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date date = null;
        try {
            date = dateFormat.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String getTimeFromDatetime(String datetime){

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newString = new SimpleDateFormat("hh:mm:ss a", Locale.ENGLISH).format(date);
        return newString;
    }

    public static String getNotificationTime(String datetime)throws ParseException{
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(datetime);
        String newString = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(date);
        return newString;

    }

    public static String getNotificationTime(Long seconds){
        Date newDate = new Date ();
        newDate.setTime((long)seconds);
        String newString = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(newDate);
        return newString;

    }

    public static String getDateTime(Long milis){

        String newString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(milis);
        return newString;
    }

    public static String getLeaveDays(String start,String end){
        if(TextUtils.equals(start,end)) return "1";
        Date endDate = null;
        try {
            endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date startDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //Comparing dates
        long difference = Math.abs(endDate.getTime() - startDate.getTime());
        long differenceDates = difference / (24 * 60 * 60 * 1000)+1;

        //Convert long to String
        String dayDifference = Long.toString(differenceDates);
        return dayDifference;

    }

    public static String getUID(){
        return UUID.randomUUID().toString();
    }


    public static int getBatteryLevel(Context mContext){

        Intent batteryIntent = mContext.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        //int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        //float batteryPct = level / (float)scale;
        return level;
    }


    public static String getDeviceInfo(){

        String manufacturer = Build.MANUFACTURER;

        String model = Build.MODEL + " " + android.os.Build.BRAND +" ("
                + android.os.Build.VERSION.RELEASE+")"
                + " API-" + android.os.Build.VERSION.SDK_INT;

        if (model.startsWith(manufacturer)) {
            return model.toUpperCase();
        } else {
            return manufacturer.toUpperCase() + " " + model;
        }

    }



}
