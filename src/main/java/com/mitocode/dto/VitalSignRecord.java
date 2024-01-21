package com.mitocode.dto;

public record VitalSignRecord(
        Integer idPatient,
        String firstName,
        // String lastName,
        String dateReg,
        String temperature,
        String pulse,
        String respiratoryRate
) {
}
