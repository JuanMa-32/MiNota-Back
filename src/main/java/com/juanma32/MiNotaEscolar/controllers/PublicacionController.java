package com.juanma32.MiNotaEscolar.controllers;

import com.juanma32.MiNotaEscolar.entities.Publicacion;
import com.juanma32.MiNotaEscolar.services.PublicacionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/publicacion")
@CrossOrigin(origins = "http://localhost:5173")
public class PublicacionController {

    @Autowired
    private PublicacionServiceImpl service;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Publicacion publicacion, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(201).body(service.save(publicacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Publicacion publicacion,BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return validation(result);
        }
        Optional<Publicacion> o = service.findById(id);
        if(o.isPresent()){
            Publicacion publi = o.get();
            publi.setTitulo(publicacion.getTitulo());
            publi.setCuerpo(publicacion.getCuerpo());
            return ResponseEntity.ok(service.save(publi));
        }
       return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(service.findAll());
    }





    private ResponseEntity<?> validation(BindingResult result){
        Map<String,String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(),e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
