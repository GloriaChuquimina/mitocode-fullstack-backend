package com.mitocode.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
// @Entity//(name = "xyz")
@Entity
public class VitalSign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idVitalSign;

    @ManyToOne//FK
    @JoinColumn(name = "id_patient", nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_PATIENT"))
    private Patient patient;

    @Column(nullable = false, length = 50)
    private String dateReg;

    @Column(length = 150)
    private String temperature;

    @Column(length = 50, nullable = false)
    private String pulse;

    @Column(length = 50, nullable = false)
    private String respiratoryRate;

}
