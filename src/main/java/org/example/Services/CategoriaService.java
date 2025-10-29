package org.example.Services;

import org.example.Model.Categoria;
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

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> getCategoriaById(Long id) {
        return categoriaRepository.findById(id);
    }
    public Optional<Categoria> getCategoriaByNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }

    public boolean checkByNombre(String nombre){
        if (categoriaRepository.existsByNombre(nombre)){
            return true;
        }
        else return false;

    }

    public boolean deleteCategoria(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}