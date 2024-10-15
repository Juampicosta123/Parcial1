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

            // Error si el array está vacío
            if(sequence.length == 0) throw new Exception("El array no debe estar vacío");
            for (int row = 0; row < sequence.length; row++) {
                char[] dnaRow = sequence[row].toCharArray();

                // Error si la matriz no es de nxn
                if(sequence.length != dnaRow.length) throw new Exception("La matriz debe ser de nxn");

                // Error si el array no incluye una letra válida
                char[] validLetters = {'A', 'C', 'G', 'T'};

                for (char character : dnaRow) {
                    boolean isValid = false;
                    for (char validLetter : validLetters) {
                        if (character == validLetter) {
                            isValid = true;
                            break;
                        }
                    }
                    if (!isValid) {
                        System.out.println("Carácter inválido encontrado: " + character);
                        throw new Exception("Carácter inválido encontrado: " + character);
                    }
                }
            }

            Optional<DNA> existingDNA = DNARepository.getBySequence(sequence);
            DNAShort dnaDtoShort = new DNAShort();
            if (existingDNA.isPresent()) {
                // Si ya existe lo devuelvo
                boolean isMutant = existingDNA.get().getIsMutant();
                dnaDtoShort.setIsMutant(isMutant);
                String description = isMutant ? "Mutante detectado" : "No es mutante";

                dnaDtoShort.setDescription(description);
                return dnaDtoShort;
            } else {
                // Verificar si es mutante
                boolean isMutant = MutantChecker.isMutant(sequence);
                String description = isMutant ? "Mutante detectado" : "No es mutante";

                // Crear nuevo DNA
                DNA dna = new DNA();
                dna.setIsMutant(isMutant);
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
