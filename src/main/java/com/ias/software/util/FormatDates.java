package com.ias.software.util;

import com.ias.software.dto.ReporteHoraDto;
import com.ias.software.exceptions.ReporteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.ReadPendingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FormatDates {
    static Logger logger = LoggerFactory.getLogger(FormatDates.class);
    public static String DateToString(Date date, String format) {
        if (date != null) {
            try {
                DateFormat dateFormat = new SimpleDateFormat(format);
                return dateFormat.format(date);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }

    }

    public static List<ReporteHoraDto> formatList(List<ReporteHoraDto> dto1){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<ReporteHoraDto> dto2 = new ArrayList<>();
        StringBuilder fi = new StringBuilder();
        StringBuilder ff = new StringBuilder();

        try {
            for(ReporteHoraDto rdto: dto1){
                fi.append(rdto.getHoraInicio().toString().substring(0,16));
                rdto.setHoraInicio(ConvertDates.convertToDate(LocalDateTime.parse(fi,formatter)));
                ff.append(rdto.getHoraFinal().toString().substring(0,16));
                rdto.setHoraFinal(ConvertDates.convertToDate(LocalDateTime.parse(ff,formatter)));

                dto2.add(rdto);
                logger.info("formato fecha",rdto);

            }

        }catch (DateTimeParseException e){
            throw new ReporteException("Esta es la cadena "+fi);

        }




        return dto2;


    }

}
