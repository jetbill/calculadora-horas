package com.ias.software.serivicetest;

import com.ias.software.entity.ReporteHora;
import com.ias.software.repository.ReporteHoraRepository;
import com.ias.software.service.ReporteHoraService;
import com.ias.software.service.ReporteHoraServiceImpl;
import com.ias.software.util.ConvertDates;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CalculadoraServiceTest {

    String hinicial = "2020-03-08 08:00";
    String hfinal = "2020-03-08 19:30";
    Date horainicial = ConvertDates.convertToString(hinicial);
    Date horafinal = ConvertDates.convertToString(hfinal);

    @InjectMocks
    ReporteHoraServiceImpl reporteHoraService;

    @Mock
    ReporteHoraRepository reporteHoraRepository;

    /*@Test
    void crearReporteTest() {
        final ReporteHora reporteHora = new ReporteHora("cod123",
                "cod2345",horainicial,horafinal );
        given(reporteHoraRepository.findByCodigoTecnico(reporteHora.getCodigoTecnico()))
                .willReturn(Optional.empty());

        given(reporteHoraRepository.save(reporteHora)).willAnswer(invocation -> invocation.getArgument(0));

        ReporteHora reporteHoraGuardado = reporteHoraService.save(reporteHora);
        assertThat(reporteHoraGuardado).isNotNull();
        verify(reporteHoraRepository).save(any(ReporteHora.class));
    }*/

    @Test
    void ListarReportesTets(){
        List<ReporteHora> reporteHoras = new ArrayList<>();
        reporteHoras.add(new ReporteHora("cod123",
                "cod2345",horainicial,horafinal ));
        reporteHoras.add(new ReporteHora("cod124",
                "cod2358",horainicial,horafinal ));

        given(reporteHoraRepository.findAll()).willReturn(reporteHoras);
        List<ReporteHora> esperado = (List<ReporteHora>) reporteHoraRepository.findAll();
        assertEquals(esperado,reporteHoras);
    }

    @Test
    void buscarTecnicoPorcodigoTest(){
        final String codigo= "cod123";
        final ReporteHora reporte =  new ReporteHora("cod123",
                "cod2345",horainicial,horafinal );
        given(reporteHoraRepository.findByCodigoTecnico(codigo))
                .willReturn(Optional.of(reporte));
        final Optional<ReporteHora> esperado = reporteHoraService.findByCodigoTecnico(codigo);
        assertThat(esperado).isNotNull();

    }



}
