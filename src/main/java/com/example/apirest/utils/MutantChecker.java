package com.example.apirest.utils;

import java.util.Optional;

public class MutantChecker {

    private static final int MIN_CONSECUTIVE = 4;

    public static Optional<String> isMutant(String[] dna) {
        Optional<String> result;

        // Verifica filas
        result = checkRows(dna);
        if (result.isPresent()) {
            return Optional.of("Mutante detectado: " + result.get());
        }

        // Verifica columnas
        result = checkColumns(dna);
        if (result.isPresent()) {
            return Optional.of("Mutante detectado: " + result.get());
        }

        // Verifica diagonal principal
        result = checkDiagonal(dna);
        if (result.isPresent()) {
            return Optional.of("Mutante detectado: " + result.get());
        }

        // Verifica diagonal inversa
        result = checkInverseDiagonal(dna);
        if (result.isPresent()) {
            return Optional.of("Mutante detectado: " + result.get());
        }

        // Si no se encuentra ninguna secuencia mutante
        return Optional.of("No es mutante");
    }

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
