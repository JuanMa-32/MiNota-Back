package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Cargo;
import com.juanma32.MiNotaEscolar.entities.Escuela;
import com.juanma32.MiNotaEscolar.repositories.EscuelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EscuelaServiceImpl implements EscuelaService{

    @Autowired
    private EscuelaRepository repository;
    @Override
    @Transactional
    public Escuela save(Escuela escuela) {
        return repository.save(escuela);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Escuela> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Escuela> findById(Long id) {
        return repository.findById(id);
    }


}
