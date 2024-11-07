package com.mishipizzeria.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mishipizzeria.apirest.apirest.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Long> {

}
