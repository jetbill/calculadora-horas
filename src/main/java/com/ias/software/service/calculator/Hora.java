package com.ias.software.service.calculator;

public class Hora {
    private long horasNormales;
    private long horasNocturnas;
    private long horasDominicales;
    private long horasExtrasNormales;
    private long horasExtrasNocturnas;
    private long horasExtrasDominicales;
    private long horasTotales;

    public Hora() {
    }

    public long getHorasNormales() {
        return horasNormales;
    }

    public void setHorasNormales(long horasNormales) {
        this.horasNormales += horasNormales;
    }

    public long getHorasNocturnas() {
        return horasNocturnas;
    }

    public void setHorasNocturnas(long horasNocturnas) {
        this.horasNocturnas += horasNocturnas;
    }

    public long getHorasDominicales() {
        return horasDominicales;
    }

    public void setHorasDominicales(long horasDominicales) {
        this.horasDominicales += horasDominicales;
    }

    public long getHorasExtrasNormales() {
        return horasExtrasNormales;
    }

    public void setHorasExtrasNormales(long horasExtrasNormales) {
        this.horasExtrasNormales += horasExtrasNormales;
    }

    public long getHorasExtrasNocturnas() {
        return horasExtrasNocturnas;
    }

    public void setHorasExtrasNocturnas(long horasExtrasNocturnas) {
        this.horasExtrasNocturnas += horasExtrasNocturnas;
    }

    public long getHorasExtrasDominicales() {
        return horasExtrasDominicales;
    }

    public void setHorasExtrasDominicales(long horasExtrasDominicales) {
        this.horasExtrasDominicales += horasExtrasDominicales;
    }

    public Long total() {
        this.horasTotales = this.horasNocturnas + this.horasNormales + this.horasDominicales + this.horasExtrasNormales + this.horasExtrasNocturnas + this.horasExtrasDominicales;
        return horasTotales;
    }

    @Override
    public String toString() {
        return "Hours{" +
                "normal=" + horasNormales +
                ", night=" + horasNocturnas +
                ", sunday=" + horasDominicales +
                ", extraNormal=" + horasExtrasNormales +
                ", extraNight=" + horasExtrasNocturnas +
                ", extraSunday=" + horasExtrasDominicales +
                ", total=" + horasTotales +
                '}';
    }

    public long getHorasTotales() {
        return horasTotales;
    }
}