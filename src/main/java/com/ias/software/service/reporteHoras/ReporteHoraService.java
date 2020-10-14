package com.ias.software.service.reporteHoras;

import com.ias.software.dto.ReporteHoraDto;
import com.ias.software.entity.ReporteHora;

import java.util.List;
import java.util.Optional;


public interface ReporteHoraService {
    ReporteHora save(ReporteHora reporteHora);
    Iterable<ReporteHora> findAll();
    List<ReporteHora> findByHoras(String idTecnico);
    List<ReporteHoraDto> convertEntityToDTOList(String idTecnico);
    Optional<ReporteHora> findByCodigoTecnico(String codigoTecnico);




}
