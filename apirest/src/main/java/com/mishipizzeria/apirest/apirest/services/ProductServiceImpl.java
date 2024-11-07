package com.mishipizzeria.apirest.apirest.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.mishipizzeria.apirest.apirest.Entities.Categoria;
import com.mishipizzeria.apirest.apirest.Entities.Producto;
import com.mishipizzeria.apirest.apirest.Repositories.CategoriaRepository;
import com.mishipizzeria.apirest.apirest.Repositories.ProductoRepository;
import com.mishipizzeria.apirest.apirest.dto.ProductoDto;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // para transacciones en la base de datos con commit y rollback,ademas de
    // mantener la conexion abierta dentro dek metodo service
    @Override
    @Transactional(readOnly = true)
    public List<Producto> finAll() {
        // TODO Auto-generated method stub
        return repository.findAll();
    }

    @Override
    public String deleteProduct(Long id) {
        // TODO Auto-generated method stub
        Producto findProduct = this.findProductById(id);
        repository.delete(findProduct);
        return "El producto  con el ID: " + id + " fue eliminado correctamente";
    }

    @Override
    public Producto findProductById(Long id) {
        // TODO Auto-generated method stub
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "El producto con ID " + id + " no se encontró"));
    }

    @Override
    public Producto updateProducto(Long id, Producto producto) {
        // TODO Auto-generated method stub
        Producto findProduct = this.findProductById(id);
        findProduct.setNombre(producto.getNombre());
        findProduct.setDetalle(producto.getDetalle());
        findProduct.setPrecio(producto.getPrecio());
        return repository.save(findProduct);
    }

    @Override
    public Producto crearProducto(ProductoDto productoDTO) {

        try {
            MultipartFile file = productoDTO.getImagen();

            String fileName = UUID.randomUUID().toString();
            byte[] bytes = file.getBytes();
            String fileOriginalName = file.getOriginalFilename();
            Long fileSize = file.getSize();
            long maxFileSize = 5 * 1024 * 1024;
            if (fileSize > maxFileSize) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tamaño del archivo excede el límite permitido (5 MB)");
            }
            if (!fileOriginalName.endsWith(".jpg") &&
            !fileOriginalName.endsWith(".jpeg") &&
            !fileOriginalName.endsWith(".png") &&
            !fileOriginalName.endsWith(".webp")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tamaño del archivo excede el límite permitido (5 MB)");
        }
            String fileExtension=fileOriginalName.substring((fileOriginalName.lastIndexOf(".")));
            String newFileName=fileName + fileExtension;
            File folder=new File("src/main/resources/picture/");

            if(!folder.exists()){
                folder.mkdirs();

            }

            Path path=Paths.get("src/main/resources/picture/" + newFileName);
            Files.write(path, bytes);
            Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            System.out.println(categoria);
            Producto producto = new Producto();

            producto.setNombre(productoDTO.getNombre());
            producto.setDetalle(productoDTO.getDetalle());
            producto.setPrecio(productoDTO.getPrecio());
            producto.setCategoria(categoria);
            // TODO Auto-generated method stub
            return repository.save(producto);

        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e.getMessage());
        }

    }

}
