package com.ias.software.service.calculator;

import com.ias.software.dto.ReporteHoraDto;
import com.ias.software.repository.ReporteHoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class CalcularHorasServiceImp implements CalcularHorasService {
    private ReporteHoraRepository repository;

    @Autowired
    public CalcularHorasServiceImp(ReporteHoraRepository repository) {
        this.repository = repository;
    }

    public CalcularHorasServiceImp() {
    }

    @Override
    public long calcularHorasNormales(LocalDateTime horaInicio, LocalDateTime horaFin) {
        if (horaInicio.getDayOfWeek().getValue() == 7) return 0;

        LocalDateTime inicioHoraLaboral = LocalDateTime.of(horaInicio.getYear(),
                horaInicio.getMonth(), horaInicio.getDayOfMonth(), 7, 0);
        LocalDateTime finHoraLaboral = LocalDateTime.of(horaInicio.getYear(),
                horaInicio.getMonth(), horaInicio.getDayOfMonth(), 19, 58);

        if (horaInicio.isBefore(inicioHoraLaboral)) horaInicio = inicioHoraLaboral;
        if (horaFin.isAfter(finHoraLaboral)) horaFin = finHoraLaboral;


        return (Math.abs(Duration.between(horaInicio, horaFin).getSeconds()) / 3600);
    }

    @Override
    public long calcularHorasNocturnas(LocalDateTime horaInicio, LocalDateTime horaFin) {
        if (horaInicio.getDayOfWeek().getValue() == 7) return 0;

        LocalDateTime inicioHoraLaboral = LocalDateTime.of(horaInicio.getYear(),
                horaInicio.getMonth(), horaInicio.getDayOfMonth(), 20, 0);
        LocalDateTime finHoraLaboral = LocalDateTime.of(horaInicio.getYear(),
                horaInicio.getMonth(), horaInicio.getDayOfMonth(), 6, 59);
        finHoraLaboral.plusDays(1);

        if (horaInicio.isAfter(inicioHoraLaboral)) horaInicio = inicioHoraLaboral;
        if (horaFin.isBefore(finHoraLaboral)) horaFin = finHoraLaboral;

        return (Math.abs(Duration.between(horaInicio, horaFin).getSeconds()) / 3600);
    }

    @Override
    public long calcularHorasDominicales(LocalDateTime horaInicio, LocalDateTime horaFin) {

        if (horaInicio.getDayOfWeek().getValue() != 7) return 0;

        LocalDateTime inicioHoraLaboral = LocalDateTime.of(horaInicio.getYear(), horaInicio.getMonth(), horaInicio.getDayOfMonth(), 8, 0);
        LocalDateTime finHoraLaboral = LocalDateTime.of(horaInicio.getYear(), horaInicio.getMonth(), horaInicio.getDayOfMonth(), 23, 0);

        if (horaInicio.isBefore(inicioHoraLaboral)) horaInicio = inicioHoraLaboral;
        if (horaFin.isAfter(finHoraLaboral)) horaFin = finHoraLaboral;


        return (Math.abs(Duration.between(horaInicio, horaFin).getSeconds()) / 3600);
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







}
