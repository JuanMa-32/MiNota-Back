package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Localidad;
import com.juanma32.MiNotaEscolar.repositories.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocalidadServiceImpl implements LocalidadService{

    @Autowired
    private LocalidadRepository repository;
    @Override
    @Transactional(readOnly = true)
    public List<Localidad> findAll() {
        return repository.findAll();
    }
}
