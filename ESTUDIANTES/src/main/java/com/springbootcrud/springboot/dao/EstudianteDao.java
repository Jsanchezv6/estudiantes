package com.springbootcrud.springboot.dao;

import com.springbootcrud.springboot.entity.Estudiante;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstudianteDao extends CrudRepository<Estudiante, Long> {
    List<Estudiante> findByName(String name);
}
