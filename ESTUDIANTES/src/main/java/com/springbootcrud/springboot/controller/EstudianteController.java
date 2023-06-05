package com.springbootcrud.springboot.controller;

import com.springbootcrud.springboot.repository.EstudianteRepository;
import com.springbootcrud.springboot.entity.Estudiante;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteRepository estudianteRepository;

    public EstudianteController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    // Listar Estudiantes
    @GetMapping
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    // Buscar Estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarEstudiantePorId(@PathVariable Long id) {
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(id);
        return estudianteOptional.map(estudiante -> ResponseEntity.ok().body(estudiante))
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar Estudiante por Nombre
    @GetMapping("/nombre")
    public List<Estudiante> buscarEstudiantePorNombre(@RequestParam String nombre) {
        return estudianteRepository.findByName(nombre);
    }

    // Crear nuevo Estudiante
    @PostMapping
    public ResponseEntity<Object> crearEstudiante(@Valid @RequestBody Estudiante estudiante) {
        try {
            Estudiante nuevoEstudiante = estudianteRepository.save(estudiante);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Modificar Estudiante existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> modificarEstudiante(@PathVariable Long id, @Valid @RequestBody Estudiante estudianteModificado) {
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(id);
        if (estudianteOptional.isPresent()) {
            Estudiante estudianteExistente = estudianteOptional.get();
            estudianteExistente.setName(estudianteModificado.getName());
            estudianteExistente.setEmail(estudianteModificado.getEmail());
            estudianteExistente.setCarnet(estudianteModificado.getCarnet());
            estudianteExistente.setAverage(estudianteModificado.getAverage());
            estudianteExistente.setCreateAt(estudianteModificado.getCreateAt());
            Estudiante estudianteActualizado = estudianteRepository.save(estudianteExistente);
            return ResponseEntity.ok().body(estudianteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar Estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(id);
        if (estudianteOptional.isPresent()) {
            estudianteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Manejar excepciones de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errores.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errores);
    }
}
