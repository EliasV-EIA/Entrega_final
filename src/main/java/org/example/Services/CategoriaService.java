package org.example.Services;

import org.example.Model.Producto;
import org.example.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Producto> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Producto saveProducto(Producto producto) {
        return categoriaRepository.save(producto);
    }

    public Optional<Producto> getProductoById(Long id) {
        return categoriaRepository.findById(id);
    }

    public boolean deleteProducto(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}