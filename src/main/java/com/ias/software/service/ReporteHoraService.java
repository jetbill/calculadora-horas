package com.ias.software.service;

import com.ias.software.dto.ReporteHoraDto;
import com.ias.software.entity.ReporteHora;

import java.util.List;


public interface ReporteHoraService {
    ReporteHora save(ReporteHora reporteHora);
    Iterable<ReporteHora> findAll();
    List<ReporteHora> findByHoras(String idTecnico);
    List<ReporteHoraDto> convertEntityToDTOList(String idTecnico);




}
