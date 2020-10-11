package com.ias.software.service;

import com.ias.software.entity.ReporteHora;
import com.ias.software.repository.ReporteHoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReporteHoraServiceImpl implements ReporteHoraService{

    private ReporteHoraRepository reporteHoraRepository;

    @Autowired
    public ReporteHoraServiceImpl(ReporteHoraRepository reporteHoraRepository) {
        this.reporteHoraRepository = reporteHoraRepository;
    }

    @Override
    @Transactional
    public ReporteHora save(ReporteHora reporteHora) {
        return reporteHoraRepository.save(reporteHora);
    }

    @Override
    @Transactional
    public Iterable<ReporteHora> findAll() {
        return reporteHoraRepository.findAll();
    }

    @Override
    public List<ReporteHora> findByHoras(String idTecnico) {
        return reporteHoraRepository.findByHoras(idTecnico);
    }


}
