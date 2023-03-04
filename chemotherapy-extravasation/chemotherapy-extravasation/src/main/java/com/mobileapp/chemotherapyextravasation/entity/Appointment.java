package com.mobileapp.chemotherapyextravasation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="appointment")
public class Appointment {

    public Appointment(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="appointmentDate")
    private LocalDate appointmentDate;

    @ManyToOne
    @JoinColumn(name="nurseId")
    private Nurse nurse;

    @ManyToOne
    @JoinColumn(name="patientId")
    private Patient patient;

    @OneToOne(mappedBy="appointment")
    private FollowUp followUp;
}

