package org.example.Services;

import org.example.Model.Carrito;
import org.example.Model.LineaCarrito;
import org.example.Model.Producto;
import org.example.Repositories.CarritoRepository;
import org.example.Repositories.LineaCarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LineaCarritoService {

    @Autowired
    private LineaCarritoRepository lineacarritoRepository;

    public Optional<LineaCarrito> getById(Long id) {
        return lineacarritoRepository.findById(id);
    }

    public LineaCarrito save(LineaCarrito lineacarrito) {
        return lineacarritoRepository.save(lineacarrito);
    }

    public boolean deleteLineacarrito(Long id) {
        if (lineacarritoRepository.existsById(id)) {
            lineacarritoRepository.deleteById(id);
            return true;
        }
        return false;
    }
//    public void agregarProducto(LineaCarrito carrito, Producto producto, int cantidad) {
//        double subtotal = producto.getPrecio() * cantidad;
//        LineaCarrito linea = new LineaCarrito(cantidad, subtotal, producto);
//        carrito.agregarLinea(linea);
//        carritoRepository.save(carrito);
//    }

//    public void eliminarProducto(Carrito carrito, Producto producto) {
//        carrito.getLineas().removeIf(l -> l.getProducto().getId().equals(producto.getId()));
//        carritoRepository.save(carrito);
//    }
}