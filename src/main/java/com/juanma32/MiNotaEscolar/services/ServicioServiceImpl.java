package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Servicio;
import com.juanma32.MiNotaEscolar.repositories.ServicioRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepository repository;


    @Override
    @Transactional
    public Servicio save(Servicio servicio) {
        return repository.save(servicio);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Servicio> findById(Long id) {
      return  repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Servicio> findAll( Long idEscuela,int mes,int anio,Pageable page) {
        return repository.findAllPorMesYAnio( idEscuela, mes, anio,page);
    }

    @Override
    public Optional<Servicio> findByCargoId(Long idCargo) {
        return repository.findByCargoId(idCargo);
    }


}
