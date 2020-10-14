package com.ias.software.service.calculator;

import com.ias.software.dto.ReporteHoraDto;
import com.ias.software.entity.ReporteHora;

import java.time.LocalDateTime;
import java.util.List;

public interface CalcularHorasService {

    long calcularHorasNormales(LocalDateTime horaInicio, LocalDateTime horaFin);
    long calcularHorasNocturnas(LocalDateTime horaInicio, LocalDateTime horaFin);
    long calcularHorasDominicales(LocalDateTime horaInicio, LocalDateTime horaFin);
    long calcularHorasNormalesExtra(LocalDateTime horaInicio, LocalDateTime horaFin, int horasTotales);
    long calcularHorasNocturnasEstra(LocalDateTime horaInicio, LocalDateTime horaFin, int horasTotales);
    long calcularHorasDominicalesExtra(LocalDateTime horaInicio, LocalDateTime horaFin, int horasTotales);


}
