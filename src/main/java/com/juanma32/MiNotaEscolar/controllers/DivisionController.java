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
            System.out.println(escuela);
            List<Division> div= escuela.getDivision();
            System.out.println(div);
            div.add( service.save(division));
            escuela.setDivision(div);
            return ResponseEntity.status(201).body(serviceEsc.save(escuela));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Division division,BindingResult result,@PathVariable Long id){
        Optional<Division> o = service.findById(id);
        if(o.isPresent()){
            if(result.hasErrors()){
                return validation(result);
            }
            Division divisionDb = o.get();
            divisionDb.setTurno(division.getTurno());
            divisionDb.setResolucion(division.getResolucion());
            divisionDb.setModalidad(division.getModalidad());
            divisionDb.setAlta(division.getAlta());
            return ResponseEntity.status(201).body(service.save(divisionDb));
        }
        return ResponseEntity.notFound().build();
     }

     @DeleteMapping("/eliminar/{idEsc}/{id}")
     public ResponseEntity<?> delete(@PathVariable Long idEsc,@PathVariable Long id){
         Optional<Escuela> oEsc = serviceEsc.findById(idEsc);
         Optional<Division> o = service.findById(id);
         if(oEsc.isPresent()){
            Escuela escuela = oEsc.get();
            List<Division> div = escuela.getDivision();
            Division division = o.get();
            div.remove(division);
            serviceEsc.save(escuela);

            return ResponseEntity.noContent().build();//204
        }
        return  ResponseEntity.notFound().build();
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

    @GetMapping("/ver/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Division> o = service.findById(id);
        if(o.isPresent()){
            Division division = o.get();
            return ResponseEntity.ok(division);
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
