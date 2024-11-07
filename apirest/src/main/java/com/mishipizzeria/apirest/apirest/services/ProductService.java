package com.mishipizzeria.apirest.apirest.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mishipizzeria.apirest.apirest.Entities.Producto;
import com.mishipizzeria.apirest.apirest.dto.ProductoDto;

public interface ProductService {
    List<Producto> finAll();

    Producto crearProducto(ProductoDto productoDTO );

    Producto findProductById(Long id);
    Producto updateProducto(Long id ,Producto producto);
    String deleteProduct(Long id );
}
