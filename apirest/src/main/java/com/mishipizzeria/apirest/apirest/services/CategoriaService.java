package com.mishipizzeria.apirest.apirest.services;

import java.util.List;

import com.mishipizzeria.apirest.apirest.Entities.Categoria;

public interface CategoriaService {
    List<Categoria> findAll();

    Categoria CreateCategoria(Categoria categoria);

    Categoria findCategoriatById(Long id);

    Categoria updateCategoria(Long id, Categoria categoria);

    String deleteCategoria(Long id);
}
