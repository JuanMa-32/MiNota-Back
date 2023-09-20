package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Cargo;
import com.juanma32.MiNotaEscolar.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public List<Cargo> findAll(Long id) {
        return null;
    }

    @Override
    public Page<Cargo> findAll(Long id,Pageable pageable) {
        return repository.findCargosByEscuelaId(id,pageable);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
