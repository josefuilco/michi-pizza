package com.mishipizzeria.apirest.apirest.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mishipizzeria.apirest.apirest.Entities.SecurityUser;
import com.mishipizzeria.apirest.apirest.Entities.User;
import com.mishipizzeria.apirest.apirest.Repositories.AuthRepository;

public class AuthServiceImpl implements UserDetailsService {

    private AuthRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepository.findByNombre(nombre);
        if (user == null) {
            throw new UsernameNotFoundException("no se encontro al usuario");
        }
        return new SecurityUser(user);
    }

}
