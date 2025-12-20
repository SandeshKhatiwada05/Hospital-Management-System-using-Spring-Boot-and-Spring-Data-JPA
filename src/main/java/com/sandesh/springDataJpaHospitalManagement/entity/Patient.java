package com.sandesh.springDataJpaHospitalManagement.entity;

import com.sandesh.springDataJpaHospitalManagement.entity.type.GenderType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(
        name = "Patient_Db",
        uniqueConstraints = {
//                @UniqueConstraint(name = "unique_patient_name", columnNames = {"phoneNumber"}),
                //sandesh with gmail kh@gmail.com cannot be created two times
                @UniqueConstraint(name = "unique_email_phone", columnNames = {"name", "email"})
        },
        indexes = {
                @Index(name = "indx_name", columnList = "name")
        }
)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient's name", nullable = false)
    private String name;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private String email;

    @Column(unique = true)
    private Long phoneNumber;

    private int age;

    @CreationTimestamp
    @Column(updatable = false) //@CreationTimestamp does better, not required
    private LocalDateTime createAt;
}
