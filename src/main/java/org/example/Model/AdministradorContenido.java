package org.example.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("ADMIN_CONTENIDO")
@Data
@NoArgsConstructor
public class AdministradorContenido extends Usuario {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "permisos_edicion", joinColumns = @JoinColumn(name = "admin_id"))
    @Column(name = "permiso")
    private Set<String> permisosDeEdicion = new HashSet<>();

    public AdministradorContenido( String nombre_usuario, String passwordHash, String rol, String fechaRegistro, String estadoCuenta,boolean nombre,boolean categoria, boolean descripcion,boolean precio, boolean stock, boolean fecha ){
        super(nombre_usuario, passwordHash,rol,fechaRegistro,estadoCuenta);
        if (nombre)
            this.permisosDeEdicion.add("nombre");
        if (categoria)
            this.permisosDeEdicion.add("categoria");
        if (descripcion)
            this.permisosDeEdicion.add("descripcion");
        if (precio)
            this.permisosDeEdicion.add("precio");
        if (stock)
            this.permisosDeEdicion.add("stock");
        if (fecha)
            this.permisosDeEdicion.add("fecha");
    }}
