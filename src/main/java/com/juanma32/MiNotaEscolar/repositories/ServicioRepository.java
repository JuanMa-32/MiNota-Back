package com.juanma32.MiNotaEscolar.repositories;

import com.juanma32.MiNotaEscolar.entities.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio,Long> {
}
