package org.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.Model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto findByNombre(String nombre);
}
