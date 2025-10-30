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

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}