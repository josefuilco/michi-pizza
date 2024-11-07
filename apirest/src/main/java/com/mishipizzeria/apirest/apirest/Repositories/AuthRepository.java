package com.mishipizzeria.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mishipizzeria.apirest.apirest.Entities.User;

@Repository

public interface AuthRepository extends JpaRepository<User, Long> {

    User findByNombre(String nombre);
}
