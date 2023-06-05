package com.springbootcrud.springboot.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "El carnet es obligatorio")
    private String carnet;

    @Column(nullable = false)
    @NotNull(message = "El promedio es obligatorio")
    private Double average;

    @Column(nullable = false)
    @NotNull(message = "La fecha de creación es obligatoria")
    private Date createAt;

    public Estudiante() {
        // Constructor por defecto para JPA
    }

    public Estudiante(String name, String email, String carnet, Double average, Date createAt) {
        this.name = name;
        this.email = email;
        this.carnet = carnet;
        this.average = average;
        this.createAt = createAt;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
