package org.example.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carritos")
@Data
@NoArgsConstructor
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaCreacion = LocalDate.now();

    @OneToOne(mappedBy = "carrito", cascade = CascadeType.ALL)
    private Cliente cliente;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaCarrito> lineas = new ArrayList<>();

    public void agregarLinea(LineaCarrito linea) {
        lineas.add(linea);
        linea.setCarrito(this);
    }

    public void eliminarLinea(LineaCarrito linea) {
        lineas.remove(linea);
        linea.setCarrito(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<LineaCarrito> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaCarrito> lineas) {
        this.lineas = lineas;
    }
}
