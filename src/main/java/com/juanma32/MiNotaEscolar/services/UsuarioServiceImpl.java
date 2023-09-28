package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Usuario;
import com.juanma32.MiNotaEscolar.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Usuario> findByDni(Integer dni) {
        return repository.findByDni(dni);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }
}
