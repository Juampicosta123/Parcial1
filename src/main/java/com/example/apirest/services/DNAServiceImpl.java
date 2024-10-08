package com.example.apirest.services;

import com.example.apirest.entities.DNA;
import com.example.apirest.repositories.DNARepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DNAServiceImpl {

    @Autowired
    private DNARepository DNARepository;

    @Transactional
    public Boolean checkIsMutant(String[] sequence) throws Exception {
        try {
            Optional<DNA> existingDNA = DNARepository.getBySequence(sequence);

            if (existingDNA.isPresent()) {
                // Si ya existe lo devuelvo
                return existingDNA.get().getIsMutant();
            } else {
                // Crear nuevo DNA
                Boolean isMutant = false;
                String description = "";

                if (checkRows(sequence).isPresent()) {
                    isMutant = true;
                    description = checkRows(sequence).get();
                } else if (checkColumns(sequence).isPresent()) {
                    isMutant = true;
                    description = checkColumns(sequence).get();
                } else if (checkDiagonal(sequence).isPresent()) {
                    isMutant = true;
                    description = checkDiagonal(sequence).get();
                } else if (checkInverseDiagonal(sequence).isPresent()) {
                    isMutant = true;
                    description = checkInverseDiagonal(sequence).get();
                }

                DNA dna = new DNA();
                dna.setIsMutant(isMutant);
                dna.setDescription(description);
                dna.setSequence(sequence);
                System.out.println(dna.toString());
                return DNARepository.save(dna).getIsMutant();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    private static final int MIN_CONSECUTIVE = 4;

    private static Optional<String> checkRows(String[] dna) {
        for (int row = 0; row < dna.length; row++) {
            if (hasConsecutive(dna[row].toCharArray())) {
                return Optional.of("Fila " + row);
            }
        }
        return Optional.empty();
    }

    private static Optional<String> checkColumns(String[] dna) {
        int length = dna[0].length();
        for (int col = 0; col < length; col++) {
            StringBuilder column = new StringBuilder();
            for (String row : dna) {
                column.append(row.charAt(col));
            }
            if (hasConsecutive(column.toString().toCharArray())) {
                return Optional.of("Columna " + col);
            }
        }
        return Optional.empty();
    }

    private static Optional<String> checkDiagonal(String[] dna) {
        int size = dna.length;
        for (int i = 0; i <= size - MIN_CONSECUTIVE; i++) {
            for (int j = 0; j <= dna[i].length() - MIN_CONSECUTIVE; j++) {
                if (hasConsecutiveInDiagonal(dna, i, j, 1)) {
                    return Optional.of("Diagonal principal desde (" + i + "," + j + ")");
                }
            }
        }
        return Optional.empty();
    }

    private static Optional<String> checkInverseDiagonal(String[] dna) {
        int size = dna.length;
        for (int i = 0; i <= size - MIN_CONSECUTIVE; i++) {
            for (int j = MIN_CONSECUTIVE - 1; j < dna[i].length(); j++) {
                if (hasConsecutiveInDiagonal(dna, i, j, -1)) {
                    return Optional.of("Diagonal inversa desde (" + i + "," + j + ")");
                }
            }
        }
        return Optional.empty();
    }

    // Revisa si hay caracteres consecutivos en un array
    private static boolean hasConsecutive(char[] sequence) {
        int count = 1;
        for (int i = 1; i < sequence.length; i++) {
            if (sequence[i] == sequence[i - 1]) {
                count++;
                if (count >= MIN_CONSECUTIVE) {
                    return true;
                }
            } else {
                count = 1;
            }
        }
        return false;
    }

    // Revisa diagonales en cualquier dirección (principal o inversa)
    private static boolean hasConsecutiveInDiagonal(String[] dna, int startRow, int startCol, int direction) {
        int count = 1;
        for (int k = 1; k < MIN_CONSECUTIVE; k++) {
            if (dna[startRow + k].charAt(startCol + k * direction) == dna[startRow + k - 1].charAt(startCol + (k - 1) * direction)) {
                count++;
                if (count >= MIN_CONSECUTIVE) {
                    return true;
                }
            } else {
                count = 1;
            }
        }
        return false;
    }
}
