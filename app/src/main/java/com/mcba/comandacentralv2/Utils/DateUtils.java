package com.mcba.comandacentralv2.Utils;

import android.text.format.DateFormat;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by mac on 30/06/2018.
 */

public class DateUtils {

    private static final long DAYMILIS = 86400000 * 1000L;
    private static final long FIFTEEN_MINUTES = 15 * 60 * 1000L;

    public static String getDateFromTimestamp(long timestamp, String format) {

//        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM");
//        String dateString = formatter.format(new Date(timestamp));

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timestamp);
        String dateString = DateFormat.format(format, cal).toString();

        return dateString;

    }

    public static String getDateFromTimestampTransactionDialog(long timestamp) {

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timestamp * 1000L);
        String dateString = DateFormat.format("EEE d, MMM, h:mm a", cal).toString();

        return dateString;

    }

    public static long getCurrentTimeInMs() {

        return System.currentTimeMillis();
    }

    public static String convertDateAux(String dateSrc) {

        String dateStr = null;
        try {
            SimpleDateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd");

            // parse the date string into Date object
            Date date = null;
            date = srcDf.parse(dateSrc);

            SimpleDateFormat destDf = new SimpleDateFormat("dd-MM-yyyy");

            // format the date into another format
            dateStr = destDf.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public static String convertDate(String dateSrc) {

        String dateStr = null;
        try {
            SimpleDateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd");

            // parse the date string into Date object
            Date date = null;
            date = srcDf.parse(dateSrc);

            SimpleDateFormat destDf = new SimpleDateFormat("EEEE d MMM");

            // format the date into another format
            dateStr = destDf.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public static Calendar stringToCalendar(String date, String pattern) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        cal.setTime(sdf.parse(date));
        return cal;
    }

}
