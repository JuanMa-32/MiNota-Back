package com.juanma32.MiNotaEscolar.repositories;

import com.juanma32.MiNotaEscolar.entities.Novedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovedadRepository  extends JpaRepository<Novedad,Long> {
}
