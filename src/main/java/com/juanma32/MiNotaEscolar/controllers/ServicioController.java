package com.juanma32.MiNotaEscolar.controllers;

import com.juanma32.MiNotaEscolar.entities.Cargo;
import com.juanma32.MiNotaEscolar.entities.Servicio;
import com.juanma32.MiNotaEscolar.services.CargoService;
import com.juanma32.MiNotaEscolar.services.ServicioServiceImpl;
import com.juanma32.MiNotaEscolar.services.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/servicio")
@CrossOrigin(origins = "http://localhost:5173")
public class ServicioController {

    @Autowired
    private ServicioServiceImpl service;
    @Autowired
    private UsuarioServiceImpl servicePers;
    @Autowired
    private CargoService serviceCar;



    @PostMapping("/{idCargo}")
    public ResponseEntity<?> save(@Valid @RequestBody Servicio servicio, BindingResult resultServicio,@PathVariable Long idCargo) {

        Optional<Cargo> o = serviceCar.findById(idCargo);
        if(o.isPresent()){
            if (resultServicio.hasErrors()) {
                return validation(resultServicio);
            }
            Cargo cargo = o.get();
            List<Servicio> servicios = cargo.getServicio();


            if(servicio.getUsuario().getId() == null ){
                System.out.println("holaa");
                servicePers.save(servicio.getUsuario());
            }

            servicios.add( service.save(servicio));
            cargo.setServicio(servicios);
            return ResponseEntity.status(201).body(serviceCar.save(cargo));
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
