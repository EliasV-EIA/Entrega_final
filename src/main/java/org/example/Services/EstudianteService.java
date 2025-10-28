package org.example.Services;

import org.example.Model.Usuario;
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

    public List<Usuario> getAllStudents() {
        return estudianteRepository.findAll();
    }

    public Usuario saveStudent(Usuario usuario){
        return estudianteRepository.save(usuario);
    }

    public Optional<Usuario> getStudentById(Long Id){
        return estudianteRepository.findById(Id);
    }


    public Usuario updateStudenInfo(Long Id, Usuario nuevaInfoUsuario){
        return estudianteRepository.findById(Id).map(
                usuario ->{
                    usuario.setNombre(nuevaInfoUsuario.getNombre());
                    usuario.setPasswordHash(nuevaInfoUsuario.getPasswordHash());
                    usuario.setRol(nuevaInfoUsuario.getRol());
                    return estudianteRepository.save(usuario);
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
