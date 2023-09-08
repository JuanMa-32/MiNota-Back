package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Division;
import com.juanma32.MiNotaEscolar.repositories.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionServiceImpl implements DivisionService{

    @Autowired
    private DivisionRepository repository;

    @Override
    public Division save(Division division) {
        return repository.save(division);
    }

    @Override
    public List<Division> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Division> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
