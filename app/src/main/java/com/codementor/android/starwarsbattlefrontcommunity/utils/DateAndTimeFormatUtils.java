package com.codementor.android.starwarsbattlefrontcommunity.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tonykazanjian on 5/13/16.
 */
public class DateAndTimeFormatUtils {

    private static final String DATE_PATTERN = "MMM d, hh:mm aaa";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN, Locale.US);

    public static String getDatePattern (final Date date){
        return SIMPLE_DATE_FORMAT.format(date);
    }
}
