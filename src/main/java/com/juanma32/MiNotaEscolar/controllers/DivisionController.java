package com.juanma32.MiNotaEscolar.controllers;

import com.juanma32.MiNotaEscolar.entities.Division;
import com.juanma32.MiNotaEscolar.entities.Escuela;
import com.juanma32.MiNotaEscolar.services.DivisionServiceImpl;
import com.juanma32.MiNotaEscolar.services.EscuelaServiceImpl;
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
@RequestMapping("/division")
@CrossOrigin(origins = "http://localhost:5173")
public class DivisionController {

    @Autowired
    private DivisionServiceImpl service;
    @Autowired
    private EscuelaServiceImpl serviceEsc;

    @PostMapping("/agregar/{id}")
    public ResponseEntity<?> save(@Valid @RequestBody Division division,BindingResult result, @PathVariable Long id) {

        Optional<Escuela> o = serviceEsc.findById(id);
        if(o.isPresent()){
            if(result.hasErrors()){
                return validation(result);
            }
            Escuela escuela = o.get();
            List<Division> div= escuela.getDivision();
            div.add(division);
            escuela.setDivision(div);
            service.save(division);
            return ResponseEntity.status(201).body(serviceEsc.save(escuela));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> findAll(@PathVariable Long id){
        Optional<Escuela> o = serviceEsc.findById(id);
        if(o.isPresent()){
            Escuela esc = o.get();
            return ResponseEntity.status(200).body(esc.getDivision());
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
