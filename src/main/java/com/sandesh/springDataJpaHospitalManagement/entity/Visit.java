package com.sandesh.springDataJpaHospitalManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visitId;

    @Column(name = "day_to_visit", nullable = false)
    private String visitDay;

    @Column(nullable = false)
    private String descriptionForVisit;

    //Many Appointments , One Patient
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonBackReference
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
}
