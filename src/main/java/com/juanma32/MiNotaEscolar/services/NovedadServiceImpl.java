package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Novedad;
import com.juanma32.MiNotaEscolar.repositories.NovedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NovedadServiceImpl implements NovedadService{

    @Autowired
    private NovedadRepository repository;

    @Override
    public Novedad save(Novedad novedad) {
        return repository.save(novedad);
    }
}
