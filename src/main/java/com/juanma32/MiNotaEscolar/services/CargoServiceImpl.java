package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Cargo;
import com.juanma32.MiNotaEscolar.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoServiceImpl implements CargoService{

    @Autowired
    private CargoRepository repository;

    @Override
    public Cargo save(Cargo cargo) {
        return repository.save(cargo);
    }

    @Override
    public Optional<Cargo> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Cargo> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
