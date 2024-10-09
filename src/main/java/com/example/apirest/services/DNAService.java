package com.example.apirest.services;

import com.example.apirest.dtos.DNAShort;
import com.example.apirest.entities.DNA;
import com.example.apirest.repositories.DNARepository;
import com.example.apirest.utils.MutantChecker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DNAService {

    @Autowired
    private DNARepository DNARepository;

    @Transactional
    public DNAShort checkIsMutant(String[] sequence) throws Exception {
        try {
            Optional<DNA> existingDNA = DNARepository.getBySequence(sequence);
            DNAShort dnaDtoShort = new DNAShort();
            if (existingDNA.isPresent()) {
                // Si ya existe lo devuelvo
                dnaDtoShort.setIsMutant(existingDNA.get().getIsMutant());
                dnaDtoShort.setDescription(existingDNA.get().getDescription());
                return dnaDtoShort;
            } else {
                // Verificar si es mutante
                Optional<String> result = MutantChecker.isMutant(sequence);

                Boolean isMutant = result.isPresent();
                String description = result.orElse("No es mutante");

                // Crear nuevo DNA
                DNA dna = new DNA();
                dna.setIsMutant(isMutant);
                dna.setDescription(description);
                dna.setSequence(sequence);

                DNARepository.save(dna);

                dnaDtoShort.setIsMutant(isMutant);
                dnaDtoShort.setDescription(description);
                return dnaDtoShort;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
