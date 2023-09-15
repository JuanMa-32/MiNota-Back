package com.juanma32.MiNotaEscolar.controllers;

import com.juanma32.MiNotaEscolar.entities.Cargo;
import com.juanma32.MiNotaEscolar.entities.Division;
import com.juanma32.MiNotaEscolar.services.CargoServiceImpl;
import com.juanma32.MiNotaEscolar.services.DivisionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cargo")
@CrossOrigin(origins = "http://localhost:5173")
public class CargoController {

    @Autowired
    private CargoServiceImpl service;
    @Autowired
    private DivisionServiceImpl serviceDiv;


    @PostMapping("/{id}")
    public ResponseEntity<?> save(@Valid @RequestBody Cargo cargo,BindingResult result,@PathVariable Long id){
        Optional<Division> o = serviceDiv.findById(id);
        if(o.isPresent()){
            if(result.hasErrors()) {
                return validation(result);
            }
            Division div = o.get();
            div.setCargo( service.save(cargo));
            return ResponseEntity.status(201).body(serviceDiv.save(div));
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
