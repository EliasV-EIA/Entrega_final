package org.example.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
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
    }

    public void setPermisosDeEdicion(Set<String> permisosDeEdicion) {
        this.permisosDeEdicion = permisosDeEdicion;
    }

    public Boolean checkPermiso(String campo){
        if (this.permisosDeEdicion.contains(campo.toLowerCase())){
            return true;
        }
        else return false;
    }
    public void changePermisos(){
        boolean[] nightmare = {JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar nombres?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
                JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar categorias?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
                JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar descripciones?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
                JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar precios?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
                JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar stocks?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
                JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar fechas de lanzamiento?", "Permisos", JOptionPane.YES_NO_OPTION) == 1};
        if (nightmare[0])
            this.permisosDeEdicion.add("nombre");
        if (nightmare[1])
            this.permisosDeEdicion.add("categoria");
        if (nightmare[2])
            this.permisosDeEdicion.add("descripcion");
        if (nightmare[3])
            this.permisosDeEdicion.add("precio");
        if (nightmare[4])
            this.permisosDeEdicion.add("stock");
        if (nightmare[5])
            this.permisosDeEdicion.add("fecha");
    }

    public Set<String> getPermisosDeEdicion() {
        return permisosDeEdicion;
    }
}
