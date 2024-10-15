package com.example.apirest.utils;

import java.util.HashMap;
import java.util.Map;

public class MutantChecker {

    private static final int MIN_CONSECUTIVE = 4;

    public static boolean isMutant(String[] dna) {
        // Mapa para contar las secuencias por cada letra
        Map<Character, Integer> sequenceCounts = new HashMap<>();

        // Contar secuencias en filas, columnas y diagonales
        searchRowSequences(dna, sequenceCounts);
        searchColumnSequences(dna, sequenceCounts);
        searchDiagonalSequences(dna, sequenceCounts);

        // Verificar si hay al menos dos secuencias de letras diferentes
        return hasAtLeastTwoDistinctSequences(sequenceCounts);
    }

    private static void searchRowSequences(String[] dna, Map<Character, Integer> sequenceCounts) {
        for (String row : dna) {
            checkAndStoreSequence(row.toCharArray(), sequenceCounts);
        }
    }

    private static void searchColumnSequences(String[] dna, Map<Character, Integer> sequenceCounts) {
        int length = dna[0].length();
        for (int col = 0; col < length; col++) {
            StringBuilder column = new StringBuilder();
            for (String row : dna) {
                column.append(row.charAt(col));
            }
            checkAndStoreSequence(column.toString().toCharArray(), sequenceCounts);
        }
    }

    private static void searchDiagonalSequences(String[] dna, Map<Character, Integer> sequenceCounts) {
        int size = dna.length;

        // Diagonales principales (de arriba hacia abajo)
        for (int i = 0; i <= size - MIN_CONSECUTIVE; i++) {
            for (int j = 0; j <= dna[i].length() - MIN_CONSECUTIVE; j++) {
                checkDiagonal(dna, i, j, 1, sequenceCounts);
            }
        }

        // Diagonales inversas (de abajo hacia arriba)
        for (int i = 0; i <= size - MIN_CONSECUTIVE; i++) {
            for (int j = MIN_CONSECUTIVE - 1; j < dna[i].length(); j++) {
                checkDiagonal(dna, i, j, -1, sequenceCounts);
            }
        }
    }

    private static void checkAndStoreSequence(char[] sequence, Map<Character, Integer> sequenceCounts) {
        int count = 1;
        for (int i = 1; i < sequence.length; i++) {
            if (sequence[i] == sequence[i - 1]) {
                count++;
                if (count >= MIN_CONSECUTIVE) {
                    sequenceCounts.merge(sequence[i], 1, Integer::sum);
                    break; // Deja de buscar más en esta secuencia
                }
            } else {
                count = 1;
            }
        }
    }

    private static void checkDiagonal(String[] dna, int startRow, int startCol, int direction, Map<Character, Integer> sequenceCounts) {
        int count = 1;
        for (int k = 1; k < MIN_CONSECUTIVE; k++) {
            if (dna[startRow + k].charAt(startCol + k * direction) ==
                    dna[startRow + k - 1].charAt(startCol + (k - 1) * direction)) {
                count++;
                if (count >= MIN_CONSECUTIVE) {
                    sequenceCounts.merge(dna[startRow + k].charAt(startCol + k * direction), 1, Integer::sum);
                    break;
                }
            } else {
                count = 1;
            }
        }
    }

    private static boolean hasAtLeastTwoDistinctSequences(Map<Character, Integer> sequenceCounts) {
        // Verificar si hay al menos dos letras distintas con secuencias válidas
        return sequenceCounts.size() >= 2;
    }
}
