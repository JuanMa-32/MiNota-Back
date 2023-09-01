package com.juanma32.MiNotaEscolar.repositories;

import com.juanma32.MiNotaEscolar.entities.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {
    List<Publicacion> findAllByOrderByFechaDePublicacionDesc();
}
