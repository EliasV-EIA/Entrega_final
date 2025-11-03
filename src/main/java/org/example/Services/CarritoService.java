package org.example.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.Model.Carrito;
import org.example.Model.LineaCarrito;
import org.example.Model.Producto;
import org.example.Repositories.CarritoRepository;

import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public Optional<Carrito> getById(Long id) {
        return carritoRepository.findById(id);
    }

    public Carrito save(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public void agregarProducto(Carrito carrito, Producto producto, int cantidad) {
        double subtotal = producto.getPrecio() * cantidad;
        LineaCarrito linea = new LineaCarrito(cantidad, subtotal, producto);
        carrito.agregarLinea(linea);
        carritoRepository.save(carrito);
    }

    public void eliminarProducto(Carrito carrito, Producto producto) {
        carrito.getLineas().removeIf(l -> l.getProducto().getId().equals(producto.getId()));
        carritoRepository.save(carrito);
    }
}