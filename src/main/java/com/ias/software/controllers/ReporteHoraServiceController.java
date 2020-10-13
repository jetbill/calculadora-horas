package com.ias.software.controllers;

import com.ias.software.dto.ReporteHoraDto;
import com.ias.software.entity.ReporteHora;
import com.ias.software.exceptions.ReporteException;
import com.ias.software.service.ReporteHoraService;
import com.ias.software.service.calculator.CalcularHorasService;
import com.ias.software.service.calculator.Hora;
import com.ias.software.util.ConvertDates;
import com.ias.software.util.FormatDates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/reporte")
public class ReporteHoraServiceController {

    private ReporteHoraService reporteHoraService;
    private CalcularHorasService horasService;
    Logger logger = LoggerFactory.getLogger(ReporteHoraServiceController.class);

    @Autowired
    public ReporteHoraServiceController(ReporteHoraService reporteHoraService) {
        this.reporteHoraService = reporteHoraService;
    }

    /*@GetMapping
   public ResponseEntity<?> findAll(){
        Iterable<ReporteHora> reportes = null;
        Map<String,Object> response = new HashMap<>();
        try {
            reportes = reporteHoraService.findAll();
        }catch (DataAccessException e){
            response.put("mensaje","Error de conexion");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

        }
        response.put("reportes",reportes);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }*/
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(reporteHoraService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ReporteHora reporteHora ){
        Map<String,Object> response = new HashMap<>();
        ReporteHora reporteHorabd = null;
        try {
            reporteHorabd = reporteHoraService.save(reporteHora);
        }catch (DataAccessException e){
            response.put("mensaje","Error de conexion");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_ACCEPTABLE);
        }
        response.put("reporte",reporteHorabd);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

   @GetMapping("/cedula/{codigoTecnico}")
    public ResponseEntity<?> findByHoras(@PathVariable String codigoTecnico){
        List<ReporteHora> reporteHoras =null;

        Map<String,Object> response = new HashMap<>();
        try {
            reporteHoras = reporteHoraService.findByHoras(codigoTecnico);


                    logger.info("horas",reporteHoras);


        }catch (DataAccessException e){
            response.put("mensaje","Error de conexion");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_ACCEPTABLE);

        }
        System.out.println(reporteHoras);
        response.put("horas",reporteHoras);

       return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/codigo/{codigo}/semana/{numeroSemana}")
    public ResponseEntity<?> calcularHora(@PathVariable String codigo,@PathVariable int numeroSemana){
        Map<String,Object> response = new HashMap<>();
        Hora hora = null;
        List<ReporteHoraDto> reporteHoras = null;
        
        try {
            reporteHoras  = reporteHoraService.convertEntityToDTOList(codigo);
            List<ReporteHoraDto> reporteHoraDtosFormated = FormatDates.formatList(reporteHoras);
            hora = horasService.horasTrabajadas(numeroSemana, reporteHoraDtosFormated);

        }catch (DataAccessException e){
            response.put("mensaje","Error de operacion");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

        }


         return ResponseEntity.ok().body(hora);
    }

}
