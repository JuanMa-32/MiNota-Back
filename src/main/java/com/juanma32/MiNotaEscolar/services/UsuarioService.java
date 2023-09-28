package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByDni(Integer dni);
    Usuario save(Usuario usuario);


}
