package com.springbootcrud.springboot.service;

import com.springbootcrud.springboot.dao.EstudianteDao;
import com.springbootcrud.springboot.entity.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteDao estudianteDao;

    @Autowired
    public EstudianteServiceImpl(EstudianteDao estudianteDao) {
        this.estudianteDao = estudianteDao;
    }

    @Override
    public List<Estudiante> findAll() {
        Iterable<Estudiante> estudiantesIterable = estudianteDao.findAll();
        List<Estudiante> estudiantesList = new ArrayList<>();
        estudiantesIterable.forEach(estudiantesList::add);
        return estudiantesList;
    }

    @Override
    public Optional<Estudiante> findById(Long id) {
        return estudianteDao.findById(id);
    }

    @Override
    public List<Estudiante> findByName(String name) {
        return estudianteDao.findByName(name);
    }

    @Override
    public void createEstudiante(Estudiante estudiante) {
        estudianteDao.save(estudiante);
    }

    @Override
    public void modifyEstudiante(Long id, Estudiante estudiante) {
        Optional<Estudiante> estudianteOptional = estudianteDao.findById(id);
        if (estudianteOptional.isPresent()) {
            Estudiante estudianteExistente = estudianteOptional.get();
            estudianteExistente.setName(estudiante.getName());
            estudianteExistente.setEmail(estudiante.getEmail());
            estudianteExistente.setCarnet(estudiante.getCarnet());
            estudianteExistente.setAverage(estudiante.getAverage());
            estudianteExistente.setCreateAt(estudiante.getCreateAt());
            estudianteDao.save(estudianteExistente);
        }
    }

    @Override
    public void deleteEstudiante(Long id) {
        if (estudianteDao.existsById(id)) {
            estudianteDao.deleteById(id);
        }
    }
}
