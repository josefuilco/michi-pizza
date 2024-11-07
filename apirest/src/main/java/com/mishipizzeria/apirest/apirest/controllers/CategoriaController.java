package com.mishipizzeria.apirest.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mishipizzeria.apirest.apirest.Entities.Categoria;
import com.mishipizzeria.apirest.apirest.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
  @Autowired
  private CategoriaService service;
  @GetMapping
  public List<Categoria> getAll(){
    return service.findAll();
  }
  @PostMapping
  public Categoria creatCategoria(@RequestBody Categoria categoria){
    return service.CreateCategoria(categoria);
  }
}
