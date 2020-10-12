package com.ias.software.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ConvertDates {

    public static LocalDateTime convertToLocalDateTime(Date date){
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

    }

    public static LocalDateTime convertToLocalDateTime2(Date date){
        return new java.sql.Timestamp(
                date.getTime()).toLocalDateTime();

    }

    public static Date convertToDate(LocalDateTime localDateTime){
        return java.sql.Timestamp.valueOf(localDateTime);

    }

    public static Date convertToDate2(LocalDateTime localDateTime){
        return java.util.Date
                .from(localDateTime.atZone(ZoneId.systemDefault())
                        .toInstant());

    }

    public static Date convertToString(String date){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(date);
        }
        catch (ParseException ex)
        {
            System.out.println(ex);
        }
        return fechaDate;

    }


}
