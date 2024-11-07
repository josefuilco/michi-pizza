package com.mishipizzeria.apirest.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mishipizzeria.apirest.apirest.Entities.Producto;
import com.mishipizzeria.apirest.apirest.dto.ProductoDto;
import com.mishipizzeria.apirest.apirest.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductService service;

    @GetMapping
    public List<Producto> getAllProductos() {

        return service.finAll();
    }

    @PostMapping(path = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProducto(@Valid @RequestBody ProductoDto productoDTO, @RequestParam("imagen") MultipartFile file, BindingResult result) {

        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            Producto producto = service.crearProducto(productoDTO);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Ocurrió un error al crear el producto");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public Producto findProductById(@PathVariable Long id) {
        return service.findProductById(id);
    }

    @PutMapping
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return service.updateProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return service.deleteProduct(id);

    }

    private ResponseEntity<Map<String, String>> validation(BindingResult result) {
        // TODO Auto-generated method stub
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
