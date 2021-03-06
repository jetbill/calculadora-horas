package com.ias.software.repository;

import com.ias.software.entity.ReporteHora;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;


public interface ReporteHoraRepository extends
        PagingAndSortingRepository<ReporteHora,Long> {
    @Query("SELECT h FROM ReporteHora h WHERE h.codigoTecnico= :codigoTecnico")
    List<ReporteHora> findByHoras(String codigoTecnico);

    @Query("SELECT c FROM ReporteHora c WHERE c.codigoTecnico= :codigoTecnico")
    Optional<ReporteHora> findByCodigoTecnico(String codigoTecnico);
}
