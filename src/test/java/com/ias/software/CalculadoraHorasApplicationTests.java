package com.ias.software;

import com.ias.software.entity.ReporteHora;
import com.ias.software.repository.ReporteHoraRepository;
import com.ias.software.service.reporteHoras.ReporteHoraServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CalculadoraHorasApplicationTests {

    LocalDateTime horainicial = LocalDateTime.of(2020, 03, 8, 8, 00, 00);
    LocalDateTime horafinal = LocalDateTime.of(2020, 03, 8, 9, 00, 00);

    @InjectMocks
    ReporteHoraServiceImpl reporteHoraService;

    @Mock
    ReporteHoraRepository  reporteHoraRepository;


    @Test
    void ListarReportesTets() {
        List<ReporteHora> reporteHoras = new ArrayList<>();
        reporteHoras.add(new ReporteHora("cod123",
                "cod2345", horainicial, horafinal));
        reporteHoras.add(new ReporteHora("cod124",
                "cod2358", horainicial, horafinal));

        given(reporteHoraRepository.findAll()).willReturn(reporteHoras);
        List<ReporteHora> esperado = (List<ReporteHora>) reporteHoraRepository.findAll();
        assertEquals(esperado, reporteHoras);
    }

    @Test
    void buscarTecnicoPorcodigoTest() {
        final String codigo = "cod123";
        final ReporteHora reporte = new ReporteHora("cod123",
                "cod2345", horainicial, horafinal);
        given(reporteHoraRepository.findByCodigoTecnico(codigo))
                .willReturn(Optional.of(reporte));
        final Optional<ReporteHora> esperado = reporteHoraService.findByCodigoTecnico(codigo);
        assertThat(esperado).isNotNull();

    }

}


