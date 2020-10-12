package com.ias.software.entity;



import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "reporte_horas")
public class ReporteHora implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_tecnico")
    private String codigoTecnico;

    @Column(name = "codigo_servicio")
    private String codigoServicio;

    @NotNull(message = "debe ingesar una fecha")
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date horaInicio;

    @NotNull(message = "debe ingesar una fecha")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "hora_final")
    private Date horaFinal;


    /*public ReporteHora(Date horaInicio, Date horaFinal) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }*/

    public ReporteHora(Date horaInicio, Date horaFinal) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public ReporteHora() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoTecnico() {
        return codigoTecnico;
    }

    public void setCodigoTecnico(String codigoTecnico) {
        this.codigoTecnico = codigoTecnico;
    }

    public String getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
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

    @Override
    public String toString() {
        return "ReporteHora{" +
                "id=" + id +
                ", codigoTecnico='" + codigoTecnico + '\'' +
                ", codigoServicio='" + codigoServicio + '\'' +
                ", horaInicio=" + horaInicio +
                ", horaFinal=" + horaFinal +
                '}';
    }
}
