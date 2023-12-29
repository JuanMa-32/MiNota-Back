package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Usuario;
import com.juanma32.MiNotaEscolar.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUserDetailService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> o = repository.findByEmail(email);

        if(o.isPresent()){
            Usuario us= o.get();

            List<GrantedAuthority> permisos = new ArrayList<>();
            permisos.add(new SimpleGrantedAuthority("ROLE_"+us.getRole().toString()));

            return new User(us.getEmail(),us.getPassword(),true,true,true,true,permisos);
        }
        throw new UsernameNotFoundException("Usuario no reconocido");
    }
}
