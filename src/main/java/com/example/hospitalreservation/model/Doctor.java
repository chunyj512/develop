package com.example.hospitalreservation.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "doctor")
    private List<Reservation> reservations = new ArrayList<>();

    protected Doctor() {}

    public Doctor(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}
