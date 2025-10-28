package org.example.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String passwordHash;
    private String rol;
    private String fechaRegistro;
    private String estadoCuenta;

    public Usuario(String nombre, String passwordHash, String rol, String fechaRegistro, String estadoCuenta) {
        this.nombre = nombre;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
        this.estadoCuenta = estadoCuenta;
    }
}