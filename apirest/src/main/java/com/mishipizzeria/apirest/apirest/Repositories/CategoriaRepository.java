package com.mishipizzeria.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mishipizzeria.apirest.apirest.Entities.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

}
