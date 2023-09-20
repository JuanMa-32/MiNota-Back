package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CargoService {

    Cargo save(Cargo cargo);
    Optional<Cargo> findById(Long id);
    List<Cargo> findAll(Long id);
    Page<Cargo> findAll(Long id,Pageable pageable);
    void deleteById(Long id);
}
