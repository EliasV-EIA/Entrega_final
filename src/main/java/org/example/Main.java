package org.example;

import org.example.Model.Usuario;
import org.example.Services.EstudianteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner run(EstudianteService estudianteService) {
        return args -> {
            System.out.println("-- Bienvenido al Sistema --");

            System.out.println("Listado de usuarios");
            List<Usuario> usuarios = estudianteService.getAllStudents();

            usuarios.forEach(System.out::println);

            /*System.out.println("Ingrese los datos de un estudiante: ");
            Usuario objEstudiante = new Usuario("Pepito",
                    "Perez",
                    "perez@gmail.com");

            Usuario estudianteRegistrado = estudianteService.saveStudent(objEstudiante);

            System.out.println("Listado de usuarios");
            usuarios = estudianteService.getAllStudents();

            usuarios.forEach(System.out::println);*/

            System.out.println("Traer estudiante por id");
            Long id_a_consultar = 5L;

            estudianteService.getStudentById(id_a_consultar).ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("No se encontró el usuario")
            );


            Usuario informacionParaActualizar = new Usuario("Pedro", "Pascal", "pascalito@gmail.com");
            Usuario informacionYaActualizada = estudianteService.updateStudenInfo(id_a_consultar,informacionParaActualizar);

            System.out.println("Usuario actualizado: "+informacionYaActualizada);


            System.out.println("Eliminar el estudiante con id 7");

            boolean resultado;

            resultado = estudianteService.deleteStudentInfo(7L);

            if(resultado){
                System.out.println("Se ha eliminado el estudiante");
            }else{
                System.out.println("No se pudo eliminar el estudiante");
            }


        };
    }
}