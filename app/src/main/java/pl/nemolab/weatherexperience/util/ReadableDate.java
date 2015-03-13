package pl.nemolab.weatherexperience.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ReadableDate {

    public static String formatDateTime(long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(date * 1000));
    }

    public static String formatDate(long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date(date * 1000));
    }

    public static String formatTime(long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(new Date(date * 1000));
    }
}
