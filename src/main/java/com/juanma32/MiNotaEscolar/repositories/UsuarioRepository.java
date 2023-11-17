package com.juanma32.MiNotaEscolar.repositories;

import com.juanma32.MiNotaEscolar.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByDni(Integer dni);


    //QUERY
    @Query( value = "SELECT a FROM Usuario a WHERE a.nombre LIKE %:nombre% AND a.apellido LIKE %:apellido")
    Page<Usuario> findByApellidoAndNombre(@Param("nombre") String nombre, @Param("apellido") String apellido, Pageable pageable);

    Page<Usuario> findByNombreContainingAndApellidoContaining(@Param("nombre") String nombre,@Param("apellido") String apellido,Pageable pageable);
}
