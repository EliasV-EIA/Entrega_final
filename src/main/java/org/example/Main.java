package org.example;

import org.example.Model.Cliente;
import org.example.Model.Usuario;
import org.example.Services.CategoriaService;
import org.example.Services.UsuarioService;
import org.example.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
public class Main {
    @Autowired
    private static ProductoService productoService;

    @Autowired
    private static CategoriaService categoriaService;

    @Autowired
    private static UsuarioService usuarioService;
    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");

        ApplicationContext context = SpringApplication.run(Main.class, args);
        usuarioService = context.getBean(UsuarioService.class);

        electorBase();
    }


    public static List<String> nombres_usuario;


    @Bean
    public CommandLineRunner run() {
        return args -> {
            //usuarioService.getAllUsuarios().forEach((u)->{nombres_usuario.add(u.getNombre());});

        };

    }
    public static void electorBase(){
        Object[] opciones= {"Login", "Registrar", "Salir"};
        int inp;
        JOptionPane.showMessageDialog(null, "Bienvenido, eliga una accion");
        inp= JOptionPane.showOptionDialog(null, "Seleccione su accion deseada", "Principal", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null, opciones, opciones[2]);
//    inp=JOptionPane.showInputDialog(null, "Escriba la opcion deseada: login , registrar, salir");

        switch (inp) {
            case 0: {
//                login();
                electorBase();
                break;
            }
            case 1: {
                registar();
                electorBase();
                break;
            }
            case 2: {
                break;
            }




        }

    }
//    public static void login(){
//        String username;
//        String password;
//        int retry;
//        username=JOptionPane.showInputDialog(null, "Nombre");
//        if (usuarioService.)
//        {
//            System.out.println(usuarios.get(username).getPasswordHash());
//            password=JOptionPane.showInputDialog(null, "Contrasena");
//
//            if (password.compareTo(usuarios.get(username).getPasswordHash())==0){
//
//                System.out.println("CORRECTo");
//                accionUsuario(usuarios.get(username));
//            }
//            else
//                JOptionPane.showMessageDialog(null, "Contrasena incorrecta");
//        }
//        else {
//            retry = JOptionPane.showConfirmDialog(null, "Ese usuario no existe, quiere intentar de nuevo?");
//            if (retry == JOptionPane.YES_OPTION){
//                login();
//            }
//
//        }
//    }
//    public static void accionUsuario(Usuario u){
//        if (u instanceof Duena){
//            System.out.println("Menu cabra");
//
//        }
//        else if (u instanceof AdministradorContenido){
//            accionAdminContenido((AdministradorContenido) u);
//        }
//        else if (u instanceof AdministradorUsuario) {
//            System.out.println("AdminUsuario");
//        }
//        else
//            throw new RuntimeException("Tipo de usuario no encontrado");
//
//    }


//    public static void accionAdminContenido(AdministradorContenido u) {
//        int opt;
//        Object[] opciones = {"Producto", "Categoria", "Salir"};
//        String inp = JOptionPane.showInputDialog(null, "Escriba opcion: salir, crear, editar, eliminar");
//        switch (inp.toLowerCase()) {
//            case "editar": {
//
//                opt = JOptionPane.showOptionDialog(null, "Que desea editar?", "Editar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[2]);
//                if (opt == 0) {
//
//                } else if (opt == 1) {
//                    if (u.getPermisosDeEdicion().contains("categoria")){
//
//                    }
//                }
//                accionAdminContenido(u);
//                break;
//            }
//            case "crear": {
//                opt = JOptionPane.showOptionDialog(null, "Que desea crear?", "Crear", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[2]);
//                if (opt == 0) {
//
//                    String id_producto;
//                    String nombre_producto;
//                    String categoria_producto;
//                    double precio_producto;
//                    int stock_producto;
//                    String fecha_producto;
//                    id_producto = JOptionPane.showInputDialog(null, "Id");
//                    nombre_producto = JOptionPane.showInputDialog(null, "Nombre");
//                    categoria_producto = JOptionPane.showInputDialog(null, "Categoria").toLowerCase();
//                    if (!categorias.containsKey(categoria_producto))
//                    {
//                        JOptionPane.showMessageDialog(null, "Categoria inexistente");
//                        accionAdminContenido(u);
//                        break;
//                    }
//                    precio_producto = Double.parseDouble(JOptionPane.showInputDialog(null, "Precio"));
//                    stock_producto = Integer.parseInt(JOptionPane.showInputDialog(null, "Stock"));
//                    fecha_producto = JOptionPane.showInputDialog(null, "Fecha");
//                    productos.put(nombre_producto,new Producto(id_producto,nombre_producto,categorias.get(categoria_producto),precio_producto,stock_producto,fecha_producto));
//
//
//                } else if (opt == 1) {
//                    String id_categoria;
//                    String nombre_categoria;
//                    String descripcion_categoria;
//                    id_categoria = JOptionPane.showInputDialog(null, "Id");
//                    nombre_categoria = (JOptionPane.showInputDialog(null, "Nombre")).toLowerCase();
//                    descripcion_categoria = JOptionPane.showInputDialog(null, "Descripcion");
//                    Categoria obj_categoria = new Categoria(id_categoria, nombre_categoria, descripcion_categoria);
//                    if (categorias.containsKey(nombre_categoria)) {
//                        JOptionPane.showMessageDialog(null, "Categoria ya existe");
//                    } else {
//                        categorias.put(nombre_categoria, obj_categoria);
//                        System.out.println(categorias);
//                    }
//                }
//                accionAdminContenido(u);
//                break;
//            }
//            case "eliminar":{
//                opt = JOptionPane.showOptionDialog(null, "Que desea eliminar?", "Eliminar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[2]);
//                if (opt==0){
//                    String nombre_producto= JOptionPane.showInputDialog(null, "Nombre del producto a eliminar");
//                    if (productos.containsKey(nombre_producto)){
//                        productos.remove(nombre_producto);
//                    }
//                    else{
//                        JOptionPane.showMessageDialog(null, "Producto inexistente");
//                    }
//                } else if (opt==1) {
//                    String nombre_categoria= JOptionPane.showInputDialog(null, "Nombre del producto a eliminar");
//                    if(categorias.containsKey(nombre_categoria)){
//                        categorias.remove(nombre_categoria);
//                    }
//                    else {
//                        JOptionPane.showMessageDialog(null, "Categoria inexistente");
//
//                    }
//
//                }
//                accionAdminContenido(u);
//                break;
//            }
//            case "salir": {
//                break;
//            }
//            default: {
//                accionAdminContenido(u);
//                break;
//            }
//        }
//    }

    public static void registar () {
        Object[] opciones = {"Cliente", "AdminUsuario", "AdminContenido"};
        int tipo;
      //  String id;
        String nombre;
        String pass;
        String fecha;
        String estado;
        String rol;
        tipo = JOptionPane.showOptionDialog(null, "Seleccione tipo de cuenta", "Registrar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        nombre = JOptionPane.showInputDialog(null, "Nombre");
        //id = JOptionPane.showInputDialog(null, "ID");
        pass = JOptionPane.showInputDialog(null, "Contrasena");
        fecha = JOptionPane.showInputDialog(null, "Fecha de registro(automatica en el futuro)");
        estado = JOptionPane.showInputDialog(null, "Estado");
        if (tipo == 0) {
            String direccion = JOptionPane.showInputDialog(null, "Direccion de envio");
            String telefono = JOptionPane.showInputDialog(null,"telefono");
            Cliente obj_cliente = new Cliente(nombre,pass,fecha,estado,direccion,telefono);
            usuarioService.saveUsuario(obj_cliente);
//        } else if (tipo == 1) {
//            rol = JOptionPane.showInputDialog(null, "Rol");
//            int nivel = Integer.parseInt(JOptionPane.showInputDialog(null, "Nivel de acceso"));
//            usuarios.put(nombre, new AdministradorUsuario(id, nombre, pass, rol, fecha, estado, nivel));
//
//        } else if (tipo == 2) {
//            rol = JOptionPane.showInputDialog(null, "Rol");
//            AdministradorContenido obj_cont = new AdministradorContenido(id, nombre, pass, rol, fecha, estado,
//                    JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar nombres?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
//                    JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar categorias?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
//                    JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar descripciones?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
//                    JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar precios?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
//                    JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar stocks?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
//                    JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar fechas de lanzamiento?", "Permisos", JOptionPane.YES_NO_OPTION) == 1);
//            usuarios.put(nombre, obj_cont);
//            System.out.println(obj_cont.getPermisosDeEdicion());
//            System.out.println(((AdministradorContenido) usuarios.get(nombre)).getPermisosDeEdicion());
//            System.out.println(usuarios.get(nombre).detalleUsuario());
//            System.out.println(usuarios.get(nombre).getClass());
        }

    }
//    public static void testmenu () {
//        String inp = JOptionPane.showInputDialog(null, "comando");
//        switch (inp) {
//            case "normal": {
//                electorBase();
//                testmenu();
//                break;
//            }
//            case "listusers": {
//                System.out.println(usuarios);
//                testmenu();
//                break;
//            }
//            case "exit": {
//                break;
//            }
//            case "geteditperms": {
//                inp = JOptionPane.showInputDialog(null, "");
//            }
//            default: {
//                testmenu();
//                break;
//            }
//        }
//    }
//    public static boolean contieneUsuario(String nombre){
//        boolean val=false;
//        usuarios.forEach((n)->{if (usuarios.get(n).getNombre()==nombre){return true;}});
//        return val;
//    }
//    public static void editarCategoria(Categoria c){
//        String inp;
//        inp = JOptionPane.showInputDialog(null, "Cual campo va a editar? (id, nombre, descripcion, salir").toLowerCase();
//        String val;
//        switch (inp){
//            case "id":
//            {
//                val=JOptionPane.showInputDialog(null,"id");
//                categorias.replace();
//            }
//
//        }
//    }
}