package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Publicacion;
import com.juanma32.MiNotaEscolar.repositories.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionServiceImpl implements PublicacionService{

    @Autowired
    private PublicacionRepository repository;

    @Override
    @Transactional
    public Publicacion save(Publicacion publicacion) {
        return repository.save(publicacion);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Publicacion> findAll() {
        return repository.findAllByOrderByFechaDePublicacionDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Publicacion> findById(Long id) {
        return repository.findById(id);
    }
}
