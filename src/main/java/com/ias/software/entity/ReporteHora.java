package com.ias.software.entity;



import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @NotNull(message = "debe ingresar un codigo")
    @Column(name = "codigo_tecnico")
    private String codigoTecnico;
    @NotNull(message = "debe ingresar un codigo")
    @Column(name = "codigo_servicio")
    private String codigoServicio;

    @NotNull(message = "debe ingesar una fecha")
    //@Column(name = "hora_inicio")
    //@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime horaInicio;

    @NotNull(message = "debe ingesar una fecha")
   // @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    //@Column(name = "hora_final")
    private LocalDateTime horaFinal;


    public ReporteHora(String codigoTecnico, String codigoServicio, LocalDateTime horaInicio, LocalDateTime horaFinal) {
        this.codigoTecnico = codigoTecnico;
        this.codigoServicio = codigoServicio;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public ReporteHora(LocalDateTime horaInicio, LocalDateTime horaFinal) {
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
