package com.juanma32.MiNotaEscolar.controllers;

import com.juanma32.MiNotaEscolar.entities.Cargo;
import com.juanma32.MiNotaEscolar.entities.Escuela;
import com.juanma32.MiNotaEscolar.services.CargoServiceImpl;
import com.juanma32.MiNotaEscolar.services.EscuelaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cargo")
@CrossOrigin(origins = "http://localhost:5173")
public class CargoController {

    @Autowired
    private CargoServiceImpl service;
    @Autowired
    private EscuelaServiceImpl serviceEsc;


    @PostMapping("/{id}")
    public ResponseEntity<?> save(@Valid @RequestBody Cargo cargo, BindingResult result, @PathVariable Long id) {
        Optional<Escuela> o = serviceEsc.findById(id);
        if (o.isPresent()) {
            if (result.hasErrors()) {
                return validation(result);
            }
            Escuela esc = o.get();
            List<Cargo> cargos = esc.getCargo();
            cargos.add(service.save(cargo));
            return ResponseEntity.status(201).body(serviceEsc.save(esc));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/{page}")
    public ResponseEntity<?> findAllCargos(@PathVariable Long id,@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page,10);
       return ResponseEntity.ok(service.findAll(id,pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Cargo> o = service.findById(id);
        if(o.isPresent()){
            Cargo cargo = o.get();
            return ResponseEntity.ok(cargo);
        }
        return ResponseEntity.badRequest().build();
     }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
