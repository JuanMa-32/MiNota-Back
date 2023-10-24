package com.juanma32.MiNotaEscolar.controllers;

import com.juanma32.MiNotaEscolar.entities.Cargo;
import com.juanma32.MiNotaEscolar.entities.Servicio;
import com.juanma32.MiNotaEscolar.services.CargoService;
import com.juanma32.MiNotaEscolar.services.ServicioServiceImpl;
import com.juanma32.MiNotaEscolar.services.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<?> save(@Valid @RequestBody Servicio servicio, BindingResult resultServicio, @PathVariable Long idCargo) {
        Optional<Cargo> o = serviceCar.findById(idCargo);

        if (o.isPresent()) {
            if (resultServicio.hasErrors()) {
                return validation(resultServicio);
            }
            Cargo cargo = o.get();
            List<Servicio> servicios = cargo.getServicio();

            if (servicio.getUsuario().getId() == null) {
                servicePers.save(servicio.getUsuario());
            }
            servicio.setCargo(cargo);//guardo en servicio el cargo
            Servicio serv = service.save(servicio);
            servicios.add(serv);
            cargo.setServicio(servicios);
            serviceCar.save(cargo);
            return ResponseEntity.status(201).body(serv);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit( @RequestBody Servicio servicio,@PathVariable Long id){
        Optional<Servicio> o = service.findById(id);
        if(o.isPresent()){
            Servicio serv = o.get();
            serv.setObservacion(servicio.getObservacion());
            return ResponseEntity.status(201).body(service.save(serv));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/baja/{id}")
    public ResponseEntity<?> darBaja(@PathVariable Long id,@RequestParam String motivoBaja,@RequestParam Date baja){
        Optional<Servicio> o = service.findById(id);
        if(o.isPresent()){
            Servicio servicio = o.get();
            servicio.setBaja(baja);
            servicio.setMotivoBaja(motivoBaja);
            service.save(servicio);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{idEscuela}/{page}")
    public ResponseEntity<?> findAll(@PathVariable Long idEscuela, @PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 10);

        return ResponseEntity.ok(service.findAll(pageable, idEscuela));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Servicio> o = service.findById(id);

        if (o.isPresent()) {
            Servicio serv = o.get();
            return ResponseEntity.ok(serv);
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
