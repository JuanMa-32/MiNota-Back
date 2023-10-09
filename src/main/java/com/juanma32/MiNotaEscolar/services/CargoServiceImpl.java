package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Cargo;
import com.juanma32.MiNotaEscolar.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CargoServiceImpl implements CargoService{

    @Autowired
    private CargoRepository repository;

    @Override
    @Transactional
    public Cargo save(Cargo cargo) {
        return repository.save(cargo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cargo> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cargo> findAll(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cargo> findAll(Long id,Pageable pageable) {
        return repository.findCargosByEscuelaId(id,pageable);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Cargo> findByDivision(Long idDivision) {
        return repository.findByDivisionId(idDivision);
    }

}
