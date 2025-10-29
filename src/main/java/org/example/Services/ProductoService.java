package org.example.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.Model.Producto;
import org.example.Repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }
    public Optional<Producto> getProductoByNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }
    public boolean checkByNombre(String nombre){
        if (productoRepository.existsByNombre(nombre)){
            return true;
        }
        else return false;

    }
    public boolean deleteProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}