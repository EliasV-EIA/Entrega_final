package org.example.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CLIENTE")
@Data
@NoArgsConstructor
public class Cliente extends Usuario {

    private String direccionEnvio;
    private String telefono;

    public Cliente(String nombre, String passwordHash, String fechaRegistro, String estadoCuenta, String direccionEnvio, String telefono) {
        super(nombre, passwordHash, "cliente", fechaRegistro, estadoCuenta);
        this.direccionEnvio = direccionEnvio;
        this.telefono = telefono;
    }
}