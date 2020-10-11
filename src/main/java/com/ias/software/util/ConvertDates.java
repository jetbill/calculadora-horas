package com.ias.software.util;

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


}
