package com.juanma32.MiNotaEscolar.repositories;

import com.juanma32.MiNotaEscolar.entities.Servicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    Optional<Servicio> findByCargoId(Long idCargo);

    @Query(value = "SELECT S.* " +
            "FROM ESCUELAS E " +
            "INNER JOIN ESCUELAS_CARGO EC ON E.ID = EC.ESCUELA_ID " +
            "INNER JOIN CARGOS C ON EC.CARGO_ID = C.ID " +
            "INNER JOIN CARGOS_SERVICIO CS ON C.ID = CS.CARGO_ID " +
            "INNER JOIN SERVICIOS S ON CS.SERVICIO_ID = S.ID " +
            "WHERE E.ID = :idEscuela " +
            "AND ( " +
            "    (YEAR(S.alta) < :anio OR (YEAR(S.alta) = :anio AND MONTH(S.alta) <= :mes)) " +
            "    AND (S.baja IS NULL OR (YEAR(S.baja) IS NULL OR (YEAR(S.baja) = :anio AND MONTH(S.baja) >= :mes))) " +
            "    OR (YEAR(S.alta) = :anio AND MONTH(S.alta) < :mes AND (YEAR(S.baja) = :anio AND MONTH(S.baja) = :mes)) " +
            ") ",
            nativeQuery = true)
    Page<Servicio> findAllPorMesYAnio(@Param("idEscuela") Long idEscuela, @Param("mes") int mes, @Param("anio") int anio, Pageable page);
}
