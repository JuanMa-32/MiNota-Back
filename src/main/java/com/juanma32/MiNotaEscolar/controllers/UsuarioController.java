package com.juanma32.MiNotaEscolar.controllers;

import com.juanma32.MiNotaEscolar.entities.Division;
import com.juanma32.MiNotaEscolar.entities.Usuario;
import com.juanma32.MiNotaEscolar.services.DivisionServiceImpl;
import com.juanma32.MiNotaEscolar.services.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl service;
    @Autowired
    private DivisionServiceImpl serviceDivision;

    @PostMapping("/agregar_alumno/{idDivision}")
    public ResponseEntity<?> agregarAlumno(@Valid @RequestBody Usuario alumno, BindingResult result, @PathVariable Long idDivision) {
        Optional<Division> o = serviceDivision.findById(idDivision);
        if(o.isPresent()){
            if(result.hasErrors()){
                return validation(result);
            }
            //traigo la lista de alumnos de la division para setear el nuevo alumno
            Division division = o.get();
            List<Usuario> alumnos = division.getAlumnos();

            //guardo el alumno en la db y en la lista
            Usuario alumnoDB = service.save(alumno);
            alumnos.add(alumnoDB);
            //guardo la division con el alumno ya seteado
            serviceDivision.save(division);
            //retorno el alumno guardado
            return ResponseEntity.status(201).body(alumnoDB);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/division/alumnos/{idDivision}")
    public ResponseEntity<?> findAllAlumnos(@PathVariable Long idDivision){
        Optional<Division> o = serviceDivision.findById(idDivision);
        if(o.isPresent()){
            Division division = o.get();
            return ResponseEntity.ok(division.getAlumnos());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/ver/{dni}")
    public ResponseEntity<?> findByid(@PathVariable Integer dni) {
        return ResponseEntity.ok(service.findByDni(dni));
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
