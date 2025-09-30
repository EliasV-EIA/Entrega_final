package org.example.Controller;


import org.example.Model.Estudiante;
import org.example.Services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {

    @Autowired
    private EstudianteService obj_estudiante_service;

    @GetMapping
    public List<Estudiante> getAllEstudiantes(){
        return obj_estudiante_service.getAllStudents();
    }
}
