package com.springbootcrud.springboot.service;

import com.springbootcrud.springboot.entity.Estudiante;

import java.util.List;
import java.util.Optional;




public interface EstudianteService {

    List<Estudiante> findAll();

    Optional<Estudiante> findById(Long id);

    List<Estudiante> findByName(String name);

    void createEstudiante(Estudiante estudiante);

    void modifyEstudiante(Long id, Estudiante estudiante);

    void deleteEstudiante(Long id);

}
