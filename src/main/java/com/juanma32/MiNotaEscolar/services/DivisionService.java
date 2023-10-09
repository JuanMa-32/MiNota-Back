package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Division;

import java.util.List;
import java.util.Optional;

public interface DivisionService {

    Division save(Division division);
    List<Division> findAll();
    Optional<Division> findById(Long id);
    void delete(Long id);


}
