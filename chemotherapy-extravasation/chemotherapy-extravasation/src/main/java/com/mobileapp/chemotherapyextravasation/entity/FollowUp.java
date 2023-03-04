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
@Table(name="followUp")
public class FollowUp {

    public FollowUp(LocalDate dateTaken, String reviewStatus, String additionalInfo, byte[] closeUpPhoto,
                    byte[] bodyPartPhoto, String actionRequired) {
        this.dateTaken = dateTaken;
        this.reviewStatus = reviewStatus;
        this.additionalInfo = additionalInfo;
        this.closeUpPhoto = closeUpPhoto;
        this.bodyPartPhoto = bodyPartPhoto;
        this.actionRequired = actionRequired;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="dateTaken")
    private LocalDate dateTaken;

    @Column(name="reviewStatus")
    private String reviewStatus;

    @Column(name="additionalInfo")
    private String additionalInfo;

    @Column(name="closeUpPhoto")
    @Lob
    private byte[] closeUpPhoto;

    @Column(name="bodyPartPhoto")
    @Lob
    private byte[] bodyPartPhoto;

    @Column(name="actionRequired")
    private String actionRequired;

    @OneToOne
    @JoinColumn(name="appointmentId")
    private Appointment appointment;
}