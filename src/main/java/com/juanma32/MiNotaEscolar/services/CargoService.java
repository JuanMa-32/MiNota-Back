package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Cargo;

import java.util.List;
import java.util.Optional;

public interface CargoService {

    Cargo save(Cargo cargo);
    Optional<Cargo> findById(Long id);
    List<Cargo> findAll();
    void deleteById(Long id);
}
