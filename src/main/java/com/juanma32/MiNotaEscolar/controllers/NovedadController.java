package com.juanma32.MiNotaEscolar.controllers;

import com.juanma32.MiNotaEscolar.entities.Novedad;
import com.juanma32.MiNotaEscolar.entities.Servicio;
import com.juanma32.MiNotaEscolar.services.NovedadServiceImpl;
import com.juanma32.MiNotaEscolar.services.ServicioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/novedad")
@CrossOrigin(origins = "*")
public class NovedadController {

    @Autowired
    private NovedadServiceImpl service;
    @Autowired
    private ServicioServiceImpl serviceServicio;

    @PostMapping("/{idServicio}")
    public ResponseEntity<?> save(@Valid @RequestBody Novedad novedad,BindingResult result,@PathVariable Long idServicio){
        Optional<Servicio> oServicio = serviceServicio.findById(idServicio);
        if(oServicio.isPresent()){
            if(result.hasErrors()){
                return validation(result);
            }
            Servicio servicio = oServicio.get();
            servicio.getNovedades().add(service.save(novedad));
            serviceServicio.save(servicio);
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
