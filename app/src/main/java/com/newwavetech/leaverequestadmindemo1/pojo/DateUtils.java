package com.newwavetech.leaverequestadmindemo1.pojo;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtils {

    public static final String DF_TO_API = "yyyy-MM-dd HH:mm:ss";
    public static final String DF_FROM_API = "yyyy-MM-dd HH.mm.ss";

    public static String getCurrentTimeString(){
        return convertMilSecTo(DF_TO_API);
    }

    private static String convertMilSecTo(String pattern){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        return formatter.format(System.currentTimeMillis());
    }

}
