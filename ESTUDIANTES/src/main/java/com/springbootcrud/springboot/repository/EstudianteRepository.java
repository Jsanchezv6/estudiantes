package com.springbootcrud.springboot.repository;

import com.springbootcrud.springboot.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {


    List<Estudiante> findByName(String nombre);
}
