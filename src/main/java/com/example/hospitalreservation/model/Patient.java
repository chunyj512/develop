package com.example.hospitalreservation.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "patient")
    private List<Reservation> reservations = new ArrayList<>();

    protected Patient() {}

    public Patient(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}
