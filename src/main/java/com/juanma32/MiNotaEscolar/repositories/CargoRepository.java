package com.juanma32.MiNotaEscolar.repositories;

import com.juanma32.MiNotaEscolar.entities.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo,Long> {

    Page<Cargo> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM CARGOS " +
            "INNER JOIN ESCUELAS_CARGO ON CARGOS.ID = ESCUELAS_CARGO.CARGO_ID " +
            "WHERE ESCUELAS_CARGO.ESCUELA_ID = :escuelaId", nativeQuery = true)
    Page<Cargo> findCargosByEscuelaId(Long escuelaId,Pageable pageable);
}
