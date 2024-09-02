package com.example.jpaLab.data;

import com.example.jpaLab.model.Patient;
import com.example.jpaLab.repository.PatientRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class PatientsDataLoader implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private Faker faker;

    @Override
    public void run(String... args) throws Exception {

        // Generating 1 to 100 patients

        List<Patient> patients = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> new Patient(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.address().fullAddress(),
                        faker.phoneNumber().phoneNumber(),
                        faker.medical().diseaseName()
                ))
                .collect(Collectors.toList());
        patientRepository.saveAll(patients);
    }
}
