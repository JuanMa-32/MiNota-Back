package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Servicio;
import com.juanma32.MiNotaEscolar.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService{

    @Autowired
    private ServicioRepository repository;


    @Override
    public Servicio save(Servicio servicio) {
        return repository.save(servicio);
    }

    @Override
    public Optional<Servicio> findById(Long id) {
        return repository.findById(id);
    }
}
