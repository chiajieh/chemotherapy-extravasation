package com.mobileapp.chemotherapyextravasation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Class to represent the Extravasation site
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "extravasation")
public class Extravasation {

    public Extravasation(LocalDate dateTaken, byte[] closeUpPhoto, byte[] bodyPartPhoto, double size, String ivSite,
                         boolean plasticSurgeryRequired, String grade) {
        this.dateTaken = dateTaken;
        this.closeUpPhoto = closeUpPhoto;
        this.bodyPartPhoto = bodyPartPhoto;
        this.size = size;
        this.ivSite = ivSite;
        this.plasticSurgeryRequired = plasticSurgeryRequired;
        this.grade = grade;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="dateTaken")
    private LocalDate dateTaken;

    @Column(name="closeUpPhoto")
    @Lob
    private byte[] closeUpPhoto;

    @Column(name="bodyPartPhoto")
    @Lob
    private byte[] bodyPartPhoto;

    @Column(name="size")
    private double size;

    @Column(name="ivSite")
    private String ivSite;

    @Column(name="plasticSurgeryRequired")
    private boolean plasticSurgeryRequired;

    @Column(name="grade")
    private String grade;

    // @Column(name="patientId", updatable=false, insertable=false)
    // private Long patientId;

    @OneToOne
    @JoinColumn(name="patientId")
    private Patient patient;
}
