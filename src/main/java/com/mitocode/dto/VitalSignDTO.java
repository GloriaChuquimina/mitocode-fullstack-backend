package com.mitocode.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VitalSignDTO {

    @EqualsAndHashCode.Include
    private Integer idVitalSign;

    @NotNull
    private PatientDTO patient;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 70, message = "{firstname.size}")
    private String firstName;

    // @NotNull
    // @NotEmpty
    // @Size(min = 3, max = 70, message = "{lastname.size}")
    // private String lastName;

    @NotNull
    @NotEmpty
    private String dateReg;

    @NotNull
    @NotEmpty
    private String temperature;

    @NotNull
    @NotEmpty
    private String pulse;

    @NotNull
    @NotEmpty
    private String respiratoryRate;
}
