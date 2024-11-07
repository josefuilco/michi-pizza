package com.mishipizzeria.apirest.apirest.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mishipizzeria.apirest.apirest.Entities.Categoria;
import com.mishipizzeria.apirest.apirest.Repositories.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository respository;

    @Override
    public Categoria CreateCategoria(Categoria categoria) {
        // TODO Auto-generated method stub
        return respository.save(categoria);
    }

    @Override
    public String deleteCategoria(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Categoria> findAll() {
        // TODO Auto-generated method stub
        return respository.findAll();
    }

    @Override
    public Categoria findCategoriatById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Categoria updateCategoria(Long id, Categoria categoria) {
        // TODO Auto-generated method stub
        return null;
    }
}
