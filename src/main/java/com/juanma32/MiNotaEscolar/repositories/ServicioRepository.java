package com.juanma32.MiNotaEscolar.repositories;

import com.juanma32.MiNotaEscolar.entities.Servicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio,Long> {


@Query(value = "SELECT DISTINCT S.* FROM ESCUELAS E "+
        "INNER JOIN ESCUELAS_CARGO EC ON E.ID = EC.ESCUELA_ID "+
        "INNER JOIN CARGOS C ON EC.CARGO_ID = C.ID "+
        "INNER JOIN CARGOS_SERVICIO CS ON C.ID = CS.CARGO_ID "+
        "INNER JOIN SERVICIOS S ON CS.SERVICIO_ID = S.ID "+
        "WHERE E.ID = :idEscuela",nativeQuery = true)
    Page<Servicio> findAll(Pageable page, Long idEscuela);


Optional<Servicio> findByCargoId(Long idCargo);
}
