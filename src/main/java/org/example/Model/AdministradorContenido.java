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

    public AdministradorContenido(String nombre, String passwordHash, String rol, String fechaRegistro, String estadoCuenta, Set<String> permisos) {
        super(nombre, passwordHash, rol, fechaRegistro, estadoCuenta);
        this.permisosDeEdicion = permisos;
    }
}
