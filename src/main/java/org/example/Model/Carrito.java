//package org.example.Model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Table(name = "carritos")
//@Data
//@NoArgsConstructor
//public class Carrito {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private Long id_cliente;
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "permisos_edicion", joinColumns = @JoinColumn(name = "cliente_id"))
//    @Column(name = "permiso")
//    private List nombre;
//    private String descripcion;
//
////    public Carrito(String nombre, String descripcion) {
////        this.nombre = nombre;
////        this.descripcion = descripcion;
////    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
////    public String getNombre() {
////        return nombre;
////    }
//
////    public void setNombre(String nombre) {
////        this.nombre = nombre;
////    }
//
//    public String getDescripcion() {
//        return descripcion;
//    }
//
//    public void setDescripcion(String descripcion) {
//        this.descripcion = descripcion;
//    }
//}