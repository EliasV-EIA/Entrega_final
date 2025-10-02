package org.example.Services;

import org.example.Model.Estudiante;
import org.example.Repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// INCORPORAR TODA LA LÓGICA CRUD
@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> getAllStudents() {
        return estudianteRepository.findAll();
    }

    public Estudiante saveStudent(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }

    public Optional<Estudiante> getStudentById(Long Id){
        return estudianteRepository.findById(Id);
    }


    public Estudiante updateStudenInfo(Long Id, Estudiante nuevaInfoEstudiante){
        return estudianteRepository.findById(Id).map(
                estudiante ->{
                    estudiante.setNombre(nuevaInfoEstudiante.getNombre());
                    estudiante.setApellido(nuevaInfoEstudiante.getApellido());
                    estudiante.setEmail(nuevaInfoEstudiante.getEmail());
                    return estudianteRepository.save(estudiante);
                }
        ).orElse(null);
    }


    public boolean deleteStudentInfo(Long Id){
        if(estudianteRepository.existsById(Id)){
            estudianteRepository.deleteById(Id);
            return true;
        }else{
            return false;
        }
    }

}
