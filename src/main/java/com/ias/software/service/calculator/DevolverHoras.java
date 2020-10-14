package com.ias.software.service.calculator;

import com.ias.software.dto.ReporteHoraDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.List;


public class DevolverHoras {
    static Logger logger = LoggerFactory.getLogger(DevolverHoras.class);

    static  private CalcularHorasService calcularHorasService = new CalcularHorasServiceImp();

    private static LocalDateTime formatdates(LocalDateTime date) {
        LocalDateTime datetime = null;
        DateTimeFormatter f = new DateTimeFormatterBuilder().parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("yyyy-MMM-dd")).toFormatter();
        try {
            datetime = LocalDateTime.parse(date.toString(), f);
            System.out.println(datetime); // 2019-12-22
        } catch (DateTimeParseException e) {
            logger.info("hora parseada " + datetime);
        }
    return datetime;
    }

   public static Hora horasTrabajadas(int diaSemana, List<ReporteHoraDto> reportehora){

        LocalDateTime firstDay = LocalDateTime.now()
                .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, diaSemana)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        LocalDateTime lastDay = firstDay.plusDays(6);

        Hora hora = new Hora();
        for (ReporteHoraDto reporteHora : reportehora) {
            if (reporteHora.getHoraInicio().isAfter(firstDay)
                    && reporteHora.getHoraInicio().isBefore(lastDay)
                    && reporteHora.getHoraFinal().isAfter(firstDay)
                    && reporteHora.getHoraFinal().isBefore(lastDay)) {

                hora.setHorasNormales(calcularHorasService.calcularHorasNormales(reporteHora.getHoraInicio(), reporteHora.getHoraFinal()));
                hora.setHorasNocturnas(calcularHorasService.calcularHorasNocturnas(reporteHora.getHoraInicio(), reporteHora.getHoraFinal()));
                hora.setHorasDominicales(calcularHorasService.calcularHorasDominicales(reporteHora.getHoraInicio(), reporteHora.getHoraFinal()));
                hora.setHorasExtrasNormales(calcularHorasService.calcularHorasNormalesExtra(reporteHora.getHoraInicio(), reporteHora.getHoraFinal(), hora.total().intValue()));
                hora.setHorasExtrasNocturnas(calcularHorasService.calcularHorasNocturnasEstra(reporteHora.getHoraInicio(), reporteHora.getHoraFinal(), hora.total().intValue()));
                hora.setHorasExtrasDominicales(calcularHorasService.calcularHorasDominicalesExtra(reporteHora.getHoraInicio(), reporteHora.getHoraFinal(), hora.total().intValue()));
            }
        }

        return hora;
    }
}
