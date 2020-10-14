package com.ias.software.service.reporteHoras;

import com.ias.software.dto.ReporteHoraDto;
import com.ias.software.entity.ReporteHora;
import com.ias.software.exceptions.ReporteException;
import com.ias.software.repository.ReporteHoraRepository;
import com.ias.software.service.reporteHoras.ReporteHoraService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;


@Service
public class ReporteHoraServiceImpl implements ReporteHoraService {

    private final ReporteHoraRepository reporteHoraRepository;

    @Autowired
    public ReporteHoraServiceImpl(ReporteHoraRepository reporteHoraRepository) {
        this.reporteHoraRepository = reporteHoraRepository;
    }

    @Override
    @Transactional
    public ReporteHora save(ReporteHora reporteHora) {
        if(!reporteHora.getHoraInicio().isBefore(reporteHora.getHoraFinal())){
            throw new ReporteException("La hora inicial debe ser menor");
        }

        return reporteHoraRepository.save(reporteHora
        );
    }

    @Override
    @Transactional
    public Iterable<ReporteHora> findAll() {
        return reporteHoraRepository.findAll();
    }

    @Override
    public List<ReporteHora> findByHoras(String idTecnico) {
        List<ReporteHora> lista1 = reporteHoraRepository.findByHoras(idTecnico);
        return lista1;
    }

    @Override
    public List<ReporteHoraDto> convertEntityToDTOList(String idTecnico) {
        ModelMapper modelMapper = new ModelMapper();
        List<ReporteHora> lista1 = reporteHoraRepository.findByHoras(idTecnico);
        Type listType = new TypeToken<List<ReporteHoraDto>>(){}.getType();
        List<ReporteHoraDto> dtoList = modelMapper.map(lista1, listType);

        return dtoList;
    }

    @Override
    public Optional<ReporteHora> findByCodigoTecnico(String codigoTecnico) {
        return reporteHoraRepository.findByCodigoTecnico(codigoTecnico);
    }


}
