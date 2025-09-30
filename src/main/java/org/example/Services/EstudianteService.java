package org.example.Services;

import org.example.Model.Estudiante;
import org.example.Repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Estudiante crearEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }



    public List<Estudiante> getAllStudents() {
        return estudianteRepository.findAll();
    }
}
