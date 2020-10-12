package com.ias.software.dto;

import com.ias.software.entity.ReporteHora;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class ReporteHoraDto {

    private Date horaInicio;
    private Date horaFinal;

    public ReporteHoraDto() {
    }

   public ReporteHoraDto(Date horaInicio, Date horaFinal) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }



    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }
}
