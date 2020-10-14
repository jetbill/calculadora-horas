package com.ias.software.dto;

import com.ias.software.entity.ReporteHora;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class ReporteHoraDto {

    private LocalDateTime horaInicio;
    private LocalDateTime horaFinal;

    public ReporteHoraDto() {
    }

   public ReporteHoraDto(LocalDateTime horaInicio, LocalDateTime horaFinal) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }



    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalDateTime horaFinal) {
        this.horaFinal = horaFinal;
    }
}
