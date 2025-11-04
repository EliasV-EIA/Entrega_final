package org.example;

import org.example.Model.*;
import org.example.Services.*;
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

    private static ProductoService productoService;

    private static CategoriaService categoriaService;

    private static UsuarioService usuarioService;

    private static CarritoService carritoService;

    private static LineaCarritoService lineaCarritoService;


    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");

        ApplicationContext context = SpringApplication.run(Main.class, args);
        usuarioService = context.getBean(UsuarioService.class);
        categoriaService = context.getBean(CategoriaService.class);
        productoService = context.getBean(ProductoService.class);


        electorBase();
    }



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
                login();
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
    public static void login(){
        String username;
        String password;
        int retry;
        username=JOptionPane.showInputDialog(null, "Nombre");
        if (usuarioService.checkByNombre(username))
        {
           // System.out.println(usuarios.get(username).getPasswordHash());
            password=JOptionPane.showInputDialog(null, "Contrasena");

          //  if (password.compareTo(usuarios.get(username).getPasswordHash())==0){
            if (password.compareTo(usuarioService.getUsuarioByNombre(username).get().getPasswordHash())==0){
                System.out.println("CORRECTO");
                accionUsuario(usuarioService.getUsuarioByNombre(username).get());
            }
            else
                JOptionPane.showMessageDialog(null, "Contrasena incorrecta");
        }
        else {
            retry = JOptionPane.showConfirmDialog(null, "Ese usuario no existe, quiere intentar de nuevo?");
            if (retry == JOptionPane.YES_OPTION){
                login();
            }

        }
    }
    public static void accionUsuario(Usuario u){
//        if (u instanceof Duena){
//            System.out.println("Menu cabra");
//
//        }
        /*else*/ if (u instanceof AdministradorContenido){
            accionAdminContenido((AdministradorContenido) u);
        }
        else if (u instanceof AdministradorUsuarios) {
            accionAdminUsuarios((AdministradorUsuarios) u);
        } else if (u instanceof Cliente) {
            accionCliente((Cliente)u);
        } else
            throw new RuntimeException("Tipo de usuario no encontrado");

    }
    public static void accionCliente(Cliente u){
        String inp = JOptionPane.showInputDialog(null, "Escriba opcion: salir, carrito");
        switch (inp.toLowerCase()){
            case "carrito":{
                accionCarrito(u.getCarrito());
                accionCliente(u);
                break;
            }
            case "salir":{
                break;
            }
            default:{
                accionCliente(u);
                break;
            }
        }
    }
    public static void accionCarrito(Carrito carrito){
        String inp = JOptionPane.showInputDialog(null, "Escriba opcion: salir, anadir, eliminar");
        switch (inp){
            case "anadir":{
                productoService.getAllProductos().forEach((c) -> {
                    System.out.println(c.getNombre());
                });
                String nombre_producto = JOptionPane.showInputDialog(null, "Nombre del producto").toLowerCase();
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad"));
                if (!productoService.checkByNombre(nombre_producto)){
                    JOptionPane.showMessageDialog(null,"Producto inexistente");
                    accionCarrito(carrito);
                    break;
                }
                LineaCarrito linea =new LineaCarrito(productoService.getProductoByNombre(nombre_producto).get(),cantidad);
                carrito.agregarLinea(linea);
                lineaCarritoService.save(linea);
                carritoService.save(carrito);
            }
            case "eliminar":{
                carrito.getLineas().forEach((c) -> {
                    System.out.println(c.getProducto().getNombre());
            });
                String nombre_producto = JOptionPane.showInputDialog(null, "Nombre del producto").toLowerCase();
                if(!carrito.checkNombreLinea(nombre_producto)){
                    JOptionPane.showMessageDialog(null, "Producto no se encuentra en el carrito");
                    accionCarrito(carrito);
                    break;
                }
                lineaCarritoService.deleteLineacarrito(carrito.getOneLinea(nombre_producto).getId());
                carrito.eliminarLinea(carrito.getOneLinea(nombre_producto));
                carritoService.save(carrito);
                accionCarrito(carrito);
                break;
            }
            case "salir":{
                break;
            }
            default:{
                accionCarrito(carrito);
                break;
            }
        }
    }
    public static void accionAdminUsuarios(AdministradorUsuarios u){
        String inp = JOptionPane.showInputDialog(null, "Escriba opcion: salir, editar, eliminar");
        switch (inp.toLowerCase()){
            case "eliminar":{
                String nombre_usuario= JOptionPane.showInputDialog(null, "Nombre del usuario a eliminar");
                if (usuarioService.checkByNombre(nombre_usuario)){
                    usuarioService.deleteUsuario(usuarioService.getUsuarioByNombre(nombre_usuario).get().getId());
                }
                else{
                    JOptionPane.showMessageDialog(null, "Usuario inexistente");
                }
            }
            case "salir":{
                break;
            }
            case "editar":{
                String nombre_usuario= JOptionPane.showInputDialog(null, "Nombre del usuario a editar");
                if (usuarioService.checkByNombre(nombre_usuario)){
                editarUsuario(usuarioService.getUsuarioByNombre(nombre_usuario).get());
                }
                else{
                    JOptionPane.showMessageDialog(null, "Usuario inexistente");
                }
            }
            default:{
                accionAdminUsuarios(u);
                break;
            }
        }
    }
    public static void accionAdminContenido(AdministradorContenido u) {
        int opt;
        Object[] opciones = {"Producto", "Categoria", "Salir"};
        String inp = JOptionPane.showInputDialog(null, "Escriba opcion: salir, crear, editar, eliminar");
        switch (inp.toLowerCase()) {
            case "editar": {

                opt = JOptionPane.showOptionDialog(null, "Que desea editar?", "Editar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[2]);
                if (opt == 0) {

                    String nombre_producto = JOptionPane.showInputDialog(null,"Nombre de el producto a editar");
                    if(productoService.checkByNombre(nombre_producto)){
                        editarProducto(u,productoService.getProductoByNombre(nombre_producto).get());
                    }

                } else if (opt == 1) {
                  //  if (u.getPermisosDeEdicion().contains("categoria")){
                    if(u.checkPermiso("categoria")){
                        String nombre_categoria= JOptionPane.showInputDialog(null, "Nombre de la categoria a editar");
                        if(categoriaService.checkByNombre(nombre_categoria)){
                            editarCategoria(categoriaService.getCategoriaByNombre(nombre_categoria).get());
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Categoria inexistente");

                        }

                    }else JOptionPane.showMessageDialog(null, "No tiene permiso para editar categorias");

                  //  }
                }
                accionAdminContenido(u);
                break;
            }
            case "crear": {
                opt = JOptionPane.showOptionDialog(null, "Que desea crear?", "Crear", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[2]);
                if (opt == 0) {

                    String nombre_producto;
                    String categoria_producto;
                    double precio_producto;
                    int stock_producto;
                    String fecha_producto;
                    nombre_producto = JOptionPane.showInputDialog(null, "Nombre").toLowerCase();
                    categoria_producto = JOptionPane.showInputDialog(null, "Categoria").toLowerCase();
                    if (!(categoriaService.checkByNombre(nombre_producto)))
                    {
                        JOptionPane.showMessageDialog(null, "Categoria inexistente");
                        accionAdminContenido(u);
                        break;
                    }
                    precio_producto = Double.parseDouble(JOptionPane.showInputDialog(null, "Precio"));
                    stock_producto = Integer.parseInt(JOptionPane.showInputDialog(null, "Stock"));
                    fecha_producto = JOptionPane.showInputDialog(null, "Fecha");
                    Producto obj_producto = new Producto(nombre_producto,precio_producto,stock_producto,fecha_producto,categoriaService.getCategoriaByNombre(categoria_producto).get());
                    productoService.saveProducto(obj_producto);


                } else if (opt == 1) {
                    String nombre_categoria;
                    String descripcion_categoria;
                    nombre_categoria = (JOptionPane.showInputDialog(null, "Nombre")).toLowerCase();
                    descripcion_categoria = JOptionPane.showInputDialog(null, "Descripcion");
                    Categoria obj_categoria = new Categoria( nombre_categoria, descripcion_categoria);
                    if (categoriaService.checkByNombre(nombre_categoria)) {
                        JOptionPane.showMessageDialog(null, "Categoria ya existe");
                    } else {
                        categoriaService.saveCategoria(obj_categoria);
                    }
                }
                accionAdminContenido(u);
                break;
            }
            case "eliminar":{
                opt = JOptionPane.showOptionDialog(null, "Que desea eliminar?", "Eliminar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[2]);
                if (opt==0){
                    String nombre_producto= JOptionPane.showInputDialog(null, "Nombre del producto a eliminar");
                    if (productoService.checkByNombre(nombre_producto)){
                        productoService.deleteProducto(productoService.getProductoByNombre(nombre_producto).get().getId());
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Producto inexistente");
                    }
                } else if (opt==1) {
                    String nombre_categoria= JOptionPane.showInputDialog(null, "Nombre de la categoria a eliminar");
                    if(categoriaService.checkByNombre(nombre_categoria)){
                        categoriaService.deleteCategoria(categoriaService.getCategoriaByNombre(nombre_categoria).get().getId());
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Categoria inexistente");

                    }

                }
                accionAdminContenido(u);
                break;
            }
            case "salir": {
                break;
            }
            default: {
                accionAdminContenido(u);
                break;
            }
        }
    }
    public static void editarUsuario(Usuario usuario){
        String mensaje ="Que campo va a editar? (nombre, contasena, estado, fecha";
        if (usuario instanceof AdministradorUsuarios){
            mensaje += ", rol, nivel";
        }
        if (usuario instanceof AdministradorContenido){
            mensaje += ", rol, permisos";
        }
        if (usuario instanceof Cliente){
            mensaje += ", direccion, telefono";
        }
        mensaje+=", salir)";
        String inp = JOptionPane.showInputDialog(null, mensaje);
        switch (inp.toLowerCase()){
            case "nombre":{
                String nuevo_nombre = JOptionPane.showInputDialog(null, "Nombre");
                usuario.setNombre(nuevo_nombre);
                usuarioService.saveUsuario(usuario);
                editarUsuario(usuario);
                break;
            }
            case "contrasena":{
                String nueva_contrasena= JOptionPane.showInputDialog(null,"Contrasena");
                usuario.setPasswordHash(nueva_contrasena);
                usuarioService.saveUsuario(usuario);
                editarUsuario(usuario);
                break;
            }
            case "estado":{
                String nuevo_estado = JOptionPane.showInputDialog(null, "Estado");
                usuario.setEstadoCuenta(nuevo_estado);
                usuarioService.saveUsuario(usuario);
                editarUsuario(usuario);
                break;
            }
            case "fecha":{
                String nueva_fecha= JOptionPane.showInputDialog(null, "Fecha");
                usuario.setFechaRegistro(nueva_fecha);
                usuarioService.saveUsuario(usuario);
                editarUsuario(usuario);
                break;
            }
            case "rol":{
                if (usuario instanceof Cliente){
                    JOptionPane.showMessageDialog(null, "El usuario no es un empleado");
                    editarUsuario(usuario);
                    break;
                }
                String nuevo_rol= JOptionPane.showInputDialog(null, "Rol");
                usuario.setRol(nuevo_rol);
                usuarioService.saveUsuario(usuario);
            }
            case "nivel":{
                if (!(usuario instanceof AdministradorUsuarios)){
                    editarUsuario(usuario);
                    break;
                }
                int nuevo_nivel = Integer.parseInt(JOptionPane.showInputDialog(null, "Nivel"));
                ((AdministradorUsuarios) usuario).setNivelAcceso( nuevo_nivel);
                usuarioService.saveUsuario(usuario);
                editarUsuario(usuario);
                break;
            }
            case "direccion":{
                if(!(usuario instanceof Cliente)){
                    editarUsuario(usuario);
                    break;
                }
                String nueva_direccion = JOptionPane.showInputDialog(null, "Direccion");
                ((Cliente) usuario).setDireccionEnvio(nueva_direccion);
                usuarioService.saveUsuario(usuario);
                editarUsuario(usuario);
                break;
            }
            case "telefono":{
                if(!(usuario instanceof Cliente)){
                    editarUsuario(usuario);
                    break;
                }
                String nuevo_telefono = JOptionPane.showInputDialog(null, "Telefono");
                ((Cliente) usuario).setTelefono(nuevo_telefono);
                usuarioService.saveUsuario(usuario);
                editarUsuario(usuario);
                break;
            }
            case "permisos":{
                if(!(usuario instanceof AdministradorContenido)){
                    editarUsuario(usuario);
                    break;
                }
                ((AdministradorContenido)usuario).changePermisos();
                usuarioService.saveUsuario(usuario);
                break;
            }

            case "salir":{
                break;
            }
            default:
                editarUsuario(usuario);
                break;
        }

    }
    public static void editarCategoria(Categoria categoria){
        String inp = JOptionPane.showInputDialog(null, "Que campo va a editar? (nombre, descripcion, salir)").toLowerCase();
        switch (inp){
            case "nombre":{
                String nuevo_nombre = JOptionPane.showInputDialog(null, "Nombre");
                categoria.setNombre(nuevo_nombre);
                categoriaService.saveCategoria(categoria);
                editarCategoria(categoria);
                break;
            }
            case "descripcion":{
                String nueva_descripcion = JOptionPane.showInputDialog(null, "Descripcion");
                categoria.setDescripcion(nueva_descripcion);
                categoriaService.saveCategoria(categoria);
                editarCategoria(categoria);
                break;
            }
            case "salir":{
                break;
            }
            default:{
                editarCategoria(categoria);
                break;
            }
        }
    }
    public static void editarProducto(AdministradorContenido usuario,Producto producto){
        String inp = JOptionPane.showInputDialog(null, "Que campo va a editar? (nombre, precio, stock, fecha, categoria, salir)").toLowerCase();
        switch (inp){

            case "nombre":{
                if(!(usuario.checkPermiso("nombre"))){
                JOptionPane.showMessageDialog(null,"No tiene permiso para editar este campo");
                editarProducto(usuario,producto);
                break;
            }
                String nuevo_nombre = JOptionPane.showInputDialog(null, "Nombre");
                producto.setNombre(nuevo_nombre);
               productoService.saveProducto(producto);
                editarProducto(usuario,producto);
                break;
            }
            case "precio":{
                if(!(usuario.checkPermiso("precio"))){
                    JOptionPane.showMessageDialog(null,"No tiene permiso para editar este campo");
                    editarProducto(usuario,producto);
                    break;
                }
                Double nuevo_precio = Double.parseDouble(JOptionPane.showInputDialog(null, "Nombre"));
                producto.setPrecio(nuevo_precio);
                productoService.saveProducto(producto);
                editarProducto(usuario,producto);
                break;
            }
            case "stock":{
                if(!(usuario.checkPermiso("stock"))){
                    JOptionPane.showMessageDialog(null,"No tiene permiso para editar este campo");
                    editarProducto(usuario,producto);
                    break;
                }
                int nuevo_stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Nombre"));
                producto.setStock(nuevo_stock);
                productoService.saveProducto(producto);
                editarProducto(usuario,producto);
                break;
            }
            case "salir":{
                break;
            }
            case "fecha":{
                if(!(usuario.checkPermiso("fecha"))){
                    JOptionPane.showMessageDialog(null,"No tiene permiso para editar este campo");
                    editarProducto(usuario,producto);
                    break;
                }
                String nueva_fecha = JOptionPane.showInputDialog(null, "Nombre");
                producto.setFechaLanzamiento(nueva_fecha);
                productoService.saveProducto(producto);
                editarProducto(usuario,producto);
                break;
            }
            case "categoria":{
                if(!(usuario.checkPermiso("categoria"))){
                    JOptionPane.showMessageDialog(null,"No tiene permiso para editar este campo");
                    editarProducto(usuario,producto);
                    break;
                }
                String categoria= JOptionPane.showInputDialog(null, "Categoria");
                if(categoriaService.checkByNombre(categoria)){
                    producto.setCategoria(categoriaService.getCategoriaByNombre(categoria).get());
                    productoService.saveProducto(producto);
                    editarProducto(usuario,producto);
                    break;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Categoria inexistente");
                    editarProducto(usuario,producto);
                    break;
                }
            }
            default:{
                editarProducto(usuario,producto);
                break;
            }
        }
    }
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
        fecha = JOptionPane.showInputDialog(null, "Fecha de registro");
        estado = JOptionPane.showInputDialog(null, "Estado");
        if (tipo == 0) {
            String direccion = JOptionPane.showInputDialog(null, "Direccion de envio");
            String telefono = JOptionPane.showInputDialog(null,"telefono");
            Cliente obj_cliente = new Cliente(nombre,pass,fecha,estado,direccion,telefono);
            usuarioService.saveUsuario(obj_cliente);
          }
            else if (tipo == 1) {
             rol = JOptionPane.showInputDialog(null, "Rol");
                int nivel = Integer.parseInt(JOptionPane.showInputDialog(null, "Nivel de acceso"));
            AdministradorUsuarios obj_adminus = new AdministradorUsuarios( nombre, pass, rol, fecha, estado, nivel);
            usuarioService.saveUsuario(obj_adminus);
            } else if (tipo == 2) {
            rol = JOptionPane.showInputDialog(null, "Rol");
            AdministradorContenido obj_cont = new AdministradorContenido( nombre, pass, rol, fecha, estado,
                    JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar nombres?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
                    JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar categorias?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
                    JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar descripciones?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
                    JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar precios?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
                    JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar stocks?", "Permisos", JOptionPane.YES_NO_OPTION) == 1,
                    JOptionPane.showConfirmDialog(null, "Tiene este usuario permiso para editar fechas de lanzamiento?", "Permisos", JOptionPane.YES_NO_OPTION) == 1);
            usuarioService.saveUsuario( obj_cont);
//            System.out.println(obj_cont.getPermisosDeEdicion());
//            System.out.println(((AdministradorContenido) usuarios.get(nombre)).getPermisosDeEdicion());
//            System.out.println(usuarios.get(nombre).detalleUsuario());
//            System.out.println(usuarios.get(nombre).getClass());
            System.out.println(usuarioService.getUsuarioByNombre(obj_cont.getNombre()).get());
        }
    }

}