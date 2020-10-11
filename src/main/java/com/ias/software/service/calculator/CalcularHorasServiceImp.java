package com.ias.software.service.calculator;

import com.ias.software.entity.ReporteHora;
import com.ias.software.repository.ReporteHoraRepository;
import com.ias.software.util.ConvertDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class CalcularHorasServiceImp implements CalcularHorasService{
    private ReporteHoraRepository repository;

    @Autowired
    public CalcularHorasServiceImp(ReporteHoraRepository repository) {
        this.repository = repository;
    }

    @Override
    public long calcularHorasNormales(LocalDateTime horaInicio, LocalDateTime horaFin) {
        if (horaInicio.getDayOfWeek().getValue() == 7) return 0;

        LocalDateTime inicioHoraLaboral = LocalDateTime.of(horaInicio.getYear(),
                horaInicio.getMonth(), horaInicio.getDayOfMonth(), 7, 0);
        LocalDateTime finHoraLaboral = LocalDateTime.of(horaInicio.getYear(),
                horaInicio.getMonth(), horaInicio.getDayOfMonth(), 20, 0);

        if (horaInicio.isBefore(inicioHoraLaboral)) horaInicio = inicioHoraLaboral;
        if (horaFin.isAfter(finHoraLaboral)) horaFin = finHoraLaboral;


        return (Math.abs(Duration.between(horaInicio, horaFin).getSeconds())/3600);
    }

    @Override
    public long calcularHorasNocturnas(LocalDateTime horaInicio, LocalDateTime horaFin) {
        if (horaInicio.getDayOfWeek().getValue() == 7) return 0;

        LocalDateTime inicioHoraLaboral = LocalDateTime.of(horaInicio.getYear(), horaInicio.getMonth(), horaInicio.getDayOfMonth(), 7, 0);
        LocalDateTime finHoraLaboral = LocalDateTime.of(horaInicio.getYear(), horaInicio.getMonth(), horaInicio.getDayOfMonth(), 20, 0);
        finHoraLaboral.plusDays(1);

        if (horaInicio.isBefore(inicioHoraLaboral)) horaInicio = inicioHoraLaboral;
        if (horaFin.isAfter(finHoraLaboral)) horaFin = finHoraLaboral;

        return (Math.abs(Duration.between(horaInicio, horaFin).getSeconds())/3600);
    }

    @Override
    public long calcularHorasDominicales(LocalDateTime horaInicio, LocalDateTime horaFin) {

        if (horaInicio.getDayOfWeek().getValue() != 7) return 0;

        LocalDateTime inicioHoraLaboral = LocalDateTime.of(horaInicio.getYear(), horaInicio.getMonth(), horaInicio.getDayOfMonth(), 8, 0);
        LocalDateTime finHoraLaboral = LocalDateTime.of(horaInicio.getYear(), horaInicio.getMonth(), horaInicio.getDayOfMonth(), 23, 0);

        if (horaInicio.isBefore(inicioHoraLaboral)) horaInicio = inicioHoraLaboral;
        if (horaFin.isAfter(finHoraLaboral)) horaFin = finHoraLaboral;


        return (Math.abs(Duration.between(horaInicio, horaFin).getSeconds())/3600);
    }

    @Override
    public long calcularHorasNormalesExtra(LocalDateTime horaInicio, LocalDateTime horaFin, int horasTotales) {
        if (horasTotales < 48) return 0;
        return calcularHorasNormales(horaInicio, horaFin);
    }

    @Override
    public long calcularHorasNocturnasEstra(LocalDateTime horaInicio, LocalDateTime horaFin, int horasTotales) {
        if (horasTotales < 48) return 0;
        return calcularHorasNocturnas(horaInicio, horaFin);
    }

    @Override
    public long calcularHorasDominicalesExtra(LocalDateTime horaInicio, LocalDateTime horaFin, int horasTotales) {
        if (horasTotales < 48) return 0;
        return calcularHorasDominicales(horaInicio, horaFin);
    }


    @Override
    public Hora horasTrabajadas(int diaSemana, List<ReporteHora> reportehora) {


        LocalDateTime firstDay = LocalDateTime.now()
                .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, diaSemana)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        LocalDateTime lastDay = LocalDateTime.now()
                .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, diaSemana)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        Hora hora = new Hora();
        for (ReporteHora reporteHora : reportehora) {
            if (ConvertDates.convertToLocalDateTime(reporteHora.getHoraInicio()).isAfter(firstDay)
                    && ConvertDates.convertToLocalDateTime(reporteHora.getHoraInicio()).isAfter(firstDay)
                    && ConvertDates.convertToLocalDateTime(reporteHora.getHoraFinal()).isAfter(lastDay)
                    && ConvertDates.convertToLocalDateTime(reporteHora.getHoraFinal()).isAfter(lastDay)) {

                hora.setHorasNormales(calcularHorasNormales(ConvertDates.convertToLocalDateTime(reporteHora.getHoraInicio()),ConvertDates.convertToLocalDateTime(reporteHora.getHoraFinal())));
                hora.setHorasNocturnas(calcularHorasNocturnas(ConvertDates.convertToLocalDateTime(reporteHora.getHoraInicio()),ConvertDates.convertToLocalDateTime(reporteHora.getHoraFinal())));
                hora.setHorasDominicales(calcularHorasDominicales(ConvertDates.convertToLocalDateTime(reporteHora.getHoraInicio()),ConvertDates.convertToLocalDateTime(reporteHora.getHoraFinal())));
                hora.setHorasExtrasNormales(calcularHorasNormalesExtra(ConvertDates.convertToLocalDateTime(reporteHora.getHoraInicio()),ConvertDates.convertToLocalDateTime(reporteHora.getHoraFinal()),hora.total().intValue()));
                hora.setHorasExtrasNocturnas(calcularHorasNocturnasEstra(ConvertDates.convertToLocalDateTime(reporteHora.getHoraInicio()),ConvertDates.convertToLocalDateTime(reporteHora.getHoraFinal()),hora.total().intValue()));
                hora.setHorasExtrasDominicales(calcularHorasDominicalesExtra(ConvertDates.convertToLocalDateTime(reporteHora.getHoraInicio()),ConvertDates.convertToLocalDateTime(reporteHora.getHoraFinal()),hora.total().intValue()));
            }
        }

        return  hora;
    }



}
