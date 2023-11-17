package com.juanma32.MiNotaEscolar.controllers;

import com.juanma32.MiNotaEscolar.entities.Cargo;
import com.juanma32.MiNotaEscolar.entities.Division;
import com.juanma32.MiNotaEscolar.entities.Escuela;
import com.juanma32.MiNotaEscolar.entities.Usuario;
import com.juanma32.MiNotaEscolar.services.CargoServiceImpl;
import com.juanma32.MiNotaEscolar.services.DivisionServiceImpl;
import com.juanma32.MiNotaEscolar.services.EscuelaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/division")
@CrossOrigin(origins = "http://localhost:5173")
public class DivisionController {

    @Autowired
    private DivisionServiceImpl service;
    @Autowired
    private EscuelaServiceImpl serviceEsc;
    @Autowired
    private CargoServiceImpl serviceCargo;

    @PostMapping("/agregar/{id}")
    public ResponseEntity<?> save(@Valid @RequestBody Division division, BindingResult result, @PathVariable Long id) {
        Optional<Escuela> o = serviceEsc.findById(id);
        if (o.isPresent()) {
            if (result.hasErrors()) {
                return validation(result);
            }
            Escuela escuela = o.get();

            List<Division> div = escuela.getDivision();

            div.add(service.save(division));
            escuela.setDivision(div);
            return ResponseEntity.status(201).body(serviceEsc.save(escuela));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Division division, BindingResult result, @PathVariable Long id) {
        Optional<Division> o = service.findById(id);
        if (o.isPresent()) {
            if (result.hasErrors()) {
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

    @PutMapping("/mover_alumnos/{id}")
    public ResponseEntity<?> moverAlumnos(@RequestBody List<Usuario> alumnos, @PathVariable Long id, @RequestParam Long idDivisionDestino) {
        Optional<Division> o = service.findById(id);
        Optional<Division> oDos = service.findById(idDivisionDestino);
        if (o.isPresent() && oDos.isPresent()) {
            Division division = o.get();//Division que contiene los alumnos
            Division divisionDestino = oDos.get();//division que recibe los alumnos

            List<Usuario> alumnosDiv = division.getAlumnos();//list con los alumnos dw la division que seran removidos
            List<Usuario> alumnosDiv1 = divisionDestino.getAlumnos();//list con los alumnos dw la division que seran agregados

            alumnosDiv.removeIf(alumno -> alumnos.stream().anyMatch(a -> a.getId().equals(alumno.getId())));//recorro la list y los remuevo

            alumnosDiv1.addAll(alumnos);//guardo los alumnos en la nueva division
            service.save(division);
            service.save(divisionDestino);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{idEsc}/{id}")
    public ResponseEntity<?> delete(@PathVariable Long idEsc, @PathVariable Long id) {
        Optional<Escuela> oEsc = serviceEsc.findById(idEsc);
        Optional<Division> o = service.findById(id);
        Optional<Cargo> oCargo = serviceCargo.findByDivision(id);
        if (oEsc.isPresent()) {
            Escuela escuela = oEsc.get();

            List<Division> div = escuela.getDivision();
            Division division = o.get();

            //busco si esta division tiene un cargo y elimino primero el cargo
            if (oCargo.isPresent()) {
                Cargo cargo = oCargo.get();
                List<Cargo> cargos = escuela.getCargo();
                cargos.remove(cargo);
            }
            div.remove(division);
            serviceEsc.save(escuela);

            return ResponseEntity.noContent().build();//204
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> findAll(@PathVariable Long id) {
        Optional<Escuela> o = serviceEsc.findById(id);
        if (o.isPresent()) {
            Escuela esc = o.get();
            return ResponseEntity.status(200).body(esc.getDivision());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/ver/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Division> o = service.findById(id);
        if (o.isPresent()) {
            Division division = o.get();
            return ResponseEntity.ok(division);
        }
        return ResponseEntity.notFound().build();
    }

    //traigo el cargo que tiene asociado la Division
    @GetMapping("/cargoDivision/{id}")
    public ResponseEntity<?> findCargo(@PathVariable Long id) {
        Optional<Cargo> o = serviceCargo.findByDivision(id);
        if (o.isPresent()) {
            Cargo cargo = o.get();
            return ResponseEntity.ok(cargo);
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
