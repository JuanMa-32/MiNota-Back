package com.juanma32.MiNotaEscolar.controllers;

import com.juanma32.MiNotaEscolar.entities.Escuela;
import com.juanma32.MiNotaEscolar.services.EscuelaServiceImpl;
import com.juanma32.MiNotaEscolar.services.LocalidadServiceImpl;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/escuela")
@CrossOrigin(origins = "http://localhost:5173")
public class EscuelaController {

    @Autowired
    private EscuelaServiceImpl service;

    @Autowired
    private LocalidadServiceImpl serviceLocalidad;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Escuela escuela,BindingResult result){
        System.out.println("hola");
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.ok(service.save(escuela));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Escuela> o = service.findById(id);
        if(o.isPresent()){
            Escuela escuela = o.get();
            return ResponseEntity.ok(escuela);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/localidades")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(serviceLocalidad.findAll());
    }



    private ResponseEntity<?> validation(BindingResult result){
        Map<String,String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(),e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
