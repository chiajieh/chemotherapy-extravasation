package com.mobileapp.chemotherapyextravasation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class to describe the properties, type, antidote and intervention for chemotherapy
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chemotherapy")
public class Chemotherapy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="type", nullable = false)
    private String type;

    @Column(name="properties")
    private String properties;

    @Column(name="antidote")
    private String antidote;

    @Column(name="intervention", length=2000)
    private String intervention;
}