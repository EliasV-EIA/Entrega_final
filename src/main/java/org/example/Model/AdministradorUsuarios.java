package org.example.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("ADMIN_USUARIOS")
@Data
@NoArgsConstructor
public class AdministradorUsuarios extends Usuario {

    private int nivelAcceso;

    public AdministradorUsuarios(String nombre, String passwordHash, String rol, String fechaRegistro, String estadoCuenta, int nivelAcceso) {
        super(nombre, passwordHash, rol, fechaRegistro, estadoCuenta);
        this.nivelAcceso = nivelAcceso;
    }
}