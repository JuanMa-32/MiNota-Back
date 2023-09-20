package com.juanma32.MiNotaEscolar.controllers;

import com.juanma32.MiNotaEscolar.entities.Escuela;
import com.juanma32.MiNotaEscolar.services.EscuelaServiceImpl;
import com.juanma32.MiNotaEscolar.services.LocalidadServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> save(@Valid @RequestBody Escuela escuela, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.ok(service.save(escuela));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Escuela escuela, BindingResult result, @PathVariable Long id) {
        Optional<Escuela> o = service.findById(id);
        if (o.isPresent()) {
            if (result.hasErrors()) {
                return validation(result);
            }
            Escuela escuelaDb = o.get();
            escuelaDb.setSubCue(escuela.getSubCue());
            escuelaDb.setNombre(escuela.getNombre());
            escuelaDb.setCalle(escuela.getCalle());
            escuelaDb.setNumeroCalle(escuela.getNumeroCalle());
            escuelaDb.setBarrio(escuela.getBarrio());
            escuelaDb.setLocalidad(escuela.getLocalidad());
            escuelaDb.setCodigoPostal(escuela.getCodigoPostal());
            escuelaDb.setTelefono(escuela.getTelefono());
            escuelaDb.setEmail(escuela.getEmail());
            escuelaDb.setEmailAlternativo(escuela.getEmailAlternativo());
            escuelaDb.setFechaCreacion(escuela.getFechaCreacion());
            escuelaDb.setAnioResolucion(escuela.getAnioResolucion());
            escuelaDb.setNumeroResolucion(escuela.getNumeroResolucion());
            return ResponseEntity.status(201).body(service.save(escuelaDb));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/caracteristicas/{id}")
    public ResponseEntity<?> editarCaracteristicas(@Valid @RequestBody Escuela escuela, BindingResult result, @PathVariable Long id) {
        Optional<Escuela> o = service.findById(id);
        if (o.isPresent()) {
            if (result.hasErrors()) {
                return validation(result);
            }
            System.out.println("hola");
            Escuela esc = o.get();
            //contextp
            esc.setAmbito(escuela.getAmbito());
            esc.setModalidadCursado(escuela.getModalidadCursado());
            esc.setServicioNutricional(escuela.getServicioNutricional());
            esc.setComparteEntrada(escuela.getComparteEntrada());
            esc.setEspecialidad(escuela.getEspecialidad());
            //datos institucionales
            esc.setAlimentacionGratuita(escuela.getAlimentacionGratuita());
            esc.setEscuelaFrontera(escuela.getEscuelaFrontera());
            esc.setSector(escuela.getSector());
            esc.setProyectosAsignados(escuela.getProyectosAsignados());
            esc.setJornadaExtendida(escuela.getJornadaExtendida());
            esc.setMedioDeAcceso(escuela.getMedioDeAcceso());
            esc.setLineasDeColectivosDeAcceso(escuela.getLineasDeColectivosDeAcceso());
            esc.setEscuelaAlbergue(escuela.getEscuelaAlbergue());
            //tecnologia-equipamientp
            esc.setEquipamientoAlumnos(escuela.getEquipamientoAlumnos());
            esc.setEquipamientoAdministrativos(escuela.getEquipamientoAdministrativos());
            esc.setEquipamientoDocentes(escuela.getEquipamientoDocentes());
            //tecnologia-conectividad
            esc.setConectividad(escuela.getConectividad());
            esc.setProveedorServicio(escuela.getProveedorServicio());
            esc.setAnchoBanda(escuela.getAnchoBanda());
            esc.setFinanciado(escuela.getFinanciado());
            esc.setTipoDeConexion(escuela.getTipoDeConexion());
            esc.setAccesodeConexion(escuela.getAccesodeConexion());
            esc.setAulasConConexion(escuela.getAulasConConexion());
            esc.setFunciona(escuela.getFunciona());
            //infraestructura
            esc.setAulas(escuela.getAulas());
            esc.setCierrePerimetral(escuela.getCierrePerimetral());
            esc.setCalefaccion(escuela.getCalefaccion());
            esc.setVentilacion(escuela.getVentilacion());
            esc.setAlarma(escuela.getAlarma());
            esc.setEdificio(escuela.getEdificio());
            esc.setTipoGas(escuela.getTipoGas());
            esc.setTipoLuz(escuela.getTipoLuz());
            esc.setAgua(escuela.getAgua());
            esc.setCloacas(escuela.getCloacas());
            esc.setServiciosMunicipales(escuela.getServiciosMunicipales());
            esc.setLaboratorioInformatica(escuela.getLaboratorioInformatica());
            esc.setLaboratorioCiencias(escuela.getLaboratorioCiencias());
            esc.setEspacioTechado(escuela.getEspacioTechado());
            esc.setCancha(escuela.getCancha());
            esc.setMedidorLuz(escuela.getMedidorLuz());
            esc.setMedidorGaz(escuela.getMedidorGaz());
            esc.setCocina(escuela.getCocina());
            esc.setComedor(escuela.getComedor());
            esc.setSum(escuela.getSum());
            esc.setBombaAgua(escuela.getBombaAgua());
            esc.setBiblioteca(escuela.getBiblioteca());
            esc.setSalonMusica(escuela.getSalonMusica());
            esc.setSereno(escuela.getSereno());
            esc.setSenialCelular(escuela.getSenialCelular());
            esc.setSistemaSeguridad(escuela.getSistemaSeguridad());
            return ResponseEntity.status(201).body(service.save(esc));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Escuela> o = service.findById(id);
        if (o.isPresent()) {
            Escuela escuela = o.get();
            return ResponseEntity.ok(escuela);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<?> findAllEscuelas() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/localidades")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(serviceLocalidad.findAll());
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
