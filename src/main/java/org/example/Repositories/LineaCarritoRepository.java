package org.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.Model.LineaCarrito;

@Repository
public interface LineaCarritoRepository extends JpaRepository<LineaCarrito, Long> {
}