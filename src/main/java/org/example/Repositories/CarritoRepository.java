package org.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.Model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}