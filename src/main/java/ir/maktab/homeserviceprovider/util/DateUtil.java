package ir.maktab.homeserviceprovider.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat simpleDateTimeFormat=new SimpleDateFormat("aa:mm am");

    public static Date convertStringToDate(String StringDate) throws ParseException {
        return simpleDateFormat.parse(StringDate);
    }

    public static LocalTime convertStringToTime(String StringTime) {
        return LocalTime.parse(StringTime);
    }


}