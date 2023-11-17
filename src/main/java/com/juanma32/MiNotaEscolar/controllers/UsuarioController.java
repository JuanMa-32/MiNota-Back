package com.juanma32.MiNotaEscolar.controllers;

import com.juanma32.MiNotaEscolar.entities.Division;
import com.juanma32.MiNotaEscolar.entities.Usuario;
import com.juanma32.MiNotaEscolar.services.DivisionServiceImpl;
import com.juanma32.MiNotaEscolar.services.UsuarioServiceImpl;
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
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl service;
    @Autowired
    private DivisionServiceImpl serviceDivision;

    @PostMapping("/agregar_alumno/nuevo/{idDivision}")
    public ResponseEntity<?> agregarAlumno(@Valid @RequestBody Usuario alumno, BindingResult result, @PathVariable Long idDivision) {
        Optional<Division> o = serviceDivision.findById(idDivision);
        if (o.isPresent()) {
            if (result.hasErrors()) {
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

    @PutMapping("/agregar_alumno/{idAlumno}/{idDivision}")
    public ResponseEntity<?> agregarAlumnoExistente(@PathVariable Long idAlumno, @PathVariable Long idDivision) {
        Optional<Usuario> o = service.findById(idAlumno);
        Optional<Division> oDiv = serviceDivision.findById(idDivision);
        if (o.isPresent() && oDiv.isPresent()) {
            Usuario usuario = o.get();
            Division division = oDiv.get();

            //traigo la lista de alumnos de la division y guardo el usuario.
            division.getAlumnos().add(usuario);
            serviceDivision.save(division);

            return ResponseEntity.status(201).body(division.getAlumnos());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> updateAlumno(@Valid @RequestBody Usuario alumno, BindingResult result, @PathVariable Long id) {
        Optional<Usuario> o = service.findById(id);
        if (o.isPresent()) {
            if (result.hasErrors()) {
                return validation(result);
            }
            Usuario alumnoDb = o.get();
            //datos domicilio
            alumnoDb.setCalle(alumno.getCalle());
            alumnoDb.setNumero(alumno.getNumero());
            alumnoDb.setLocalidad(alumno.getLocalidad());
            alumnoDb.setCodigoPostal(alumno.getCodigoPostal());
            alumnoDb.setBarrio(alumno.getBarrio());
            alumnoDb.setManzana(alumno.getManzana());
            alumnoDb.setCasa(alumno.getCasa());
            alumnoDb.setDepto(alumno.getDepto());
            alumnoDb.setPiso(alumno.getPiso());
            alumnoDb.setReferenciaDomicilio(alumno.getReferenciaDomicilio());

            //datos personales
            alumnoDb.setGenero(alumno.getGenero());
            alumnoDb.setEstadoCivil(alumno.getEstadoCivil());
            alumnoDb.setOcupacion(alumno.getOcupacion());
            alumnoDb.setNivelEstudio(alumno.getNivelEstudio());
            alumnoDb.setGrupoSanguineo(alumno.getGrupoSanguineo());
            alumnoDb.setObraSocial(alumno.getObraSocial());
            alumnoDb.setTelefonoFijo(alumno.getTelefonoFijo());
            alumnoDb.setCelular(alumno.getCelular());
            alumnoDb.setPrestadora(alumno.getPrestadora());

            return ResponseEntity.status(201).body(service.save(alumnoDb));

        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/division/alumnos/{idDivision}")
    public ResponseEntity<?> findAllAlumnos(@PathVariable Long idDivision) {
        Optional<Division> o = serviceDivision.findById(idDivision);
        if (o.isPresent()) {
            Division division = o.get();
            return ResponseEntity.ok(division.getAlumnos());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/alumno/buscar/{page}")
    public ResponseEntity<?> findByAlumno(@PathVariable int page,@RequestParam String nombre,@RequestParam String apellido){
        Pageable pageable = PageRequest.of(page,5);
        return ResponseEntity.ok(service.findByNombreAndApellido(nombre,apellido,pageable));
    }

    @GetMapping("/ver/{dni}")
    public ResponseEntity<?> findByDni(@PathVariable Integer dni) {
        return ResponseEntity.ok(service.findByDni(dni));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Usuario> o = service.findById(id);
        if (o.isPresent()) {
            Usuario alumno = o.get();
            return ResponseEntity.ok(alumno);
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
